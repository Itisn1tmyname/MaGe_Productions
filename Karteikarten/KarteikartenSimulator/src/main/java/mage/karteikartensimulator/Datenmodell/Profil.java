package mage.karteikartensimulator.Datenmodell;

import java.util.HashMap;

public class Profil {

    public String name;
    private HashMap<String, Integer> stats;

    public Profil(String json) {
        //TODO
    }

    @Override
    public String toString() {
        //TODO
        return "Steuerung.Profil{" +
                "name='" + name + '\'' +
                ", stats=" + stats +
                '}';
    }

    public int getSerie(String frage){
        return stats.get(frage);
    }

    public void adjustSerie(String frage, int val){
        stats.merge(frage, val, Integer::sum);
    }

}