package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Categorie;
import tn.esprit.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorieServices implements InterfaceCRUD<Categorie> {
    Connection conn= MaConnexion.getInstance().getConn();

    @Override
    public void ajouter(Categorie categorie) {
        try{
            String req="INSERT INTO `categorie`(`titre`,`image`,`description`) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,categorie.getTitre());
            ps.setString(2,categorie.getCategorieImage());
            ps.setString(3,categorie.getDescription());

            ps.executeUpdate();
            System.out.println("Categorie ajoutée avec succes");
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Categorie categorie) {
        try{
            String req="UPDATE `categorie` SET `titre`=? ,`image`=? WHERE `id_categorie`= ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1,categorie.getTitre());
            ps.setString(2, categorie.getCategorieImage());
            ps.setInt(3,categorie.getId_categorie());

            ps.executeUpdate();
            System.out.println("Categorie modifiée avec succes");
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        try{
            String req="DELETE FROM `categorie` WHERE `id_categorie`=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("Categorie supprimée avec succes");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public List<Categorie> afficher() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM categorie";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                int id = rs.getInt("id_categorie");
                String titre = rs.getString("titre");
                String image = rs.getString("image");
                String categorieDescription=rs.getString("description");
                Categorie categorie = new Categorie(id, titre,image,categorieDescription);
                categories.add(categorie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }
    public Categorie afficherParId(int id){
        Categorie categorie = null;
        String req="SELECT * FROM categorie WHERE `id_categorie`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    categorie = new Categorie();
                    categorie.setId_categorie(rs.getInt("id_categorie"));
                    categorie.setTitre(rs.getString("titre"));
                    categorie.setCategorieImage(rs.getString("image"));
                    categorie.setDescription(rs.getString("description"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }


    public Map<String, Integer> calculerNbProjets(int id) {
        Map<String, Integer> nbProjetsParCategorie = new HashMap<>();
        try {
            String req = "SELECT c.titre AS categorie_titre, COUNT(p.id_projet) AS nb_projets " +
                    "FROM categorie c " +
                    "LEFT JOIN projet p ON c.id_categorie = p.id_categorie " +
                    "WHERE c.id_categorie = ? " +
                    "GROUP BY c.titre";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String categorieTitre = rs.getString("categorie_titre");
                int nbProjets = rs.getInt("nb_projets");
                nbProjetsParCategorie.put(categorieTitre, nbProjets);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nbProjetsParCategorie;
    }

}

