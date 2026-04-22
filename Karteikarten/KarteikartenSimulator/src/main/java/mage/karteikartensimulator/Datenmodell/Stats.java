package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;

public class Stats {

    private String id;
    private Integer evaluation;
    private LocalDateTime dateLastChecked;
    private LocalDateTime dateLastRichtig;

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
    public Stats(String json){
        //TODO
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
