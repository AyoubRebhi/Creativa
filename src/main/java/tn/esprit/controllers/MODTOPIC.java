package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
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
    void Modifer(ActionEvent event) {
        TopicService test = new TopicService();
        this.topic.setSubject(desc.getText());
        this.topic.setNom(nom.getText());
        test.modifier(this.topic);
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






}
