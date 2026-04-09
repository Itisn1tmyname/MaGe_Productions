package mage.karteikartensimulator.Datenmodell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Data {

    private static final Data instance = new Data();
    private static final String fileName = "testset.json";

    private List<Karteikarte> karteikarten;

    private Data() {
        karteikarten = generateKarteikarten();
    }

    public static Data getInstance() { return instance;}

    public void datenSpeichern() throws IOException {

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName))) {
            bw.write("{\"name\": \"" + fileName.replace(".json", "") + "\", " +
                    "\"info\": \"Hardcoded Testing Set. Remove before release...\", \"karten\": [\n");
            Iterator<Karteikarte> iter = karteikarten.iterator();
            while (iter.hasNext()){
                Karteikarte karte = iter.next();
                bw.write(karte + (iter.hasNext() ? ",\n" : "\n"));
            }
            bw.write("]}");
        }
    }

    public void datenLaden() throws IOException{

    }

    private List<Karteikarte> generateKarteikarten() {
        ArrayList<Karteikarte> karten = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Karteikarte karte = new Karteikarte(
                    String.valueOf(i),
                    new ArrayList<>(List.of(
                            new Tag("LF 3.4 Hard- und Software"),
                            new Tag("Standard"),
                            new Tag("Test " + i)
                    )),
                    new Lernfeld(3, 4, "Hard- und Software"),
                    "Frage " + i,
                    "Antwort " + i,
                    "#000000");
            karten.add(karte);
        }

        return karten;
    }


}
