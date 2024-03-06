package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.Models.Codepromo;
import tn.esprit.Services.ServiceCodepromo;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCodepromo implements Initializable {
    @FXML
    private Button retourBTN;
    @FXML
    private Label labelFX;

    @FXML
    private ListView<Codepromo> listView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCodepromo serviceCodepromo = new ServiceCodepromo();
        List<Codepromo> l = null;
        try {
            l = serviceCodepromo.afficher();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(l);
        listView.getItems().addAll(l);

    }
    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceAdmin.fxml"));
        try {
            listView.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

}

    public void ajoutercode(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCodepromo.fxml"));
        try {
            labelFX.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
