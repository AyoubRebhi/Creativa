package tn.esprit.Services;

import tn.esprit.Models.Comments;
import tn.esprit.Utils.MaConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentService {
    MaConnexion connexion=(MaConnexion) MaConnexion.getInstance();

    public void InsererComment(Comments comment){
        try {
         PreparedStatement ps = connexion.getConn().prepareStatement("INSERT INTO `comments`(`Post_id`, `Editeur`, `Content`, `Seen`) VALUES (?,?,?,?)");
            ps.setInt(1,comment.getPost_id());
            ps.setInt(2,comment.getEditeur());
            ps.setString(3,comment.getContent());
            ps.setInt(4,comment.getSeen());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        System.out.println("Le Commentaire a été ajouté avec succès !");
    }
    public List<Comments> AfficherAll(int p_id){
        List<Comments> comments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `comments` WHERE Post_id=?";
            PreparedStatement statement = connexion.getConn().prepareStatement(sql);
            statement.setInt(1, p_id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Comments comment= new Comments();
                comment.setContent(res.getString(4));
                comment.setEditeur(res.getInt(3));
                comment.setId(res.getInt(1));
                comment.setId(res.getInt(2));
                comment.setSeen(res.getInt(5));

                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    public List<Comments> Affichernotification(int p_id){
        List<Comments> comments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM comments WHERE Seen = ? AND Post_id = ?";
            PreparedStatement statement = connexion.getConn().prepareStatement("SELECT * FROM comments WHERE Post_id = ? AND Seen=?");
            statement.setInt(1,p_id);
            statement.setInt(2,0);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Comments comment= new Comments();
                comment.setContent(res.getString(4));
                comment.setEditeur(res.getInt(3));
                comment.setId(res.getInt(1));
                comment.setPost_id(res.getInt(2));
                comment.setSeen(res.getInt(5));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
    public void changeSeen(Comments comment){
        try {
            comment.setSeen(1);
            PreparedStatement preparedStatement = connexion.getConn().prepareStatement("UPDATE `comments` SET `Post_id`=?,`Editeur`=?,`Content`=?,`Seen`=? WHERE Id=?");
             // Définit la valeur de la colonne `Seen` sur true
            preparedStatement.setInt(1, comment.getPost_id());
            preparedStatement.setInt(2,comment.getEditeur());
            preparedStatement.setString(3,comment.getContent());
            preparedStatement.setInt(4,comment.getSeen());
            preparedStatement.setInt(5,comment.getId());// Utilisez le comment.getId() pour mettre à jour l'enregistrement spécifique
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
