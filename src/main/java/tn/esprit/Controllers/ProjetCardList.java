package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProjetCardList {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grid;
    @FXML
    private Label labelFX;
    private List<Projet> projets;
    @FXML
    private RadioButton rButton1;

    @FXML
    private RadioButton rButton2;

    @FXML
    private RadioButton rButton3;
    @FXML
    private ToggleGroup filtrage;

    private List<Projet> projetsFiltres;

    public void getFiltre(ActionEvent event){
        ProjetServices projetServices = new ProjetServices();
        if(rButton1.isSelected()){
            projetsFiltres = projetServices.afficherProjetParNbJaime();
        } else if (rButton2.isSelected()) {
            projetsFiltres = projetServices.afficherProjetParDateCreation();
        } else if (rButton3.isSelected()) {
            projetsFiltres = projetServices.afficherProjetParPrix();
        }
        int col=0;
        int row=1;
        try {

            projets = projetsFiltres;
            projets = projets.stream()
                    .filter(Projet::getIsVisible)
                    .collect(Collectors.toList());

            for (int i=0;i<projets.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ProjetCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ProjetCard cardController = fxmlLoader.getController();
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
        } catch (IOException e  ) {
            System.err.println(e.getMessage());
        }
    }

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
               fxmlLoader.setLocation(getClass().getResource("/ProjetCard.fxml"));
               AnchorPane anchorPane = fxmlLoader.load();
               ProjetCard cardController = fxmlLoader.getController();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjetCardList.fxml"));
            Parent root = loader.load();
            ProjetCardList controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
