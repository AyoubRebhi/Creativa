package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    private String userEmail;  // Set the user's email before loading this controller

    private UserService userService = new UserService();  // Your user service

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @FXML
    private void resetPassword(ActionEvent event) {
        String newPassword = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Veuillez saisir le nouveau mot de passe et confirmer.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Les mots de passe ne correspondent pas.");
            return;
        }
        System.out.println("entree vers reset password"+userEmail+passwordField.getText());

        // Verify the reset password token
        if (userService.resetPassword(userEmail,passwordField.getText())) {
            // Perform the password reset
            System.out.println("entree vers reset password");
            if (userService.resetPassword(userEmail, newPassword)) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Réinitialisation du mot de passe réussie.");
                // Redirect to login page or perform other actions
                redirectToLoginPage(event);


            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la réinitialisation du mot de passe. Veuillez réessayer.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Code de réinitialisation du mot de passe invalide.");
        }
    }
    private void redirectToLoginPage(ActionEvent event) {
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
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}