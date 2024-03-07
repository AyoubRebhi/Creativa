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
            String req = "INSERT INTO `projet`(`titre`, `description`, `media`, `prix`, `id_categorie`,`id_user`) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, projet.getTitre());
            ps.setString(2, projet.getDescription());
            ps.setString(3, projet.getMedia());
            ps.setDouble(4, projet.getPrix());
            ps.setInt(5, projet.getCategorie());
            ps.setInt(6,projet.getId_user());

            ps.executeUpdate();
            System.out.println("Projet ajouté avec succès");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //NB: statement est plus rapide que prepared statement si on a pas des parametres à passer
    @Override
    public void modifier(Projet projet) {
        try {
            String req = "UPDATE `projet` SET `titre`= ?, `description`= ?, `media`= ?, `prix`= ?, `id_categorie`=?, `updatedAt`=CURRENT_TIMESTAMP,`id_user`=? WHERE `id_projet`= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, projet.getTitre());
            ps.setString(2, projet.getDescription());
            ps.setString(3, projet.getMedia());
            ps.setDouble(4, projet.getPrix());
            ps.setInt(5, projet.getCategorie());
            ps.setInt(6, projet.getId());
            ps.setInt(7,projet.getId_user());

            ps.executeUpdate();
            System.out.println("Projet modifié avec succès");

        } catch (SQLException ex) {
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
                projet.setVisible(rs.getBoolean("isVisible"));
                projet.setCreatedAt(rs.getTimestamp("createdAt"));
                projet.setUpdatedAt(rs.getTimestamp("updatedAt"));

                int categorieId = rs.getInt("id_categorie");
                String categorieTitre = rs.getString("categorie_titre");
                String categorieImage = rs.getString("media");
                String categorieDescription = rs.getString("description");
                // Vérifie si la catégorie existe déjà dans la map, sinon, crée une nouvelle catégorie
                Categorie categorie = categoriesMap.get(categorieId);
                if (categorie == null) {
                    categorie = new Categorie(categorieId, categorieTitre,categorieImage,categorieDescription);
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
    public List<Projet> afficherProjetByUser(int id_user) {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT * FROM projet WHERE id_user = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id_user);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Projet projet = new Projet();
                    projet.setId(rs.getInt("id_projet"));
                    projet.setTitre(rs.getString("titre"));
                    projet.setDescription(rs.getString("description"));
                    projet.setPrix(rs.getDouble("prix"));
                    projet.setCategorie(rs.getInt("id_categorie"));
                    projet.setMedia(rs.getString("media"));
                    projet.setVisible(rs.getBoolean("isVisible"));
                    projet.setCreatedAt(rs.getTimestamp("createdAt"));
                    projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
                    projets.add(projet);
                }
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
                    projet.setMedia(rs.getString("media"));
                    projet.setVisible(rs.getBoolean("isVisible"));
                    projet.setCreatedAt(rs.getTimestamp("createdAt"));
                    projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
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
                    projet.setVisible(rs.getBoolean("isVisible"));
                    projet.setCreatedAt(rs.getTimestamp("createdAt"));
                    projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
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
                    projet.setVisible(rs.getBoolean("isVisible"));
                    projet.setCreatedAt(rs.getTimestamp("createdAt"));
                    projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
                    projets.add(projet);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }
    public List<Projet> chercherProjet(String ch, int id_user) {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT p.*, c.titre AS categorie_titre FROM projet p " +
                "JOIN categorie c ON p.id_categorie = c.id_categorie " +
                "WHERE p.id_user = ? AND (p.titre LIKE ? OR c.titre LIKE ? OR p.description LIKE ?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            String searchPattern = "%" + ch + "%";
            ps.setInt(1, id_user);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Projet projet = new Projet();
                    projet.setId(rs.getInt("id_projet"));
                    projet.setTitre(rs.getString("titre"));
                    projet.setDescription(rs.getString("description"));
                    projet.setCategorie(rs.getInt("id_categorie"));
                    projet.setPrix(rs.getDouble("prix"));
                    projet.setVisible(rs.getBoolean("isVisible"));
                    projet.setCreatedAt(rs.getTimestamp("createdAt"));
                    projet.setUpdatedAt(rs.getTimestamp("updatedAt"));

                    // Ajouter le projet à la liste
                    projets.add(projet);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }


    //Fonctions de filtrage
    public List<Projet> afficherProjetParNbJaime() {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT p.*, COUNT(j.id_projet) AS nombreJaime " +
                "FROM projet p LEFT JOIN jaime j ON p.id_projet = j.id_projet " +
                "GROUP BY p.id_projet " +
                "ORDER BY COUNT(j.id_projet) DESC ";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(rs.getInt("id_projet"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("description"));
                projet.setPrix(rs.getDouble("prix"));
                projet.setCategorie(rs.getInt("id_categorie"));
                projet.setMedia(rs.getString("media"));
                projet.setVisible(rs.getBoolean("isVisible"));
                projet.setCreatedAt(rs.getTimestamp("createdAt"));
                projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
                projet.setNombreJaime(rs.getInt("nombreJaime"));
                projets.add(projet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }
    public List<Projet> afficherProjetParDateCreation() {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT * FROM projet ORDER BY createdAt DESC ";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(rs.getInt("id_projet"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("description"));
                projet.setPrix(rs.getDouble("prix"));
                projet.setCategorie(rs.getInt("id_categorie"));
                projet.setMedia(rs.getString("media"));
                projet.setVisible(rs.getBoolean("isVisible"));
                projet.setCreatedAt(rs.getTimestamp("createdAt"));
                projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
                projets.add(projet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }
    public List<Projet> afficherProjetParPrix() {
        List<Projet> projets = new ArrayList<>();
        String req = "SELECT * FROM projet ORDER BY prix DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(rs.getInt("id_projet"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("description"));
                projet.setPrix(rs.getDouble("prix"));
                projet.setCategorie(rs.getInt("id_categorie"));
                projet.setMedia(rs.getString("media"));
                projet.setVisible(rs.getBoolean("isVisible"));
                projet.setCreatedAt(rs.getTimestamp("createdAt"));
                projet.setUpdatedAt(rs.getTimestamp("updatedAt"));
                projets.add(projet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return projets;
    }


    //Utilisé pour la forme AjouterProjet.fxml
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
    //Utilisé pour Ajouter Projet
    public List<String> afficherTitresCategories() {
        List<String> titresCategories = new ArrayList<>();
        String req = "SELECT titre FROM categorie";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                titresCategories.add(rs.getString("titre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return titresCategories;
    }
    public void modifierVisibilite(int projetId, boolean isVisible) throws SQLException {
        String req = "UPDATE projet SET `isVisible` = ? WHERE id_projet = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setBoolean(1, isVisible);
            ps.setInt(2, projetId);
            ps.executeUpdate();
            System.out.println("Visibilité du projet modifiée avec succès");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    public void calculerNombreJaimePourProjet(Projet projet) {
        String req = "SELECT COUNT(*) AS nombreJaime FROM jaime WHERE id_projet = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, projet.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    projet.setNombreJaime(rs.getInt("nombreJaime"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }






}
