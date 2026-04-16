package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mage.karteikartensimulator.Datenmodell.*;
import mage.karteikartensimulator.Main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Frage {

    //TODO: Change the list to not be hardcoded
    private static final List<Karteikarte> KARTEN = Data.getInstance().getKartenSets().get("alle").getKarten();

    @FXML
    private Label labelLernfeld;
    @FXML
    private Label labelFragentext;
    @FXML
    private Label labelHashtags;
    @FXML
    private Button buttonAntwortAnzeigen;
    @FXML
    private static Stage stage;

    private Karteikarte karte;
    private static Iterator<Karteikarte> iterator;

    public static boolean hasNext() {
        return iterator.hasNext();
    }

    @FXML
    public void handleAnzeigenButton() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("KarteiAntwort.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
            Antwort antwort = loader.getController();
            antwort.setContent(karte);
            stage.show();
        } catch (IOException ignored) {
            //TODO
        }
    }

    public void starten(List<Karteikarte> karten, Stage stage) {
        Frage.stage = stage;
        Antwort.setStage(stage);
        iterator = karten.iterator();
        setContent();
    }

    public void setContent(){
        if (iterator.hasNext()) {
            karte = iterator.next();
            labelLernfeld.setText(karte.getLernfeld().toString().replace("\"", ""));
            labelFragentext.setText(karte.getFrage().replace("\\n", "\n"));
            labelHashtags.setText(karte.getTagString());
        }
    }
}
