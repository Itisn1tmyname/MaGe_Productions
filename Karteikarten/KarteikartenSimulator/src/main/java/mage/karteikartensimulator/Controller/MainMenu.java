package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import mage.karteikartensimulator.Datenmodell.*;
import mage.karteikartensimulator.Main;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.Icon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainMenu {

    @FXML
    private TilePane tileKartenSets;
    @FXML
    private ToggleGroup toggleGroupKartenSets;
    @FXML
    private StackPane mainPane;
    @FXML
    private FontIcon iconFullscreen;
    @FXML
    private FontIcon iconClose;
    @FXML
    private FontIcon iconMinimize;

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
        dialog.initOwner(mainPane.getScene().getWindow());
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

    @FXML
    public void handleStart() {
        //TODO: frage nach gewünschten Sortier- oder Filterfunktionen
        if (selectedSet == null || selectedSet.getKarten().isEmpty()) return;

        KarteiSet set = setFiltern();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("KarteiFrage.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.setAlwaysOnTop(true);
//        stage.setFullScreen(true);
//        stage.setFullScreenExitHint("");
        try {
            stage.setScene(new Scene(loader.load()));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getScene().setFill(Color.TRANSPARENT);
            Frage frage = loader.getController();
            frage.starten(set.getKarten(), stage);
            stage.show();
        } catch (IOException ignored) {
            //TODO
        }
    }

    //TODO: Vor Anzeigen des Sets fragen, wie/ob gefiltert werden soll...
    private KarteiSet setFiltern() {

        //gefiltertes KartenSet: Teil der Karten, die nach Karteibox-Prinzip tatsächlich angezeigt werden
        KarteiSet filtered = new KarteiSet(selectedSet.getName(), selectedSet.getInfo(), new ArrayList<Karteikarte>());

        //TODO: Variablen in config einführen, die über die Zeitabstände pro nacheinander Richtig beantwortet bestimmen (Hier hardcoded: Einmal richtig: 2 Tage warten, 2mal richtig: 4 Tage...
        final int[] tage = {1,3,6,13}; //Wenn nach zwei Tagen wieder gefragt wird, dann muss ein Tag übersprungen werden

        HashMap<String, Stats> tempMap = Data.getInstance().getActiveProfil().getStats();

        for (Karteikarte karte : selectedSet.getKarten()) {
            String id = karte.getId();
            if (!tempMap.containsKey(id)) {
                //Wenn Karte dem Profil noch nicht bekannt ist (noch nicht als richtig/falsch beantwortet gespeichert) füge sie dem Set hinzu
                filtered.getKarten().add(karte);
            } else {
                //Nachschauen, ob die Karte positive Wertung hat (ein bis mehrmals richtig beantwortet), dann nach Datum filtern
                Stats stats = tempMap.get(id);
                if (stats.evaluation() <= 0) { //Wenn die Karte zuletzt falsch beantwortet wurde, füge sie dem Set hinzu
                    filtered.getKarten().add(karte);

                } else {
                    int i = (stats.evaluation() > tage.length) ? tage.length - 1 : stats.evaluation() - 1;
                    if (stats.dateLastRichtig().plusDays(tage[i]).isBefore(LocalDateTime.now())) //Wenn laut Werte-Tabelle genug Tage verstrichen sind
                        filtered.getKarten().add(karte);
                }
            }
        }

        return filtered;
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

    @FXML
    public void handleClose() {
        Main.getStage().close();
    }

    @FXML
    public void handleMinimize() {
        Main.getStage().setIconified(true);
    }

    @FXML
    public void handleFullscreen() {
        Main.getStage().setFullScreen(!Main.getStage().isFullScreen());
    }

    public void setPaneInsets(double insets) {
        mainPane.setPadding(new Insets(insets));
    }

    public void setFullScreenIcons(boolean fullscreen) {
        if (fullscreen){
            iconMinimize.setIconLiteral("codicon-chevron-down:35:DIMGREY");
            iconFullscreen.setIconLiteral("mdi2f-fullscreen-exit:35:DIMGREY");
            iconClose.setIconLiteral("mdi2w-window-close:35:DIMGREY");
        }
        else {
            iconMinimize.setIconLiteral("codicon-chevron-down:30:DIMGREY");
            iconFullscreen.setIconLiteral("mdi2f-fullscreen:30:DIMGREY");
            iconClose.setIconLiteral("mdi2w-window-close:30:DIMGREY");
        }
    }
}
