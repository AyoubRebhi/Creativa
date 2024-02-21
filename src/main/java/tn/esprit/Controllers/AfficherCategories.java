package tn.esprit.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.Models.Categorie;
import tn.esprit.Models.Projet;

public class AfficherCategories {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Categorie, Integer> idCol;

    @FXML
    private TableColumn<Projet, Integer> nbProjetsCol;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<Categorie, String> titreCol;

    @FXML
    void initialize() {
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'AfficherCategories.fxml'.";
        assert nbProjetsCol != null : "fx:id=\"nbProjetsCol\" was not injected: check your FXML file 'AfficherCategories.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'AfficherCategories.fxml'.";
        assert titreCol != null : "fx:id=\"titreCol\" was not injected: check your FXML file 'AfficherCategories.fxml'.";

    }

}
