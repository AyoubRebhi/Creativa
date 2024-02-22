package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Categorie;
import tn.esprit.Models.Projet;
import tn.esprit.Utils.MaConnexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjetServices implements InterfaceCRUD <Projet> {

    Connection conn= MaConnexion.getInstance().getConn();

    @Override
    public void ajouter(Projet projet) {
        try {
            String req = "INSERT INTO `projet`(`titre`, `description`, `media`, `prix`, `id_categorie`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, projet.getTitre());
            ps.setString(2, projet.getDescription());

            // Insérer le contenu du fichier dans la base de données en tant que BLOB
            if (projet.getMedia() != null) {
                FileInputStream inputStream = new FileInputStream(projet.getMedia());
                ps.setBinaryStream(3, inputStream, (int) projet.getMedia().length());
            } else {
                ps.setNull(3, java.sql.Types.BLOB);
            }

            ps.setDouble(4, projet.getPrix());
            ps.setInt(5, projet.getCategorie());

            ps.executeUpdate();
            System.out.println("Projet ajouté avec succes");

        } catch (SQLException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    //NB: statement est plus rapide que prepared statement si on a pas des parametres à passer
    @Override
    public void modifier(Projet projet) {
        try{
            String req ="UPDATE `projet` SET `titre`= ?, `description`= ?, `prix`= ?, `id_categorie`=? WHERE `id_projet`= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,projet.getTitre());
            ps.setString(2,projet.getDescription());
            ps.setDouble(3,projet.getPrix());
            ps.setInt(4,projet.getCategorie());
            ps.setInt(5,projet.getId());

            ps.executeUpdate();
            System.out.println("Projet modifieé avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifierMedia(Projet projet){
        try{
            String req ="UPDATE `projet` SET `media`=? WHERE `id_projet`=?";
            PreparedStatement ps = conn.prepareStatement(req);
            if (projet.getMedia() != null) {
                FileInputStream inputStream = new FileInputStream(projet.getMedia());
                ps.setBinaryStream(1, inputStream, (int) projet.getMedia().length());

            } else {
                ps.setNull(1, java.sql.Types.BLOB);
            }

            ps.setInt(2,projet.getId());

            ps.executeUpdate();
            System.out.println("Media modifiée avec succes");

        }catch(SQLException | FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void modifierCategorie(Projet projet){
        try{
            String req = "UPDATE `projet` SET `categorie_id`=? WHERE `id_projet`=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, projet.getCategorie());
            ps.setInt(2,projet.getId());

            ps.executeUpdate();
            System.out.println("Projet modifieé avec succes");

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `projet` WHERE `id_projet`= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("Projet supprimé avec succes");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Projet> afficher() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        Map<Integer, Categorie> categoriesMap = new HashMap<>(); // Pour stocker les catégories par ID

        String req = "SELECT p.*, c.titre AS categorie_titre FROM projet p JOIN categorie c ON p.id_categorie = c.id_categorie";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(rs.getInt("id_projet"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("description"));
                projet.setCategorie(rs.getInt("id_categorie"));
                projet.setPrix(rs.getDouble("prix"));

                int categorieId = rs.getInt("id_categorie");
                String categorieTitre = rs.getString("categorie_titre");

                // Vérifie si la catégorie existe déjà dans la map, sinon, crée une nouvelle catégorie
                Categorie categorie = categoriesMap.get(categorieId);
                if (categorie == null) {
                    categorie = new Categorie(categorieId, categorieTitre);
                    categoriesMap.put(categorieId, categorie);
                }

                // Ajoute le projet à la liste des projets de la catégorie
                categorie.getProjets().add(projet);

                projets.add(projet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }

    public Projet afficherProjetParId(int id) {
        Projet projet = null;
        String req = "SELECT * FROM projet WHERE id_projet = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    projet = new Projet();
                    projet.setId(rs.getInt("id_projet"));
                    projet.setTitre(rs.getString("titre"));
                    projet.setDescription(rs.getString("description"));
                    projet.setPrix(rs.getDouble("prix"));
                    projet.setCategorie(rs.getInt("id_categorie"));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projet;
    }

    public List<Projet> chercherParTitre(String titre) {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT * FROM projet WHERE titre LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, "%" + titre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Projet projet = new Projet();
                    projet.setId(rs.getInt("id_projet"));
                    projet.setTitre(rs.getString("titre"));
                    projet.setDescription(rs.getString("description"));
                    projet.setPrix(rs.getDouble("prix"));
                    projet.setCategorie(rs.getInt("id_categorie"));
                    projets.add(projet);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }

    public List<Projet> rechercheParCategorie(int id_categorie) {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT * FROM projet WHERE id_categorie = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id_categorie);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Projet projet = new Projet();
                    projet.setId(rs.getInt("id_projet"));
                    projet.setTitre(rs.getString("titre"));
                    projet.setDescription(rs.getString("description"));
                    projet.setPrix(rs.getDouble("prix"));
                    projet.setCategorie(rs.getInt("id_categorie"));
                    projets.add(projet);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }
    public String afficherTitreCategorie(int id) {
        String titreCategorie = null;
        String req = "SELECT c.titre FROM projet p JOIN categorie c ON p.id_categorie = c.id_categorie WHERE p.id_projet = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    titreCategorie = rs.getString("titre");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return titreCategorie;
    }




}
