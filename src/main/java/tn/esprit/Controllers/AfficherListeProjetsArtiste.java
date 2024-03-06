package tn.esprit.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AfficherListeProjetsArtiste  {
    public Button checherBTN;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grid;
    @FXML
    private Label labelFX;
    private List<Projet> projets;

    @FXML
    void initialize() {
        ProjetServices projetServices = new ProjetServices();
        int col=0;
        int row=1;
        try {

            projets = projetServices.afficher();
            projets = projets.stream()
                    .filter(Projet::getIsVisible)
                    .collect(Collectors.toList());

            for (int i=0;i<projets.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardProjetArtiste.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CardProjetArtiste cardController = fxmlLoader.getController();
                int id= projets.get(i).getId();
                cardController.setData(projets.get(i),id);
                if(col==1){
                    col=0;
                    row++;
                }
                grid.add(anchorPane,col++,row);
                //Set projet grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //Set projet grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane,new Insets(25));
            }
        } catch (SQLException | IOException e  ) {
            System.err.println(e.getMessage());
        }
    }
    public void redirectVersProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
            Parent root = loader.load();
            AfficherListeProjetsArtiste controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterProjet(ActionEvent event) {
    }

    public void redirectVersAcceuil(ActionEvent event) {
    }

    public void redirectVersAfficherProjets(ActionEvent event) {
    }

    public void chercherProjet(ActionEvent event) {
    }
}
