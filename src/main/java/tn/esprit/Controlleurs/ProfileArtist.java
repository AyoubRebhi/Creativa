package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.Interfaces.UpdateListener;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.UserService;

import java.io.IOException;

public class ProfileArtist implements UpdateListener {


    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private Label tel;
    @FXML
    private Label username;
    @FXML
    private Label adresse;

    // Vous devez injecter le UserService dans votre contrôleur
    public UserService userService=new UserService();

    // Méthode appelée après le chargement du FXML
    @FXML
    public void initialize() {
        // Assurez-vous que le userService est initialisé avant d'appeler cette méthode
        updateLabels();
    }

    // Méthode pour mettre à jour les labels avec les données du UserService
    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        nom.setText(utilisateur.getLastName());
        prenom.setText(utilisateur.getFirstName());
        email.setText(utilisateur.getEmail());
        tel.setText(String.valueOf(utilisateur.getNumTel()));
        username.setText(utilisateur.getUsername());
        adresse.setText(utilisateur.getAddress());


    }


    public void handeUpdateClient(ActionEvent event) {
        // You can create a new FXML file and controller for the update page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateUser.fxml"));
        Parent updateRoot;

        try {
            updateRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Pass the selected user to the controller of the update page
        updateUserController updateController = loader.getController();
        updateController.initData(userService.getById(session.id_utilisateur));
// Pass the selected user to the controller of the update page

        updateController.setUpdateListener(this); // Set profileUser as the update listener
        // Create a new stage for the update page
        Stage updateStage = new Stage();
        updateStage.initModality(Modality.WINDOW_MODAL);
        updateStage.initOwner(((Node) nom).getScene().getWindow());
        updateStage.setScene(new Scene(updateRoot));

        // Show the update stage
        updateStage.show();
    }

    @Override
    public void onUpdate(User updatedUser) {
        // Mettez à jour les labels avec les données de l'utilisateur mis à jour
        nom.setText(updatedUser.getLastName());
        prenom.setText(updatedUser.getFirstName());
        email.setText(updatedUser.getEmail());
        tel.setText(String.valueOf(updatedUser.getNumTel()));
        username.setText(updatedUser.getUsername());
        adresse.setText(updatedUser.getAddress());
    }

    public void directTomdp(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangermdpArtist.fxml"));
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
