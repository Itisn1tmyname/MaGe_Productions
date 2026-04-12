package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Profil {

    public String name;
    private boolean istAktivesProfil;
    private HashMap<String, Integer> stats; //einer Karten-ID wird eine Wertung zugeordnet
    private HashMap<String, LocalDateTime> dateLastChecked;
    private HashMap<String, LocalDateTime> dateLastRichtig;

    public Profil(String json) {
        //TODO
    }

    public Profil(String name, HashMap<String, Integer> stats) {
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
        return stats.get(kartenID);
    }

    public void stufeSteigern(String kartenID){
        stats.merge(kartenID, 1, (old, nw) -> (old > 0) ? old + nw : nw);
    }

    public void stufeKleiner(String kartenID) {
        stats.merge(kartenID, 0, (old, nw) -> (old > 0) ? nw : old - 1) ;
    }

}