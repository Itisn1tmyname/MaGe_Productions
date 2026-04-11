package mage.karteikartensimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    private Profil profil;
    private HashMap<String, Karteikarte> masterMap;
    private ArrayList<KarteiSet> sets;
    public static Stage stage;

    //TODO: Booleans für Zustand der Daten: Hat der Nutzer eine Änderung vorgenommen?



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                "MainMenu.fxml"
//                "KarteiFrage.fxml"
//                "KarteiAntwort.fxml"
//                "KarteiFrage.fxml"
//                "OptionenMenu.fxml"
        ));

        stage.setTitle("Karteikarten Simulator ver. " + VERSION);
        stage.setFullScreen(true);
        stage.setScene(new Scene(fxmlLoader.load()));
        Main.stage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void init() throws Exception {
        String[] requiredFiles = {
                "config/Karten/alle.json",
                "config/config.json",
                "config/Profile/standard.json"
        };

        for (String fileName : requiredFiles) {
            Path path = Path.of(fileName);

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

    public Profil getProfil() {
        return profil;
    }

    public HashMap<String, Karteikarte> getMasterMap() {
        return masterMap;
    }

    public ArrayList<KarteiSet> getSets() {
        return sets;
    }
}
