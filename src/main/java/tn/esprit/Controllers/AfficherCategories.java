package tn.esprit.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCategories {

    @FXML
    private ListView<String> listView;
    @FXML
    private Label labelFX;
    private List<Categorie> categories;


    @FXML
    void initialize() {
        CategorieServices categorieServices = new CategorieServices();
        try {
            categories = categorieServices.afficher();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            String idName = "ID,     ";
            String categorieName = "Categorie,     ";
            String nbProjetName = "Nombre de projets, ";
            observableList.add(idName + categorieName + nbProjetName);
            for (Categorie categorie : categories) {
                int idCategorie = categorie.getId_categorie();
                String titreCategorie = categorie.getTitre();
                int nbProjets = categorieServices.calculerNbProjets(idCategorie).get(titreCategorie);
                observableList.add(idCategorie + ",       " + titreCategorie + ",                " + nbProjets);
            }
            listView.setItems(observableList);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    CategorieServices cs = new CategorieServices();

    public void modifierCategorie(ActionEvent event) {
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun projet sélectionné");
            alert.setContentText("Veuillez sélectionnez un projet à modifier");
            alert.showAndWait();
            return;
        }

        String  selectedCategorie = listView.getSelectionModel().getSelectedItem();
        String[] parts = selectedCategorie.split(",");
        int categorieId = Integer.parseInt(parts[0].trim());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCategorieAdmin.fxml"));
        try {
            Parent root = loader.load();
            ModifierCategorie controller = loader.getController();
            controller.setParametre(categorieId,cs.afficherParId(categorieId));
            labelFX.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
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
    public void supprimerCategorie(ActionEvent event) throws SQLException {
        String selectedCategorie = listView.getSelectionModel().getSelectedItem();
        if (selectedCategorie != null && !selectedCategorie.isEmpty()) {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");
            confirmationDialog.initModality(Modality.APPLICATION_MODAL);

            // Ajouter les boutons Oui et Annuler
            confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            confirmationDialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.YES) {
                    // Supprimer la catégorie
                    String[] parts = selectedCategorie.split(",");
                    if (parts.length > 0) {
                        int categorieId = Integer.parseInt(parts[0].trim());
                        CategorieServices categorieServices = new CategorieServices();
                        try {
                            categorieServices.supprimer(categorieId);
                            refreshCategoriesList();
                            showAlert("Suppression réussie", "La catégorie a été supprimée avec succès.");
                        } catch (SQLException e) {
                            showAlert("Erreur", "Une erreur s'est produite lors de la suppression de la catégorie.");
                        }
                    }
                }
            });
        } else {
            showAlert("Erreur", "Veuillez sélectionner une catégorie à supprimer.");
        }
    }
    public void refreshCategoriesList() throws SQLException {
        CategorieServices categorieServices = new CategorieServices();
        categories.clear();
        categories.addAll(categorieServices.afficher());
        ObservableList<String> observableList = FXCollections.observableArrayList();
        String idName = "ID,     ";
        String categorieName = "Categorie,     ";
        String nbProjetName = "Nombre de projets, ";
        observableList.add(idName + categorieName + nbProjetName);
        for (Categorie categorie : categories) {
            int idCategorie = categorie.getId_categorie();
            String titreCategorie = categorie.getTitre();
            int nbProjets = categorieServices.calculerNbProjets(idCategorie).get(titreCategorie);
            observableList.add(idCategorie + ",       " + titreCategorie + ",                " + nbProjets);
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


}
