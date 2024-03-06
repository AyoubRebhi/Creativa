package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.Models.Codepromo;
import tn.esprit.Services.ServiceCodepromo;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterCodepromo {
    @FXML
    private Button ajouterBTN;

    @FXML
    private TextField codepromoTF;

    @FXML
    private Label label;

    @FXML
    private TextField pourcentageTF;


    @FXML
    void ajouter(ActionEvent event) {
        // Vérification du format du code promo
        String codePromoText = codepromoTF.getText();
        if (!codePromoText.matches("\\d{4}")) { // Vérifie si le code promo est composé de 4 chiffres
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un code promo composé de 4 chiffres !");
            alert.showAndWait();
            return;
        }

        // Le code promo est valide, vous pouvez maintenant vérifier s'il existe déjà
        int codePromo = Integer.parseInt(codePromoText);

        // Vérifier si le code promo existe déjà dans la base de données
        ServiceCodepromo serviceCodepromo = new ServiceCodepromo();
        if (serviceCodepromo.codePromoExiste(String.valueOf(codePromo))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Le code promo saisi existe déjà !");
            alert.showAndWait();
            return;
        }

        // Le code promo n'existe pas encore, vous pouvez l'ajouter
        String pourcentage = pourcentageTF.getText();
        Codepromo c = new Codepromo();
        c.setCode_promo(codePromo);
        c.setPourcentage(pourcentage);

        // Ajout du code promo
        serviceCodepromo.ajouter(c);

        // Affichage d'une confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("Code promo ajouté avec succès !");
        alert.showAndWait();

        // Réinitialisation des champs et mise à jour des notifications
        notificationBTN(null);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Time.fxml"));
            Parent root = loader.load();
            Time timeController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void notificationBTN(ActionEvent event) {
        // Créer une instance de ServiceCodepromo
        ServiceCodepromo serviceCodepromo = new ServiceCodepromo();

        // Récupérer le dernier code promo
        Codepromo dernierCodePromo = serviceCodepromo.lastCodePromo();

        // Construire le texte de la notification en fonction du dernier code promo
        String texteNotification = "Un nouveau code promo a été ajouté";
        if (dernierCodePromo != null) {
            texteNotification += ": " + dernierCodePromo.getCode_promo() + ", " + dernierCodePromo.getPourcentage();
        }

        // Afficher la notification
        Notifications.create()
                .title("Notification")
                .text(texteNotification)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .show();
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCodepromo.fxml"));
        try {
            pourcentageTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}