package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.UserService;

import java.io.IOException;

public class sidebarClient {

    @FXML
    private Label name;
    @FXML
    private Button acceuil;

    @FXML
    private Button profile;

    @FXML
    private Button carte;

    @FXML
    private Hyperlink out;
UserService userService=new UserService();
    @FXML
    private void redirectAcceuil(ActionEvent event) {
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
    void initialize() {
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
    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        name.setText(utilisateur.getUsername());



    }
}
