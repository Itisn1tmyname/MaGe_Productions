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

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName))) {
            bw.write("{\"name\": \"" + fileName.replace(".json", "") + "\", " +
                    "\"info\": \"Hardcoded Testing Set. Remove before release...\", \"karten\": [\n");

            Iterator<Karteikarte> iter = testSet.getKarten().iterator();
            while (iter.hasNext()){
                Karteikarte karte = iter.next();
                bw.write(karte + (iter.hasNext() ? ",\n" : "\n"));
            }
            bw.write("]}");
        }
    }

    public void datenLaden() throws IOException{
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String input;
            KarteiSet in = new KarteiSet(null, null, new ArrayList<>());
            int i = 0;
            while (!(input = br.readLine()).equals("]}")) {
                if (i == 0) {
                    String[] setData = input.split(", ");
                    in = new KarteiSet(setData[0].replaceFirst("\\{\"name\": ", "").replace("\"", ""),
                            setData[1].replaceFirst("\"info\": ", "").replace("\"", ""),
                            new ArrayList<>());
                } else {
                    String[] split = input.split("[\\[\\]]");

                    String id = split[0].replaceAll("(\\{\"id\": \")|(\", \"tags\": )", "");

                    String[] tagArray = split[1].replace("\"", "").split(", ");
                    ArrayList<Tag> tags = new ArrayList<>();
                    for (String t : tagArray) tags.add(new Tag(t));

                    split = split[2].replaceFirst(", ", "").split(", ");
                    for (int j = 0; j < split.length; j++) split[j] = split[j]
                            .replaceAll(".*\": ", "").replace("\"", "");

                    int zahl1 = Integer.parseInt(split[0].replaceAll("(LF )|\\..*", ""));
                    int zahl2 = Integer.parseInt(split[0].replaceAll("(LF [0-9]+\\.)|:.*", ""));
                    Lernfeld lernfeld = new Lernfeld(zahl1, zahl2, split[0].replaceAll("(LF [0-9]+\\.[0-9]+: )", ""));

                    in.getKarten().add(new Karteikarte(
                            id,
                            tags,
                            lernfeld,
                            null,
                            null,
                            null
                    ));
                }
                i++;
            }
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
