package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.Interfaces.LocalUserStateObserver;
import tn.esprit.Models.User;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.util.List;

public class UserListContoller implements LocalUserStateObserver {
    @FXML
    private ListView<VBox> usersListView;

    private UserService userService = new UserService();

    @FXML
    public void initialize() {
        // Initialize your ListView here, for example:
        updateListView();
    }

    private void updateListView() {
        List<User> users = userService.afficher();
        System.out.println(users);

        // Clear existing content
        usersListView.getItems().clear();

        // Add each user as a cell in a new row
        for (User user : users) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cellule1.fxml"));
            try {
                HBox userCell = loader.load();
                usercellule userController = loader.getController();
                userController.setLocalObserver(this);
                userController.setUserListController(this);
                userController.updateButtonState(user.isBlocked());

                // Update the labels with user data
                userController.setUsername1Label(user.getUsername());
                userController.setEmailLabel(user.getEmail());
                userController.setNumLabel(String.valueOf(user.getNumTel()));
                userController.setId(user.getId());
                userController.setBloquerButtonDisabled(user.isBlocked());

                // Disable the 'debloquer' button if the user is not blocked
                userController.setDebloquerButtonDisabled(!user.isBlocked());
                // Set spacing directly in the HBox
                userCell.setSpacing(5);

                // Create a new VBox for each user (new row)
                VBox userRow = new VBox(userCell);

                // Set spacing between rows
                userRow.setSpacing(10);

                // Add the user row to the ListView
                usersListView.getItems().add(userRow);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading cellule1.fxml: " + e.getMessage());
            }
        }

    }


    @Override
    public void onUserStateChanged(int userId, boolean isBlocked) {
        System.out.println("Local state changed for user " + userId + ". Blocked: " + isBlocked);

//        // Iterate through the ListView items to find the corresponding user
//        usersListView.getItems().forEach(vBox -> {
//            HBox userCell = (HBox) vBox.getChildren().get(0); // Assuming the userCell is the first child
//            usercellule userController = (usercellule) userCell.getChildren().get(0);
//
//            if (userController.getId() == userId) {
//                // Update the 'Bloquer' button state
//                userController.setBloquerButtonDisabled(isBlocked);
//                userController.setDebloquerButtonDisabled(!isBlocked);
//            }
//        });
    }
}