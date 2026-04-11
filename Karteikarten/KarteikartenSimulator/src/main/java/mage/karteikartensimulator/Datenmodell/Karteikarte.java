package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Karteikarte {

    private String id;
    private List<Tag> tags;
    private Lernfeld lernfeld;
    private String frage;
    private String antwort;
    private String farbe = "#000000";

    public static final Pattern kartenPattern = Pattern.compile("\\s*\\{\\s*\"id\": \"(?<id>.+)\",\\s*"
            + "\"tags\": \\[\\s*(?<tags>(?:\".*\")*)],\\s*"
            + "\"lernfeld\": \"LF (?<zahl1>[0-9]+).(?<zahl2>[0-9]+): (?<name>.+)\",\\s*"
            + "\"frage\": \"(?<frage>.+)\",\\s*"
            + "\"antwort\": \"(?<antwort>.+)\",\\s*"
            + "\"farbe\": \"(?<farbe>#[0-9A-F]{6})\"\\s*}\\s*"
    );
    private static final Pattern tagPattern = Pattern.compile("\"([^\"]*)\"");

    public Karteikarte(ArrayList<Tag> tags, Lernfeld lernfeld, String frage, String antwort) {

        Tag lf = new Tag(lernfeld.toString().replace("\"", ""));
        if (!tags.contains(lf))
            tags.addFirst(lf);

        this.tags = tags;
        this.lernfeld = lernfeld;
        this.frage = frage;
        this.antwort = antwort;
        this.id = calculateID();
    }

    //TODO: Validierung der Daten statt Exception werfen...
    public Karteikarte(String frage, String antwort, String zahl1, String zahl2, String lernfeldname, String... tags)
            throws NumberFormatException {
        int z1 = Integer.parseInt(zahl1);
        int z2 = Integer.parseInt(zahl2);

        this.tags = new ArrayList<>();

        for (String tag : tags) {
            if (!tag.isEmpty()) this.tags.add(new Tag(tag));
        }

        this.lernfeld = new Lernfeld(z1, z2, lernfeldname);

        Tag lf = new Tag(lernfeld.toString().replace("\"", ""));
        if (!this.tags.contains(lf))
            this.tags.addFirst(lf);

        this.frage = frage;
        this.antwort = antwort;
        this.id = calculateID();
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

        Matcher matcher = kartenPattern.matcher(json);

        if (matcher.matches()) {

            Matcher tagMatcher = tagPattern.matcher(matcher.group("tags"));

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

    private String calculateID() {
        return LocalDateTime.now().format(Data.ID_FORMATTER) + "_" + (char)(Math.abs(this.frage.hashCode()%26)+65)
                + "/" + (char)(Math.abs(this.antwort.hashCode()%26)+65);
    }


    @Override
    public String toString() {
        return '{' +
                "\n\t\"id\": \"" + id + '\"' +
                ", \n\t\"tags\": " + tags +
                ", \n\t\"lernfeld\": " + lernfeld +
                ", \n\t\"frage\": \"" + frage + '\"' +
                ", \n\t\"antwort\": \"" + antwort + '\"' +
                ", \n\t\"farbe\": \"" + farbe + '\"' +
                "\n}";
    }

    public String getId() {
        return id;
    }

    public String getFrage() {
        return frage;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getTagString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tags.size(); i++){
            sb.append("#").append(tags.get(i).name().replace(" ", "_"));
            if (i < tags.size() - 1) sb.append("   ");
        }

        return sb.toString();
    }

    public Lernfeld getLernfeld() {
        return lernfeld;
    }

    public String getAntwort() {
        return antwort;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }
}
