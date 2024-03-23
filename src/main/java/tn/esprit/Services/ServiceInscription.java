/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Services;

import tn.esprit.Utils.MaConnexion;
import tn.esprit.Models.Inscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


public class ServiceInscription {
    
    Connection cnx = MaConnexion.getInstance().getConn();
    private Object connexion;
    PreparedStatement stmt;
    PreparedStatement ste;
    
        public void Inscrire(Inscription i, int formation_id) {
        try {
            // Define the SQL INSERT statement
            String sql = "INSERT INTO inscritption (nom,prenom,email,formation_id) VALUES (?,?,?,?)";
            stmt = cnx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set the parameter values for the INSERT statement
            stmt.setString(1, i.getNom());
            stmt.setString(2, i.getPrenom());
            stmt.setString(3, i.getEmail());
           // stmt.setString(2, c.getNom());
            stmt.setInt(4, formation_id); // Use the provided post_id

            stmt.executeUpdate();
            System.out.println("Inscription ajoutée avec succès !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
        
        
       public List<Inscription> afficheInscriptionByIdFomation(int idFormation) {
        List<Inscription> inscriptions = new ArrayList<>();

        try {
            // Define the SQL SELECT statement
            String sql = "SELECT * FROM inscritption WHERE formation_id = ?";
            stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idFormation);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inscription inscrit = new Inscription();
                inscrit.setId_inscrit(rs.getInt("id_inscrit"));
                inscrit.setNom(rs.getString("nom"));
                inscrit.setPrenom(rs.getString("prenom"));
                inscrit.setEmail(rs.getString("email"));
                inscrit.setFormation_id(rs.getInt("formation_id"));

                inscriptions.add(inscrit);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return inscriptions;
    }
       
       
    public void Clear() {
    String req = "DELETE FROM inscritption";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate();
        System.out.println("Toutes les lignes de la table 'Inscription' ont été supprimées !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       }
    
       public void supprimerInscription(int id) {
    try {
        String req=" DELETE FROM inscritption WHERE id_inscrit ="+ id ;

        PreparedStatement St = cnx.prepareStatement(req);
        St.executeUpdate();
        System.out.println("L'inscritption est supprimé");}
     catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }    
   }
}
