package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProjet.fxml"));
        try{
            Parent root = loader.load();
            AjouterProjet controller = loader.getController();
            labelFX.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void modifierProjet(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
        try {
            Parent root = loader.load();
            ModifierProjet controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void supprimerProjet(ActionEvent event) throws SQLException {
        String selectedProjet = listView.getSelectionModel().getSelectedItem();
        if (selectedProjet != null && !selectedProjet.isEmpty()) {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir supprimer ce projet ?");
            confirmationDialog.initModality(Modality.APPLICATION_MODAL);

            // Ajouter les boutons Oui et Annuler
            confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            confirmationDialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.YES) {
                    // Supprimer le projet
                    String[] parts = selectedProjet.split(",");
                    if (parts.length > 0) {
                        int projetId = Integer.parseInt(parts[0].trim());
                        ProjetServices projetServices = new ProjetServices();
                        try {
                            projetServices.supprimer(projetId);
                            refreshProjetsList();
                            showAlert("Suppression réussie", "Le projet a été supprimé avec succès.");
                        } catch (SQLException e) {
                            showAlert("Erreur", "Une erreur s'est produite lors de la suppression du projet.");
                        }
                    }
                }
            });
        } else {
            showAlert("Erreur", "Veuillez sélectionner un projet à supprimer.");
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
