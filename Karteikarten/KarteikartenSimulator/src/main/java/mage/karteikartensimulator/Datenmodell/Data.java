package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Data {

    private static final Data instance = new Data();
    private static final String fileName = "test.txt";

    private List<Karteikarte> karteikarten;

    private Data() {
        karteikarten = generateKarteikarten();
    }

    public Data getInstance() { return instance;}

    public void datenSpeichern() {

    }

    public void datenLaden() {

    }

    private List<Karteikarte> generateKarteikarten() {
        ArrayList<Karteikarte> karten = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Karteikarte karte = new Karteikarte(
                    new HashSet<Tag>(List.of(
                            new Tag("LF 3.4 Hard- und Software"),
                            new Tag("Standard"),
                            new Tag("Test " + i)
                    )),
                    new Lernfeld(3, 4, "Hard- und Software"),
                    "Frage " + i,
                    "Antwort " + i,
                    "#000000");
        }

        return karten;
    }


}
