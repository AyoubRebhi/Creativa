package tn.esprit.Controllers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;

public class CategorieController {

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
    public CategorieController() {
        this.cs = new CategorieServices(); // Initialisation de cs
    }
    public void setParametre(Categorie categorie,int id){
        this.categorie = categorie;
        titreCatLabel.setText(categorie.getTitre());
        Map<String, Integer> nbProjetsParCategorie = cs.calculerNbProjets(id);
        // Récupérer le nombre de projets à partir du Map
        int nbProjets = nbProjetsParCategorie.getOrDefault(categorie.getTitre(), 0);
        nbProjetLabel.setText(String.valueOf(nbProjets)+"Projets");
        Image image = new Image(getClass().getResourceAsStream(categorie.getCategorieImage()));
        imageCatLabel.setImage(image);
    }

}
