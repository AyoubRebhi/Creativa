package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.Models.session;

public class sidebarAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label labelFX;

    @FXML
    void redirectVersAfficherCategories(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarAdminCategories.fxml"));
            Parent root = loader.load();
            sidebarAdmin controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void redirectVersAfficherProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarAdminProjets.fxml"));
            Parent root = loader.load();
            sidebarAdmin controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {

    }

    public void redirectVersAcceuil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarAdmin.fxml"));
            Parent root = loader.load();
            sidebarAdmin controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void diectutuilisateur(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/listUserOriginale.fxml"));
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

    public void ProfileClient(MouseEvent mouseEvent) {



    }
}

