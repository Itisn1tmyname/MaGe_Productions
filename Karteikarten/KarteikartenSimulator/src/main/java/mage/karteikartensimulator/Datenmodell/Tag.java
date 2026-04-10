package mage.karteikartensimulator.Datenmodell;

public record Tag(String name){
    @Override
    public String toString() {
        return "\"" + name + "\"";
    }
}
