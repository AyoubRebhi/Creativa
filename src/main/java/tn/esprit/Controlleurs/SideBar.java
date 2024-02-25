package tn.esprit.Controlleurs;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SideBar {
    @FXML
    private VBox sidebar;

    @FXML
    private StackPane overlay;

    @FXML
    private Pane hiddenSidebar;

    @FXML
    private void toggleSidebar() {
        TranslateTransition openNav = new TranslateTransition(new Duration(350), sidebar);
        openNav.setToX(sidebar.getTranslateX() == 0 ? -sidebar.getWidth() : 0);

        TranslateTransition openOverlay = new TranslateTransition(new Duration(350), overlay);
        openOverlay.setToX(overlay.getTranslateX() == 0 ? -sidebar.getWidth() : 0);

        openNav.play();
        openOverlay.play();
    }

    public void handleHome(ActionEvent actionEvent) {
    }

    public void handleSettings(ActionEvent actionEvent) {
    }

    public void handleAbout(ActionEvent actionEvent) {
    }
}
