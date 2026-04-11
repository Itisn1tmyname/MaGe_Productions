package mage.karteikartensimulator.Datenmodell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private static final Data instance = new Data();

    //TODO: remove testset, do the fileNames in a loop? But directories for files hardcoded.
    private static final String fileName = "KarteikartenSimulator/testset.json";

    public static final DateTimeFormatter ID_FORMATTER = DateTimeFormatter.ofPattern("yy-D-NNNN");
    public static final DateTimeFormatter PROFIL_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm");

    //TODO: Remove Testset, save config, profile, and specific set data instead...
    private KarteiSet testSet;

    private Data() {}

    public static Data getInstance() { return instance;}

    //TODO: Remove Testset,
    public KarteiSet getTestSet() {
        return testSet;
    }

    public void datenSpeichern() throws IOException{
        datenSpeichern(fileName);
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

    public static List<Karteikarte> generateKarteikarten() {
        ArrayList<Karteikarte> karten = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Karteikarte karte = new Karteikarte(
                    new ArrayList<>(List.of(
                            new Tag("LF 3.4 Hard- und Software"),
                            new Tag("Standard"),
                            new Tag("Test " + i)
                    )),
                    new Lernfeld(3, 4, "Hard- und Software"),
                    "Frage " + i,
                    "Antwort " + i);
            karten.add(karte);
        }

        return karten;
    }

    public static void populate(String fileName) throws Exception{
        Path path = Path.of(fileName);
        switch (fileName) {
            case "config/Karten/alle.json" : {
                try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                    bw.write("""
                            {
                            	"name": "testset",
                            	"info": "Hardcoded Testing Set. Remove before release...",
                            	"karten": [
                            		{
                            			"id": "1",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 1"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 1",\s
                            			"antwort": "Antwort 1",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "2",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 2"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 2",\s
                            			"antwort": "Antwort 2",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "3",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 3"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 3",\s
                            			"antwort": "Antwort 3",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "4",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 4"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 4",\s
                            			"antwort": "Antwort 4",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "5",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 5"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 5",\s
                            			"antwort": "Antwort 5",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "6",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 6"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 6",\s
                            			"antwort": "Antwort 6",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "7",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 7"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 7",\s
                            			"antwort": "Antwort 7",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "8",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 8"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 8",\s
                            			"antwort": "Antwort 8",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "9",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 9"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 9",\s
                            			"antwort": "Antwort 9",\s
                            			"farbe": "#000000"
                            		},
                            		{
                            			"id": "10",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 10"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 10",\s
                            			"antwort": "Antwort 10",\s
                            			"farbe": "#000000"
                            		}
                            	]
                            }""");
                }
                break;
            }
            case "config/config.json" : {
                try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                    bw.write("""
                            """);
                }
                break;
            }
            case "config/Profile/standard.json" : {
                try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                    bw.write("""
                            {
                                "name": "Standard",
                                "istAktivesProfil": true,
                                "stats": {
                                    "1": 0,
                                    "2": 0,
                                    "3": 0,
                                    "4": 0,
                                    "5": 0,
                                    "6": 0,
                                    "7": 0,
                                    "8": 0,
                                    "9": 0,
                                    "10": 0
                                },
                                "dateLastChecked": {
                                    "1": "06-04-11/13-16",
                                    "2": "06-04-11/13-16",
                                    "3": "06-04-11/13-16",
                                    "4": "06-04-11/13-16",
                                    "5": "06-04-11/13-16",
                                    "6": "06-04-11/13-16",
                                    "7": "06-04-11/13-16",
                                    "8": "06-04-11/13-16",
                                    "9": "06-04-11/13-16",
                                    "10": "06-04-11/13-16"
                                },
                                "dateLastRichtig": {
                                    "1": "06-04-11/13-16",
                                    "2": "06-04-11/13-16",
                                    "3": "06-04-11/13-16",
                                    "4": "06-04-11/13-16",
                                    "5": "06-04-11/13-16",
                                    "6": "06-04-11/13-16",
                                    "7": "06-04-11/13-16",
                                    "8": "06-04-11/13-16",
                                    "9": "06-04-11/13-16",
                                    "10": "06-04-11/13-16"
                                }
                            }""");
                }
                break;
            }
            default: throw new Exception("Check your Code, Problem populating hardcoded files (name error)!");
        }
    }

}
