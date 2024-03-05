package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/AfficherTopic.fxml"))));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Notifications notifications = Notifications.create();
        notifications.owner(stage); // Set the owning window
        notifications.text("PATATI PATATA");
        notifications.title("Success");
        notifications.hideAfter(Duration.seconds(5));
        notifications.darkStyle();
        notifications.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}




