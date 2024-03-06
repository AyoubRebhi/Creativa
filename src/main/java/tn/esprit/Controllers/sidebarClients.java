package tn.esprit.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

public class sidebarClients {


    @FXML
    private Label labelFX;
    private List<Projet> projets;
    @FXML
    private ListView<String> listView;
    private ProjetServices ps = new ProjetServices();


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

    public void redirectVersProjets(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarClient.fxml"));
            Parent root = loader.load();
            sidebarClients controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficherProjet(ActionEvent event) {
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun projet sélectionné");
            alert.setContentText("Veuillez sélectionnez un projet à modifier");
            alert.showAndWait();
            return;
        }
        String  selectedProjet = listView.getSelectionModel().getSelectedItem();
        String[] parts = selectedProjet.split(",");
        int projetId = Integer.parseInt(parts[0].trim());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUnProjetParIdClient.fxml"));

        try {
            System.out.println("test");
            Parent root = loader.load();
            AfficherUnProjetParIdClient controller = loader.getController();
            System.out.println("test");
            controller.setParametre(projetId,ps.afficherProjetParId(projetId));
            System.out.println("test");
            labelFX.getScene().setRoot(root);
            System.out.println("test");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
