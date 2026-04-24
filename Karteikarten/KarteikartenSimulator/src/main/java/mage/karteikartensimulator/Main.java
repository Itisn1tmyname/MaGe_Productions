package mage.karteikartensimulator;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mage.karteikartensimulator.Controller.MainMenu;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.KarteiSet;
import mage.karteikartensimulator.Datenmodell.Karteikarte;
import mage.karteikartensimulator.Datenmodell.Profil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {


    private static final String VERSION = "0.1";
    private static Stage mainStage;


    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                "MainMenu.fxml"
//                "KarteiFrage.fxml"
//                "KarteiAntwort.fxml"
//                "KarteiFrage.fxml"
//                "DialogOptionenMenu.fxml"
        ));

        stage.setTitle("Karteikarten Simulator ver. " + VERSION);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setScene(new Scene(fxmlLoader.load()));
        MainMenu mainMenu = fxmlLoader.getController();
        stage.fullScreenProperty().addListener((_,_,n) -> {
            if (n) mainMenu.setPaneInsets(0);
            else mainMenu.setPaneInsets(12);
            mainMenu.setFullScreenIcons(n);
        });
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getScene().setFill(Color.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void init() throws Exception {
        String[] requiredFiles = {
                Data.CONFIG_FILENAME,
                Data.SET_ALLE_FILENAME,
                Data.PROFIL_STANDARD_FILENAME
        };

        for (String fileName : requiredFiles) {
            Path path = Path.of(Data.CONFIG_DIRECTORY + fileName);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                Data.populate(fileName);
            }
        }

        Data.getInstance().datenLaden();
    }

    @Override
    public void stop() throws Exception {
        Data.getInstance().datenSpeichern();
    }

    public static Stage getStage() {
        return mainStage;
    }
}
