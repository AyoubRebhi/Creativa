package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class CommandeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label nbProjetLabel;

    @FXML
    private Label titreCatLabel;
    private Commande commande;
    private ServiceCommande cs;

    @FXML
    private TextField code_promoTF;

    @FXML
    private TextField dateLivTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField id_cmdTF;
    @FXML
    private Label prixProduitLabel;
    @FXML
    private Label fraisLivLabel;
    @FXML
    private Button PasserLiv;

    @FXML
    private Label modifier_commandes;

    @FXML
    private TextField mt_totalTF;

    @FXML
    private Button retourBTN;

    @FXML
    private TextField statusTF;

    @FXML
    private DatePicker datePicker;

    @FXML
    private DatePicker datePicker2;


    public void setParametre(Commande commande){
        this.cs = new ServiceCommande(); // Initialisation de cs

        this.commande = commande;
        id_cmdTF.setText(String.valueOf(commande.getId_cmd()));
        id_userTF.setText(String.valueOf(commande.getId_user()));
        dateTF.setText(String.valueOf(commande.getDate()));
        mt_totalTF.setText(String.valueOf(commande.getMt_total()));
        dateLivTF.setText(String.valueOf(commande.getDate_livraison_estimee()));
        code_promoTF.setText(String.valueOf(commande.getCode_promo()));
        statusTF.setText(String.valueOf(commande.getStatus()));
        prixProduitLabel.setText(String.valueOf(commande.getPrix_produit()));
        fraisLivLabel.setText(String.valueOf(commande.getFrais_livraison()));

    }
}
