package tn.esprit.Services;

import tn.esprit.Models.Jaime;
import tn.esprit.Utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JaimeServices {
    Connection conn= MaConnexion.getInstance().getConn();

    public void insererJaime(Jaime jaime){
        try{
            String req = "INSERT INTO `jaime` (`id_user`, `id_projet`) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,jaime.getId_user());
            ps.setInt(2,jaime.getId_projet());

            ps.executeUpdate();
            System.out.println("Le like a été ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du like : " + e.getMessage());
        }
    }
}
