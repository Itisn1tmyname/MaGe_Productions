package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.Karteikarte;
import mage.karteikartensimulator.Main;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Antwort {

    //TODO: Fix fxml File - Schatten ist nicht durchsichtig!

    @FXML
    private Label labelLernfeld;
    @FXML
    private Label labelAntworttext;
    @FXML
    private Label labelHashtags;
    @FXML
    private static Stage stage;
    @FXML
    private Button buttonAntwortNichtWerten;

    public static void setStage(Stage stage) {
        Antwort.stage = stage;
    }

    public void setContent(Karteikarte karte){
        labelLernfeld.setText(karte.getLernfeld().toString().replace("\"", ""));
        labelHashtags.setText(karte.getTagString());
        labelAntworttext.setText(karte.getAntwort().replace("\\n", "\n"));
        buttonAntwortNichtWerten.requestFocus();
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
                stage.hide();
                stage.setScene(new Scene(loader.load()));
                stage.getScene().setFill(Color.TRANSPARENT);
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

    @FXML
    public void handleClose() {
        stage.close();
    }
}
