package tn.esprit.Controlleurs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.Models.CarteFidelite;
import tn.esprit.Services.CarteFideliteService;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListCartes implements Initializable {

    @FXML
    private ListView<HBox> carteListView;

    @FXML
    private Label labelFX;

    @FXML
    private Button updateBTN;

    private CarteFideliteService carteService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carteService = new CarteFideliteService(); // Instantiate your CarteFideliteService

        // Fetch card data from your service
        List<CarteFidelite> carteData = carteService.afficher();

        // Create header
        HBox header = new HBox();
        Label idHeader = new Label("ID");
        Label userIdHeader = new Label("IDClient");
        Label pointsHeader = new Label("Points");
        // Add labels for other headers...

        // Customize the font if needed
        idHeader.setFont(new Font(15));
        userIdHeader.setFont(new Font(16));
        pointsHeader.setFont(new Font(16));
        // Add headers and separators to the header HBox
        header.getChildren().addAll(idHeader, new Separator(),new Separator(), userIdHeader,new Separator(), new Separator(), pointsHeader,new Separator());
        // Add header to the ListView
        carteListView.getItems().add(header);

        // Dynamically create columns based on the number of elements in each row
        for (CarteFidelite carte : carteData) {

            HBox carteRow = new HBox();
CarteFideliteService h=new CarteFideliteService();
            // Assuming you have getters in your CarteFidelite class, replace them with the actual method names
            Label idLabel = new Label(String.valueOf(carte.getIdCarteFidelite()));
            Label userIdLabel = new Label(String.valueOf(h.consulterUtilisateurParIdCarte(carte.getIdCarteFidelite()).getId()));
            Label pointsLabel = new Label(String.valueOf(carte.getPoints()));
            // Add labels for other properties...

            // Customize the font if needed
            idLabel.setFont(new Font(18));
            userIdLabel.setFont(new Font(15));
            pointsLabel.setFont(new Font(15));
            // Add labels and separators to the HBox
            carteRow.getChildren().addAll(idLabel,new Separator(), new Separator(), userIdLabel,new Separator(), new Separator(), pointsLabel);

            Button updateButton = new Button("Modifier");
            updateButton.setOnAction(event -> handleUpdateCarte(carte));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setOnAction(event -> handleDeleteCarte(carte)); // Assign the delete action

            HBox actionsBox = new HBox(updateButton, deleteButton);
            carteRow.getChildren().addAll(new Separator(),new Separator(),new Separator(), actionsBox);

            // Add HBox to the ListView
            carteListView.getItems().add(carteRow);
        }
    }

    private void handleUpdateCarte(CarteFidelite carte) {
        // Similar to handleUpdateUser, create a new FXML file and controller for the update page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatecarte.fxml"));
        Parent updateRoot;

        try {
            updateRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Pass the selected carte to the controller of the update page
        UpdateCarteController updateController = loader.getController();
        updateController.initData(carte);

        // Create a new stage for the update page
        Stage updateStage = new Stage();
        updateStage.initModality(Modality.WINDOW_MODAL);
        updateStage.initOwner(((Node) carteListView).getScene().getWindow());
        updateStage.setScene(new Scene(updateRoot));

        // Show the update stage
        updateStage.show();
    }

    private void handleDeleteCarte(CarteFidelite carte) {
        // Similar to handleDeleteUser, create a confirmation dialog
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation de suppression");
        confirmationDialog.setContentText("Voulez-vous vraiment supprimer cette carte ?");

        // Show the confirmation dialog and wait for the user's response
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, proceed with the delete logic
                System.out.println("Deleting carte with ID: " + carte.getIdCarteFidelite());
                CarteFideliteService s = new CarteFideliteService();
                s.supprimer(carte.getIdCarteFidelite());

                // After deletion, you might want to refresh the ListView
                // Clear the existing items
                carteListView.getItems().clear();
                // Reinitialize the carteListView
                initialize(null, null);
            } else {
                // User clicked Cancel or closed the dialog, do nothing
                System.out.println("Delete operation canceled.");
            }
        });
    }
}

