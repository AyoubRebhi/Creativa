package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.Models.POST;
import tn.esprit.Services.PostService;

import java.io.File;

public class ModifierPost {
    private POST post;
    private String media;
    @FXML
    private TextField desc;

    @FXML
    private TextField editeur;

    @FXML
    private TextField titre;

    @FXML
    void SelectMedia(ActionEvent event) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Selection media");
        File selectedFile=fileChooser.showOpenDialog(null);
        if(selectedFile !=null){
            String url=selectedFile.toURI().toString();
            this.media=url;
        }
    }

    @FXML
    void initialize(POST post) {
        assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'ModifierPost.fxml'.";
        assert editeur != null : "fx:id=\"editeur\" was not injected: check your FXML file 'ModifierPost.fxml'.";
        assert titre != null : "fx:id=\"titre\" was not injected: check your FXML file 'ModifierPost.fxml'.";
this.post=post;
desc.setText(this.post.getDescription());
editeur.setText(this.post.getEditeur());
titre.setText(this.post.getTitre());
    }
    @FXML
    void Modifier(ActionEvent event) {
        PostService post=new PostService();
        this.post.setDescription(desc.getText());
        this.post.setTitre(titre.getText());
        this.post.setEditeur(editeur.getText());
        this.post.setMedia(media);

        post.modifier(this.post);
    }
}
