package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mage.karteikartensimulator.Datenmodell.*;

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
    }

    @FXML
    public void handleAnzeigenButton() {
        //TODO: Change Scene and don't change FragenText...
        labelFragentext.setText(karte.getAntwort());
    }
}
