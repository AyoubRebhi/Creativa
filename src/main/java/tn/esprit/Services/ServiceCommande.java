package tn.esprit.Services;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.*;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Commande;
import tn.esprit.Utils.MaConnexion;

import java.util.*;
import java.sql.Connection;

public class ServiceCommande implements InterfaceCRUD<Commande> {


    Connection conn = MaConnexion.getInstance().getConn();

    //ajouter commande
    @Override
    public void ajouter(Commande c) {
        String req = "INSERT INTO commande (id_user, id_projet, date,mt_total,date_livraison_estimee,code_promo,status,prix,frais_liv) VALUES (?, ?, ?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setDate(5, c.getDate_livraison_estimee());
            ps.setInt(6, c.getCode_promo());
            ps.setString(7, c.getStatus());
            ps.setFloat(8, c.getPrix_produit());
            ps.setFloat(9, c.getFrais_livraison());

            ps.executeUpdate();
            System.out.println("Commande ajoutée avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //modifier commande
    @Override
    public void modifier(Commande c) {
        String req = "update commande set id_user=? , id_projet=? , date=? , mt_total=? ,date_livraison_estimee=?,code_promo=?,status=?,prix=?,frais_liv=? where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setDate(5, c.getDate_livraison_estimee());
            ps.setInt(6, c.getCode_promo());
            ps.setString(7, c.getStatus());
            ps.setFloat(8, c.getPrix_produit());
            ps.setFloat(9, c.getFrais_livraison());
            ps.setInt(10, c.getId_cmd());
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
        String req = "UPDATE commande SET status = 'annulée' WHERE id_cmd = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, idCommande);
            ps.executeUpdate();
            System.out.println("Commande annulée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                c.setPrix_produit(res.getFloat(9));
                c.setFrais_livraison(res.getFloat(10));

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
                    c.setPrix_produit(res.getFloat(9));
                    c.setFrais_livraison(res.getFloat(10));
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




    public String getPrixParTitreProjet(String titreProjet) throws SQLException {
        String prix = null;
        String req = "SELECT prix FROM projet WHERE titre = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, titreProjet);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    prix = res.getString("prix");
                }
            }
        }
        return prix;
    }

    public String getprixproduit(String titreProjet) throws SQLException {
        String prix = null;
        String req = "SELECT prix FROM projet WHERE titre = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, titreProjet);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    prix = res.getString("prix");
                }
            }
        }
        return prix;
    }

    public boolean codePromoDejaUtiliseParUtilisateur(String codePromo, String utilisateur) {
        boolean codePromoDejaUtilise = false;
        String req = "SELECT * FROM commande WHERE code_promo = ? AND id_user = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, codePromo);
            ps.setInt(2, getIdUtilisateurParNomComplet(utilisateur));
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    codePromoDejaUtilise = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codePromoDejaUtilise;
    }
public int getidbyotherFields(int idUser, int idProjet, Date date, String mtTotal, Date dateLivraisonEstimee, int codePromo, String status, float prixProduit, float fraisLivraison) throws SQLException {
    int id = -1;
    String req = "SELECT id_cmd FROM commande WHERE id_user = ? AND id_projet = ? AND date = ? AND mt_total = ? AND date_livraison_estimee = ? AND code_promo = ? AND status = ? AND prix = ? AND frais_liv = ?";
    try (PreparedStatement ps = conn.prepareStatement(req)) {
        ps.setInt(1, idUser);
        ps.setInt(2, idProjet);
        ps.setDate(3, date);
        ps.setString(4, mtTotal);
        ps.setDate(5, dateLivraisonEstimee);
        ps.setInt(6, codePromo);
        ps.setString(7, status);
        ps.setFloat(8, prixProduit);
        ps.setFloat(9, fraisLivraison);
        System.out.println(ps.toString());
        try (ResultSet res = ps.executeQuery()) {
            if (res.next()) {
                id = res.getInt(1);
            }
        }
    }
    return id;
}

public Commande getCommandeByotherFields(int idUser, int idProjet, Date date, String mtTotal, Date dateLivraisonEstimee, int codePromo, String status, float prixProduit, float fraisLivraison) throws SQLException {
    Commande c = new Commande();
    String req = "SELECT * FROM commande WHERE id_user = ? AND id_projet = ? AND date = ? AND mt_total = ? AND date_livraison_estimee = ? AND code_promo = ? AND status = ? AND prix = ? AND frais_liv = ?";
    try (PreparedStatement ps = conn.prepareStatement(req)) {
        ps.setInt(1, idUser);
        ps.setInt(2, idProjet);
        ps.setDate(3, date);
        ps.setString(4, mtTotal);
        ps.setDate(5, dateLivraisonEstimee);
        ps.setInt(6, codePromo);
        ps.setString(7, status);
        ps.setFloat(8, prixProduit);
        ps.setFloat(9, fraisLivraison);
        try (ResultSet res = ps.executeQuery()) {
            if (res.next()) {
                c.setId_cmd(res.getInt(1));
                c.setId_user(res.getInt(2));
                c.setId_projet(res.getInt(3));
                c.setDate(res.getDate(4));
                c.setMt_total(res.getString(5));
                c.setDate_livraison_estimee(res.getDate(6));
                c.setCode_promo(res.getInt(7));
                c.setStatus(res.getString(8));
                c.setPrix_produit(res.getFloat(9));
                c.setFrais_livraison(res.getFloat(10));
            }
        }
    }
    return c;
}
    public List<Commande> searchCommand(String searchText) throws SQLException {
        String query = "SELECT * FROM Commande WHERE id_cmd LIKE ? OR id_user LIKE ? OR id_projet LIKE ? OR date LIKE ? OR mt_total LIKE ? OR date_livraison_estimee LIKE ? OR code_promo LIKE ? OR status LIKE ? OR prix LIKE ? OR frais_liv LIKE ?";
        List<Commande> searchResults = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            String wildcardSearch = "%" + searchText + "%";
            for (int i = 1; i <= 10; i++) {
                pstmt.setString(i, wildcardSearch);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Commande commande = new Commande(
                            rs.getInt("id_cmd"),
                            rs.getInt("id_user"),
                            rs.getInt("id_projet"),
                            rs.getDate("date"),
                            rs.getString("mt_total"),
                            rs.getDate("date_livraison_estimee"),
                            rs.getInt("code_promo"),
                            rs.getString("status"),
                            rs.getFloat("prix"),
                            rs.getFloat("frais_liv")
                    );
                    searchResults.add(commande);
                }
            }
        }

        return searchResults;
    }




}