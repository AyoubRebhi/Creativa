package tn.esprit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommande.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

}