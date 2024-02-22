package tn.esprit.Services;
import java.sql.SQLException;
import java.sql.*;
import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Commande;
import tn.esprit.Utils.MaConnexion;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;

public class ServiceCommande implements InterfaceCRUD<Commande> {


    Connection conn = MaConnexion.getInstance().getConn();

    //ajouter commande
    @Override
    public void ajouter(Commande c) {
        String req = "INSERT INTO commande (id_user, id_projet, date,mt_total,quantite,date_livraison_estimee,code_promo) VALUES (?, ?, ?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setInt(5, c.getQuantite());
            ps.setDate(6, c.getDate_livraison_estimee());
            ps.setInt(7, c.getCode_promo());
            ps.executeUpdate();
            System.out.println("Commande ajoutée avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //modifier commande
    @Override
    public void modifier(Commande c) {
        String req = "update commande set id_user=? , id_projet=? , date=? , mt_total=? , quantite=? ,date_livraison_estimee=?,code_promo=? where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.setString(4, c.getMt_total());
            ps.setInt(5, c.getQuantite());
            ps.setDate(6, c.getDate_livraison_estimee());
            ps.setInt(7, c.getCode_promo());
            ps.setInt(8, c.getId_cmd());
            ps.executeUpdate();
            System.out.println("Commande modifiée avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //supprimer commande
    @Override
    public void supprimer(int id) {
        String req = " delete from commande where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commande supprimée avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
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


                commandes.add(c);
            }
            return commandes;
        }

    }
}
