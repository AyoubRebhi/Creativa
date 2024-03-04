package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class InterfaceUser implements Initializable {
    @FXML
    private Button AfficherBTN;

    @FXML
    private Button ajouterBTN;

    @FXML
    private Label label;
    @FXML
    private Label notificationLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    void AfficherC(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCommandeUser.fxml"));
        try {
            Parent root = loader.load();
            AfficherCommandeUser controller = loader.getController();
            ajouterBTN.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AjouterC(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AjouterCommande.fxml"));
        try {
            Parent root = loader.load();
            AjouterCommande controller = loader.getController();
            ajouterBTN.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void notificationBTN(ActionEvent event) {
        Notifications.create()
                .title("Notification")
                .text("Un nouveau code promo a été ajouté !")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .show();
    }



}

