package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

record Tag(String name){
    @Override
    public String toString() {
        return "\"" + name + "\"";
    }
}
record Lernfeld(int zahl1, int zahl2, String name){
    @Override
    public String toString() {
        return "\"LF " + zahl1 + "." + zahl2 + ": " + name +"\"";
    }
}


public class Karteikarte {

    private String id;
    private List<Tag> tags;
    private Lernfeld lernfeld;
    private String frage;
    private String antwort;
    private String farbe;

    public Karteikarte(String id, ArrayList<Tag> tags, Lernfeld lernfeld, String frage, String antwort, String farbe) {
        this.id = id;
        this.tags = tags;
        this.lernfeld = lernfeld;
        this.frage = frage;
        this.antwort = antwort;
        this.farbe = farbe;
    }

    @Override
    public String toString() {
        return '{' +
                "\"id\": \"" + id + '\"' +
                ", \"tags\": " + tags +
                ", \"lernfeld\": " + lernfeld +
                ", \"frage\": \"" + frage + '\"' +
                ", \"antwort\": \"" + antwort + '\"' +
                ", \"farbe\": \"" + farbe + '\"' +
                '}';
    }
}
