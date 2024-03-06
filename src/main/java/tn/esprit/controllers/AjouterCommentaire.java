package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.Models.Comments;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.CommentService;

import java.io.IOException;
import java.util.List;

public class AjouterCommentaire {
    @FXML
    private TextField Content;

    @FXML
    private TextField Editeur;

    @FXML
    private Text Post;
    @FXML
    private ListView<Comments> List_id;
    @FXML
    void Ajouter(ActionEvent event) {
        CommentService commentService=new CommentService();
        Comments com=new Comments();
        com.setContent(Content.getText());
        com.setSeen(0);
        com.setEditeur(Integer.parseInt(Editeur.getText()));
        com.setPost_id(Integer.parseInt(Post.getText()));
        commentService.InsererComment(com);
    }
    @FXML
    void initialize() {
        assert Content != null : "fx:id=\"Content\" was not injected: check your FXML file 'AjouterCommentaire.fxml'.";
        assert Editeur != null : "fx:id=\"Editeur\" was not injected: check your FXML file 'AjouterCommentaire.fxml'.";
        assert Post != null : "fx:id=\"Post\" was not injected: check your FXML file 'AjouterCommentaire.fxml'.";
        CommentService commentService = new CommentService();
        List<Comments> list = commentService.Affichernotification(1);
        ObservableList<Comments> observableList = FXCollections.observableList(list);
        List_id.setItems(observableList);
        List_id.setCellFactory(listView -> new ListCell<Comments>() {
            @Override
            protected void updateItem(Comments item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);

                } else {
                    setText(item.toString()); // Assuming you override toString() in Comments class
                    // Si vous avez une propriété dans la classe Comments représentant une image, vous pouvez l'afficher ainsi :
                    // ImageView imageView = new ImageView(item.getImage());
                    // setGraphic(imageView);
                }
            }
        });}
    }
