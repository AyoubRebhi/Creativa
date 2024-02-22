package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import tn.esprit.Models.session;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class  LoginController {




    @FXML
    private TextField loginId;

    @FXML
    private PasswordField motpasse;

    @FXML
    private Button button;




    @FXML
    private void initialize() {
        // Initialisation du contrôleur (peut être utilisé pour effectuer des actions lors du chargement du FXML)
    }

    @FXML
    private void submit(ActionEvent event) throws SQLException {
        // Récupérer les valeurs des champs

        System.out.println("hh");
        if (loginId.getText().isEmpty() || motpasse.getText().isEmpty()) {
            motpasse.getScene().getWindow();
            System.out.println("u should type something");
        }
        UserService S=new UserService();

        // Appeler la fonction de vérification dans AuthentificationManager
        boolean authentifie = S.verifierUtilisateur(loginId.getText(), motpasse.getText());
        System.out.println(" connectéé"+authentifie);
        // Ajouter le reste de la logique selon le résultat de l'authentification
        if (authentifie) {
            // Authentification réussie, charger la nouvelle scène
            int p = S.getUtilisateurid(loginId.getText(), motpasse.getText());
            System.out.println("hhh"+S.getUtilisateurid(loginId.getText(), motpasse.getText()));
            session.id_utilisateur=p;
            System.out.println("hajer connectéé" +p);
            redirectToLoginPage(event);

        } else {
            // L'authentification a échoué, afficher une alerte d'erreur
            afficherAlerteErreur("Authentification échouée", "Veuillez vérifier vos identifiants.");
        }

    }



    private void redirectToLoginPage(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CarteList.fxml"));
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
        private void afficherAlerteErreur(String titre, String contenu) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle(titre);
            alerte.setHeaderText(null);
            alerte.setContentText(contenu);
            alerte.showAndWait();
        }



//    @FXML
//    private void Inscriptionlink(ActionEvent event) {
//        try {
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("Inscription.fxml"));
//            Parent root =loader.load();
//            email.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void chargerNouvelleScene(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la scène actuelle (si nécessaire)
            Stage currentStage = (Stage) button.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void redirect(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/inscription.fxml"));
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

    public void redirectto(ActionEvent actionEvent) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/inscription.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }
    }
}
