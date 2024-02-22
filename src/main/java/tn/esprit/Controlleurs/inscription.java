package tn.esprit.Controlleurs;

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
import javafx.stage.Window;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextField numtel;

    @FXML
    private TextField username;

    @FXML
    private Button inscriptionButton;

    private UserService userService = new UserService(); // Injection du service

    @FXML
    protected void adduser(ActionEvent event) {
//        // Récupération des données du formulaire
//        String nomText = nom.getText();
//        String prenomText = prenom.getText();
//        String emailText = email.getText();
//        String mdpText = mdp.getText();
//        boolean isClient = clientRadioButton.isSelected();
//        boolean isArtiste = artisteRadioButton.isSelected();
//        System.out.println("hhhhhhh" + nom.getText() + prenomText+prenomText+emailText+mdp.getText());
//

//        int tel = Integer.parseInt(numtel.getText());
//        String usernameText = username.getText();
//        System.out.println("hhhhhhh" + role);

        // Vérification de la saisie
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || mdp.getText().isEmpty() || email.getText().isEmpty() || username.getText().isEmpty()  ||numtel.getText().isEmpty()||
                (!clientRadioButton.isSelected() && !artisteRadioButton.isSelected())) {


            System.out.println("d5al");
            // Affichage de l'alerte d'erreur
//            showAlert(Alert.AlertType.ERROR,
//                    "Form Error!", "Veuillez remplir tous les champs!");}// Arrête l'exécution si des champs sont vides
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "rempliisez tous les champs");
        } else {
            String role = "";
            if (clientRadioButton.isSelected()) {
                role = "CLIENT";
                System.out.println("hhhhhhh" + role);

            } else if (artisteRadioButton.isSelected()) {
                role = "ARTIST";
                System.out.println("hhhhhhh" + role);

            }
            if (mdp.getText().length() < 8) {
                showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Le mot de passe doit contenir au moins 8 caractères.");
                return;
            }

            //Vérification de l'adresse e-mail avec une regex
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

            // Si tous les champs sont remplis et l'adresse e-mail est valide, appeler la méthode ajouterUser du service
            userService.ajouter4(new User(nom.getText(), prenom.getText(), username.getText(), mdp.getText(),
                    Role.valueOf(role), "", "", "", email.getText(), Integer.parseInt(numtel.getText())));

            // Afficher une confirmation (vous pouvez personnaliser le contenu de l'alerte)
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Utilisateur ajouté avec succès!");

            redirectToLoginPage(event);

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
