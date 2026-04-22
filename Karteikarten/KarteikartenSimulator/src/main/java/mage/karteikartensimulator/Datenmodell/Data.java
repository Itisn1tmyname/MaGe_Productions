package mage.karteikartensimulator.Datenmodell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private static final Data instance = new Data();

    public static final String CONFIG_DIRECTORY = "Karteikarten-config/";
    public static final String SET_ALLE_FILENAME = "Karten/alle.json";
    public static final String CONFIG_FILENAME = "config.json";
    public static final String PROFIL_STANDARD_FILENAME = "Profile/standard.json";

    public static final DateTimeFormatter ID_FORMATTER = DateTimeFormatter.ofPattern("yy-D-NNNN");
    public static final DateTimeFormatter PROFIL_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm");

    //TODO: Make these collections observable. Instantiate with FXCollections.observable...
    private static final ObservableMap<String, KarteiSet> kartenSets = FXCollections.observableHashMap();
    private static final ObservableMap<String, Profil> profile = FXCollections.observableHashMap();
    private static final Einstellungen einstellungen = new Einstellungen();

    //TODO: Booleans für Zustand der Daten: Hat der Nutzer eine Änderung vorgenommen?

    private Data() {}

    public static Data getInstance() { return instance;}

    public Map<String, KarteiSet> getKartenSets() {
        return kartenSets;
    }

    public Map<String, Profil> getProfile() {
        return profile;
    }

    //TODO: Speichere nicht nur Kartensets, sondern auch Profile und Einstellungen
    public void datenSpeichern() throws IOException{
        kartenSpeichern();
        profileSpeichern();
        configSpeichern();
    }

    private void kartenSpeichern() throws IOException {
        //TODO: Nicht nur Kartenset mit Name "alle" speichern, sondern Map kartenSets nach Strings in einer Schleife durchlaufen
        //TODO: Speichern (in Schleife) nur, wenn bestimmter Boolean (haben sich die Daten geändert?) true ist...
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(CONFIG_DIRECTORY + SET_ALLE_FILENAME))) {
            bw.write(kartenSets.get("alle").toString());
        }
    }

    private void profileSpeichern() {
        //TODO: Speichern nur, wenn bestimmter Boolean (haben sich die Daten geändert?) true ist...
    }

    private void configSpeichern() {
        //TODO: Speichern nur, wenn bestimmter Boolean (haben sich die Daten geändert?) true ist...
    }

    public void datenLaden() throws IOException{
        kartenLaden();
        profileLaden();
        configLaden();
    }

    private void kartenLaden(){
        //TODO: Nicht nur alle.json laden, sondern in einer Schleife sämtliche im Ordner Karten enthaltene .json files durchgehen...
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(CONFIG_DIRECTORY + SET_ALLE_FILENAME))) {
            String input;
            while((input = br.readLine()) != null) {
                sb.append(input);
            }
            kartenSets.put("alle", new KarteiSet(sb.toString()));
        } catch (Exception e) {
            //TODO: Exception Handling!
            System.out.println(e.getMessage());
            System.out.println("Exception in Data.kartenladen()!");
        }
    }

    private void profileLaden(){
        //TODO
    }

    private void configLaden() {
        //TODO
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
        //TODO: Change hardcoded values
        Path path = Path.of(CONFIG_DIRECTORY + fileName);
        switch (fileName) {
            case SET_ALLE_FILENAME: {
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
                            			"antwort": "Antwort 1"
                            		},
                            		{
                            			"id": "2",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 2"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 2",\s
                            			"antwort": "Antwort 2"
                            		},
                            		{
                            			"id": "3",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 3"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 3",\s
                            			"antwort": "Antwort 3"
                            		},
                            		{
                            			"id": "4",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 4"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 4",\s
                            			"antwort": "Antwort 4"
                            		},
                            		{
                            			"id": "5",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 5"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 5",\s
                            			"antwort": "Antwort 5"
                            		},
                            		{
                            			"id": "6",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 6"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 6",\s
                            			"antwort": "Antwort 6"
                            		},
                            		{
                            			"id": "7",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 7"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 7",\s
                            			"antwort": "Antwort 7"
                            		},
                            		{
                            			"id": "8",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 8"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 8",\s
                            			"antwort": "Antwort 8"
                            		},
                            		{
                            			"id": "9",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 9"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 9",\s
                            			"antwort": "Antwort 9"
                            		},
                            		{
                            			"id": "10",\s
                            			"tags": ["LF 3.4 Hard- und Software", "Standard", "Test 10"],\s
                            			"lernfeld": "LF 3.4: Hard- und Software",\s
                            			"frage": "Frage 10",\s
                            			"antwort": "Antwort 10"
                            		}
                            	]
                            }""");
                }
                break;
            }
            case CONFIG_FILENAME : {
                try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                    bw.write("""
                            """);
                }
                break;
            }
            case PROFIL_STANDARD_FILENAME : {
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
