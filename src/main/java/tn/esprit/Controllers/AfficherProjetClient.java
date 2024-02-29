package tn.esprit.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

import java.util.Date;

public class AfficherProjetClient {
    @FXML
    private Label categorieLabel;

    @FXML
    private Label createdAtLablel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView jaimeIcon;

    @FXML
    private Label prixLabel;

    @FXML
    private ImageView projetImage;

    @FXML
    private Label titreLabel;
    private Projet projet;
    private int id;


    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    void setParametre(int id, Projet projet){
        this.projet=projet;
        this.id = id;
        ProjetServices ps = new ProjetServices();
        titreLabel.setText(projet.getTitre());
        descriptionLabel.setText(projet.getDescription());
        prixLabel.setText(String.valueOf(projet.getPrix()));
        createdAtLablel.setText(String.valueOf(projet.getCreatedAt()));
        categorieLabel.setText(ps.afficherTitreCategorie(projet.getCategorie()));

    }
}
