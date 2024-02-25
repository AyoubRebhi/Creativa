package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Services.UserService;

import java.io.IOException;

public class VerficationCodeMotpasse {

    @FXML
    private TextField verificationCodeField;

    private UserService userService = new UserService();
    private String userEmail;
    private String resetPasswordToken;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @FXML
    private void validateVerificationCode(ActionEvent actionEvent) {
        String enteredCode = verificationCodeField.getText();

        if (enteredCode.equals(resetPasswordToken)) {
            // Verification successful, load the ResetPassword.fxml
            loadResetPasswordPage(actionEvent);
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Code de vérification invalide.");
        }
    }

    private void loadResetPasswordPage(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResetPassword.fxml"));
            Parent root = loader.load();


            // Passer les données nécessaires au contrôleur de vérification
            ResetPasswordController ResetController = loader.getController();
            ResetController.setUserEmail(userEmail);

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
