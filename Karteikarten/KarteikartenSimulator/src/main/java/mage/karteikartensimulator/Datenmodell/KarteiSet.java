package mage.karteikartensimulator.Datenmodell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KarteiSet {

    private String name;
    private String info;
    private ArrayList<Karteikarte> karten;

    //TODO: JSON anpassen, wenn Karten als Map mit Key->Value übergeben werden!
    public static final Pattern setPattern = Pattern.compile("\\s*\\{\\s*\"name\": \"(?<name>.+)\",\\s*"
            + "\"info\": \"(?<info>.+)\",\\s*\"karten\": \\[\\s*(?<karten>[\\s\\S]*)]\\s*}\\s*");

    public KarteiSet(String name, String info, ArrayList<Karteikarte> karten) {
        this.name = name;
        this.info = info;
        this.karten = karten;
    }

    /**
     *
     * @param json This constructor accepts a json-formatted String. E.g.:
     *       {
     *          "name": "testset",
     *          "info": "Hardcoded Testing Set",
     *          "karten": [
     *               {...},
     *               {...},
     *               {...}, ...
     *          ]
     *       }
     *
     */
    public KarteiSet(String json) throws Exception{

        Matcher matcher = setPattern.matcher(json);

        if (matcher.matches()) {

            this.karten = new ArrayList<>();
            String[] k = matcher.group("karten").split("},");

            for (int i = 0; i < k.length; i++) {
                karten.add(new Karteikarte((i < k.length - 1) ? k[i] + "}" : k[i]));
            }

            this.name = matcher.group("name");
            this.info = matcher.group("info");

        } else {
            throw new Exception("JSON input doesn't match correctly, please check .json file!");
        }

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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("{\n\t\"name\": \"" + name + "\",\n\t\"info\": \"" + info + "\",\n\t\"karten\": [");
        Iterator<Karteikarte> iter = karten.iterator();
        while (iter.hasNext()) {
            sb.append("\n\t\t").append(iter.next().toString().replace("\t", "\t\t\t").replace("}", "\t\t}"));
            if (iter.hasNext()) sb.append(",");
        }
        sb.append("\n\t]\n}");

        return sb.toString();
    }
}