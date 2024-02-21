package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.Models.User;
import tn.esprit.Services.UserService;
import tn.esprit.Utils.SmsSender;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetPasswordController implements Initializable {

    @FXML
    private TextField tfCode;
    @FXML
    private Button btnSend;
    User sa = new User();
    public static int a = (int) (Math.random() * 9999);
    public static String numTelephone;

    /**
     * Initializes the controller class.
     */


    @FXML
    private void SendCode(ActionEvent event) {

        System.out.println("hajer");
        UserService f = new UserService();
        System.out.println(tfCode.getText());
        if ((!tfCode.getText().matches("\\d{8}")) || (tfCode.getText().equals(""))) {
            System.out.println("utilisateur incorrecte");
        } else {
            numTelephone = tfCode.getText();
            SmsSender s = new SmsSender();
            s.send(String.valueOf(a), numTelephone);


            try {
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Forgetpassword.fxml"));
                Parent root = loader2.load();
                tfCode.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}