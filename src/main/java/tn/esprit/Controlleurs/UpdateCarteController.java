package tn.esprit.Controlleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Models.CarteFidelite;
import tn.esprit.Services.CarteFideliteService;

public class UpdateCarteController {


    @FXML
    private TextField pointsFields;

    @FXML
    private Button updateButton;

    private CarteFideliteService carteService;

    private CarteFidelite currentCarte; // To store the selected carte for updating

    // Method to initialize data when the update page is opened
    public void initData(CarteFidelite carte) {
        currentCarte = carte;
        populateFields(); // Populate the form fields with the carte data
    }

    // Method to populate the form fields with carte data
    private void populateFields() {
        System.out.println("hh"+currentCarte.getPoints());
        pointsFields.setText(String.valueOf(currentCarte.getPoints()));

        // Populate other fields...
    }

    @FXML
    private void initialize() {
        carteService = new CarteFideliteService(); // Instantiate your CarteFideliteService

        // Set action for the update button
        updateButton.setOnAction(event -> handleUpdateCarte());

        // You can add validation or other setup logic here...
    }

    // Method to handle the update logic
    private void handleUpdateCarte() {
        // Get the updated values from the form fields
        String updatedPoints = pointsFields.getText();

        // Perform the update logic using your CarteFideliteService
        currentCarte.setPoints(Integer.parseInt(updatedPoints));

        // Update the carte using your CarteFideliteService
        carteService.modifier(currentCarte);

        // Show a confirmation alert
        showConfirmationAlert("Carte updated successfully!");

        // Close the update stage
        closeUpdateStage();
    }

    // Method to show a confirmation alert
    private void showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to close the update stage
    private void closeUpdateStage() {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
}
