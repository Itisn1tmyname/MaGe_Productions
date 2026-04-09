package mage.karteikarten.test_1_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                //"gridPaneTest.fxml"
                //"HBoxTest.fxml"
                //"gridPaneTest.fxml"
                //"BorderPaneTest.fxml"
                "FlowTileStackPaneTest.fxml"
        ));

//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);
//        root.setVgap(10);
//        root.setHgap(10);
//
//        Label greeting = new Label("Welcome to JavaFX!");
//        root.getChildren().add(greeting);
//
//        stage.setTitle("Hello World!");
//        stage.setScene(new Scene(root, 300, 275));
//        stage.show();
        Scene scene = new Scene(fxmlLoader.load(), 420, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
