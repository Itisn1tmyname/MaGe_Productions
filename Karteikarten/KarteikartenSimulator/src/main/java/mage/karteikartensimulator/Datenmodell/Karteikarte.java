package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final Pattern kartenPattern = Pattern.compile("\\s*\\{\\s*\"id\": \"(?<id>.+)\",\\s*"
            + "\"tags\": \\[\\s*(?<tags>(?:\".*\")*)],\\s*"
            + "\"lernfeld\": \"LF (?<zahl1>[0-9]+).(?<zahl2>[0-9]+): (?<name>.+)\",\\s*"
            + "\"frage\": \"(?<frage>.+)\",\\s*"
            + "\"antwort\": \"(?<antwort>.+)\",\\s*"
            + "\"farbe\": \"(?<farbe>#[0-9A-F]{6})\"\\s*}\\s*");

    public Karteikarte(String id, ArrayList<Tag> tags, Lernfeld lernfeld, String frage, String antwort, String farbe) {
        this.id = id;
        this.tags = tags;
        this.lernfeld = lernfeld;
        this.frage = frage;
        this.antwort = antwort;
        this.farbe = farbe;
    }

    /**
     *
     * @param json This constructor accepts a json-formatted String. E.g.:
     *     {
     *        "id": "1",
     *        "tags": [
     *             "LF 3.4 Hard- und Software",
     *             "Standard",
     *             "Test 1"
     *        ],
     *        "lernfeld": "LF 3.4: Hard- und Software",
     *        "frage": "Frage 1",
     *        "antwort": "Antwort 1",
     *        "farbe": "#000000"
     *     }
     */
    public Karteikarte(String json) throws Exception{

        Pattern tagRegex = Pattern.compile("\"(.+)\"");

        Matcher matcher = kartenPattern.matcher(json);

        if (matcher.matches()) {

            Matcher tagMatcher = tagRegex.matcher(matcher.group("tags"));

            this.tags = new ArrayList<>();
            while (tagMatcher.find()){
                tags.add(new Tag(tagMatcher.group(1)));
            }

            this.id = matcher.group("id");
            this.lernfeld = new Lernfeld(
                    Integer.parseInt(matcher.group("zahl1")),
                    Integer.parseInt(matcher.group("zahl2")),
                    matcher.group("name")
            );
            this.frage = matcher.group("frage");
            this.antwort = matcher.group("antwort");
            this.farbe = matcher.group("farbe");

        } else {
            System.out.println(json);
            throw new Exception("JSON input doesn't match correctly, please check .json file!");
        }
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
