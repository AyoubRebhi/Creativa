package tn.esprit.Controlleurs;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class newSideBar {
    @FXML
    private VBox root;

    @FXML
    private VBox sidebar;

    @FXML
    private VBox mainContent;

    @FXML
    private Button toggleButton;

    @FXML
    public void initialize() {
        toggleButton.setOnAction(event -> toggleSidebar());
    }

    private void toggleSidebar() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidebar);

        if (sidebar.getTranslateX() != 0) {
            transition.setToX(0);
        } else {
            transition.setToX(-sidebar.getWidth());
        }

        transition.play();
    }
}
