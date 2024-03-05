package tn.esprit.maytest.Controllers;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import tn.esprit.maytest.Models.Formation;
import tn.esprit.maytest.Models.Inscription;
import tn.esprit.maytest.Services.ServiceFormation;
import tn.esprit.maytest.Services.ServiceInscription;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class ListInscriptionController implements Initializable {

    @FXML
    private TableView<Inscription> id_liste;
    @FXML
    private TableColumn<Inscription, String> id_nom;
    @FXML
    private TableColumn<Inscription, String> id_prenom;
    @FXML
    private TableColumn<Inscription, String> id_email;
    @FXML
    private TableColumn<Inscription, LocalDate> id_date;
    @FXML
    private Label id;
    @FXML
    private Button id_del;
    @FXML
    private Button id_clear;
    @FXML
    private Button id_liste_inscrit;
 
    private int idE;
    private Formation e = new Formation(); 
    @FXML
    private Button id_abondonner;
    /**
     * Initializes the controller class.
     */
    
    
     public void tarata()
       {   
        ServiceFormation ps = new ServiceFormation();
        e=ps.readById(idE);
        //System.out.print(e);
       }
         
    public void initData(int data) {
       this.idE=data;
      String stringNumber = Integer.toString(idE);

      id.setText(stringNumber);
      tarata();
            }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ImageView imageView = new ImageView(getClass().getResource("/images/2222.png").toExternalForm());
        id_liste_inscrit.setGraphic(imageView);
        // TODO
                id_liste_inscrit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listPost();
            }
        });
    }    

    @FXML
    private void listPost() {
        
        ServiceInscription uc =new ServiceInscription();
        ArrayList arrayList = null;
        arrayList = (ArrayList) uc.afficheInscriptionByIdFomation(idE);
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        id_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("dateNow"));  
        
    }


    @FXML
    private void clear(ActionEvent event) {
       ServiceInscription e =new ServiceInscription();
        e.Clear();
        JOptionPane.showMessageDialog(null, "List vidé !");
    }

    @FXML
    private void deleteInscription(ActionEvent event) {
         int id = Integer.valueOf(id_liste.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));
           ServiceInscription rs = new  ServiceInscription();
           rs.supprimerInscription(id);
           id_liste.getItems().removeAll(id_liste.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void abodonner(ActionEvent event) throws IOException {
        Parent page8 = FXMLLoader.load(getClass().getResource("../../../../../resources/tn/esprit/maytest/AffichageFormation.fxml"));
        Scene scene = new Scene(page8);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
