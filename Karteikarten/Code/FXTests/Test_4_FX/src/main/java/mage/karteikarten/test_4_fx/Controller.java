package mage.karteikarten.test_4_fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import mage.karteikarten.test_4_fx.datamodel.TodoData;
import mage.karteikarten.test_4_fx.datamodel.TodoItem;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Controller {

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label dueLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize(){

        todoListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            if (newValue != null) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                itemDetailsTextArea.setText(item.getDetails());

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMMM yyyy");
                dueLabel.setText(dtf.format(item.getDeadline()));
            }
        });

//        todoListView.getItems().addAll(getTodoItems());
        todoListView.getItems().addAll(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void showNewItemDialoge() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());


        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = loader.getController();
            TodoItem item = controller.processResults();
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            todoListView.getSelectionModel().select(item);
            System.out.println("OK Pressed!");
        } else {
            System.out.println("CANCEL Pressed.");
        }

    }




//    @FXML //Commented out because we added a Listener in initialize() instead.
//    public void handleClickListView() {
//        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
////        System.out.println("The selected item is " + item);
//
//        itemDetailsTextArea.setText(item.getDetails());
//        dueLabel.setText(item.getDeadline().toString());
//
//    }

//    private List<TodoItem> getTodoItems() { //Initial Hardcoded values
//        TodoItem item1 = new TodoItem ("Mail birthday card",
//                "Buy a 30th birthday card for John", LocalDate.of(2025, Month.APRIL, 27));
//        TodoItem item2 = new TodoItem ("Doctor's appointment",
//                "See Dr. Smith at 123 Main Street. Bring paperwork", LocalDate.of(2026, Month.APRIL, 22));
//        TodoItem item3 = new TodoItem ("Finish design proposal for client",
//                "I promised Mike I'd email website mockups by Friday 22nd April", LocalDate.of(2026, Month.APRIL, 24));
//        TodoItem item4 = new TodoItem ("Pickup Doug at the train station",
//                "Doug's arriving on March 23rd on the 5:00 train", LocalDate.of(2026, Month.MARCH, 23));
//        TodoItem item5 = new TodoItem ("Pick up dry cleaning",
//                "The clothes should be ready by Friday", LocalDate.of(2026, Month.APRIL, 10));
//
//        return new ArrayList<>(List.of(item1, item2, item3, item4, item5));
//    }

}
