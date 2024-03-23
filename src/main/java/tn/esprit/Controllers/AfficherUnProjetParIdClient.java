package tn.esprit.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.Models.Jaime;
import tn.esprit.Models.Projet;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.JaimeServices;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Services.UserService;

import java.io.File;
import java.io.IOException;

public class AfficherUnProjetParIdClient {
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
    @FXML
    private ImageView commdeBTN;
    private Projet projet;
    private int id;
    @FXML
    private Label labelFX;


    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public void redirectVersAfficherProjets(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsClient.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsClient controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectVersProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsClient.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsClient controller = loader.getController();
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
        commdeBTN.setOnMouseEntered(event ->{
            commdeBTN.setEffect(shadow);
        });
        commdeBTN.setOnMouseExited(event ->{
            commdeBTN.setEffect(null);
        });
        commdeBTN.setOnMouseClicked(event->{
            commanderProjet();
        });
        jaimeIcon.setOnMouseEntered(event->{
            jaimeIcon.setEffect(shadow);
        });
        jaimeIcon.setOnMouseExited(event->{
            jaimeIcon.setEffect(null);
        });
        jaimeIcon.setOnMouseClicked(event->{
            
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

    public void insererJaime(MouseEvent mouseEvent) {
        UserService userService = new UserService();
        User utilisateur = userService.getById(session.id_utilisateur);

        // 1. Get user ID from session (assuming you have a session management)
        int userId = utilisateur.getId(); // Replace with your logic to get user ID

        // 2. Create a Jaime object with project ID and user ID
        Jaime jaime = new Jaime(projet.getId(), userId);

        // 3. Call JaimeServices to insert the like
        JaimeServices js = new JaimeServices();
        js.insererJaime(jaime);

        // 4. (Optional) Update UI to reflect the like (e.g., change icon or display message)
        // You can update the jaimeIcon image or display a success message here.
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void commanderProjet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommande.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur de AjouterCommande
            AjouterCommande controller = loader.getController();

            // Appeler une méthode pour passer l'ID et l'objet Projet
            controller.setProjet(this.id, this.projet);

            // Changer la scène pour afficher AjouterCommande.fxml
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void initialize(){
        updateLabels();
    }
    @FXML
    private void directToProfile(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileClient.fxml"));
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

    @FXML
    private void directToCard(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cartefedilite.fxml"));
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

    @FXML
    private void redirecttoLogin(ActionEvent event) {
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
    @FXML
    private Label name;
    UserService userService = new UserService();
    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        name.setText(utilisateur.getUsername());



    }


    public void directTOcommande(ActionEvent event) {

        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceUser.fxml"));
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

    public void DirectTopost(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTopicClient.fxml"));
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

    public void DirecttoFormations(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageFormationClient.fxml"));
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
    public void directToProjet(ActionEvent event) {

        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsClient.fxml"));
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


}
