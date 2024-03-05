/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.maytest.Controllers;

import tn.esprit.maytest.Models.Formation;
import tn.esprit.maytest.Services.ServiceFormation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 */
public class AjoutFormationController implements Initializable {

    @FXML
    private ImageView iv_gallerie;
    @FXML
    private Button id_add_im;
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_places;
    @FXML
    private TextField tf_prix;
    @FXML
    private Button id_add_f;

    private Formation postToAdd = new Formation();
    private File selectedFile;
    @FXML
    private Label label_titre_control_saisir;
    @FXML
    private Label label_description_control_saisir;
    @FXML
    private Label label_titre_nb_place_saisir;
    @FXML
    private Label label_prix_place_saisir;
    @FXML
    private Button id_abondonner;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ImageView imageView2 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/add.png").toExternalForm());

        id_add_f.setGraphic(imageView2);
         ImageView imageView1 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/telecharge.png").toExternalForm());
        id_add_im.setGraphic(imageView1);
    }    

    @FXML
    private void AddImage(ActionEvent event) {
            Stage s = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image files", "*.jpg", "*.png", "*.jpeg", "*.JPEG"));
        
        selectedFile = fc.showOpenDialog(s);
       
        String imgPath = selectedFile.getAbsoluteFile().getPath().toString();
        postToAdd.setMedia(selectedFile.getAbsoluteFile().getName().toString());

        Image img = new Image(selectedFile.toURI().toString());

        iv_gallerie.setImage(img);
    }

    
private void copyImageToServer(File selectedFile) throws IOException {
    String to = "C:\\Users\\mahmo\\Documents\\NetBeansProjects\\Gestion_formation\\src\\images\\" + selectedFile.getName();
    System.out.println("Copying image from: " + selectedFile.getAbsolutePath());
    System.out.println("Copying image to: " + to);

    Path src = selectedFile.toPath();
    Path dest = Paths.get(to);
    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);

    System.out.println("image copied successfully.");
}

    
    
  @FXML
private void add_for(ActionEvent event) throws IOException {
    ServiceFormation sf = new ServiceFormation();

    // Récupération des valeurs des champs
    String titre = tf_titre.getText();
    String description = tf_description.getText();
    String placesText = tf_places.getText();
    String prixText = tf_prix.getText();
    String media = "";

    // Vérification si une image a été sélectionnée
    if (selectedFile != null) {
        media = selectedFile.getName();

        try {
            copyImageToServer(selectedFile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    } else {
        // Afficher un message d'erreur si aucune image n'a été sélectionnée
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une image.");
        alert.showAndWait();
        return;
    }

    // Vérification si les champs obligatoires sont vides
    if (titre.isEmpty() || description.isEmpty() || placesText.isEmpty() || prixText.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs obligatoires.");
        alert.showAndWait();
        return;
    }

    // Vérification si les champs nb_places et prix sont des nombres valides
    int nbPlaces;
    double prix;
    try {
        nbPlaces = Integer.parseInt(placesText);
        prix = Double.parseDouble(prixText);
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir des valeurs numériques pour les champs 'Nombre de places' et 'Prix'.");
        alert.showAndWait();
        return;
    }

    // Création de l'objet Formation et ajout dans la base de données
    ServiceFormation postService = new ServiceFormation();
    Formation nouvelleFormation = new Formation();
    nouvelleFormation.setTitre(titre);
    nouvelleFormation.setDescription(description);
    nouvelleFormation.setNb_places(nbPlaces);
    nouvelleFormation.setPrix(prix);
    nouvelleFormation.setMedia(media);
    postService.ajouterFormation(nouvelleFormation);

    // Affichage d'une alerte de confirmation
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Post bien ajouté !");
    alert.showAndWait();
}

    @FXML
    private void abodonner(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/tn/esprit/maytest/AffichageFormation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
}
