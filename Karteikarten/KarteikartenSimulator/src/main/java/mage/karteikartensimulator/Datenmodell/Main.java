package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //TODO: Testing Main, delete this when not needed anymore!
        List<Karteikarte> karten = Data.generateKarteikarten();
        karten.forEach(System.out::println);

        String test = "Frage 10";
        int t = (test.hashCode()%26)+65;
        System.out.println((char) t + ": " + t);
        System.out.println(test.hashCode());
    }

}
