package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.Models.TOPIC;
import tn.esprit.Models.session;
import tn.esprit.Services.TopicService;
import tn.esprit.Services.UserService;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ADDTOPIC {
    @FXML
    private TextField Desc_top;
private String Images;
    @FXML
    private ImageView Img;
    @FXML
    private TextField Nom_u;
    @FXML
    private TextField catego_id;
    UserService s=new UserService();
    @FXML
    void AfficherSujet(ActionEvent event) {
      FXMLLoader  fxmlLoader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/AfficherTopic.fxml"))));
        try {
            Nom_u.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void AjouterSujet(ActionEvent event) {
        TopicService test= new TopicService();
        TOPIC t2= new TOPIC();
        t2.setNom(Nom_u.getText());
        t2.setCatego_ID(catego_id.getText());
        t2.setSubject(Desc_top.getText());
        t2.setImage(this.Images);
        test.ajouter(t2);

    }
    @FXML
    void selectImage(ActionEvent event) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Selection media");
        File selectedFile=fileChooser.showOpenDialog(null);
        if(selectedFile !=null){
            String url =selectedFile.toURI().toString();
            this.Images=url;
            Image image= new Image(this.Images);
            Img.setImage(image);


        }
    }

    public void AfficherSujet1(ActionEvent event) {
if(s.getUtilisateurRole(session.id_utilisateur)=="CLIENT") {
    try {
        // Charger la page de connexion à partir du fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTopicClient.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle à partir de l'événement
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Changer la scène actuelle vers la nouvelle scène de connexion
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérer les erreurs de chargement de la page de connexion
    }
}
else {
    try {
        // Charger la page de connexion à partir du fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTopic.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle à partir de l'événement
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Changer la scène actuelle vers la nouvelle scène de connexion
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérer les erreurs de chargement de la page de connexion
    }
}
}
    FXMLLoader  fxmlLoader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("/AfficherTopic.fxml"))));

}
