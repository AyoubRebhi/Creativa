package tn.esprit.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.Interfaces.LocalUserStateObserver;
import tn.esprit.Models.Role;
import tn.esprit.Models.User;
import tn.esprit.Services.UserService;

import java.io.IOException;
import java.util.List;

public class UserListContoller implements LocalUserStateObserver {
    @FXML
    private ListView<VBox> usersListView;

    @FXML
    private TextField searchBar;

    private UserService userService = new UserService();

    @FXML
    public void initialize() {
        // Initialize your ListView here, for example:
        updateListView();

        // Add a listener to the search bar to filter users on text change
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                filterUsers(newValue.trim().toLowerCase()));

        checkAndUnblockUsers();
    }


    public void checkAndUnblockUsers() {
        List<User> blockedUsers = userService.getBlockedUsers();

        for (User blockedUser : blockedUsers) {
            long remainingTime = userService.getRemainingBlockTime(blockedUser.getUsername());
            if (remainingTime <= 0) {
                // Unblock the user
                userService.unblockUserById(blockedUser.getId());
            }
        }
    }

    private void updateListView() {
        List<User> users = userService.afficher();
        usersListView.getItems().clear();

        for (User user : users) {
            // Rest of the code to add users to the ListView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cellule1.fxml"));
            try {
                HBox userCell = loader.load();
                usercellule userController = loader.getController();
                userController.setLocalObserver(this);
                userController.setUserListController(this);
                userController.updateButtonState(user.isBlocked());
                userController.setRole(user.getRole().name().toLowerCase());
                // Update the labels with user data
                userController.setUserPhoto(user.getRole());

                userController.setUsername1Label(user.getUsername());
                userController.setEmailLabel(user.getEmail());
                userController.setNumLabel(String.valueOf(user.getNumTel()));
                userController.setId(user.getId());
                userController.setBloquerButtonDisabled(user.isBlocked());
                if (user.getRole() == Role.ADMIN) {
                    System.out.println("Admin");

                    userController.getMonBouton().setVisible(false);
                    userController.getMonBouton1().setVisible(false);

                }
                userCell.setSpacing(5);
                VBox userRow = new VBox(userCell);
                userRow.setSpacing(10);

                usersListView.getItems().add(userRow);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading cellule1.fxml: " + e.getMessage());
            }
        }
    }

    private void filterUsers(String keyword) {
        List<User> filteredUsers = userService.searchUsers(keyword);
        updateListView(filteredUsers);
    }

    private void updateListView(List<User> users) {
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
                userController.setRole(user.getRole().name().toLowerCase());
                userController.setUserPhoto(user.getRole());

                // Update the labels with user data
                userController.setUsername1Label(user.getUsername());
                userController.setEmailLabel(user.getEmail());
                userController.setNumLabel(String.valueOf(user.getNumTel()));
                userController.setId(user.getId());
                userController.setBloquerButtonDisabled(user.isBlocked());
                System.out.println("hhhh"+user.getRole());
                System.out.println("hhhh"+user.getRole());



                userCell.setSpacing(5);
                VBox userRow = new VBox(userCell);
                userRow.setSpacing(10);

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
    }
}