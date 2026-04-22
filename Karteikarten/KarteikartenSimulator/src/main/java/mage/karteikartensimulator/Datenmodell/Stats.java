package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDateTime;

public class Stats {

    private String name;
    private Integer evaluation;
    private LocalDateTime dateLastChecked;
    private LocalDateTime dateLastRichtig;

    public Stats(String json){
        //TODO
    }

    public Stats(String name, Integer evaluation, LocalDateTime dateLastChecked, LocalDateTime dateLastRichtig) {
        this.name = name;
        this.evaluation = evaluation;
        this.dateLastChecked = dateLastChecked;
        this.dateLastRichtig = dateLastRichtig;
    }

    public Stats(String name, Integer evaluation) {
        this(name, evaluation, LocalDateTime.now(), LocalDateTime.now().minusYears(20));
    }

    @Override
    public String toString() {
        return '\"'+ name +"\": {" +
                " \n\t\"evaluation\": " + evaluation +
                ", \n\t\"dateLastChecked\": \"" + dateLastChecked.format(Data.PROFIL_FORMATTER) + '\"' +
                ", \n\t\"dateLastRichtig\": \"" + dateLastRichtig.format(Data.PROFIL_FORMATTER) + '\"' +
                "\n}";
    }


    public void stufeSteigern(){
        evaluation = (evaluation > 0) ? evaluation + 1 : 1;
    }

    public void stufeKleiner(){
        evaluation = (evaluation > 0) ? 0 : evaluation - 1;
    }

    public String name() {
        return name;
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
