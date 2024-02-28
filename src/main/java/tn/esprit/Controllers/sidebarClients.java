package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import tn.esprit.Models.Projet;

public class sidebarClients {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox cardLayoout;

    private List<Projet> recentlyAdded;

    @FXML
    void initialize() {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
            for (int i = 0; i < recentlyAdded().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));
                cardLayoout.getChildren().add(cardBox);
            }
        }catch (IOException e) {
                throw new RuntimeException(e);

        }
    }
    private List<Projet> recentlyAdded(){
        List<Projet> ls = new ArrayList<>();
        Projet projet = new Projet();
        projet.setTitre("Projet 1");
        projet.setDescription("Description du projet 1 ");
        projet.setMedia("/img/projet1Test");
        ls.add(projet);

        return ls;
    }
}
