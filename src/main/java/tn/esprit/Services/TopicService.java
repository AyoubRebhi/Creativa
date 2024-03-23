package tn.esprit.Services;
import tn.esprit.Interfaces.InterfaceCRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.Models.TOPIC;
import tn.esprit.Utils.MaConnexion;
public class TopicService implements InterfaceCRUD<TOPIC>{

    MaConnexion connexion = (MaConnexion) MaConnexion.getInstance();
    @Override
    public void ajouter (TOPIC topic){
        try {
            PreparedStatement pr = connexion.getConn().prepareStatement("INSERT INTO `topic`(`Catego_ID`, `Nom`, `Subject`, `Image`) VALUES (?,?,?,?)");
            pr.setString(1,topic.getCatego_ID());
            pr.setString(2, topic.getNom());
            pr.setString(3, topic.getSubject());
            pr.setString(4, topic.getImage());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    @Override
    public void modifier(TOPIC topic) {
        try {
         PreparedStatement   pr = connexion.getConn().prepareStatement("UPDATE `topic` SET `Nom`=?,`Subject`=?,`Image`=? WHERE Id=?");
            pr.setString(1, topic.getNom());
            pr.setString(2, topic.getSubject());
            pr.setString(3, topic.getImage());
            pr.setInt(4, topic.getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    @Override
    public void supprimer(int id){
        try {
            PreparedStatement  pr = connexion.getConn().prepareStatement("DELETE FROM `topic` WHERE Id=?");
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };
   public List<TOPIC> afficher(){
       List<TOPIC> topics = new ArrayList<>();

       try {
        PreparedStatement pr= connexion.getConn().prepareStatement("SELECT * FROM `topic`");
        ResultSet res= pr.executeQuery();
while (res.next()){
    System.out.println(res);
    TOPIC t=new TOPIC();
    t.setId(res.getInt(1));
    t.setCatego_ID(res.getString(2));
    t.setNom(res.getString(3));
    t.setSubject(res.getString(4));
    t.setImage(res.getString(5));
    topics.add(t);
}
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return topics;
};

}
