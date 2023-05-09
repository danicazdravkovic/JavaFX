package GUIKorisnikLogin;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLDocumentController {

    @FXML
    public TextField korisnickoIme;
    @FXML
    public PasswordField sifra;
    @FXML
    public Button kreirajKorisnika;
    @FXML
    public Button prijaviKorisnika;

    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
        GUIKontrolerLogin kngui = new GUIKontrolerLogin(this);

    }

    public Stage stage;

    void postaviStage(Stage stage) {
        this.stage = stage;
    }

    public void zatvoriFormu() {
        stage.close();
    }

}
