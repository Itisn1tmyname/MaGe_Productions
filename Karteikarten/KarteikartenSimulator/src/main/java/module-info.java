module KarteikartenSimulator {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.kordamp.ikonli.javafx;

    opens mage.karteikartensimulator;
    exports mage.karteikartensimulator to javafx.fxml;
    exports mage.karteikartensimulator.Controller to javafx.fxml;
    exports mage.karteikartensimulator.Datenmodell to javafx.fxml;
    opens mage.karteikartensimulator.Datenmodell;
}