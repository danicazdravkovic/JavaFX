  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//IV predavanje cas II
package GUIKorisnikRegistracija;

import GUIKorisnikLogin.JFX01;
import GUIKorisnikRegistracija.Osluskivaci.OsluskivacKreirajKorisnika;
import Server_client.GenerickiKontrolerServer;
import Server_client.GenerickiKontrolerServer_Service;
import Server_client.GenerickiTransferObjekat;
import Server_client.Korisnik;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 *
 * @author user
 */
public class GUIKontrolerRegistracija {

    FXMLDocumentController fxcon;
    GenerickiTransferObjekat gto;
    GenerickiKontrolerServer_Service service;
    GenerickiKontrolerServer kal;

    public GUIKontrolerRegistracija(FXMLDocumentController fxcon) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, FileNotFoundException, ClassNotFoundException {
        this.fxcon = fxcon;
        service = new GenerickiKontrolerServer_Service();//umesto soketa ovde se kreira server
        kal = service.getGenerickiKontrolerServerPort();//ovu klasu generise veb servis
        gto = new GenerickiTransferObjekat();

        this.fxcon.registrujSe.setOnAction(new OsluskivacKreirajKorisnika(this));
    }

    public void poruka(String poruka) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Poruka:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(poruka);
        infoAlert.showAndWait();
    }

    String nazivSORegistruj() {
        return "registrujKorisnika";
    }

    public void pozivSO(String nazivSO) {
        if (nazivSO.equals("registrujKorisnika")) {
            gto = kal.registrujKorisnika(gto);
        }

    }

    String transferObjekatPoruka() {
        return gto.getPoruka();
    }

    private boolean popunjenaPolja() {
        String korIme = this.fxcon.korisnickoIme.getText();
        String ime = this.fxcon.ime.getText();
        String prezime = this.fxcon.prezime.getText();
        String sifra = this.fxcon.sifra.getText();
        return (!korIme.isEmpty() && !ime.isEmpty() && !sifra.isEmpty() && !prezime.isEmpty());
    }

    private Korisnik popuniKorisnika() {
        Korisnik kor = new Korisnik();
        kor.setKorisnickoIme(fxcon.korisnickoIme.getText());
        kor.setSifra(fxcon.sifra.getText());
        kor.setIme(fxcon.ime.getText());
        kor.setPrezime(fxcon.prezime.getText());
        kor.setBrojPoena(0);
        System.out.println(kor.getKorisnickoIme());
                System.out.println(kor.getSifra());
        System.out.println(kor.getIme());
        System.out.println(kor.getPrezime());
        return kor;
    }

    public void registrujKorisnika() {

        if (popunjenaPolja()) {

            Korisnik neregistrovanKorisnik = popuniKorisnika();
            gto.setGdo(neregistrovanKorisnik);
            pozivSO(nazivSORegistruj());

            if (gto.isSignal()) {
                poruka("Sistem je zapamtio igrača.");
                try {
                    JFX01 jfx01 = new JFX01();
                    jfx01.start(new Stage());
                    fxcon.zatvoriFormu();

                } catch (Exception ex) {
                    Logger.getLogger(GUIKontrolerRegistracija.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                poruka("Sistem ne može da zapamti novog igrača");
            }
        } else {

            poruka("Unesite sva polja!");
        }

    }

}
