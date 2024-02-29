package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.TopicService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ADDTOPIC {
    @FXML
    private TextField Desc_top;
private String Images;
    @FXML
    private ImageView Img;
    @FXML
    private TextField Nom_u;
    @FXML
    private TextField catego_id;
    @FXML
    void AfficherSujet(ActionEvent event) {
      FXMLLoader  fxmlLoader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/AfficherTopic.fxml"))));
        try {
            Nom_u.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void AjouterSujet(ActionEvent event) {
        TopicService test= new TopicService();
        TOPIC t2= new TOPIC();
        t2.setNom(Nom_u.getText());
        t2.setCatego_ID(Integer.parseInt(catego_id.getText()));
        t2.setSubject(Desc_top.getText());
        t2.setImage(this.Images);
        test.ajouter(t2);

    }
    @FXML
    void selectImage(ActionEvent event) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Selection media");
        File selectedFile=fileChooser.showOpenDialog(null);
        if(selectedFile !=null){
            String url =selectedFile.toURI().toString();
            this.Images=url;
            Image image= new Image(this.Images);
            Img.setImage(image);


        }
    }
}
