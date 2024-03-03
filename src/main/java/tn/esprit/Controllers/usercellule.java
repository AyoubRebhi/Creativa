package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import tn.esprit.Interfaces.LocalUserStateObserver;
import tn.esprit.Services.UserService;

import java.sql.Timestamp;
import java.util.Optional;

public class usercellule {

    // Reference to the UserListController
    private UserListContoller userListController;

    // Observer to notify about local changes in user state
    private LocalUserStateObserver localObserver;

    // UserService instance for user-related operations
    private UserService userService = new UserService();

    // FXML elements
    @FXML
    private Label usernam;

    @FXML
    private Label email;

    @FXML
    private Label num;

    @FXML
    private Button bloquer;

    @FXML
    private Button debloquer;

    // User ID associated with this cell
    private int id;

    // Setter for the UserListController
    public void setUserListController(UserListContoller userListController) {
        this.userListController = userListController;
    }

    // Setter for the LocalUserStateObserver
    public void setLocalObserver(LocalUserStateObserver localObserver) {
        this.localObserver = localObserver;
    }

    // Getter for the User ID
    public int getId() {
        return id;
    }

    // Setter for the User ID
    public void setId(int id) {
        this.id = id;
    }

    // Setter for the Label of the username1
    public void setUsername1Label(String username) {
        usernam.setText(username);
    }

    // Setter for the Label of the email
    public void setEmailLabel(String userEmail) {
        email.setText(userEmail);
    }

    // Setter for the Label of the num
    public void setNumLabel(String userNum) {
        num.setText(userNum);
    }

    // Bloquer utilisateur button click event
    @FXML
    private void bloquerUtulisateur(ActionEvent event) {
        // Display a dialog to get the block duration
        boolean validInput = false;

        while (!validInput) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Bloquer Utilisateur");
            dialog.setHeaderText("Entrez la durée de blocage (en minutes) :");
            dialog.setContentText("Durée:");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                try {
                    int dureeEnMinutes = Integer.parseInt(result.get());
                    bloquerUtilisateurAvecDuree(dureeEnMinutes);
                    validInput = true;
                } catch (NumberFormatException e) {
                    // Display an alert for invalid input
                    afficherAlerteErreur("Erreur de Saisie", "Veuillez entrer un nombre entier pour la durée.");
                }
            } else {
                // User canceled the dialog
                validInput = true;  // Exit the loop
            }
        }
    }

    // Bloquer utilisateur logic
    private void bloquerUtilisateurAvecDuree(int dureeEnMinutes) {
        // Calculate the block end date based on the given duration
        Timestamp blockEndDate = new Timestamp(System.currentTimeMillis() + dureeEnMinutes * 60 * 1000);

        // Call the UserService to block the user
        userService.blockUserById(id, blockEndDate);

        // Notify the observer (UserListController) about the user state change
        onUserStateChangedLocally(true);
        // Update the button state
        updateButtonState(true);
    }

    // Debloquer utilisateur button click event
    @FXML
    private void debloquerutulisateur(ActionEvent event) {
        // Call the UserService to unblock the user
        userService.unblockUserById(id);

        // Notify the observer (UserListController) about the user state change
        onUserStateChangedLocally(false);
        // Update the button state
        updateButtonState(false);
    }

    // Notify local observer about user state change
    private void onUserStateChangedLocally(boolean isBlocked) {
        if (localObserver != null) {
            localObserver.onUserStateChanged(id, isBlocked);
        }
    }

    // Update button state based on the user's block status
    public void updateButtonState(boolean isBlocked) {
        // Update the 'Bloquer' button state
        setBloquerButtonDisabled(isBlocked);
        setDebloquerButtonDisabled(!isBlocked);
    }

    // Method to disable the 'bloquer' button
    public void setBloquerButtonDisabled(boolean disabled) {
        bloquer.setDisable(disabled);
    }

    // Method to disable the 'debloquer' button
    public void setDebloquerButtonDisabled(boolean disabled) {
        debloquer.setDisable(disabled);
    }


    private void afficherAlerteErreur(String titre, String contenu) {
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(contenu);
        alerte.showAndWait();
    }
}
