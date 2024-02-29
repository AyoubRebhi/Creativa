package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.TopicService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AfficherTopic {


    @FXML
    private ListView<TOPIC> List_id;



    @FXML
    void initialize() {
        assert List_id != null : "fx:id=\"List_id\" was not injected: check your FXML file 'AfficherTopic.fxml'.";
        TopicService test= new TopicService();
        List<TOPIC> res= test.afficher();
        ObservableList<TOPIC> observable = FXCollections.observableList(res);
        List_id.setItems(observable);
        List_id.setCellFactory(listView -> {
            ListCell<TOPIC> cell = new ListCell<>() {
                @Override
                protected void updateItem(TOPIC item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {

                        // Nettoyer la cellule
                        setText(null);
                        setGraphic(null);

                        // Vérifier si la cellule est vide
                        if (empty || item == null) {
                            return;
                        }

                        // Créer une ImageView avec l'image associée à l'objet TOPIC
                        if (!item.getImage().isEmpty()) {
                            // Créer une ImageView avec l'image associée à l'objet TOPIC
                            ImageView imageView = new ImageView(new Image(item.getImage()));
                            imageView.setFitWidth(50);
                            imageView.setFitHeight(50);

                            // Définir le texte et l'image de la cellule
                            setText(item.toString());
                            setGraphic(imageView);
                            setAlignment(Pos.CENTER_LEFT);
                        } else {
                            // Si l'URL de l'image est vide, définir seulement le texte de la cellule
                            setText(item.toString());
                        }                   // Assuming you override toString() in POST class
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty() && event.getClickCount() == 1) {
                    TOPIC selectedTopic = cell.getItem();
                    // Load FXML file for displaying post
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierTopic.fxml"));
                        Parent root = loader.load();
                        MODTOPIC controller = loader.getController();
                        controller.initialize(selectedTopic);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            return cell;
        });

    }

    }


