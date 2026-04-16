package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.Karteikarte;
import mage.karteikartensimulator.Main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Antwort {

    //TODO: Change the list to not be hardcoded
    private static final List<Karteikarte> KARTEN = Data.getInstance().getKartenSets().get("alle").getKarten();

    @FXML
    private Label labelLernfeld;
    @FXML
    private Label labelAntworttext;
    @FXML
    private Label labelHashtags;
    @FXML
    private Button buttonAntwortRichtig;
    @FXML
    private Button buttonAntwortNichtWerten;
    @FXML
    private Button buttonAntwortFalsch;
    @FXML
    private static Stage stage;

    public static void setStage(Stage stage) {
        Antwort.stage = stage;
    }

    public void setContent(Karteikarte karte){
        labelLernfeld.setText(karte.getLernfeld().toString().replace("\"", ""));
        labelHashtags.setText(karte.getTagString());
        labelAntworttext.setText(karte.getAntwort().replace("\\n", "\n"));
    }

    @FXML
    public void handleNichtWerten() {
        next();
    }

    @FXML
    public void handleRichtig() {
        //TODO: Karte werten! Wertung über Profil...
        next();
    }

    @FXML
    public void handleFalsch() {
        //TODO:Karte werten! Profil...
        next();
    }

    private void next() {
        if (Frage.hasNext()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("KarteiFrage.fxml"));
            try {
                stage.setScene(new Scene(loader.load()));
                Frage frage = loader.getController();
                frage.setContent();
                stage.show();
            } catch (IOException ignored) {
                //TODO
            }
        } else {
            stage.close();
        }
    }
}
