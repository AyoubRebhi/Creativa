package tn.esprit.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Models.User;
import tn.esprit.Models.session;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AfficherListeProjetsAdmin {

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
            //projets = projets.stream()
              //      .filter(Projet::getIsVisible)
                //    .collect(Collectors.toList());

            for (int i=0;i<projets.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardProjetAdmin.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CardProjetAdmin cardController = fxmlLoader.getController();
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
        updateLabels();
        ProjetServices projetServices = new ProjetServices();
        int col=0;
        int row=1;


            projets = projetServices.afficher();
            //projets = projets.stream()
            //      .filter(Projet::getIsVisible)
            //      .collect(Collectors.toList());

            for (int i=0;i<projets.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardProjetAdmin.fxml"));
                AnchorPane anchorPane = null;
                try {
                    anchorPane = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                CardProjetAdmin cardController = fxmlLoader.getController();
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

    }


    public void redirectVersAfficherCategories(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeCategoriesAdmin.fxml"));
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

    public void redirectVersAfficherProjets(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsAdmin.fxml"));
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

    @FXML
    private Label name;
    UserService userService=new UserService();


    private void updateLabels() {
        User utilisateur = userService.getById(session.id_utilisateur);


        // Mettez à jour les labels avec les données de l'utilisateur
        name.setText(utilisateur.getUsername());



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
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileAdmin.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }

    }
    public void directTocommande(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceAdmin.fxml"));
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
    public void directToLogin(ActionEvent event) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/log.fxml"));
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
        session.id_utilisateur = 0; // Assurez-vous de réinitialiser la session ou l'utilisateur approprié

    }

}
