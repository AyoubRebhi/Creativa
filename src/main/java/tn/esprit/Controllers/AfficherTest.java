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
    private DatePicker date1;

    @FXML
    private DatePicker date222;

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

        double prixProduit = commande.getPrix_produit();
        double fraisLivraison = commande.getFrais_livraison();
        int codePromo = commande.getCode_promo();
        String status = commande.getStatus();

        Label dateLabel = new Label("Date de la commande: " + date);
        Label dateLivraisonEstimeeLabel = new Label("Date de livraison estimée: " + dateLivraisonEstimee);
        Label presenterLabel3 = new Label("Montant total: " + montantTotal);
        Label presenterLabel4 = new Label("Prix produit: " + prixProduit);
        Label presenterLabel5 = new Label("Frais de livraison: " + fraisLivraison);
        Label presenterLabel6 = new Label("Code promo: " + codePromo);
        Label presenterLabel7 = new Label("Statut: " + status);

        card.getChildren().addAll(dateLabel, dateLivraisonEstimeeLabel, presenterLabel3, presenterLabel4, presenterLabel5, presenterLabel6, presenterLabel7);

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
        });
        return card;
    }

    @FXML
    void ModifyResUserBtn(ActionEvent event) {
        try {
            if (listView.getSelectionModel().getSelectedItem() == null) {
                System.out.println("Veuillez sélectionner une commande à modifier.");
            } else {
                int selectedCommandId = listView.getSelectionModel().getSelectedItem().getId_cmd();

                Commande newCommande = new Commande();
                newCommande.setId_cmd(selectedCommandId);
                newCommande.setMt_total(text1.getText());
                newCommande.setCode_promo(Integer.parseInt(text2.getText()));
                newCommande.setStatus(text3.getText());
                newCommande.setDate(java.sql.Date.valueOf(date1.getValue()));
                newCommande.setDate_livraison_estimee(java.sql.Date.valueOf(date222.getValue()));

                SR.modifier(newCommande);

                listView.getItems().clear();
                listView.getItems().addAll(SR.afficher());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
