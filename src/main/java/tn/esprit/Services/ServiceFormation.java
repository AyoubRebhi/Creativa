/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Services;

import tn.esprit.Utils.MaConnexion;
import tn.esprit.Models.Formation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ServiceFormation {
    
    Connection cnx = MaConnexion.getInstance().getConn();
    private Object connexion;
    PreparedStatement stmt;
    PreparedStatement ste;

    
     public void ajouterFormation(Formation f){
        try {
            // Define the SQL INSERT statement
            String sql = "INSERT INTO formation (titre,description,media,nb_places,prix) VALUES (?, ?, ?, ?, ?)";
            stmt = cnx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            // Set the parameter values for the INSERT statement
            stmt.setString(1, f.getTitre());
            stmt.setString(2, f.getDescription());
            stmt.setString(3, f.getMedia());
            stmt.setInt(4, f.getNb_places());
            stmt.setDouble(5, f.getPrix());

            stmt.executeUpdate();
             System.out.println("Post ajoutée avec succés !");
              }catch (SQLException e) {
            System.err.println(e.getMessage());
         }
     }
    
     public List<Formation> getAllFormations() {
        List<Formation> formations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM formation";
            stmt = cnx.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String description = rs.getString("description");
                String media = rs.getString("media");
                int nb_places = rs.getInt("nb_places");
                double prix = rs.getDouble("prix");
                
                Formation formation = new Formation(id, titre, description, media, nb_places, prix);
                formations.add(formation);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return formations;
    }
     
     
   public void supprimerFormation(int id) {
    try {
        String req=" DELETE FROM Formation WHERE id="+ id ;

        PreparedStatement St = cnx.prepareStatement(req);
        St.executeUpdate();
        System.out.println("Le Formation est supprimé");}
        catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }    
   }
   
     public void update(Formation e) {
    try {
          
         String req = "UPDATE `formation` SET `id`='" + e.getId()+ "',`titre`='" + e.getTitre()+
                 "',`description`='" + e.getDescription()+ "', `media`='" + e.getMedia()+ "',`nb_places`='"+ e.getNb_places()+ "',`prix`='" +e.getPrix()+
                 "' WHERE id=" + e.getId();
         
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("la Formation est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
      public Formation readById(int id) {
        Formation p = null; // Déclarer la variable p en dehors du bloc if

    try {
        PreparedStatement statement = cnx.prepareStatement("SELECT * FROM formation WHERE id = "+id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            p = new Formation (rs.getInt(1), 
                          rs.getString(2), 
                          rs.getString(3), 
                          rs.getString(4),
                          rs.getInt(5),
                          rs.getDouble(6)
                                );
        }
    } catch (SQLException e) {
            // Gérer l'exception SQLException
            System.out.print(e.getMessage());
            //Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, e);
    }
    return p;
    }

      
    public void Clear() {
    String req = "DELETE FROM formation";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate();
        System.out.println("Toutes les lignes de la table 'Formation' ont été supprimées !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       }
      
    
    public ObservableList<Formation> searchent2(String searchTerm) {
        ObservableList<Formation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM formation");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Formation offre = new Formation(
                          rs.getString(2), 
                          rs.getString(3),
                          rs.getString(4),
                          rs.getInt(5),
                          rs.getDouble(6)
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
      
}
