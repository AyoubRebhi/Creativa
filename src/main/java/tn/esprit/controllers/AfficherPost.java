package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;

import java.io.File;
import java.io.IOException;

public class AfficherPost {

private POST p;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private Slider slider;
    @FXML
    private MediaView mediaView;
    @FXML
    void initialize(POST Post) {
        String mediaPath = Post.getMedia();
        this.p=Post;
        if (mediaPath != null && !mediaPath.equals("null")) {
            media = new Media(Post.getMedia());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener((((observableValue, oldValue, newValue) ->{
                slider.setValue(newValue.toSeconds());
            }
                     )));

        } else {
            System.out.println("Media path is null.");
            // Handle the case where mediaPath is null or "null"
        }
    }
    @FXML
    void Modifier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPost.fxml"));
        Parent root = loader.load();
        ModifierPost controller = loader.getController();
        controller.initialize(p);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void pauseMedia(ActionEvent event) {
mediaPlayer.pause();
    }

    @FXML
    void playMedia(ActionEvent event) {
mediaPlayer.play();
    }

    @FXML
    void resetMedia(ActionEvent event) {
mediaPlayer.seek(Duration.ZERO);
    }
    @FXML
    void seeking(MouseEvent event) {
mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }
}
