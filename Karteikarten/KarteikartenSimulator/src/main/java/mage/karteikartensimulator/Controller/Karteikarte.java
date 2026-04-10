package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mage.karteikartensimulator.Datenmodell.*;

import java.io.IOException;
import java.util.List;

public class Karteikarte {

    //TODO: Change the list to not be hardcoded
    private static final List<mage.karteikartensimulator.Datenmodell.Karteikarte> KARTEN = Data.getInstance().getTestSet().getKarten();

    @FXML
    private Label labelLernfeld;
    @FXML
    private Label labelFragentext;
    @FXML
    private Label labelAntworttext;
    @FXML
    private Label labelHashtags;
    @FXML
    private Button buttonAntwortAnzeigen;
    @FXML
    private Button buttonAntwortRichtig;
    @FXML
    private Button buttonAntwortNichtWerten;
    @FXML
    private Button buttonAntwortFalsch;

    private mage.karteikartensimulator.Datenmodell.Karteikarte karte;


    @FXML
    public void initialize(){
        this.karte = KARTEN.getFirst();
        setKartenLabels(karte);
    }

    private void setKartenLabels(mage.karteikartensimulator.Datenmodell.Karteikarte karte) {
        labelLernfeld.setText(karte.getLernfeld().toString().replace("\"", ""));
        labelFragentext.setText(karte.getFrage());
        labelHashtags.setText(karte.getTagString());
        labelAntworttext.setText(karte.getAntwort());
    }

    @FXML
    public void handleAnzeigenButton() {
        //TODO: Change Scene and don't change FragenText...
        labelFragentext.setText(karte.getAntwort());
    }
}
