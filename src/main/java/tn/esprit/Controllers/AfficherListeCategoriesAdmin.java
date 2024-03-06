package tn.esprit.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import tn.esprit.Models.Categorie;
import tn.esprit.Models.Projet;
import tn.esprit.Services.CategorieServices;
import tn.esprit.Services.ProjetServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AfficherListeCategoriesAdmin {

    @FXML
    private Label labelFX;
    @FXML
    private AnchorPane anchorContainerID;
    @FXML
    private GridPane grid;
    @FXML
    private RadioButton rButton1;

    @FXML
    private RadioButton rButton2;
    @FXML
    private ToggleGroup filtrage;

    @FXML
    private ScrollPane scroll;
    private List<Categorie> categories;
    private List<Categorie> categoriesFiltres;

    @FXML
    void initialize() {
        CategorieServices categorieServices = new CategorieServices();
        int col=0;
        int row=1;
        try {
            categories = categorieServices.afficher();
            for (int i=0;i<categories.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardCategorie.fxml"));
                this.anchorContainerID = fxmlLoader.load();
                CardCategorie categorieController = fxmlLoader.getController();
                int id=categories.get(i).getId_categorie();
                categorieController.setParametre(categories.get(i),id);
                if (col==3){
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

    public void ajouterCategorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategorieAdmin.fxml"));
        try{
            Parent root = loader.load();
            AjouterCategorie controller = loader.getController();
            labelFX.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void getFiltre(ActionEvent event){
        CategorieServices categorieServices = new CategorieServices();
        if(rButton1.isSelected()){
            categoriesFiltres = categorieServices.afficheCategorieParNbProjet();
        } else if (rButton2.isSelected()) {
            categoriesFiltres = categorieServices.afficherCategorieParTitre();
        }
        int col=0;
        int row=1;
        try {
            categories = categorieServices.afficher();
            for (int i=0;i<categories.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardCategorie.fxml"));
                this.anchorContainerID = fxmlLoader.load();
                CardCategorie categorieController = fxmlLoader.getController();
                int id=categories.get(i).getId_categorie();
                categorieController.setParametre(categories.get(i),id);
                if (col==3){
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
