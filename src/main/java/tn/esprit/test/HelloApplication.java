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
import tn.esprit.Models.Comments;
import tn.esprit.services.CommentService;

import java.util.List;
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
        CommentService commentService= new CommentService();
        List<Comments> comments=commentService.Affichernotification(2);
        for(Comments c:comments){
        Notifications notifications = Notifications.create();
        notifications.owner(stage); // Set the owning window
        notifications.text(c.getContent());
        notifications.title("New notification");
        notifications.hideAfter(Duration.seconds(5));
        notifications.darkStyle();
        notifications.show();
        commentService.changeSeen(c);}

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}




