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


    Connection conn= MaConnexion.getInstance().getConn();
//ajouter commande
    @Override
    public void ajouter(Commande c) {
        String req ="INSERT INTO commande (id_user, id_projet, date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getId_user());
            ps.setInt(2, c.getId_projet());
            ps.setDate(3, c.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//modifier commande
    @Override
    public void modifier(Commande c){
        String req = "update commande set id_user=? , id_projet=? , date=? where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {

        ps.setInt(1,c.getId_user());
        ps.setInt(2,c.getId_projet());
        ps.setDate(3,c.getDate());
        ps.setInt(4, c.getId_cmd());
        ps.executeUpdate();
            System.out.println("modifiée avec succée");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//supprimer commande
    @Override
    public void supprimer(int id){
        String req = " delete from commande where id_cmd=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
        ps.setInt(1,id);
        ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//afficher commande
    @Override
    public List<Commande> afficher()throws SQLException {
        List<Commande> commandes = new ArrayList<>();

        String req ="select * from commande";
        Statement st = conn.createStatement();
        ResultSet res =st.executeQuery(req);
        while(res.next()){
            Commande c = new Commande();
            c.setId_cmd(res.getInt(1));
            c.setId_user(res.getInt(2));
            c.setId_projet(res.getInt(3));
            c.setDate(res.getDate(4));


            commandes.add(c);}
        return commandes; }


}
