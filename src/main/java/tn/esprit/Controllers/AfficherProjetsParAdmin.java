package tn.esprit.Controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;

public class AfficherProjetsParAdmin {



    @FXML
    private Label labelFX;

    @FXML
    private ListView<String> listView;


    @FXML
    private ImageView refreshICON;


    private List<Projet> projets;


    @FXML
    void changerVisibilte(ActionEvent event) throws SQLException {
        String selectedProjet = listView.getSelectionModel().getSelectedItem();
        if (selectedProjet != null && !selectedProjet.isEmpty()) {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation de visibilité");
            confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir mettre ce projet invisible ?");
            confirmationDialog.initModality(Modality.APPLICATION_MODAL);

            // Ajouter les boutons Oui et Annuler
            confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            confirmationDialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.YES) {
                    // Modifier la visibilité du projet
                    String[] parts = selectedProjet.split(",");
                    if (parts.length > 0) {
                        int projetId = Integer.parseInt(parts[0].trim());
                        ProjetServices projetServices = new ProjetServices();
                        try {
                            projetServices.modifierVisibilite(projetId,false);
                            refreshProjetsList();
                            showAlert("Opération réussite", "La visibilité est modifiée avec succès.");
                        } catch (SQLException e) {
                            showAlert("Erreur", "Une erreur s'est produite lors de cette operation.");
                        }
                    }
                }
            });
        } else {
            showAlert("Erreur", "Veuillez sélectionner un projet.");
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
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

            projets = projetServices.afficher();
            projets = projets.stream().filter(Projet::getIsVisible).collect(Collectors.toList());
            ObservableList<String> observableList = FXCollections.observableArrayList();
            String idProjet = "ID,                          ";
            String titreProjet = "Titre,                                      ";
            String descriptionProjet = "Description,                            ";
            String categorie = "Categorie,       ";
            String prixProjet = "Prix,     ";
            observableList.add(idProjet + titreProjet + descriptionProjet + categorie + prixProjet);
            for (Projet projet : projets) {
                // Vérifier si le projet est visible
                if (projet.getIsVisible()) {
                    int id = projet.getId();
                    String titre = minDescription(projet.getTitre());
                    String description = minDescription(projet.getDescription());
                    int idCat = projet.getCategorie();
                    String cat = projetServices.afficherTitreCategorie(idCat);
                    double prix = projet.getPrix();
                    observableList.add(id + ",               " + titre + ",                 " + description + ",              " + cat + ",           " + prix);
                }
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
