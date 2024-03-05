/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.maytest.Controllers;

import tn.esprit.maytest.Utils.Pdf;
import tn.esprit.maytest.Models.Formation;
import tn.esprit.maytest.Services.ServiceFormation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import tn.esprit.maytest.Controllers.UpdateController;
import tn.esprit.maytest.Models.Formation;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class AffichageFormationController implements Initializable {

    @FXML
    private TableView<Formation> id_liste;
    @FXML
    private TableColumn<Formation, String> id_titre;
    @FXML
    private TableColumn<Formation, String> id_description;
    @FXML
    private TableColumn<Formation, String> id_image;
    @FXML
    private Button id_liste_post;
    @FXML
    private TableColumn<Formation, Integer> id_places;
    @FXML
    private TableColumn<Formation, Double> id_prix;
    @FXML
    private Button id_del;
    @FXML
    private Button id_ajout;
    @FXML
    private Button id_update;
    ObservableList<Formation> eventList2;
    @FXML
    private Button id_clear;
    @FXML
    private Button id_pdf;
    @FXML
    private TextField rech;
    @FXML
    private Button id_listInscrit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImageView imageView4 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/pdf.png").toExternalForm());
        id_pdf.setGraphic(imageView4);
        ImageView imageView3 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/remouve.png").toExternalForm());
        id_clear.setGraphic(imageView3);
        ImageView imageView2 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/update.png").toExternalForm());
        id_update.setGraphic(imageView2);
        ImageView imageView1 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/del.png").toExternalForm());
        id_del.setGraphic(imageView1);
        ImageView imageView5 = new ImageView(getClass().getResource("/tn/esprit/maytest/images/add.png").toExternalForm());
        id_ajout.setGraphic(imageView5);
        ImageView imageView = new ImageView(getClass().getResource("/tn/esprit/maytest/images/2222.png").toExternalForm());
        id_liste_post.setGraphic(imageView);
        id_liste_post.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listPost();
            }
        });
    }


    @FXML
    private void listPost() {
        ServiceFormation uc =new ServiceFormation();
        ArrayList arrayList = null;
        arrayList = (ArrayList) uc.getAllFormations();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        id_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        id_image.setCellValueFactory(new PropertyValueFactory<>("media"));
        id_places.setCellValueFactory(new PropertyValueFactory<>("nb_places"));  
         id_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));  

    }

    @FXML
    private void deleteFormation(ActionEvent event) {
        String selectedItem = id_liste.getSelectionModel().getSelectedItem().toString();

        if (selectedItem != null) {
            try {
                System.out.println(selectedItem);
                int id = Integer.parseInt(selectedItem.split("=")[1].substring(0, 2));
                System.out.println(id);
                ServiceFormation serviceFormation = new ServiceFormation();
                serviceFormation.supprimerFormation(id);

                // Remove the selected item from the list
                id_liste.getItems().remove(selectedItem);
                listPost();
            } catch (NumberFormatException e) {
                // Handle the case where parsing the ID fails
                System.err.println("Error parsing the ID: " + e.getMessage());
            }
        } else {
            // Handle the case where nothing is selected
            System.out.println("No item selected for deletion.");
        }
    }


    @FXML
    private void ajouterFormation(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(this.getClass().getResource("/tn/esprit/maytest/AjoutFormation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        listPost();
    }

    @FXML
private void UpdateFormation(ActionEvent event) throws IOException {
    Formation selectedFormation = id_liste.getSelectionModel().getSelectedItem();
    if (selectedFormation != null) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/maytest/Update.fxml"));
        Parent root = loader.load();
        UpdateController controller = loader.getController();
        controller.initData(selectedFormation.getId());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } else {
        // Afficher un message d'erreur si aucune formation n'est sélectionnée
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une formation à mettre à jour.");
        alert.showAndWait();
    }
}

    @FXML
    private void clear(ActionEvent event) {
        ServiceFormation e =new ServiceFormation();
        e.Clear();
        JOptionPane.showMessageDialog(null, "List vidé !");
        listPost();
    }

    @FXML
    private void pdf(ActionEvent event) {
        
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("Liste des tableau artestique");
            System.out.println("impression done");
        } catch (Exception ex) {
            //Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void FiltrerKeyReleased3(KeyEvent event) {
           String searchTerm = rech.getText();
    ServiceFormation su = new ServiceFormation ();
    ObservableList<Formation> list = su.searchent2(searchTerm);
    List<Formation> filteredList = list.stream()
        .filter(entretient -> entretient.getTitre().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

   id_liste.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void inscrire(ActionEvent event) throws IOException {
                eventList2=id_liste.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId();
                if ( id !=0)
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/maytest/Inscription.fxml"));
                Parent root = loader.load();
                InscriptionController aa = loader.getController();
                aa.initData(id);
                id_update.getScene().setRoot(root);
      }
    }

    @FXML
    private void listerInscriptions(ActionEvent event) throws IOException {
             eventList2=id_liste.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId();
                if ( id !=0)
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/maytest/Inscription.fxml"));
                Parent root = loader.load();
                ListInscriptionController aa = loader.getController();
                aa.initData(id);
                id_update.getScene().setRoot(root);
      }
    }

    
}
