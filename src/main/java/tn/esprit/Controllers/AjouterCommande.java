package tn.esprit.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCodepromo;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;

public class AjouterCommande implements Initializable{
    ServiceCommande sm= new ServiceCommande();
    @FXML
    private ComboBox<String> idCombobox;
    @FXML
    private Button afficherBTN;

    @FXML
    private Label ajouter_commandes;

    @FXML
    private TextField code_promoTF;

    @FXML
    private Button continuerBTN;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField id_projetTF;

    @FXML
    private Button appliquerID;

    @FXML
    private TextField id_userTF;

    @FXML
    private Label MTtotalLabel;

    @FXML
    private Label prixProduitLabel;
    @FXML
    private Label fraisLivLabel;
    @FXML
    private Button PasserLiv;
    @FXML
    private TextField statusTF;

    @FXML
    private DatePicker datePicker;

    @FXML
    private DatePicker datePicker2;
    @FXML
    private Text MTtext;

    @FXML
    private Text MTtext1;

    @FXML
    private Text MTtext2;

    @FXML
    private Text MTtext3;
    @FXML
    private Label RemiseLabel;





    @FXML
    void AjouterCommande(ActionEvent event) throws SQLException {
        // Vérifier si tous les champs obligatoires sont remplis
        if (code_promoTF.getText().isEmpty() || id_userTF.getText().isEmpty() || MTtotalLabel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        // Vérifier si le code promo est disponible dans la base de données
        ServiceCodepromo serviceCodepromo = new ServiceCodepromo();
        String codePromo = code_promoTF.getText();
        if (!serviceCodepromo.codePromoExiste(codePromo)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le code promo saisi n'existe pas !");
            alert.showAndWait();
            return;
        }

        // Vérifier si le code promo a déjà été utilisé par le même utilisateur
        ServiceCommande serviceCommande = new ServiceCommande();
        String utilisateur = id_userTF.getText(); // Récupérer le nom de l'utilisateur
        if (serviceCommande.codePromoDejaUtiliseParUtilisateur(codePromo, utilisateur)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Ce code promo a déjà été utilisé par le même utilisateur !");
            alert.showAndWait();
            return;
        }

        //ajouter la commande si le code promo existe dans la base de données et n'a pas été utilisé par le même utilisateur
        Commande c = new Commande();

        c.setId_user(serviceCommande.getIdUtilisateurParNomComplet(utilisateur));
        c.setId_projet(serviceCommande.getAllProjectTitlesAndIds().get(idCombobox.getValue()));
        c.setDate(Date.valueOf(datePicker.getValue()));
        c.setMt_total(String.valueOf(MTtotalLabel.getText()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        c.setDate_livraison_estimee(Date.valueOf(datePicker2.getValue()));
        c.setMt_total(MTtotalLabel.getText());
        c.setCode_promo(Integer.parseInt(codePromo));
        c.setStatus(statusTF.getText());
        c.setPrix_produit( Float.parseFloat(prixProduitLabel.getText()));
        float fraisLivraison = 8.0f; // Définition du frais de livraison à 8dt
        c.setFrais_livraison(fraisLivraison);

        serviceCommande.ajouter(c);
        // Affichage d'une alerte pour indiquer que la commande a été ajoutée avec succès
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("La commande a été ajoutée avec succès !");
        alert.showAndWait();
    }






    @FXML
    void PasserLiv(ActionEvent event) throws SQLException {
        // Vérifier si tous les champs obligatoires pour ajouter une commande sont remplis
        if (code_promoTF.getText().isEmpty() || id_userTF.getText().isEmpty() || MTtotalLabel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez ajouter une commande avant de passer à la livraison !");
            alert.showAndWait();
            return;
        }

        // Si tous les champs sont remplis, passer à la scène d'ajout de livraison
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterLivraison.fxml"));
        try {
            datePicker2.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/sidebarClient.fxml"));
        try {
            statusTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void select(ActionEvent event) {
        String titreProjet = idCombobox.getSelectionModel().getSelectedItem().toString();
        try {
            float prixFloat = Float.parseFloat(sm.getPrixParTitreProjet(titreProjet));
            float prix_produit = Float.parseFloat(sm.getprixproduit(titreProjet));

            // Récupérer le texte du RemiseLabel
            String remiseLabel = RemiseLabel.getText();

            // Vérifier si le texte du RemiseLabel n'est pas vide
            if (!remiseLabel.isEmpty()) {
                // Supprimer le symbole de pourcentage (%) du texte de remise et convertir en entier
                int pourcentage = Integer.parseInt(remiseLabel.replace("%", ""));

                // Calculer le montant de la remise
                float remise = (pourcentage / 100.0f) * prixFloat;

                // Calculer le prix après remise
                float prixApresRemise = prixFloat - remise;

                // Mettre à jour les labels avec les nouveaux prix
                MTtotalLabel.setText(String.valueOf(prixApresRemise));
                prixProduitLabel.setText(String.valueOf(prix_produit));
            } else {
                // Si le texte du RemiseLabel est vide, ajouter 8 au prixFloat
                prixFloat += 8;

                // Mettre à jour les labels avec les nouveaux prix
                MTtotalLabel.setText(String.valueOf(prixFloat));
                prixProduitLabel.setText(String.valueOf(prix_produit));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            // Gérer le cas où le texte du RemiseLabel n'est pas un nombre valide
            // Peut-être afficher un message d'erreur à l'utilisateur
            e.printStackTrace();
        }
    }



    @FXML
    void AppliquerCode(ActionEvent event) {
        // Récupérer le code promo saisi par l'utilisateur
        String codePromo = code_promoTF.getText();

        // Vérifier si le code promo est valide
        ServiceCodepromo serviceCodePromo = new ServiceCodepromo();
        if (serviceCodePromo.codePromoValide(Integer.parseInt(codePromo))) {
            // Le code promo est valide, procéder à l'application de la réduction
            String pourcentageStr = serviceCodePromo.getPourcentageByCodePromo(Integer.parseInt(codePromo));
            if (pourcentageStr != null) {
                int pourcentage = Integer.parseInt(pourcentageStr.replace("%", ""));
                RemiseLabel.setText("Réduction: " + pourcentage + "%");
                float prixFloat = Float.parseFloat(MTtotalLabel.getText());
                float nouveauPrix = prixFloat - (prixFloat * (pourcentage / 100.0f));
                MTtotalLabel.setText(String.valueOf(nouveauPrix));
            } else {
                RemiseLabel.setText("Code promo invalide");
            }
        } else {
            // Le code promo n'est pas valide ou a expiré, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le code promo saisi n'est pas valide ou a expiré !");
            alert.showAndWait();
        }
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
            e.printStackTrace(); // Gérer l'exception selon votre cas
        }
        statusTF.setText("En cours");
        datePicker.setValue(LocalDate.now());

        // Définir la date estimée de livraison comme 5 jours après la date de passation de commande
        LocalDate date = datePicker.getValue();
        LocalDate date_Livraison_Estimee = date.plusDays(5);

// Définir la date estimée de livraison dans le DatePicker
        datePicker2.setValue(date_Livraison_Estimee);

    }



}