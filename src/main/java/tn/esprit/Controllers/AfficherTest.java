package tn.esprit.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherTest implements Initializable {
    @FXML
    private Button modifierBTN;

    @FXML
    private HBox EMPHBox;

    @FXML
    private ComboBox<?> criteriaComBox;

    @FXML
    private TextField searchBarEvents;

    @FXML
    private Button sortButton;

    @FXML
    private ComboBox<?> sortOrderComBox;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;
    @FXML
    private TextField prix1;

    @FXML
    private TextField frais;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date222;

    @FXML
    private TextField id_projet;

    @FXML
    private TextField id_user;

    @FXML
    private TextField id_cmd;

    @FXML
    private ListView<Commande> listView;

    private final ServiceCommande SR = new ServiceCommande();
    private ObservableList<String> types;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        types = FXCollections.observableArrayList();

        try {
            List<Commande> commandes = SR.afficher();
            for (Commande commande : commandes) {
                VBox card = createCardView(commande);
                EMPHBox.getChildren().add(card);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des commandes depuis la base de données : " + e.getMessage());
        }
    }

    private VBox createCardView(Commande commande) {
        VBox card = new VBox();
        card.getStyleClass().add("card-view");

        String date = commande.getDate().toString();
        String dateLivraisonEstimee = commande.getDate_livraison_estimee() != null ? commande.getDate_livraison_estimee().toString() : "Non définie";

        String mtTotalStr = commande.getMt_total();
        double montantTotal;
        if (mtTotalStr != null) {
            try {
                mtTotalStr = mtTotalStr.replaceAll("[^\\d.]", "");
                montantTotal = Double.parseDouble(mtTotalStr);
            } catch (NumberFormatException e) {
                montantTotal = 0.0;
            }
        } else {
            montantTotal = 0.0;
        }

        Double prixProduit = Double.valueOf(commande.getPrix_produit()); // Prix du produit peut être null
        double prixProduitValue = prixProduit != null ? prixProduit.floatValue() : 0.0; // Défaut à 0.0 si null

        int codePromo = commande.getCode_promo();
        String status = commande.getStatus();
        float prix = commande.getPrix_produit();
        float fraisLivraison= commande.getFrais_livraison();

        Label dateLabel = new Label("Date de la commande: " + date);
        Label dateLivraisonEstimeeLabel = new Label("Date de livraison estimée: " + dateLivraisonEstimee);
        Label presenterLabel3 = new Label("Montant total: " + montantTotal);
        Label presenterLabel4 = new Label("Prix produit: " + prixProduitValue); // Utilisation de prixProduitValue
        Label presenterLabel5 = new Label("Frais de livraison: " + fraisLivraison);
        Label presenterLabel6 = new Label("Code promo: " + codePromo);
        Label presenterLabel7 = new Label("Statut: " + status);
        Label presenterLabel8 = new Label("Prix: " + prix);
        Label presenterLabel9 = new Label("Frais de livraison: " + fraisLivraison);

        card.getChildren().addAll(dateLabel, dateLivraisonEstimeeLabel, presenterLabel3, presenterLabel4, presenterLabel5, presenterLabel6, presenterLabel7, presenterLabel8, presenterLabel9);

        card.setSpacing(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-color: #ffffff;");
        card.setMinWidth(Control.USE_PREF_SIZE);
        card.setMaxWidth(Control.USE_PREF_SIZE);

        double finalMontantTotal = montantTotal;
        card.setOnMouseClicked(event -> {
            date1.setValue(LocalDate.parse(commande.getDate().toString()));
            date222.setValue(LocalDate.parse(dateLivraisonEstimee));
            text1.setText(String.valueOf(finalMontantTotal));
            text2.setText(String.valueOf(codePromo));
            text3.setText(status);
            id_user.setText(String.valueOf(commande.getId_user()));
            id_projet.setText(String.valueOf(commande.getId_projet()));
            prix1.setText(String.valueOf(prixProduitValue));
            frais.setText(String.valueOf(fraisLivraison));
            id_cmd.setText(String.valueOf(commande.getId_cmd()));
        });
        return card;
    }


    @FXML
    void ModifyResUserBtn(ActionEvent event) {
        try {
            int idUser = Integer.parseInt(id_user.getText());
            int idProjet = Integer.parseInt(id_projet.getText());

            String mtTotal = text1.getText();
            int codePromo = Integer.parseInt(text2.getText());
            String status = text3.getText();
            LocalDate date = date1.getValue();
            LocalDate dateLivraisonEstimee = date222.getValue();

            float prix = 0.0f; // Initialisation du prix
            float fraisLivraison = 0.0f; // Initialisation du frais de livraison

            // Vérification et conversion du prix
            if (!prix1.getText().isEmpty()) {
                prix = Float.parseFloat(prix1.getText());
            }

            // Vérification et conversion des frais de livraison
            if (!frais.getText().isEmpty()) {
                fraisLivraison = Float.parseFloat(frais.getText());
            }

            Commande newCommande = new Commande();
            newCommande.setMt_total(mtTotal);
            newCommande.setCode_promo(codePromo);
            newCommande.setStatus(status);
            newCommande.setDate(java.sql.Date.valueOf(date));
            newCommande.setDate_livraison_estimee(java.sql.Date.valueOf(dateLivraisonEstimee));
            newCommande.setId_user(idUser);
            newCommande.setId_projet(idProjet);
            newCommande.setPrix_produit(prix);
            newCommande.setFrais_livraison(fraisLivraison);
            newCommande.setId_cmd(Integer.parseInt(id_cmd.getText()));
            System.out.println(newCommande);

            SR.modifier(newCommande);

            EMPHBox.getChildren().clear(); // Effacer les anciennes cartes
            List<Commande> commandes = SR.afficher(); // Récupérer les nouvelles commandes
            for (Commande commande : commandes) {
                VBox card = createCardView(commande); // Créer une nouvelle carte pour chaque commande
                EMPHBox.getChildren().add(card); // Ajouter la carte à la HBox
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion en float échoue
            System.err.println("Erreur de format pour le prix ou les frais de livraison : " + e.getMessage());
        }
    }



    @FXML
    void backToBackBtn1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
