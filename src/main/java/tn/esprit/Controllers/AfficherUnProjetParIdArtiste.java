package tn.esprit.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class AfficherUnProjetParIdArtiste {
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
    @FXML
    private ImageView retourIcon;
    private Projet projet;
    private int id;
    @FXML
    private Label labelFX;


    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    public void redirectVersAfficherProjets(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setParametre(int id, Projet projet) {
        DropShadow shadow = new DropShadow();
        retourIcon.setOnMouseEntered(event -> {
            retourIcon.setEffect(shadow);
        });

        retourIcon.setOnMouseExited(event -> {
            retourIcon.setEffect(null);
        });

        retourIcon.setOnMouseClicked(event -> {
            redirectVersAfficherProjets(event);
        });

        this.projet = projet;
        this.id = id;
        ProjetServices ps = new ProjetServices();
        titreLabel.setText(projet.getTitre());
        descriptionLabel.setText(projet.getDescription());
        prixLabel.setText(String.valueOf(projet.getPrix()) + "Dt");
        createdAtLablel.setText("Ajouté le:" + String.valueOf(projet.getCreatedAt()));
        categorieLabel.setText("#" + ps.afficherTitreCategorie(projet.getCategorie()));

        // Récupérer le chemin du média depuis le projet
        String media = projet.getMedia();

        if (media != null && !media.isEmpty()) {
            // Créer un objet File avec le chemin du média
            File file = new File(media);
            Image image;
            if (file.exists()) {
                // Si le fichier existe, charger l'image à partir du fichier
                image = new Image(file.toURI().toString());
            } else {
                // Si le fichier n'existe pas, charger une image par défaut
                image = new Image(getClass().getResourceAsStream("/images/imageVideIcon.png"));
            }
            // Définir l'image de projetImage avec l'image créée
            projetImage.setImage(image);
        } else {
            // Si le chemin du média est null ou vide, charger une image par défaut
            Image image = new Image(getClass().getResourceAsStream("/images/imageVideIcon.png"));
            projetImage.setImage(image);
        }
    }

    public void redirectVersAcceuil(ActionEvent event) {
    }

    public void redirectVersAfficherProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ProjetServices ps = new ProjetServices();

    public void modifierProjet(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
            Parent root = loader.load();
            ModifierProjet controller = loader.getController();
            controller.setParametre(id,ps.afficherProjetParId(id));
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProjet(ActionEvent event) {
        ProjetServices projetServices = new ProjetServices();

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation de supprission ");
        confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);

        // Ajouter les boutons Oui et Annuler
        confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                ps.supprimer(id);
                showAlert("Opération réussie", "Le projet a été archivé avec succès.");
                redirectVersAfficherProjets(event);
            }
        });
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
