package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.PostService;
import tn.esprit.services.TopicService;
import tn.esprit.Utils.MaConnexion;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MODTOPIC {
    private TOPIC topic;

    @FXML
    private ImageView Img_Topic;
    @FXML
    private Text desc;

    @FXML
    private Text nom;

    @FXML
    private ListView<POST> PostsList;


    @FXML
    void Add_Post(ActionEvent event) {
        FXMLLoader  fxmlLoader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/AjouterPost.fxml"))));
        try {
            desc.getScene().setRoot(fxmlLoader.load());
           AjouterPost ajout=fxmlLoader.getController();
           ajout.initialize(topic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void Supprimer(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce média ?");

        // Gérer la réponse de l'utilisateur à la boîte de dialogue de confirmation
        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Action lorsque l'utilisateur clique sur "Oui"
        alert.resultProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == buttonTypeYes) {
                TopicService t= new TopicService();
                t.supprimer(this.topic.getId());
            } else {
                // L'utilisateur a annulé la suppression
                // Gérer ce cas selon vos besoins
            }
        });
        alert.showAndWait();
        // Afficher la boîte de dialogue de confirmation lorsque l'utilisateur clique sur le bouton de suppression

    }
    @FXML
    void Modifer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierTopic1.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ModifierTopic controller = loader.getController();
        controller.initialize(topic);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void initData(TOPIC topic) {
        this.topic = topic;
        desc.setText(topic.getSubject());
        nom.setText(topic.getNom());
        // For example, if you have text fields to display topic information, you can set their text values here
    }
    @FXML
    void initialize(TOPIC topic) {
        Image image= new Image(topic.getImage());
        Img_Topic.setImage(image);
        assert PostsList != null : "fx:id=\"PostsList\" was not injected: check your FXML file 'ModifierTopic.fxml'.";
        assert desc != null : "fx:id=\"desc\" was not injected: check your FXML file 'ModifierTopic.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'ModifierTopic.fxml'.";

        if (topic != null) {
            initData(topic);

            PostService p = new PostService();
            List<POST> posts = p.rechercher(topic.getId());
            ObservableList<POST> observable = FXCollections.observableList(posts);
            PostsList.setItems(observable);
            PostsList.setCellFactory(listView -> {
                ListCell<POST> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(POST item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            System.out.println(item.getMedia());
                            setText(item.toString());
                            // Assuming you override toString() in POST class
                        }
                    }
                };

                cell.setOnMouseClicked(event -> {
                    if (!cell.isEmpty() && event.getClickCount() == 1) {
                        POST selectedPost = cell.getItem();
                        // Load FXML file for displaying post
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPost.fxml"));
                            Parent root = loader.load();
                            AfficherPost controller = loader.getController();
                            controller.initialize(selectedPost);
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
    @FXML
    void AfficherList(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/AfficherTopic.fxml"))));
        try {
            desc.getScene().setRoot(fxmlLoader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
