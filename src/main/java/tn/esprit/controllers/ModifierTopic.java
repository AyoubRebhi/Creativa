package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.PostService;
import tn.esprit.services.TopicService;

import java.io.File;

public class ModifierTopic {
    private String media;
    private TOPIC topic;
    @FXML
    private TextField Categorie;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Subject;
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
    void initialize(TOPIC topic) {
        assert Categorie != null : "fx:id=\"desc\" was not injected: check your FXML file 'ModifierPost.fxml'.";
        assert Nom != null : "fx:id=\"editeur\" was not injected: check your FXML file 'ModifierPost.fxml'.";
        assert Subject != null : "fx:id=\"titre\" was not injected: check your FXML file 'ModifierPost.fxml'.";
        this.topic=topic;
        Subject.setText(this.topic.getSubject());
        Nom.setText(this.topic.getNom());
        Categorie.setText(this.topic.getCatego_ID());
    }
    @FXML
    void Modifier(ActionEvent event) {
        TopicService t=new TopicService();
        this.topic.setNom(Nom.getText());
        this.topic.setSubject(Subject.getText());
        this.topic.setImage(media);
        t.modifier(this.topic);
    }
}
