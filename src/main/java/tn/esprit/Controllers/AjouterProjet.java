package tn.esprit.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Services.UserService;

public class AjouterProjet {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> categorieChoiceBox;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private Button mediaBTN;

    @FXML
    private Label modifier_categories;
    @FXML
    private Label labelFX;

    @FXML
    private TextField prixTF;

    @FXML
    private TextField titreTF;
    private Projet projet = new Projet();



    @FXML
    void afficherProjets(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void ajouterMedia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Récupérer le chemin du fichier sélectionné
            String mediaPath = selectedFile.getAbsolutePath();
            // Stocker le chemin du média dans la variable du projet
            projet.setMedia(mediaPath);
        }
    }

    @FXML
    void ajouterProjet(ActionEvent event) {
        String titre = titreTF.getText();
        String description = descriptionTA.getText();
        String prixText = prixTF.getText();
        int idCategorie = categorieChoiceBox.getSelectionModel().getSelectedIndex() + 1; // L'indice de la liste commence à partir de 0

        // Vérifier si les champs obligatoires sont vides
        if (titre.isEmpty() || description.isEmpty() || prixText.isEmpty()) {
            showAlert("Erreur", "Tous les champs sont obligatoires !");
            return;
        }

        // Vérifier si le prix est un nombre valide
        double prix;
        try {
            prix = Double.parseDouble(prixText);
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le prix doit être un nombre !");
            return;
        }

        // Récupérer le média depuis le projet
        String media = projet.getMedia();
        User utilisateur = userService.getById(session.id_utilisateur);


        // Créer le projet avec le média récupéré
        Projet projet = new Projet(titre, description, media, prix, idCategorie, utilisateur.getId());

        // Ajouter le projet
        ProjetServices projetServices = new ProjetServices();
        projetServices.ajouter(projet);

        showAlert("Succès", "Le projet a été ajouté avec succès.");
        refreshProjetsList();
    }


    public void refreshProjetsList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titreTF.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        assert categorieChoiceBox != null : "fx:id=\"categorieChoiceBox\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert descriptionTA != null : "fx:id=\"descriptionTA\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert mediaBTN != null : "fx:id=\"mediaBTN\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert modifier_categories != null : "fx:id=\"modifier_categories\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert prixTF != null : "fx:id=\"prixTF\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert titreTF != null : "fx:id=\"titreTF\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        updateLabels();
        // Récupérer les titres des catégories depuis ProjetServices
        ProjetServices projetServices = new ProjetServices();
        List<String> titresCategories = projetServices.afficherTitresCategories();

        // Peupler la categorieChoiceBox avec les titres des catégories
        categorieChoiceBox.setItems(FXCollections.observableArrayList(titresCategories));
    }



    public void redirectVersAfficherProjets(ActionEvent event) {
    }
    @FXML
    private Label name;
    UserService userService=new UserService();

    // Méthode pour mettre à jour les labels avec les données du UserService
    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        name.setText(utilisateur.getUsername());
    }
    public void directologin(ActionEvent event) {
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

    public void directToFormation(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageFormation.fxml"));
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
        // Assurez-vous de réinitialiser la session ou l'utilisateur approprié

    }
    public void redirectVersProfile(ActionEvent event) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileArtist.fxml"));
            Parent root = loader.load();
            sidebarArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectVersinscription(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListInscription.fxml"));
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
        // Assure
    }

    public void directtopost(ActionEvent event) {



        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTopic.fxml"));
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
