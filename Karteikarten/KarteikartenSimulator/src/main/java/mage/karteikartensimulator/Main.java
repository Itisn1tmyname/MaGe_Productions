package mage.karteikartensimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mage.karteikartensimulator.Datenmodell.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {


    private static final String VERSION = "0.1";
    private Profil profil;
    private ArrayList<KarteiSet> sets;



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                "mainMenu.fxml"
//                "KarteiFrage.fxml"
//                "KarteiAntwort.fxml"
//                "KarteiFrage.fxml"
//                "OptionenMenu.fxml"
        ));

        stage.setTitle("Karteikarten Simulator ver. " + VERSION);
        stage.setFullScreen(true);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public static void main(String[] args) {

//        launch(args);

        try {
            Data.getInstance().datenSpeichern();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
