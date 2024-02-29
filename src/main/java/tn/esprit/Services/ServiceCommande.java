package tn.esprit.Services;
import java.sql.SQLException;
import java.sql.*;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Commande;
import tn.esprit.Utils.MaConnexion;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Map;

public class ServiceCommande implements InterfaceCRUD<Commande> {


    Connection conn = MaConnexion.getInstance().getConn();

    //ajouter commande
    @Override
    public void ajouter(Commande c) {
        String req = "INSERT INTO commande (id_user, id_projet, date,mt_total,date_livraison_estimee,code_promo,status) VALUES (?, ?, ?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setDate(5, c.getDate_livraison_estimee());
            ps.setInt(6, c.getCode_promo());
            ps.setString(7, c.getStatus());

            ps.executeUpdate();
            System.out.println("Commande ajoutée avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //modifier commande
    @Override
    public void modifier(Commande c) {
        String req = "update commande set id_user=? , id_projet=? , date=? , mt_total=? ,date_livraison_estimee=?,code_promo=?,status=? where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setDate(5, c.getDate_livraison_estimee());
            ps.setInt(6, c.getCode_promo());
            ps.setString(7, c.getStatus());
            ps.setInt(8, c.getId_cmd());
            ps.executeUpdate();
            System.out.println("Commande modifiée avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(int id) {

    }


    public void annulerCommande(int idCommande) throws SQLException {
        // Créer une boîte de dialogue de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir annuler cette commande ?");

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // L'utilisateur a appuyé sur le bouton OK, annuler la commande
                String req = "UPDATE commande SET status = 'annulée' WHERE id_cmd = ?";
                try (PreparedStatement ps = conn.prepareStatement(req)) {
                    ps.setInt(1, idCommande);
                    ps.executeUpdate();
                    System.out.println("Commande annulée avec succès !");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (response == ButtonType.CANCEL) {
                    // L'utilisateur a appuyé sur le bouton Annuler, ne rien faire
                    System.out.println("Opération d'annulation de commande annulée par l'utilisateur.");
                }
            } else {
                // L'utilisateur a appuyé sur le bouton Annuler ou a fermé la boîte de dialogue, ne rien faire
                System.out.println("Opération d'annulation de commande annulée par l'utilisateur.");
            }
        });
    }
    @Override
    public void annulerLivraison(int id) throws SQLException {

    }


    //afficher commande
    @Override
    public List<Commande> afficher() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande";
        try (Statement st = conn.createStatement();
             ResultSet res = st.executeQuery(req)) {
            while (res.next()) {
                Commande c = new Commande();
                c.setId_cmd(res.getInt(1));
                c.setId_user(res.getInt(2));
                c.setId_projet(res.getInt(3));
                c.setDate(res.getDate(4));
                c.setMt_total(res.getString(5));
                c.setDate_livraison_estimee(res.getDate(6));
                c.setCode_promo(res.getInt(7));
                c.setStatus(res.getString(8));

                commandes.add(c);
            }
            return commandes;
        }

    }
    public List<Commande> afficherCommandesUtilisateur(int idUtilisateur) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM commande WHERE id_user = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, idUtilisateur);
            try (ResultSet res = ps.executeQuery()) {
                while (res.next()) {
                    Commande c = new Commande();
                    c.setId_cmd(res.getInt(1));
                    c.setId_user(res.getInt(2));
                    c.setId_projet(res.getInt(3));
                    c.setDate(res.getDate(4));
                    c.setMt_total(res.getString(5));
                    c.setDate_livraison_estimee(res.getDate(6));
                    c.setCode_promo(res.getInt(7));
                    c.setStatus(res.getString(8));
                    commandes.add(c);
                }
            }
        }
        return commandes;
    }



    public int getIdUtilisateurParNomComplet(String nomComplet) throws SQLException {
        int idUtilisateur = -1; // Valeur par défaut si aucun utilisateur n'est trouvé
        String req = "SELECT id_user FROM user WHERE CONCAT(nom, ' ', prenom) = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, nomComplet);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    idUtilisateur = res.getInt("id_user");
                }
            }
        }
        return idUtilisateur;
    }

    // ServiceCommande
    public Map<String, Integer> getAllProjectTitlesAndIds() throws SQLException {
        Map<String, Integer> projectTitlesAndIds = new HashMap<>();
        String req = "SELECT id_projet, titre FROM projet";
        try (PreparedStatement ps = conn.prepareStatement(req);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                int id_projet = res.getInt("id_projet");
                String titre = res.getString("titre");
                projectTitlesAndIds.put(titre, id_projet);
            }
        }
        return projectTitlesAndIds;
    }


    /*public String getNomCompletUtilisateur(int idUtilisateur) throws SQLException {
        String nomComplet = null;
        String req = "SELECT CONCAT(nom, ' ', prenom) AS nom_complet FROM user WHERE id_user = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, idUtilisateur);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    nomComplet = res.getString("nom_complet");

                }
            }
        }
        return nomComplet;
    }*/
    /*public List<String> afficherNomsPrenomsUtilisateurs() throws SQLException {
        List<String> nomsComplets = new ArrayList<>();
        String req = "SELECT CONCAT(nom, ' ', prenom) AS nom_complet FROM user";
        try (Statement st = conn.createStatement();
             ResultSet res = st.executeQuery(req)) {
            while (res.next()) {
                String nomComplet = res.getString("nom_complet");
                nomsComplets.add(nomComplet);
            }
        }
        return nomsComplets;
    }*/



}