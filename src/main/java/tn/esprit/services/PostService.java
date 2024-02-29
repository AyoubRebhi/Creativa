package tn.esprit.services;
import tn.esprit.Interfaces.InterfaceCRUD;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;
import tn.esprit.Utils.MaConnexion;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileInputStream;
public class PostService implements InterfaceCRUD<POST> {
    MaConnexion connexion = (MaConnexion) MaConnexion.getInstance();
    @Override
    public void ajouter (POST post){
        try {
            PreparedStatement pr = connexion.getConn().prepareStatement("INSERT INTO `post`( `Editeur`, `Media`, `Titre`, `Description`, `Topic_id`) VALUES (?,?,?,?,?)");
            pr.setInt(5,post.getTopic_id());
            pr.setString(1, post.getEditeur());
            pr.setString(2,post.getMedia());
            pr.setString(3,post.getTitre());
            pr.setString(4, post.getDescription());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    @Override
    public void modifier(POST post) {
        try {
            PreparedStatement   pr = connexion.getConn().prepareStatement("UPDATE `post` SET`Editeur`=?,`Media`=?,`Titre`=?,`Description`=?,`Topic_id`=? WHERE Id=?");
            pr.setInt(5,post.getTopic_id());
            pr.setString(1, post.getEditeur());
            pr.setString(3,post.getTitre());
            pr.setString(4, post.getDescription());
            pr.setString(2,post.getMedia());
            pr.setInt(6,post.getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    @Override
    public void supprimer(int id){
        try {
            PreparedStatement  pr = connexion.getConn().prepareStatement("DELETE FROM `post` WHERE Id=?");
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };
    public List<POST> afficher(){
        List<POST> topics = new ArrayList<>();

        try {
            PreparedStatement pr= connexion.getConn().prepareStatement("SELECT * FROM `post`");
            ResultSet res= pr.executeQuery();
            while (res.next()){
                System.out.println(res);
                POST t=new POST();
                t.setId(res.getInt(1));
                t.setTopic_id(res.getInt(6));
                t.setEditeur(res.getString(2));
                t.setTitre(res.getString(4));
                t.setDescription(res.getString(5));
                topics.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topics;
    };
    public List<POST> recherche_by_topic(){
        List<POST> posts = new ArrayList<>();
        try {
            Statement ste=connexion.getConn().createStatement();
            String sql = "SELECT t.Nom as topicTitle, p.Description as content " +
                    "FROM 'topic' t INNER JOIN 'post' p ON p.Topic_id = t.id";
            ResultSet res=ste.executeQuery(sql);
            while (res.next()){
                POST t=new POST();
                t.setId(res.getInt(1));
                t.setTopic_id(res.getInt(6));
                t.setEditeur(res.getString(2));
                t.setTitre(res.getString(4));
                t.setDescription(res.getString(5));
                posts.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }
    public List<POST> rechercher(int t_id) {
        List<POST> posts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM post WHERE Topic_id = ?";
            PreparedStatement statement = connexion.getConn().prepareStatement(sql);
            statement.setInt(1, t_id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                POST post = new POST();
                post.setId(res.getInt(1));
                post.setEditeur(res.getString(2));
                post.setTitre(res.getString(4));
                post.setDescription(res.getString(5));
                post.setTopic_id(res.getInt(6));
                post.setMedia(res.getString(3));
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }
}
