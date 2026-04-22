package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Profil {

    public String name;
    private boolean istAktivesProfil;
    private HashMap<String, Stats> stats; //einer Karten-ID wird eine Wertung zugeordnet

    /**
     *
     * @param json - This constructor accepts a json-formatted String in the following form:
     *             {
     *                "name": "standard",
     *                "istAktivesProfil": true,
     *                "stats": [
     *                   {
     *                      "id": "ID-1",
     *                      "evaluation": 0,
     *                      "dateLastChecked": "2026.04.22 14:09",
     *                      "dateLastRichtig": "2026.04.22 14:09"
     *                   },
     *                   {...},
     *                   ...
     *                ]
     *             }
     *
     */
    public Profil(String json) {
        //TODO
    }

    public Profil(String name, HashMap<String, Stats> stats) {
        this.name = name;
        this.stats = stats;
    }

    @Override
    public String toString() {

        System.out.println("Achtung! Profil.toString() ist derzeit fehlerhaft, stats werden nicht ordnungsgemäß übersetzt!");

        return '{' +
                "\"name\": \"" + name + '\"' +
                ", \"stats\": " + stats +
                '}';
    }

    public int getStufe(String kartenID){
        return stats.get(kartenID).evaluation();
    }

    public void stufeSteigern(String kartenID){
        if (stats.containsKey(kartenID)) stats.get(kartenID).stufeSteigern();
        else stats.put(kartenID, new Stats(kartenID, 1));
    }

    public void stufeKleiner(String kartenID){
        if (stats.containsKey(kartenID)) stats.get(kartenID).stufeKleiner();
        else stats.put(kartenID, new Stats(kartenID, 0));
    }

    public Stats getStats(String kartenID){
        return stats.get(kartenID);
    }
}