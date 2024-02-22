package tn.esprit.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

public class AfficherProjets {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AjouterBTN;

    @FXML
    private Label labelFX;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button suppBTN;
    @FXML
    private ImageView refreshICON;

    @FXML
    private Button updateBTN;
    private List<Projet> projets;

    @FXML
    void ajouterProjet(ActionEvent event) {

    }

    @FXML
    void modifierProjet(ActionEvent event) {

    }

    @FXML
    void supprimerProjet(ActionEvent event) {

    }

    @FXML
    void initialize() {
        DropShadow shadow = new DropShadow();
        refreshICON.setOnMouseEntered(event -> {
            refreshICON.setEffect(shadow);
        });

        // Retirer l'effet d'ombre lorsque la souris quitte l'ImageView
        refreshICON.setOnMouseExited(event -> {
            refreshICON.setEffect(null);
        });

        // Rafraîchir l'affichage lorsqu'on clique sur l'ImageView
        refreshICON.setOnMouseClicked(event -> {
            // Rafraîchir l'affichage en rappelant la méthode initialize()
            initialize();
        });

        ProjetServices projetServices = new ProjetServices();
        try{
            projets = projetServices.afficher();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            String idProjet ="ID,                          ";
            String titreProjet ="Titre,                                      ";
            String descriptionProjet ="Description,                            ";
            String categorie ="Categorie,       ";
            String prixProjet="Prix,     ";
            observableList.add(idProjet+titreProjet+descriptionProjet+categorie+prixProjet);
            for(Projet projet : projets){
                int id = projet.getId();
                String titre = minDescription(projet.getTitre());
                String description = minDescription(projet.getDescription());
                int idCat = projet.getCategorie();
                String cat = projetServices.afficherTitreCategorie(idCat);
                double prix = projet.getPrix();
                observableList.add(id + ",               "+titre+",                 "+description +",              "+ cat + ",           "+ prix);
            }
            listView.setItems(observableList);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private String minDescription(String description) {
        int length = description.length();
        if (length < 22) {
            StringBuilder stringBuilder = new StringBuilder(description);
            int spacesToAdd = 20 - length;
            for (int i = 0; i < spacesToAdd; i++) {
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        } else {
            return description.substring(0, 20) + "...";
        }
    }

}
