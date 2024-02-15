package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Projet;
import tn.esprit.Utils.MaConnexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String req="SELECT * FROM projet";
        Statement st = conn.createStatement();
        ResultSet rs =st.executeQuery(req);

        while (rs.next()){
            Projet projet= new Projet();
            projet.setId(rs.getInt("id_projet"));
            projet.setTitre(rs.getString("titre"));
            projet.setDescription(rs.getString("description"));
            projet.setCategorie(rs.getInt("id_categorie"));


            projet.setPrix(rs.getDouble("prix"));

            projets.add(projet);
        }


        return projets;
    }
}
