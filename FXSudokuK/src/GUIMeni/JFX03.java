package GUIMeni;

import DomenskiObjekat.Korisnik;
import java.net.Socket;
import java.net.URL;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author user
 */
public class JFX03 extends Application {

    FXMLDocumentController con;
    Korisnik ulogovanKorisnik;
    Socket soket;

    public JFX03(Korisnik ulogovanKorisnik) {
        this.ulogovanKorisnik = ulogovanKorisnik;
//        this.soket=soket;
    }

    @Override
    public void start(Stage stage) throws Exception {

        String resourcePath = "FXMLDocument.fxml";
        URL location = getClass().getResource(resourcePath);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();
        con = (FXMLDocumentController) fxmlLoader.getController();
//        con.postaviSoket(soket);

        con.postaviStage(stage);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("CSS/stylesheet.css");
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        con.postaviUlogovanogKorisnika(ulogovanKorisnik);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public GUIMeni.FXMLDocumentController getController() {
        return con;
    }

    public void zatvoriFormu() {

        con.stage.close();
    }

//    public void postaviUlogovanogKorisnika(Long id) {
//        con.idKorisnika = id;
//        System.out.println("GUIGlavniMeni.JFX03.postaviUlogovanogKorisnika() njegov ID je: " + con.idKorisnika);
//    }
//
//    public Long vratiUlogovanogKorisnika() {
//        return con.idKorisnika;
//    }
    public Korisnik vratiKorisnika() {
        return ulogovanKorisnik;
    }

}
