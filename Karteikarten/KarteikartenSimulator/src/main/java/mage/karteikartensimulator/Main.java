package mage.karteikartensimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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


    @Override
    public void start(Stage stage) throws IOException {

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
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(fxmlLoader.load()));
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
}
