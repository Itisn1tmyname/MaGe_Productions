package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stats {

    private String id;
    private Integer evaluation;
    private LocalDateTime dateLastChecked;
    private LocalDateTime dateLastRichtig;

    public static final Pattern statPattern = Pattern.compile("\\s*\\{\\s*\"id\": \"(?<id>.+)\",\\s*"
            + "\"evaluation\": (?<eval>-*\\d+),\\s*"
            + "\"dateLastChecked\": \"(?<dateCheck>\\d{4}.\\d{2}.\\d{2} \\d{2}:\\d{2})\",\\s*"
            + "\"dateLastRichtig\": \"(?<dateRicht>\\d{4}.\\d{2}.\\d{2} \\d{2}:\\d{2})\"\\s*}\\s*");


    /**
     *
     * @param json - this Constructor takes a String in json-format as follows:
     *             {
     *                "id": "26-101-39189196882900_P/W",
     *                "evaluation": 0,
     *                "dateLastChecked": "2026.04.22 14:09",
     *                "dateLastRichtig": "2026.04.22 14:09"
     *             }
     */
    public Stats(String json) throws Exception {
        Matcher matcher = statPattern.matcher(json);

        if (matcher.matches()) {
            String dateCheck = matcher.group("dateCheck");
            String dateRicht = matcher.group("dateRicht");
            try {
                this.dateLastChecked = LocalDateTime.parse(dateCheck, Data.PROFIL_FORMATTER);
                this.dateLastRichtig = LocalDateTime.parse(dateRicht, Data.PROFIL_FORMATTER);
            } catch (DateTimeParseException ignored) {
                throw new Exception("\"Stats (Profile): JSON input date format doesn't match correctly, please check .json file!\n\nWrong input:\n"
                        + matcher.group("dateCheck") + "\n" + matcher.group("dateRicht"));
            }

            this.id = matcher.group("id");
            this.evaluation = Integer.parseInt(matcher.group("eval"));

        } else throw new Exception("Stats (Profile): JSON input doesn't match correctly, please check .json file!\n\nWrong input:\n"
                +json+"\n"+matcher.group("dateCheck")+"  |   "+matcher.group("dateRicht"));
    }

    public Stats(String id, Integer evaluation, LocalDateTime dateLastChecked, LocalDateTime dateLastRichtig) {
        this.id = id;
        this.evaluation = evaluation;
        this.dateLastChecked = dateLastChecked;
        this.dateLastRichtig = dateLastRichtig;
    }

    public Stats(String id, Integer evaluation) {
        this(id, evaluation, LocalDateTime.now(), LocalDateTime.now().minusYears(20));
    }

    @Override
    public String toString() {
        return '{' +
                " \n\t\"id\": \"" + id + "\"," +
                " \n\t\"evaluation\": " + evaluation +
                ", \n\t\"dateLastChecked\": \"" + dateLastChecked.format(Data.PROFIL_FORMATTER) + '\"' +
                ", \n\t\"dateLastRichtig\": \"" + dateLastRichtig.format(Data.PROFIL_FORMATTER) + '\"' +
                '}';
    }


    public void stufeSteigern(){
        evaluation = (evaluation > 0) ? evaluation + 1 : 1;
    }

    public void stufeKleiner(){
        evaluation = (evaluation > 0) ? 0 : evaluation - 1;
    }

    public String id() {
        return id;
    }

    public Integer evaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public LocalDateTime dateLastChecked() {
        return dateLastChecked;
    }

    public void setDateLastChecked(LocalDateTime dateLastChecked) {
        this.dateLastChecked = dateLastChecked;
    }

    public LocalDateTime dateLastRichtig() {
        return dateLastRichtig;
    }

    public void setDateLastRichtig(LocalDateTime dateLastRichtig) {
        this.dateLastRichtig = dateLastRichtig;
    }
}
