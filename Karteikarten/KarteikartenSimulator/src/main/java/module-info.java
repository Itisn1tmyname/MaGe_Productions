module KarteikartenSimulator {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.materialdesign2;

    opens mage.karteikartensimulator;
    exports mage.karteikartensimulator to javafx.fxml;
    exports mage.karteikartensimulator.Controller to javafx.fxml;
    exports mage.karteikartensimulator.Datenmodell to javafx.fxml;
    opens mage.karteikartensimulator.Datenmodell;
    opens mage.karteikartensimulator.Controller to javafx.fxml;
}