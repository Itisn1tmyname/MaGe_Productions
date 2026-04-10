package mage.karteikartensimulator.Datenmodell;

public record Lernfeld(int zahl1, int zahl2, String name){
    @Override
    public String toString() {
        return "\"LF " + zahl1 + "." + zahl2 + ": " + name +"\"";
    }
}
