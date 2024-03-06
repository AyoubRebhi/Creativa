package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class sidebarArtiste {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label labelFX;


    @FXML
    void redirectVersAfficherProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
            Parent root = loader.load();
            sidebarArtiste controller = loader.getController();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarArtiste.fxml"));
            Parent root = loader.load();
            sidebarArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFiltre(ActionEvent event) {

    }

    public void ajouterProjet(ActionEvent event) {
    }
}
