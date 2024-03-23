package tn.esprit.Controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.CategorieServices;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Services.UserService;

public class AfficherUnProjetParIdAdmin {

    @FXML
    private Label categorieLabel;

    @FXML
    private Label createdAtLablel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label prixLabel;

    @FXML
    private ImageView projetImage;
    @FXML
    private ImageView archiverIcon;
    @FXML
    private ImageView desarchiverIcon;


    @FXML
    private Label titreLabel;
    @FXML
    private ImageView retourIcon;
    private Projet projet;
    private int id;
    @FXML
    private Label labelFX;

    @FXML
    void redirectVersAfficherCategories(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarAdminCategories2.fxml"));
            Parent root = loader.load();
            AfficherUnProjetParIdAdmin controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectVersAfficherProjets(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsAdmin.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsAdmin controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectVersProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsAdmin.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsAdmin controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void setParametre(int id, Projet projet) {
        DropShadow shadow = new DropShadow();
        if (projet.getIsVisible()) {
            archiverIcon.setDisable(false); // Activer archiverIcon
            desarchiverIcon.setDisable(true); // Désactiver desarchiverIcon
        } else {
            archiverIcon.setDisable(true); // Désactiver archiverIcon
            desarchiverIcon.setDisable(false); // Activer desarchiverIcon
        }
        retourIcon.setOnMouseEntered(event -> {
            retourIcon.setEffect(shadow);
        });
        archiverIcon.setOnMouseEntered(event ->{
            archiverIcon.setEffect(shadow);
        });
        desarchiverIcon.setOnMouseEntered(event->{
            desarchiverIcon.setEffect(shadow);
        });

        retourIcon.setOnMouseExited(event -> {
            retourIcon.setEffect(null);
        });
        archiverIcon.setOnMouseExited(event ->{
            archiverIcon.setEffect(null);
        });
        desarchiverIcon.setOnMouseExited(event->{
            desarchiverIcon.setEffect(null);
        });
        retourIcon.setOnMouseClicked(event -> {
            redirectVersAfficherProjets(event);
        });
        archiverIcon.setOnMouseClicked(event ->{
            archiverProjet(event);
        });
        desarchiverIcon.setOnMouseClicked(event->{
            desarchiverProjet(event);
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

    private void archiverProjet(MouseEvent event) {
        ProjetServices projetServices = new ProjetServices();

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation d'archivage");
        confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir archiver cette catégorie ?");
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);

        // Ajouter les boutons Oui et Annuler
        confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                try {
                    projetServices.modifierVisibilite(id, false);
                    showAlert("Opération réussie", "Le projet a été archivé avec succès.");
                    redirectVersAfficherProjets(event);
                } catch (SQLException e) {
                    showAlert("Erreur", "Une erreur s'est produite lors de l'archivage du projet.");
                }

            }
        });

    }
    public void desarchiverProjet(MouseEvent event) {
        ProjetServices projetServices = new ProjetServices();

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation d'archivage");
        confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir archiver cette catégorie ?");
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);

        // Ajouter les boutons Oui et Annuler
        confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                try {
                    projetServices.modifierVisibilite(id, true);
                    showAlert("Opération réussie", "Le projet a été d avec succès.");

                    redirectVersAfficherProjets(event);
                } catch (SQLException e) {
                    showAlert("Erreur", "Une erreur s'est produite lors de l'archivage du projet.");
                }

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


    @FXML
    void initialize() {
        updateLabels();
    }
    @FXML
    private Label name;
    UserService userService=new UserService();


    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        name.setText(utilisateur.getUsername());



    }
    @FXML
    public void diectutuilisateur(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/listUserOriginale.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }

    }
    public void ProfileClient(MouseEvent mouseEvent) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileAdmin.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }

    }
    public void directTocommande(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceAdmin.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }


    }
    public void directToLogin(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/log.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }
        session.id_utilisateur = 0; // Assurez-vous de réinitialiser la session ou l'utilisateur approprié

    }
}
