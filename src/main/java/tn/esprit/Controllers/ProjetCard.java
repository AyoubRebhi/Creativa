package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

import java.io.File;

public class ProjetCard {


    @FXML
    private Label prixLabel;
    @FXML
    private Label categorieLabel;

    @FXML
    private Label boxTitre;

    @FXML
    private ImageView imageProjet;

    @FXML
    private Label nbJaimeNB;
    @FXML
    private ImageView viewMoreBTN;
    private Projet projet;
    private ProjetServices ps;
    public ProjetCard(){
        this.ps = new ProjetServices();
    }

    public void setData(Projet projet, int id){
        DropShadow shadow = new DropShadow();
        viewMoreBTN.setOnMouseEntered(event -> {
            viewMoreBTN.setEffect(shadow);
        });

        viewMoreBTN.setOnMouseExited(event -> {
            viewMoreBTN.setEffect(null);
        });

        viewMoreBTN.setOnMouseClicked(event -> {
            afficherProjetById(event);
        });
        
        this.projet = projet;
        boxTitre.setText(projet.getTitre());
        ps.calculerNombreJaimePourProjet(projet);
        nbJaimeNB.setText(String.valueOf(projet.getNombreJaime()));
        prixLabel.setText(String.valueOf(projet.getPrix())+" Dt");
        categorieLabel.setText("#"+ps.afficherTitreCategorie(projet.getCategorie()));
        String media = projet.getMedia();

        if (media != null && !media.isEmpty()) { // Check if media path is not null or empty
            // Create a File object with the media path
            File file = new File(media);
            Image image;
            if (file != null && file.exists()) {
                // If the file is not null and exists, load the image from the file
                image = new Image(file.toURI().toString());
            } else {
                // If the file is null or does not exist, load a default image
                image = new Image(getClass().getResourceAsStream("/images/imageVideIcon.png"));
            }

            // Set the project image with the loaded image
            imageProjet.setImage(image);
        } else {
            // If media path is null or empty, load a default image
            imageProjet.setImage(new Image(getClass().getResourceAsStream("/images/imageVideIcon.png")));
        }

    }
    @FXML
    void afficherProjetById(MouseEvent event) {

    }
}
