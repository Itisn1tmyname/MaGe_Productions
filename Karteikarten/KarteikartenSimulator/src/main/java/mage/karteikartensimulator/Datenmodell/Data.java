package mage.karteikartensimulator.Datenmodell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data {

    private static final Data instance = new Data();
    private static final String fileName = "testset.json";

    private KarteiSet testSet;

    private Data() {}

    public static Data getInstance() { return instance;}

    public KarteiSet getTestSet() {
        return testSet;
    }

    public void datenSpeichern() throws IOException{
        datenSpeichern(testSet.getName() + ".json");
    }

    private void datenSpeichern(String fileName) throws IOException {
        //TODO: Speichern nur, wenn bestimmter Boolean (haben sich die Daten geändert?) true ist...

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName))) {
            bw.write(testSet.toString());
        }
    }

    public void datenLaden() throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String input;
            while((input = br.readLine()) != null) {
                sb.append(input);
            }
            testSet = new KarteiSet(sb.toString());
        } catch (Exception e) {
            //TODO: Exception Handling!
            System.out.println(e.getMessage());
        }
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
