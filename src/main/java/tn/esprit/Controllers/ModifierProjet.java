package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;
import tn.esprit.test.HelloApplication;

public class ModifierProjet {
    private int id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ChoiceBox<String> categorieChoiceBox;

    @FXML
    private TextField descriptionTF;

    @FXML
    private Label labelFX;

    @FXML
    private TextField prixTF;

    @FXML
    private TextField titreTF;
    private ListView<String> listView;
    private Projet projet;

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    @FXML
    void afficherProjets(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AfficherListeProjetsArtiste.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    @FXML
    void modifierProjet(ActionEvent event) {
        Projet nouveauProjet = getProjet();
        ProjetServices projetServices = new ProjetServices();
        projetServices.modifier(nouveauProjet);
        showAlert("Succès", "Le projet a été modifié avec succès.");
        refreshProjetsList();

    }
    public void refreshProjetsList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeProjetsArtiste.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titreTF.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void setParametre(int id,Projet projet){
        this.projet=projet;
        this.id=id;
        ProjetServices projetServices = new ProjetServices();
        List<String> titresCategories = projetServices.afficherTitresCategories();
        categorieChoiceBox.setItems(FXCollections.observableArrayList(titresCategories));
        titreTF.setText( projet.getTitre());
        descriptionTF.setText( projet.getDescription());
        prixTF.setText(String.valueOf(projet.getPrix()));
        categorieChoiceBox.getSelectionModel().select(projet.getCategorie());
    }

    private Projet getProjet(){
        String nouveauTitre = titreTF.getText();
        String nouvelleDescription = descriptionTF.getText();
        double nouveauPrix = Double.parseDouble(prixTF.getText());
        int idCategorie = categorieChoiceBox.getSelectionModel().getSelectedIndex() + 1;
        return new Projet(id, nouveauTitre, nouvelleDescription, null, nouveauPrix, idCategorie);
    }

    public void redirectVersAcceuil(ActionEvent event) {
    }

    public void redirectVersAfficherProjets(ActionEvent event) {
    }
}
