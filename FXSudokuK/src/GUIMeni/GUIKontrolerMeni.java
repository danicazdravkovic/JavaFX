/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIMeni;

import DomenskiObjekat.Korisnik;
import GUIIzmenaPodataka.JFX06;
import GUIKorisnikLogin.JFX01;
import GUIMeni.Osluskivaci.OsluskivacIzmenaPodataka;
import GUIMeni.Osluskivaci.OsluskivacPoeni;
import GUIMeni.Osluskivaci.OsluskivacPravilaIgre;
import GUIPoeni.JFX05;
import GUIPravilaIgre.JFX04;
import TransferObjekat.GenerickiTransferObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Sinisa
 */
public class GUIKontrolerMeni {

    Socket soket;
    ObjectOutputStream out;
    ObjectInputStream in;
    GenerickiTransferObjekat gto;
    FXMLDocumentController fxcon;
    int mat[][];

    public GUIKontrolerMeni(FXMLDocumentController fxcon) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
        this.fxcon = fxcon;
        this.gto = new GenerickiTransferObjekat();
        soket = new Socket("127.0.0.1", 8189);

        obojiTablu();

        this.fxcon.pravilaIgre.setOnAction(new OsluskivacPravilaIgre(this));
        this.fxcon.prikupljeniPoeni.setOnAction(new OsluskivacPoeni(this));
        this.fxcon.izmeniNalog.setOnAction(new OsluskivacIzmenaPodataka(this));

    }

    void obojiTablu() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                try {
                    postaviBoje(x, y);
                } catch (NoSuchFieldException ex) {
                    System.err.println("Nije pronadjeno specificirano polje u GUIKontrolerGlavniMeni klasi" + ex.getStackTrace() + x + y);
                } catch (IllegalArgumentException ex) {
                    System.err.println("Nisu prosledjeni ocekivani argumenti u GUIKontrolerGlavniMeni klasi" + ex.getStackTrace());
                } catch (IllegalAccessException ex) {
                    System.err.println("Nedozvoljen pristup u GUIKontrolerGlavniMeni klasi" + ex.getStackTrace());
                }

            }
        }
    }

    public void pravilaIgre() {
        try {
            System.out.println("Ovo su pravila igre");
            JFX04 jfx04 = new JFX04();
            jfx04.start(new Stage());
        } catch (Exception ex) {
            System.err.println("Greska prilikom pozivanja pravila igre u GUIKontrolerGlavniMeni " + ex.getStackTrace());
        }

    }

    public void poruka(String poruka) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Poruka:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(poruka);
        infoAlert.showAndWait();
    }

    public boolean porukaDaNe(String poruka) {
        Alert alert = new Alert(AlertType.CONFIRMATION, poruka, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Poruka:");
        alert.setHeaderText(null);
        alert.showAndWait();
        return (alert.getResult() == ButtonType.YES);

    }

    public void postaviBoje(int x, int y) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String nazivDugmeta = "p" + x + y;
        Class cls = this.fxcon.getClass();
        Field field = cls.getDeclaredField(nazivDugmeta);
        TextField b = (TextField) field.get(this.fxcon);
        if (x == 0 || x == 2 || x == 4 || x == 6 || x == 8) {
            b.getStyleClass().add("sivoPolje");
        }

    }

    public void prikaziPoene() {
        try {

            JFX05 jfx05 = new JFX05();
            jfx05.start(new Stage());
            jfx05.postaviPodatke(fxcon.vratiUlogovanogKorisnika());
        } catch (Exception ex) {
            System.err.println("Greska u GUIKontrolerGlavniMeni, metoda prikazi moj profil" + ex.getStackTrace());
        }
    }

    private String nazivSOUpisiPoene() {
        return "upisiPoene";
    }

    public void pozivSO(String nazivSO) {
        gto.setNazivOperacije(nazivSO);
        try {
            out = new ObjectOutputStream(soket.getOutputStream());
            in = new ObjectInputStream(soket.getInputStream());
        } catch (IOException ex) {
            System.err.println("Greska prilikom otvaranja in i/ili out tokova");
        }
        try {
            out.writeObject(gto);
        } catch (IOException ex) {

            System.err.println("Greska prilikm slanja objekta kod out write metode");
        }

        try {
            gto = (GenerickiTransferObjekat) in.readObject();
        } catch (IOException ex) {
            System.err.println("Greska kod ucitavanja objekta in read object metoda" + ex.getStackTrace());
        } catch (ClassNotFoundException ex) {

            System.err.println("Klasa nije pronadjena");
        }
    }

    void upisiPoene(Korisnik ulogovanKorisnik) {

        gto.setDK(ulogovanKorisnik);

        pozivSO(nazivSOUpisiPoene());//slanje neulogovanog korisnika na server

        if (gto.getSignal()) {
            poruka("Poeni su uspesno uneti. Ukupan broj poena je: " + fxcon.getUlogovanKorisnik().getBrojPoena());
        } else {
            poruka("Poeni nisu uspesno uneti!");
        }
    }

    public void izmenaPodataka() {
        try {
            JFX06 jfx06 = new JFX06(fxcon.getUlogovanKorisnik());
            jfx06.start(new Stage());
        } catch (Exception ex) {
            System.err.println("Greska prilikom pozivanja izmene podataka u GUIKontrolerGlavniMeni " + ex.getStackTrace());
        }

    }

    private String nazivSOObrisiKorisnika() {
        return "obrisiKorisnika";
    }

    void obrisiKorisnika(Korisnik ulogovanKorisnik) {
        boolean odg = porukaDaNe("Da li zaista zelis da obrises svoj nalog?");
        if (odg) {
            gto.setDK(ulogovanKorisnik);

            pozivSO(nazivSOObrisiKorisnika());//slanje neulogovanog korisnika na server

            if (gto.getSignal()) {
                poruka("Sistem je uspesno obrisao korisnika.");
            } else {
                poruka("Sistem ne moze da obrise korinsika!");
            }
            fxcon.zatvoriFormu();
            try {
                JFX01 jfx01 = new JFX01();
                jfx01.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(GUIKontrolerMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
