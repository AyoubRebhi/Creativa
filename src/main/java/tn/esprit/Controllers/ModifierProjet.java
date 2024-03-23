package tn.esprit.Controllers;

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
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.CategorieServices;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Services.UserService;

public class ModifierProjet {
    private int id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ChoiceBox<String> categorieChoiceBox;

    @FXML
    private TextField descriptionTF;

    @FXML
    private Label labelFX;

    @FXML
    private TextField prixTF;

    @FXML
    private TextField titreTF;
    private ListView<String> listView;
    private Projet projet;

    @FXML
    public void initialize(){
        updateLabels();
    }
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
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
    void modifierProjet(ActionEvent event) {
        // Modifier le projet
        Projet nouveauProjet = getProjet();
        ProjetServices projetServices = new ProjetServices();
        projetServices.modifier(nouveauProjet);
        showAlert("Succès", "Le projet a été modifié avec succès.");
        refreshProjetsList();
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

    void setParametre(int id,Projet projet){
        this.projet=projet;
        this.id=id;
        ProjetServices projetServices = new ProjetServices();
        List<String> titresCategories = projetServices.afficherTitresCategories();
        categorieChoiceBox.setItems(FXCollections.observableArrayList(titresCategories));
        titreTF.setText( projet.getTitre());
        descriptionTF.setText( projet.getDescription());
        prixTF.setText(String.valueOf(projet.getPrix()));
        categorieChoiceBox.getSelectionModel().select(projet.getCategorie() -1);
    }

    private Projet getProjet(){
        CategorieServices categorieServices = new CategorieServices();
        String nouveauTitre = titreTF.getText();
        String nouvelleDescription = descriptionTF.getText();
        String prixText = prixTF.getText();
        int nouveauidCategorie = categorieChoiceBox.getSelectionModel().getSelectedIndex() + 1;
        System.out.println(nouveauidCategorie);
        if (nouveauTitre.isEmpty() || nouvelleDescription.isEmpty() || prixText.isEmpty()) {
            showAlert("Erreur", "Tous les champs sont obligatoires !");

        }
        double prix = 0;
        try {
            prix = Double.parseDouble(prixText);
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le prix doit être un nombre !");
        }
        String media = projet.getMedia();
        User utilisateur = userService.getById(session.id_utilisateur);


        return new Projet(id, nouveauTitre, nouvelleDescription, media, prix, nouveauidCategorie, utilisateur.getId());
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
