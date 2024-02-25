package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class VerificationController {
    @FXML
    private TextField verificationCodeField;

    private String userEmail;
    private String verificationCode;
    private boolean verificationSuccessful = false; // Ajout de la propriété

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    // Ajout de la méthode getter
    public boolean isVerificationSuccessful() {
        return verificationSuccessful;
    }

    @FXML
    protected void validerCode(ActionEvent event) {
        String codeSaisi = verificationCodeField.getText();

        // Vérifiez ici si le code saisi correspond au code envoyé par e-mail
        // Utilisez la méthode du service pour vérifier le code
        if (codeSaisi.equals(verificationCode)) {
            // Code de vérification valide, vous pouvez ici ajouter le code pour finaliser l'inscription
            // userService.finaliserInscription(userEmail);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Inscription réussie !");
            verificationSuccessful = true; // Marquer la vérification comme réussie
            closeVerificationWindow();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Code de vérification invalide.");
        }
    }

    private void closeVerificationWindow() {
        // Obtenez la scène actuelle et fermez la fenêtre
        verificationCodeField.getScene().getWindow().hide();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
