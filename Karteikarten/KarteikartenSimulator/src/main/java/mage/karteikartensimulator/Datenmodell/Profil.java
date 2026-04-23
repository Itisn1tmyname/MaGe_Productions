package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profil {

    public String name;
    private boolean istAktivesProfil;
    private HashMap<String, Stats> stats; //einer Karten-ID wird eine Wertung zugeordnet
    public static final Pattern profilPattern = Pattern.compile("\\s*\\{\\s*\"name\": \"(?<name>.+)\",\\s*"
            + "\"istAktivesProfil\": (?<aktiv>true|false),\\s*"
            + "\"stats\": \\[(?<stats>[\\s\\S]*)]\\s*}\\s*");

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
    public Profil(String json) throws Exception{
        Matcher matcher = profilPattern.matcher(json);

        if (matcher.matches()) {
            this.stats = new HashMap<>();
            Matcher statMatcher = Stats.statPattern.matcher(matcher.group("stats"));
            while (statMatcher.find()) {
                stats.put(statMatcher.group("id"), new Stats(statMatcher.group()));
            }
            this.name = matcher.group("name");
            this.istAktivesProfil = matcher.group("aktiv").equals("true");

        } else throw new Exception("Profil: JSON input doesn't match correctly, please check .json file!\n\nWrong input:\n"+json);
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