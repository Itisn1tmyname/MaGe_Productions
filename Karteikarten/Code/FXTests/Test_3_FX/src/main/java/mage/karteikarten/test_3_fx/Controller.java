package mage.karteikarten.test_3_fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private TextField nameField;
    @FXML
    private CheckBox clearBox;
    @FXML
    private Label ourLabel;

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if (e.getSource().equals(helloButton))
            System.out.println("Hello, "+ nameField.getText());
        else if (e.getSource().equals(byeButton))
            System.out.println("Bye, "+ nameField.getText());

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Platform.runLater(() -> ourLabel.setText("Something happened!"));
                } catch (InterruptedException ignored){}
            }
        };

        new Thread(task).start();

        if (clearBox.isSelected()) {
            nameField.clear();
            handleKeyReleased();
        }

        System.out.println("The following Button was pressed: "+ e.getSource());
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }


    @FXML
    public void handleChange() {
        System.out.println("The checkbox is " + (clearBox.isSelected() ? "checked" : "unchecked"));
    }

    @FXML
    public void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

}
