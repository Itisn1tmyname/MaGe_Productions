package mage.karteikartensimulator.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import mage.karteikartensimulator.Main;

import java.io.IOException;
import java.util.Optional;

public class MainMenu {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button buttonKarteiErstellen;
    @FXML
    private Button buttonOptions;

    @FXML
    public void handleKarteiErstellen() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Karteikarte Erstellen...");

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("KarteiErstellen.fxml"));
            dialog.getDialogPane().setContent(loader.load());

        } catch (IOException e) {
            System.out.println("Der Dialog, um eine neue Karteikarte zu erstellen, konnte nicht geladen werden...");
            System.out.println("-----------------------------------------------");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            //TODO: Input handling des Dialogs
            System.out.println("OK pressed!");
        } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            //TODO: Is das nötig?
            System.out.println("CANCEL pressed.");
        }
    }
}
