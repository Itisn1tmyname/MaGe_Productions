module mage.karteikarten.test_1_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens mage.karteikarten.test_1_fx to javafx.fxml;
    exports mage.karteikarten.test_1_fx;
}