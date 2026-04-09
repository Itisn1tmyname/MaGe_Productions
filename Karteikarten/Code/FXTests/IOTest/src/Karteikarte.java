import javax.swing.colorchooser.ColorChooserComponentFactory;
import java.util.HashSet;

record Tag(String name){
    @Override
    public String toString() {
        return name;
    }
}
record Lernfeld(int zahl1, int zahl2, String name){
    @Override
    public String toString() {
        return "LF " + zahl1 + "." + zahl2 + ": " + name;
    }
}


public class Karteikarte {

    private HashSet<Tag> tags;
    private Lernfeld lernfeld;
    private String frage;
    private String antwort;
    private String farbe;

    public Karteikarte(HashSet<Tag> tags, Lernfeld lernfeld, String frage, String antwort, String farbe) {
        this.tags = tags;
        this.lernfeld = lernfeld;
        this.frage = frage;
        this.antwort = antwort;
        this.farbe = farbe;
    }
}
