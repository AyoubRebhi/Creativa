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
import tn.esprit.Utils.EmailsUtils;

import java.io.IOException;

public class emailForPasswordControlleur {
    @FXML
    private TextField emailField;

    private UserService userService = new UserService();

    public void sendVerificationCode(ActionEvent actionEvent) {
        if (emailField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Veuillez saisir votre adresse e-mail.");
        } else if (!userService.emailExists(emailField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "L'adresse e-mail n'existe pas dans notre système.");
        } else {
            String resetPasswordToken = generateResetPasswordToken();

            // Generate and send reset password token
            EmailsUtils.sendResetPasswordToken(emailField.getText(), resetPasswordToken);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Un e-mail de réinitialisation a été envoyé à votre adresse.");
            loadVerificationPage(emailField.getText(), resetPasswordToken, actionEvent);
        }
    }

    private void loadVerificationPage(String email, String resetPasswordToken, ActionEvent actionEvent) {
        try {
            // Charger la page de vérification à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnterVerificationCode.fxml"));
            Parent root = loader.load();

            // Passer les données nécessaires au contrôleur de vérification
            VerficationCodeMotpasse verificationController = loader.getController();
            verificationController.setUserEmail(email);

            verificationController.setResetPasswordToken(resetPasswordToken);

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de vérification
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de vérification
        }
    }

    private String generateResetPasswordToken() {
        // Implement your logic to generate a secure reset password token.
        // This can be a random string, a UUID, or a token based on specific criteria.
        // For simplicity, let's use a random string in this example.
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            token.append(characters.charAt(index));
        }

        return token.toString();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

