package tn.esprit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class test extends Application {


    double x,y = 0;

        @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/listUserOriginale.fxml"));

            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        }}