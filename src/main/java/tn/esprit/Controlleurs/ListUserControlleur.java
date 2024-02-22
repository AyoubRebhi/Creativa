package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.esprit.Models.User;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListUserControlleur implements Initializable {
    @FXML
    private ListView<HBox> userListView;

    @FXML
    private Label labelFX;

    @FXML
    private Button updateBTN;

    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = new UserService(); // Instantiate your UserService

        // Fetch user data from your service
        List<User> userData = userService.afficher();

        // Create header
        HBox header = new HBox();
        Label idHeader = new Label("ID");
        Label lastNameHeader = new Label("nom");
        Label firstNameHeader = new Label("prenom");
        Label EMAILHeader = new Label("email");
        Label role = new Label("role");
        Label numtel = new Label("num tel");

        // Add labels for other headers...

        // Customize the font if needed
        idHeader.setFont(new Font(15));
        lastNameHeader.setFont(new Font(16));
        firstNameHeader.setFont(new Font(16));
        numtel.setFont(new Font(15));
        EMAILHeader.setFont(new Font(17));
        role.setFont(new Font(15));
        // Add headers and separators to the header HBox
        header.getChildren().addAll(idHeader, new Separator(), new Separator(), lastNameHeader, new Separator(), new Separator(), firstNameHeader, new Separator(), new Separator(), EMAILHeader, new Separator(), new Separator(), new Separator(), numtel, new Separator(), new Separator(), new Separator(), role);
        // Add header to the ListView
        userListView.getItems().add(header);

        // Dynamically create columns based on the number of elements in each row
        for (User user : userData) {
            HBox userRow = new HBox();

            // Assuming you have getters in your User class, replace them with the actual method names
            Label idLabel = new Label(String.valueOf(user.getId()));
            Label lastNameLabel = new Label(user.getLastName());
            Label firstNameLabel = new Label(user.getFirstName());
            Label emmail = new Label(user.getEmail());
            Label numTelLabel = new Label(String.valueOf(user.getNumTel()));
            System.out.println("hhhh"+(user.getNumTel()));
            Label rolle = new Label(user.getRole().name());

            // Add labels for other properties...

            // Customize the font if needed
            idLabel.setFont(new Font(18));
            lastNameLabel.setFont(new Font(15));
            firstNameLabel.setFont(new Font(15));
            numTelLabel.setFont(new Font(10));
            emmail.setFont(new Font(10));
            rolle.setFont(new Font(15));

            // Add labels and separators to the HBox
             userRow.getChildren().addAll(idLabel, new Separator(), lastNameLabel, new Separator(), firstNameLabel, new Separator(), emmail, new Separator(), numTelLabel, new Separator(), rolle);

   Button updateButton = new Button("Modifier");
            updateButton.setStyle("-fx-background-color: #8aa2ce;");
            updateButton.setOnAction(event -> handleUpdateUser(user));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #c86363;");


            deleteButton.setOnAction(event -> handleDeleteUser(user)); // Assign the delete action

            HBox actionsBox = new HBox(updateButton, deleteButton);
            userRow.getChildren().addAll(new Separator(), actionsBox);

            // Add HBox to the ListView
            userListView.getItems().add(userRow);
        }
    }
    private void handleUpdateUser(User user) {
        // You can create a new FXML file and controller for the update page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateUser.fxml"));
        Parent updateRoot;

        try {
            updateRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Pass the selected user to the controller of the update page
        updateUserController updateController = loader.getController();
        updateController.initData(user);

        // Create a new stage for the update page
        Stage updateStage = new Stage();
        updateStage.initModality(Modality.WINDOW_MODAL);
        updateStage.initOwner(((Node) userListView).getScene().getWindow());
        updateStage.setScene(new Scene(updateRoot));

        // Show the update stage
        updateStage.show();

    }

    // Method to handle the delete action
    private void handleDeleteUser(User user) {
        // Create a confirmation dialog
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation de suppression");
        confirmationDialog.setContentText("Voulez-vous vraiment supprimer cet utilisateur ?");

        // Show the confirmation dialog and wait for the user's response
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, proceed with the delete logic
                System.out.println("Deleting user with ID: " + user.getId());
UserService s=new UserService();
s.supprimer(user.getId());
                // You can call your UserService method to delete the user
                // For example: userService.deleteUser(user.getId());

                // After deletion, you might want to refresh the ListView
                // Clear the existing items
                userListView.getItems().clear();
                // Reinitialize the userListView
                initialize(null, null);
            } else {
                // User clicked Cancel or closed the dialog, do nothing
                System.out.println("Delete operation canceled.");
            }
        });
    }
//        @FXML
//        void ModifierLivraison(ActionEvent event) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/ModifierLivraison.fxml"));
//            try {
//                Parent root = loader.load();
//                ModifierLivraison controller = loader.getController();
//                labelFX.getScene().setRoot(root);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        @FXML
        void SupprimerCommande(ActionEvent event) {


        }
}
