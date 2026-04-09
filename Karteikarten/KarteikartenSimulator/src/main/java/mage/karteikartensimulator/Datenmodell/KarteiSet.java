package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.List;

public class KarteiSet {

    private String name;
    private String info;
    private ArrayList<Karteikarte> karten;

    public KarteiSet(String name, String info, ArrayList<Karteikarte> karten) {
        this.name = name;
        this.info = info;
        this.karten = karten;
    }

    public List<Karteikarte> getKarten() {
        return karten;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    //TODO
}