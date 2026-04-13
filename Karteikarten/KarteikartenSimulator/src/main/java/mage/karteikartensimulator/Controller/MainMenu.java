package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.KarteiSet;
import mage.karteikartensimulator.Main;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class MainMenu {

    @FXML
    private TilePane tileKartenSets;
    @FXML
    private ToggleGroup toggleGroupKartenSets;
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button buttonKarteiErstellen;
    @FXML
    private Button buttonOptions;
    @FXML
    private Button buttonNeuesSet;
    @FXML
    private Button buttonStart;

    @FXML
    private Label labelSetInfo;

    private KarteiSet selectedSet;

    @FXML
    public void handleKarteiErstellen() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Karteikarte Erstellen...");
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("DialogKarteiErstellen.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Der Dialog, um eine neue Karteikarte zu erstellen, konnte nicht geladen werden...");
            System.out.println("-----------------------------------------------");
            System.out.println(e.getMessage());
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            //TODO: Input handling des Dialogs - Erstellen.karteErstellen() anpassen.
            Erstellen controller = loader.getController();
            controller.karteErstellen();
        } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            //TODO: Is das nötig?
            System.out.println("CANCEL pressed.");
        }
    }

    public void initialize() {
        //TODO
        populateSetView();
    }

    //TODO: rufe diese Methode, wenn ein neues Set hinzugefügt wurde oder ein altes Set gelöscht wurde.
    private void populateSetView() {
        tileKartenSets.getChildren().clear();
        buttonStart.setDisable(true);
        selectedSet = null;
        setInfo();
        Map<String, KarteiSet> set = Data.getInstance().getKartenSets();
        populateSetView("Alle Karten", set.get("alle"));
        if (set.size() > 1) set.forEach((name, kSet) -> {
            if (!name.equals("alle")) populateSetView(name, kSet);
        });
    }

    private void populateSetView(String name, KarteiSet kSet) {
        ToggleButton child = new ToggleButton(name);
        child.setToggleGroup(toggleGroupKartenSets);
        child.setPrefHeight(100);
        child.setPrefWidth(200);
        child.setFont(new Font(18));
        child.setOnAction(_ -> {
            if (child.isSelected()) selectedSet = kSet;
            else if (selectedSet.equals(kSet)) selectedSet = null;
            buttonStart.setDisable(selectedSet == null);
            setInfo();
        });
        tileKartenSets.getChildren().add(child);
    }

    private void setInfo() {
        labelSetInfo.setText(selectedSet == null ? "Bitte wähle ein Karteikartenset aus, um zu starten!"
                : selectedSet.getName() + ": " + selectedSet.getInfo());
    }
}
