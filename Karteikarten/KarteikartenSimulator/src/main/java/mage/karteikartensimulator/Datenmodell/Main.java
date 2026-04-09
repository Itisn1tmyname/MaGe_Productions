package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String test = """
                {"id": "1", "tags": ["LF 3.4 Hard- und Software", "Standard", "Test 1"], "lernfeld": "LF 3.4: Hard- und Software", "frage": "Frage 1", "antwort": "Antwort 1", "farbe": "#000000"},""";

        String[] split = test.split("[\\[\\]]");

        String id = split[0].replaceAll("(\\{\"id\": \")|(\", \"tags\": )", "");

        String[] tagArray = split[1].replace("\"", "").split(", ");
        ArrayList<Tag> tags = new ArrayList<>();
        for (String t : tagArray) tags.add(new Tag(t));

        split = split[2].replaceFirst(", ", "").split(", ");
        for (int i = 0; i < split.length; i++) split[i] = split[i]
                .replaceAll(".*\": ", "").replace("\"", "");

        String zahl1 = split[0].replaceAll("(LF )|\\..*", "");
        String zahl2 = split[0].replaceAll("(LF [0-9]+\\.)|:.*", "");

        int z1 = Integer.parseInt(zahl1);
        int z2 = Integer.parseInt(zahl2);

        Lernfeld lernfeld = new Lernfeld(z1, z2, split[0].replaceAll("(LF [0-9]+\\.[0-9]+: )", ""));



        System.out.println(id);
        System.out.println(tags);
        System.out.println(z1);
        System.out.println(z2);
        System.out.println(lernfeld.name());
        System.out.println(lernfeld);
        System.out.println(Arrays.toString(split));
    }

}
