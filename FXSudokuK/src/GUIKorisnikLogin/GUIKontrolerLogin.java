/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIKorisnikLogin;

import DomenskiObjekat.Korisnik;
import GUIKorisnikLogin.Osluskivaci.OsluskivacRegistracijaKorisnika;
import GUIKorisnikLogin.Osluskivaci.OsluskivacPrijavaKorisnika;
import GUIKorisnikRegistracija.JFX02;
import GUIMeni.JFX03;
import TransferObjekat.GenerickiTransferObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class GUIKontrolerLogin {

    FXMLDocumentController fxcon;
    Socket soket;
    ObjectOutputStream out;
    ObjectInputStream in;
    GenerickiTransferObjekat gto;

    public GUIKontrolerLogin(FXMLDocumentController fxcon) throws IOException {
        this.fxcon = fxcon;//referenca na komponente

        this.fxcon.kreirajKorisnika.setOnAction(new OsluskivacRegistracijaKorisnika(this));
        this.fxcon.prijaviKorisnika.setOnAction(new OsluskivacPrijavaKorisnika(this));

        soket = new Socket("127.0.0.1", 8189);
        gto = new GenerickiTransferObjekat();

        this.fxcon.sifra.setFocusTraversable(false);//da polje sifra ne bude u fokusu

    }

    public void poruka(String poruka) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Poruka:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(poruka);
        infoAlert.showAndWait();
    }

    private String nazivSOPrijaviKorisnika() {
        return "prijaviKorisnika";
    }

    private boolean popunjenaPolja() {
        String sifra = this.fxcon.sifra.getText();
        String ime = this.fxcon.korisnickoIme.getText();

        return (!sifra.isEmpty() && !ime.isEmpty());
    }

    public void prijaviKorisnika() {
        if (popunjenaPolja()) {

            Korisnik neulogovanKorisnik = popuniKorisnika();
            gto.setDK(neulogovanKorisnik);
            System.out.println("Korisnik koji se prijavljuje korisnicko ime: " + neulogovanKorisnik.getKorisnickoIme() + " i sifra: " + neulogovanKorisnik.getSifra());

            pozivSO(nazivSOPrijaviKorisnika());//slanje neulogovanog korisnika na server

            if (gto.getSignal()) {
                JFX03 jfx03 = new JFX03((Korisnik) gto.getDK());//glavni meni
                poruka("Igrač je uspešno ulogovan u sistem");

                try {

                    jfx03.start(new Stage());
                    this.fxcon.zatvoriFormu();

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    System.out.println(ex.getMessage());
                }

            } else {
                poruka("Sistem ne može da nađe igrača na osnovu unetih vrednosti za prijavljivanje");

            }
        }
    }

    public void registrujKorisnika() {
        JFX02 jfx02 = new JFX02();
        try {

            jfx02.start(new Stage());
            this.fxcon.zatvoriFormu();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.out.println(ex.getMessage());
        }
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

    private Korisnik popuniKorisnika() {
        Korisnik kor = new Korisnik();
        kor.setSifra(fxcon.sifra.getText());
        kor.setKorisnickoIme(fxcon.korisnickoIme.getText());
        return kor;
    }
    
    public void zatvoriFormu() {
        fxcon.zatvoriFormu();
    }
}
