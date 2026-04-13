package mage.karteikartensimulator.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import mage.karteikartensimulator.Datenmodell.Data;
import mage.karteikartensimulator.Datenmodell.KarteiSet;
import mage.karteikartensimulator.Main;

import java.io.IOException;
import java.util.Optional;

public class MainMenu {

    @FXML
    private TilePane tileKartensets;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button buttonKarteiErstellen;
    @FXML
    private Button buttonOptions;
    @FXML
    private Button buttonNeuesSet;

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
        //TODO: die Liste der Sets von Data abgreifen und für jeden Setname ein Label zu tileKartensets hinzufügen.
        //TODO: TableView anschauen, mit ObservableMap verknüpfen <String name, KartenSet set>
    }

    //TODO: Remove test-Code and bind button "Neues Set" to actual functionality.
    public void testObservableMap() {
        try {
            Data.getInstance().getKartenSets().put("test", new KarteiSet("""
                    {
                    	"name": "Testset",
                    	"info": "Test Test",
                    	"karten": [
                    		{
                    			"id": "26-103-36976869865800_A/G",\s
                    			"tags": ["LF 3.0: CCNA", "OSI", "Grundlagen"],\s
                    			"lernfeld": "LF 3.0: CCNA",\s
                    			"frage": "Was ist der Unterschied zwischen dem OSI- und dem TCP/IP-Modell?",\s
                    			"antwort": "Das OSI-Modell hat 7 Schichten, das TCP/IP-Modell nur 4. OSI ist stärker zur Erklärung und Analyse, da es feinschichtiger ist. Das TCP/IP-Modell ist näher an reale Internet-Protokolle angelehnt."
                    		},
                    		{
                    			"id": "26-103-39189196882900_P/W",\s
                    			"tags": ["LF 3.0: CCNA", "OSI", "Grundlagen"],\s
                    			"lernfeld": "LF 3.0: CCNA",\s
                    			"frage": "Wie heißen die Schichten des OSI-Modells?",\s
                    			"antwort": "Schicht 1: Bitübertragung\\nSchicht 2: Sicherung\\nSchicht 3: Vermittlung / Netz\\nSchicht 4: Transport\\nSchicht 5: Sitzung / (Kommunikation)\\nSchicht 6: Darstellung\\nSchicht 7: Anwendung\\n\\n(Schicht 8: der Nutzer)"
                    		}
                    	]
                    }"""));
            buttonNeuesSet.setDisable(true);

            System.out.println("""
                    ---------------------------
                    Testset erstellt und hinzugefügt:
                    
                    """);
            Data.getInstance().getKartenSets().forEach((name, set) -> System.out.println(set));

            new Thread(() -> {
                try {
                    System.out.println("""
                            ------------------------------------
                            Going to sleep!
                            """);
                    Thread.sleep(10000);
                    System.out.println("""
                            Done sleeping!
                            -------------------------------------""");
                } catch (InterruptedException ignored) {}
                Platform.runLater(() -> {
                    Data.getInstance().getKartenSets().remove("test");
                    buttonNeuesSet.setDisable(false);
                    System.out.println("""
                            -------------------------------------
                            Testset wurde gelöscht:
                            
                            """);
                    Data.getInstance().getKartenSets().forEach((name, set) -> System.out.println(set));
                });
            }).start();


        } catch (Exception ignored) {}
    }
}
