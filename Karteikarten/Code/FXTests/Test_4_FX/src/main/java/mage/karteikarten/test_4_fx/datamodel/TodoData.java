package mage.karteikarten.test_4_fx.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {

    private static final TodoData instance = new TodoData();
    private static final String fileName = "Code/FXTests/Test_4_FX/todoList.txt";
    private List<TodoItem> todoItems;
    private final DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }

    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void addTodoItem (TodoItem item) {
        todoItems.add(item);
    }

    public void loadTodoItems() throws IOException {

        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        String input;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);

                TodoItem item = new TodoItem(shortDescription, details, date);
                todoItems.add(item);
            }
        }
    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (TodoItem item : todoItems) {
                bw.write(String.format("%s\t%s\t%s\n", item.getShortDescription(),
                        item.getDetails(), formatter.format(item.getDeadline())));
            }
        }
    }
}
