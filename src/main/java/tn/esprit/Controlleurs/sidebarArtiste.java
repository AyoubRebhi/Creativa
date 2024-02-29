package tn.esprit.Controlleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.UserService;

public class sidebarArtiste {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Label name;
    @FXML
    private URL location;
    @FXML
    private Label labelFX;
    UserService userService=new UserService();

    // Méthode pour mettre à jour les labels avec les données du UserService
    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        name.setText(utilisateur.getUsername());



    }

    @FXML
    void redirectVersAfficherProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarArtisteProjets.fxml"));
            Parent root = loader.load();
            sidebarArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        updateLabels();
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

    public void redirectVersProfile(ActionEvent event) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileArtist.fxml"));
            Parent root = loader.load();
            sidebarArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
