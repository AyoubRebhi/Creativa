package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Commande;
import tn.esprit.Models.Livraison;
import tn.esprit.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceLivraison implements InterfaceCRUD<Livraison> {
    Connection conn= MaConnexion.getInstance().getConn();
//ajouter livraison
    @Override
    public void ajouter(Livraison l) {
        String req = "INSERT INTO livraison (id_cmd, id_user, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, l.getId_cmd());
            ps.setInt(2, l.getId_user());
            ps.setString(3, l.getStatus());
            ps.executeUpdate();
            System.out.println("Livraison ajoutée avec succée!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


}
//modifier livraison
    @Override
    public void modifier(Livraison l) {
        String req = "update livraison set id_cmd=? , id_user=? , status=? where id_liv=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
        ps.setInt(1,l.getId_cmd());
        ps.setInt(2,l.getId_user());
        ps.setString(3,l.getStatus());
        ps.setInt(4,l.getId_liv());
        ps.executeUpdate();
        System.out.println("modifiée avec succée");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //supprimer livraison
    @Override
    public void supprimer(int id) {
        String req = " delete from livraison where id_liv=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
        ps.setInt(1,id);
        ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//afficher livraison
    @Override
    public List<Livraison> afficher() throws SQLException{
        List<Livraison> livraisons = new ArrayList<>();
        String req ="select * from livraison";
        Statement st = conn.createStatement();
        ResultSet res =st.executeQuery(req);
        while(res.next()){
            Livraison l = new Livraison();
            l.setId_liv(res.getInt(1));
            l.setId_cmd(res.getInt(2));
            l.setId_user(res.getInt(3));
            l.setStatus(res.getString(4));


            livraisons.add(l);}return livraisons;
    }
}
