package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Projet;
import tn.esprit.Utils.MaConnexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProjetServices implements InterfaceCRUD <Projet> {


    Connection conn= MaConnexion.getInstance().getConn();


    @Override
    public void ajouter(Projet projet) {
        try{
            String req ="INSERT INTO `projet`(`titre`, `description`, `media`, `prix`) VALUES ('"+projet.getTitre() +"','"+ projet.getDescription() +"','"+ projet.getMedia() +"'," + projet.getPrix()+ ")";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Projet ajouté avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Projet projet) {
    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public List<Projet> afficher() {
        return null;
    }
}
