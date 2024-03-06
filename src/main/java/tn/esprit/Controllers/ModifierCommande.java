package tn.esprit.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import tn.esprit.Services.ServiceCodepromo;

public class ModifierCommande implements Initializable{
    @FXML
    private ComboBox<String> idCombobox;
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


    @FXML
    void modifierBTN(ActionEvent event) throws SQLException {
        try {
            ServiceCommande serviceCommande = new ServiceCommande();

            int idCmd = Integer.parseInt(id_cmdTF.getText());
            int idUser = serviceCommande.getIdUtilisateurParNomComplet(id_userTF.getText());

            int idProjet = serviceCommande.getAllProjectTitlesAndIds().size();
            Date date = Date.valueOf(datePicker.getValue());
            Date dateLivraisonEstimee = Date.valueOf(datePicker2.getValue());
            String mtTotal = mt_totalTF.getText();
            ServiceCodepromo serviceCodepromo = new ServiceCodepromo();
            int codePromo = Integer.parseInt(code_promoTF.getText());
            if (!serviceCodepromo.codePromoExiste(String.valueOf(codePromo))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Le code promo saisi n'existe pas !");
                alert.showAndWait();
                return;
            }
            String status = statusTF.getText();
            float prixProduit = Float.parseFloat(prixProduitLabel.getText());
            float fraisLivraison = Float.parseFloat(fraisLivLabel.getText());
            Commande c = new Commande(idCmd, idUser, idProjet, date, mtTotal, dateLivraisonEstimee, codePromo, status ,prixProduit,fraisLivraison);
            serviceCommande = new ServiceCommande();
            serviceCommande.modifier(c);

            // Message de confirmation
            showAlert(AlertType.INFORMATION, "Succès", "Commande modifiée avec succès !");
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erreur", "Veuillez saisir des données valides !");
        }
    }



    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ServiceCommande serviceCommande = new ServiceCommande();
            Map<String, Integer> projectTitlesAndIds = serviceCommande.getAllProjectTitlesAndIds();

            // Récupérer les titres de projet depuis la map
            ObservableList<String> projectTitles = FXCollections.observableArrayList();
            for (String title : projectTitlesAndIds.keySet()) {
                projectTitles.add(title);
            }

            // Ajouter les titres de projet à la ComboBox
            idCombobox.getItems().addAll(projectTitles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statusTF.setText("En cours");
        datePicker.setValue(LocalDate.now());

        // Définir la date estimée de livraison comme 5 jours après la date de passation de commande
        LocalDate datePassationCommande = datePicker.getValue();
        LocalDate dateEstimeeLivraison = datePassationCommande.plusDays(5);

// Définir la date estimée de livraison dans le DatePicker
        datePicker2.setValue(dateEstimeeLivraison);
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCommande.fxml"));
        try {
            mt_totalTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setIdCommande(int idCmd) {
        id_cmdTF.setText(String.valueOf(idCmd));
    }
    public void setIdUtilisateur(int idUser) {
        id_userTF.setText(String.valueOf(idUser));
    }


}