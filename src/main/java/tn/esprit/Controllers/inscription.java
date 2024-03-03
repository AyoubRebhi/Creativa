package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.Models.Role;
import tn.esprit.Models.User;
import tn.esprit.Services.UserService;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tn.esprit.Utils.EmailsUtils.sendVerificationEmail;

public class inscription {


    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private PasswordField mdp;

    @FXML
    private RadioButton clientRadioButton;

    @FXML
    private RadioButton artisteRadioButton;
    @FXML
    private PasswordField mdp1;

    @FXML
    private TextField numtel;

    @FXML
    private TextField username;

    @FXML
    private Button inscriptionButton;

    private UserService userService = new UserService(); // Injection du service

    public void adduser(ActionEvent event) {
        // Vérification de la saisie
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || mdp.getText().isEmpty() || mdp1.getText().isEmpty()
                || email.getText().isEmpty() || username.getText().isEmpty() || numtel.getText().isEmpty() ||
                (!clientRadioButton.isSelected() && !artisteRadioButton.isSelected())) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Remplissez tous les champs.");
        } else if (!mdp.getText().equals(mdp1.getText())) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Les mots de passe ne correspondent pas.");
        } else if (!isNumeric(numtel.getText()) || numtel.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Le numéro de téléphone doit être numérique et contenir 8 chiffres.");
        } else {
            String role = "";
            if (clientRadioButton.isSelected()) {
                role = "CLIENT";
            } else if (artisteRadioButton.isSelected()) {
                role = "ARTIST";
            }

            if (mdp.getText().length() < 8) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Le mot de passe doit contenir au moins 8 caractères.");
                return;
            }

            // Vérification de l'adresse e-mail avec une regex
            if (!validateEmail(email.getText())) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Adresse e-mail invalide.");
                return;
            }

            if (userService.emailExists(email.getText())) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "L'adresse e-mail existe déjà.");
                return;
            }

            // Vérification si le username existe déjà dans la base de données
            if (userService.usernameExists(username.getText())) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom d'utilisateur existe déjà.");
                return;
            }

            String verificationCode = generateVerificationCode();
            sendVerificationEmail(email.getText(), verificationCode);

            // Ouvrir la fenêtre de vérification
            if (openVerificationWindow(email.getText(), verificationCode)) {
                // La vérification du code est réussie, ajouter l'utilisateur
                userService.ajouter4(new User(nom.getText(), prenom.getText(), username.getText(), mdp.getText(),
                        Role.valueOf(role), "", "", "", email.getText(), Integer.parseInt(numtel.getText())));

                // Afficher une confirmation
                redirectToLoginPage(event);
            } else {
                // La vérification du code a échoué, afficher un message d'erreur
                showAlert(Alert.AlertType.ERROR, "Erreur", "Code de vérification incorrect.");
            }
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private String generateVerificationCode() {
        // Générez un code aléatoire (vous pouvez personnaliser la logique selon vos besoins)
        // Dans cet exemple, un code à 6 chiffres
        int code = 100000 + (int) (Math.random() * 900000);
        return String.valueOf(code);
    }

    private boolean openVerificationWindow(String userEmail, String verificationCode) {
        try {
            // Charger la page de vérification à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/verification.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur de la fenêtre de vérification
            VerificationController verificationController = loader.getController();

            // Passez les informations nécessaires au contrôleur de la fenêtre de vérification
            verificationController.setUserEmail(userEmail);
            verificationController.setVerificationCode(verificationCode);

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Créer une nouvelle étape (stage) pour la fenêtre de vérification
            Stage verificationStage = new Stage();
            verificationStage.setScene(scene);

            // Afficher la fenêtre de vérification
            verificationStage.showAndWait();

            // Renvoyer true si la vérification a réussi, false sinon
            return verificationController.isVerificationSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la fenêtre de vérification
            return false;
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

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    private boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
