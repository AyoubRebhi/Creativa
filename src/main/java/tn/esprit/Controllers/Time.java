package tn.esprit.Controllers;

import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class Time extends Application implements Initializable {

    @FXML
    private Button buttonStart;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Integer> hoursInput;

    @FXML
    private Text hoursTimer;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private ComboBox<Integer> minutesInput;

    @FXML
    private Text minutesTimer;

    @FXML
    private ComboBox<Integer> secondsInput;

    @FXML
    private Text secondsTimer;

    @FXML
    private AnchorPane timerPane;
    Map<Integer,String> numberMap;
    Integer currSeconds;
    Thread thrd;
    @FXML
    void start(ActionEvent event) {
        currSeconds = hmsToSeconds(hoursInput.getValue(),minutesInput.getValue(),secondsInput.getValue());
        hoursInput.setValue(0);
        minutesInput.setValue(0);
        secondsInput.setValue(0);
    scrollUp();
    }
    void startCountdown(){
        thrd =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                            SetOutput();
                        Thread.sleep(1000);

                        if(currSeconds==0){
                            System.out.println("Time is up");
                            scrollDown();
                            thrd.stop();
                        }
                        currSeconds-= 1;
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        thrd.start();
    }
    void SetOutput() {
        LinkedList<Integer> curHms = secondsToHms(currSeconds);
        hoursTimer.setText(numberMap.get(curHms.get(0)));
        minutesTimer.setText(numberMap.get(curHms.get(1)));
        secondsTimer.setText(numberMap.get(curHms.get(2)));
    }
    Integer hmsToSeconds(Integer h,Integer m,Integer s){
        Integer hToSeconds = h*3600;
        Integer mToSeconds = m*60;
        Integer Total = hToSeconds+mToSeconds+s;
        return Total;
    }
    LinkedList<Integer>secondsToHms(Integer currSeconds){
        Integer hours = currSeconds/3600;
        currSeconds = currSeconds%3600;
        Integer minutes = currSeconds/60;
        currSeconds = currSeconds%60;
        Integer seconds = currSeconds;
        LinkedList<Integer> anwswer = new LinkedList<>();
        anwswer.add(hours);
        anwswer.add(minutes);
        anwswer.add(seconds);
        return anwswer;
    }
    @FXML
    void unstart(ActionEvent event) {
        thrd.stop();
        scrollDown();
    }
void scrollUp(){
        TranslateTransition trl = new TranslateTransition();
        trl.setDuration(Duration.millis(100));
        trl.setToX(0);
        trl.setToY(-200);
        trl.setNode(menuPane);
        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(timerPane);
     ParallelTransition pt = new ParallelTransition(trl,tr2);
     pt.setOnFinished(e-> {
         try {
             System.out.println("start counting");
             startCountdown();
         }catch (Exception e2){

         }
     });
        pt.play();
}
void scrollDown(){
    TranslateTransition trl = new TranslateTransition();
    trl.setDuration(Duration.millis(100));
    trl.setToX(0);
    trl.setToY(200);
    trl.setNode(timerPane);
    TranslateTransition tr2 = new TranslateTransition();
    tr2.setDuration(Duration.millis(100));
    tr2.setFromX(0);
    tr2.setFromY(-200);
    tr2.setToX(0);
    tr2.setToY(0);
    tr2.setNode(menuPane);
    ParallelTransition pt = new ParallelTransition(trl,tr2);

    pt.play();



    }
    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        ObservableList<Integer> minutesAndSecondsList = FXCollections.observableArrayList();
        for (int i = 0; i < 60; i++) {
            if (0 <= i && i <= 24) {
                hoursList.add(Integer.valueOf(i));
            }
            minutesAndSecondsList.add(Integer.valueOf(i));

        }
        hoursInput.setItems(hoursList);
        hoursInput.setValue(0);
        minutesInput.setItems(minutesAndSecondsList);
        minutesInput.setValue(0);
        secondsInput.setItems(minutesAndSecondsList);
        secondsInput.setValue(0);
        numberMap=new TreeMap<Integer,String>();
        for (int i = 0; i <= 60; i++) {
            if (0 <= i && i <= 9) {
                numberMap.put(i, "0" + Integer.valueOf(i).toString());
            } else {
                numberMap.put(i, "" + Integer.valueOf(i).toString());
            }
        }
    }
}