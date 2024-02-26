package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CardController {

    @FXML
    private VBox card;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    private String title;
    private String description;

    public void initialize() {
        titleLabel.setText(title);
        descriptionLabel.setText(description);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @FXML
    private void handleShowProject() {
        // Action when "Show Project" button is clicked
        // You can define what happens here, for example opening a new window or navigating to another view
    }
}
