package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.Models.POST;
import tn.esprit.Models.TOPIC;
import tn.esprit.services.PostService;
import tn.esprit.services.TopicService;

import java.io.File;
import java.io.IOException;

public class AfficherPost {
private String Images;
private POST p;
    private Media media;
    @FXML
    private ImageView Img;
    @FXML
    private Button buttonPLay;
    @FXML
    private Button buttonPause;

    @FXML
    private Button buttonReset;
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
                PostService t= new PostService();
                t.supprimer(this.p.getId());
            } else {
                // L'utilisateur a annulé la suppression
                // Gérer ce cas selon vos besoins
            }
        });
        alert.showAndWait();
    }
    private MediaPlayer mediaPlayer;
    @FXML
    private Slider slider;
    @FXML
    private MediaView mediaView;
    @FXML
    void initialize(POST Post) {
        String mediaPath = Post.getMedia();
        this.p = Post;

        if (mediaPath != null && !mediaPath.equals("null")) {
            if (mediaPath.toLowerCase().endsWith(".mp4")) {
                // Afficher des boutons pour les fichiers MP4
                buttonPLay.setVisible(true);
buttonPause.setVisible(true);
buttonReset.setVisible(true);
mediaView.setVisible(true);
Img.setVisible(false);
                // Initialiser le lecteur média pour les fichiers MP4
                media = new Media(mediaPath);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                    slider.setValue(newValue.toSeconds());
                });
            } else if (mediaPath.toLowerCase().endsWith(".png")|| mediaPath.toLowerCase().endsWith(".jpg")) {
                // Afficher une ImageView pour les fichiers MP3
                buttonPLay.setVisible(false);
                buttonPause.setVisible(false);
                buttonReset.setVisible(false);
                slider.setVisible(false);
                mediaView.setVisible(false);
                this.Images=mediaPath;
                Image image= new Image(this.Images);
                Img.setImage(image);

            } else {
                // Autre type de fichier
                System.out.println("Type de fichier non pris en charge.");
                // Gérer ce cas selon vos besoins
            }
        } else {
            System.out.println("Le chemin du média est null.");
            // Gérer le cas où le chemin du média est null ou "null"
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
