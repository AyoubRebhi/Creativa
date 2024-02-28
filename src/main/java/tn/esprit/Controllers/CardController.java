package tn.esprit.Controllers;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.esprit.Models.Projet;

public class CardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox box;

    @FXML
    private Label boxDescription;

    @FXML
    private Label boxTitre;

    @FXML
    private ImageView imageProjet;

    @FXML
    private Label nbJaimeNB;

    private String [] colors = {"#ab824e","#b78f5b"};

    public void setData(Projet projet){
        Image image = new Image(getClass().getResourceAsStream(projet.getMedia()));
        imageProjet.setImage(image);
        boxTitre.setText(projet.getTitre());
        boxDescription.setText(projet.getDescription());

    }



}
