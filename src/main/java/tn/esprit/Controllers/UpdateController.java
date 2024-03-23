package tn.esprit.Controllers;

import tn.esprit.Models.Formation;
import tn.esprit.Services.ServiceFormation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class UpdateController implements Initializable {

    @FXML
    private ImageView iv_gallerie_update;
    @FXML
    private Button id_update_im_;
    @FXML
    private TextField tf_titre_update;
    @FXML
    private TextField tf_description_update;
    @FXML
    private TextField tf_places_update;
    @FXML
    private TextField tf_prix_update;
    @FXML
    private Button id_add_formation;
    @FXML
    private Label id;
    @FXML
    private Button id_abondonner;

      public void tarata()
       {
        
        ServiceFormation ps = new ServiceFormation();
        e=ps.readById(idE);
        //System.out.print(e);
       }
      
        private File selectedFile;
    private Formation postToAdd = new Formation();


           private int idE;
    private Formation e = new Formation(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          ImageView imageView = new ImageView(getClass().getResource("/images/telecharge.png").toExternalForm());
         id_update_im_.setGraphic(imageView);
           
    }    

    @FXML
    private void AddImage(ActionEvent event) {
         Stage s = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image files", "*.jpg", "*.png", "*.jpeg", "*.JPEG"));
        
        selectedFile = fc.showOpenDialog(s);
       
        String imgPath = selectedFile.getAbsoluteFile().getPath().toString();
        postToAdd.setMedia(selectedFile.getAbsoluteFile().getName().toString());

        Image img = new Image(selectedFile.toURI().toString());

        iv_gallerie_update.setImage(img);
    }

    @FXML
    private void update_formation(ActionEvent event) throws IOException {
        
          Formation r = new Formation();
       ServiceFormation su = new ServiceFormation() ;  
        r.setId(idE); 
        r.setTitre(tf_titre_update.getText());
        r.setDescription(tf_description_update.getText());
        r.setNb_places(Integer.parseInt(tf_places_update.getText()));
        r.setPrix(Double.parseDouble(tf_prix_update.getText()));
        
            try {
        r.setTitre(tf_titre_update.getText());
        r.setDescription(tf_description_update.getText());

      } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ prix doit étre un nombre !");
        alert.showAndWait();
        return;
       }
               
       String imageName = selectedFile.getName();
       postToAdd.setMedia(imageName);

    // Vérifier si le fichier sélectionné est null
     if (selectedFile != null) {
        System.out.println("Selected File: " + selectedFile.getAbsolutePath());
        copyImageToServer(selectedFile);
        System.out.println("image copied successfully.");
    } else {
        System.out.println("No image selected.");
    }
    r.setMedia(imageName);

    su.update(r);
        
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("modification éffectuée");
                alert.showAndWait();
                
                
                
        Parent page1 = FXMLLoader.load(getClass().getResource("/AffichageFormation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
                
    }
    
    
    
    
    
private void copyImageToServer(File selectedFile) throws IOException {
    String to = "C:\\Users\\user\\Desktop\\gestion des formations et des inscriptions\\Gestion_formation\\Gestion_formation\\src\\images\\" + selectedFile.getName();



    System.out.println("Copying image from: " + selectedFile.getAbsolutePath());
    System.out.println("Copying image to: " + to);

    Path src = selectedFile.toPath();
    Path dest = Paths.get(to);
    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);

    System.out.println("image copied successfully.");
}
    
    
   public void initData(int data) {
        this.idE = data;
        id.setText(String.valueOf(idE));
        e = new ServiceFormation().readById(idE);
        tf_titre_update.setText(e.getTitre());
        tf_description_update.setText(e.getDescription());
        tf_places_update.setText(String.valueOf(e.getNb_places()));
        tf_prix_update.setText(String.valueOf(e.getPrix()));
    }

    @FXML
    private void abodonner(ActionEvent event) throws IOException {
        Parent page8 = FXMLLoader.load(getClass().getResource("/AffichageFormation.fxml"));
        Scene scene = new Scene(page8);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
