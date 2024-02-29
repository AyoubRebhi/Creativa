package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.PostService;

import java.io.File;

public class AjouterPost {
    private int t_id;
    private String media;
    @FXML
    private TextField desc;

    @FXML
    private TextField editeur;

    @FXML
    private TextField titre;

    @FXML
    void select_media(ActionEvent event) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Selection media");
        File selectedFile=fileChooser.showOpenDialog(null);
        if(selectedFile !=null){
            String url=selectedFile.toURI().toString();
            this.media=url;
        }
    }
    @FXML
    void initialize(TOPIC t) {
        assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'AjouterPost.fxml'.";
        assert editeur != null : "fx:id=\"editeur\" was not injected: check your FXML file 'AjouterPost.fxml'.";
        assert titre != null : "fx:id=\"titre\" was not injected: check your FXML file 'AjouterPost.fxml'.";
t_id=t.getId();
    }
    @FXML
    void Ajouter(ActionEvent event) {
        PostService post=new PostService();
        POST p=new POST();
        p.setEditeur(editeur.getText());
        p.setTitre(titre.getText());
        p.setDescription(desc.getText());
        p.setMedia(this.media);
        p.setTopic_id(t_id);
        post.ajouter(p);
    }
}
