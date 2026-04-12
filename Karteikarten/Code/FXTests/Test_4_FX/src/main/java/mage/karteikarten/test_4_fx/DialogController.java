package mage.karteikarten.test_4_fx;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mage.karteikarten.test_4_fx.datamodel.TodoData;
import mage.karteikarten.test_4_fx.datamodel.TodoItem;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField textFieldDescription;
    @FXML
    private TextArea textAreaDetails;
    @FXML
    private DatePicker datePickerDeadline;

    public TodoItem processResults(){
        String shortDescription = textFieldDescription.getText().trim();
        String details = textAreaDetails.getText().trim();
        LocalDate deadline = datePickerDeadline.getValue();

        TodoItem item = new TodoItem(shortDescription, details, deadline);
        TodoData.getInstance().addTodoItem(item);

        return item;
    }
}
