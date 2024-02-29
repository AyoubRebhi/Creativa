package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

public class sidebarClients {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox cardLayoout;
    private List<Projet> projets;
    @FXML
    private ListView<String> listView;


    @FXML
    void initialize() {

        ProjetServices projetServices = new ProjetServices();
        try {
            projets = projetServices.afficher();
            projets = projets.stream().filter(Projet::getIsVisible).collect(Collectors.toList());
            ObservableList<String> observableList = FXCollections.observableArrayList();
            String idProjet = "ID,                          ";
            String titreProjet = "Titre,                                      ";
            String descriptionProjet = "Description,                            ";
            String categorie = "Categorie,       ";
            String prixProjet = "Prix,     ";
            String nbJaimes = "Jaimes,    ";
            observableList.add(idProjet + titreProjet + descriptionProjet + categorie + prixProjet + nbJaimes);
            for (Projet projet : projets) {
                // Vérifier si le projet est visible
                if (projet.getIsVisible()) {
                    int id = projet.getId();
                    String titre = minDescription(projet.getTitre());
                    String description = minDescription(projet.getDescription());
                    int idCat = projet.getCategorie();
                    String cat = projetServices.afficherTitreCategorie(idCat);
                    double prix = projet.getPrix();
                    int nbj = projet.getNombreJaime();
                    observableList.add(id + ",               " + titre + ",                 " + description + ",              " + cat + ",           " + prix+",       "+nbj);
                }
            }
            listView.setItems(observableList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void refreshProjetsList() throws SQLException {
        ProjetServices projetServices = new ProjetServices();
        projets.clear();
        projets.addAll(projetServices.afficher());
        ObservableList<String> observableList = FXCollections.observableArrayList();
        String idProjet = "ID,                          ";
        String titreProjet = "Titre,                                      ";
        String descriptionProjet = "Description,                            ";
        String categorie = "Categorie,       ";
        String prixProjet = "Prix,     ";
        observableList.add(idProjet + titreProjet + descriptionProjet + categorie + prixProjet);
        for (Projet projet : projets) {
            int id = projet.getId();
            String titre = minDescription(projet.getTitre());
            String description = minDescription(projet.getDescription());
            int idCat = projet.getCategorie();
            String cat = projetServices.afficherTitreCategorie(idCat);
            double prix = projet.getPrix();
            observableList.add(id + ",               " + titre + ",                 " + description + ",              " + cat + ",           " + prix);
        }
        listView.setItems(observableList);
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
