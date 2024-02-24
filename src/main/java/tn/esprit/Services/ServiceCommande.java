package tn.esprit.Services;
import java.sql.SQLException;
import java.sql.*;
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
        String req = "INSERT INTO commande (id_user, id_projet, date,mt_total,quantite,date_livraison_estimee,code_promo,status) VALUES (?, ?, ?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setInt(5, c.getQuantite());
            ps.setDate(6, c.getDate_livraison_estimee());
            ps.setInt(7, c.getCode_promo());
            ps.setString(8, c.getStatus());

            ps.executeUpdate();
            System.out.println("Commande ajoutée avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //modifier commande
    @Override
    public void modifier(Commande c) {
        String req = "update commande set id_user=? , id_projet=? , date=? , mt_total=? , quantite=? ,date_livraison_estimee=?,code_promo=?,status=? where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setInt(5, c.getQuantite());
            ps.setDate(6, c.getDate_livraison_estimee());
            ps.setInt(7, c.getCode_promo());
            ps.setString(8, c.getStatus());
            ps.setInt(9, c.getId_cmd());
            ps.executeUpdate();
            System.out.println("Commande modifiée avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(int id) {

    }

    //supprimer commande
    /*@Override
    public void supprimer(int id) {
        String req = " delete from commande where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commande supprimée avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public void annulerCommande(int idCommande) throws SQLException {
        String req = "UPDATE commande SET status = 'annulée' WHERE id_cmd = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, idCommande);
            ps.executeUpdate();
            System.out.println("Commande annulée avec succès !");
        }
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
                c.setQuantite(res.getInt(6));
                c.setDate_livraison_estimee(res.getDate(7));
                c.setCode_promo(res.getInt(8));
                c.setStatus(res.getString(9));

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
                    c.setQuantite(res.getInt(6));
                    c.setDate_livraison_estimee(res.getDate(7));
                    c.setCode_promo(res.getInt(8));
                    c.setStatus(res.getString(9));
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