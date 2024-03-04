package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.UserService;

import java.io.IOException;

public class changemdpArtist {

    @FXML
    public PasswordField oldmdp;

    @FXML
    public PasswordField newmdp;

    @FXML
    public PasswordField newmdp1;
    public UserService userService =new UserService();

    @FXML
    private void handlePasswordChange(ActionEvent ActionEvent) {
        System.out.println("1"+newmdp.getText());
        System.out.println("2"+newmdp1.getText());


        // Vérifiez si le nouveau mot de passe et la confirmation correspondent
        if (!newmdp.getText().equals(newmdp1.getText())) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nouveau mot de passe ne correspond pas à la confirmation.");
            return;
        }

        if (newmdp1.getText().length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nouveau mot de passe doit contenir au moins 8 caractères.");
            return;
        }

        // Vérifiez si l'ancien mot de passe est correct
        if (!checkOldPassword(oldmdp.getText())) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Ancien mot de passe incorrect.");
            return;
        }

        // Mettez à jour le mot de passe dans la base de données
        updatePassword(newmdp1.getText());

        // Affichez une alerte de succès
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Mot de passe mis à jour avec succès.");
        directTomdp(ActionEvent);
    }

    // Vérifie si l'ancien mot de passe est correct
    private boolean checkOldPassword(String oldPassword) {
        // Obtenez l'utilisateur actuel depuis la base de données
        User currentUser = userService.getById(session.id_utilisateur);

        // Vérifiez si l'ancien mot de passe correspond au mot de passe stocké
        return BCrypt.checkpw(oldPassword, currentUser.getPassword());
    }

    // Met à jour le mot de passe dans la base de données
    private void updatePassword(String newPassword) {
        // Obtenez l'utilisateur actuel depuis la base de données
        User currentUser = userService.getById(session.id_utilisateur);

        ;

        // Mettez à jour le mot de passe dans l'objet utilisateur


        // Mettez à jour l'objet utilisateur dans la base de données
        userService.resetPasswordparid(session.id_utilisateur,newPassword);
    }

    // Affiche une alerte
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void directTomdp(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileArtist.fxml"));
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

    public void directtoprofile(MouseEvent mouseEvent) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileArtist.fxml"));
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
}
