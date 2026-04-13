package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.Karteikarte;

public class Erstellen {

    @FXML
    private TextArea textAreaFrage;
    @FXML
    private TextArea textAreaAntwort;
    @FXML
    private TextField textZ1;
    @FXML
    private TextField textZ2;
    @FXML
    private TextField textLernfeld;
    @FXML
    private TextField textTags;
    @FXML
    private DialogPane dialogPane;


    public void initialize(){
        dialogPane.setMinWidth(630);
        dialogPane.setMinHeight(655);
    }

    public void karteErstellen(){
        String frage = textAreaFrage.getText().trim().replace("\n", "\\n");
        String antwort = textAreaAntwort.getText().trim().replace("\n", "\\n");
        String z1 = textZ1.getText().trim();
        String z2 = textZ2.getText().trim();
        String lf = textLernfeld.getText().trim();
        String[] tags = textTags.getText().split(",");

        for (int i = 0; i < tags.length; i++) tags[i] = tags[i].trim();

        Karteikarte karte = new Karteikarte(frage, antwort, z1, z2, lf, tags);

        //TODO: println entfernen
        System.out.println(karte);

        //TODO: Set, in dem gespeichert wird, anpassen. In Data eine Funktion zum hinzufügen einer Karte erstellen,
        // damit neue Karten immer auch zu "alle.json" hinzugefügt werden. Auswahlmöglichkeit hinzufügen, um dem User
        // zu ermöglichen, die Ziel-Sets zu wählen.
        Data.getInstance().getKartenSets().get("alle").getKarten().add(karte);
    }
}
