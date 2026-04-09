module mage.karteikarten.test_4_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens mage.karteikarten.test_4_fx to javafx.fxml;
    exports mage.karteikarten.test_4_fx;
}