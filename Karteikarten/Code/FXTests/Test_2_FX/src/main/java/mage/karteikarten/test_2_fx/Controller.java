package mage.karteikarten.test_2_fx;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private ColorPicker picker;

    public void handlePicker() {
        Label colorLabel = (Label) picker.getChildrenUnmodifiable().get(1);
        System.out.println(colorLabel.getText());
    }
}
