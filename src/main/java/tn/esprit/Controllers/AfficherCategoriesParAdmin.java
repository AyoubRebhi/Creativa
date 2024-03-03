package tn.esprit.Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherCategoriesParAdmin  {

    @FXML
    private Label labelFX;
    @FXML
    private AnchorPane anchorContainerID;
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
    private List<Categorie> categories;
    @FXML
    void initialize() {
        CategorieServices categorieServices = new CategorieServices();
        int col=0;
        int row=1;
        try {
            categories = categorieServices.afficher();
            for (int i=0;i<categories.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Categorie.fxml"));
                this.anchorContainerID = fxmlLoader.load();
                CategorieController categorieController = fxmlLoader.getController();
                int id=categories.get(i).getId_categorie();
                categorieController.setParametre(categories.get(i),id);
                if (col==2){
                    col=0;
                    row++;
                }
                grid.add(anchorContainerID,col++,row);
                //Set Categorie grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //Set Categorie grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorContainerID,new Insets(10));


            }

        } catch (SQLException | IOException e  ) {
            System.err.println(e.getMessage());
        }
    }
}
