package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.Karteikarte;

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

    private Karteikarte karte;


    @FXML
    public void initialize(){
        this.karte = KARTEN.getFirst();
        setKartenLabels(karte);
    }

    private void setKartenLabels(Karteikarte karte) {
        labelLernfeld.setText(karte.getLernfeld().toString().replace("\"", ""));
        labelHashtags.setText(karte.getTagString());
        labelAntworttext.setText(karte.getAntwort().replace("\\n", "\n"));
    }

}
