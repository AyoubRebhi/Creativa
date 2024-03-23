package tn.esprit.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;

public class CardCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ImageView imageCatLabel;

    @FXML
    private Label nbProjetLabel;

    @FXML
    private Label titreCatLabel;
    private Categorie categorie;
    private CategorieServices cs;
    public CardCategorie() {
        this.cs = new CategorieServices(); // Initialisation de cs
    }
    public void setParametre(Categorie categorie,int id){
        this.categorie = categorie;
        titreCatLabel.setText(categorie.getTitre());
        Map<String, Integer> nbProjetsParCategorie = cs.calculerNbProjets(id);
        // Récupérer le nombre de projets à partir du Map
        int nbProjets = nbProjetsParCategorie.getOrDefault(categorie.getTitre(), 0);
        nbProjetLabel.setText(String.valueOf(nbProjets)+"Projets");
        String  imagePath = categorie.getCategorieImage();
        if(imagePath !=null && !imagePath.isEmpty()){
            File file = new File(imagePath);
            Image image;
            if (file != null && file.exists()) {
                // If the file is not null and exists, load the image from the file
                image = new Image(file.toURI().toString());
            } else {
                // If the file is null or does not exist, load a default image
                image = new Image(getClass().getResourceAsStream("/images/imageVideIcon.png"));
            }
            imageCatLabel.setImage(image);
        }else {
            imageCatLabel.setImage(new Image(getClass().getResourceAsStream("/images/imageVideIcon.png")));
        }
    }

    public void modifierCategorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCategorieAdmin.fxml"));
            Parent root = loader.load();
            ModifierCategorie controller = loader.getController();
            controller.setParametre(categorie.getId_categorie(), categorie);
            titreCatLabel.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void supprimerCategorie(ActionEvent event) {
    }
}
