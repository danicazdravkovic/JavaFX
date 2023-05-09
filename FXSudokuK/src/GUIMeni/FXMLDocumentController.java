package GUIMeni;

import DomenskiObjekat.Korisnik;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLDocumentController {

    String nivo = "";
//    Socket soket;
    @FXML
    public TextField p00;
    @FXML
    public TextField p01;
    @FXML
    public TextField p02;
    @FXML
    public TextField p03;
    @FXML
    public TextField p04;
    @FXML
    public TextField p05;
    @FXML
    public TextField p06;
    @FXML
    public TextField p07;
    @FXML
    public TextField p08;

    @FXML
    public TextField p10;
    @FXML
    public TextField p11;
    @FXML
    public TextField p12;
    @FXML
    public TextField p13;
    @FXML
    public TextField p14;
    @FXML
    public TextField p15;
    @FXML
    public TextField p16;
    @FXML
    public TextField p17;
    @FXML
    public TextField p18;

    @FXML
    public TextField p20;
    @FXML
    public TextField p21;
    @FXML
    public TextField p22;
    @FXML
    public TextField p23;
    @FXML
    public TextField p24;
    @FXML
    public TextField p25;
    @FXML
    public TextField p26;
    @FXML
    public TextField p27;
    @FXML
    public TextField p28;

    @FXML
    public TextField p30;
    @FXML
    public TextField p31;
    @FXML
    public TextField p32;
    @FXML
    public TextField p33;
    @FXML
    public TextField p34;
    @FXML
    public TextField p35;
    @FXML
    public TextField p36;
    @FXML
    public TextField p37;
    @FXML
    public TextField p38;

    @FXML
    public TextField p40;
    @FXML
    public TextField p41;
    @FXML
    public TextField p42;
    @FXML
    public TextField p43;
    @FXML
    public TextField p44;
    @FXML
    public TextField p45;
    @FXML
    public TextField p46;
    @FXML
    public TextField p47;
    @FXML
    public TextField p48;

    @FXML
    public TextField p50;
    @FXML
    public TextField p51;
    @FXML
    public TextField p52;
    @FXML
    public TextField p53;
    @FXML
    public TextField p54;
    @FXML
    public TextField p55;
    @FXML
    public TextField p56;
    @FXML
    public TextField p57;
    @FXML
    public TextField p58;

    @FXML
    public TextField p60;
    @FXML
    public TextField p61;
    @FXML
    public TextField p62;
    @FXML
    public TextField p63;
    @FXML
    public TextField p64;
    @FXML
    public TextField p65;
    @FXML
    public TextField p66;
    @FXML
    public TextField p67;
    @FXML
    public TextField p68;

    @FXML
    public TextField p70;
    @FXML
    public TextField p71;
    @FXML
    public TextField p72;
    @FXML
    public TextField p73;
    @FXML
    public TextField p74;
    @FXML
    public TextField p75;
    @FXML
    public TextField p76;
    @FXML
    public TextField p77;
    @FXML
    public TextField p78;

    @FXML
    public TextField p80;
    @FXML
    public TextField p81;
    @FXML
    public TextField p82;
    @FXML
    public TextField p83;
    @FXML
    public TextField p84;
    @FXML
    public TextField p85;
    @FXML
    public TextField p86;
    @FXML
    public TextField p87;
    @FXML
    public TextField p88;

    @FXML
    public MenuItem pravilaIgre;

    @FXML
    public MenuItem osnovniNivo;

    @FXML
    public MenuItem napredniNivo;

    @FXML
    public MenuItem autor;

    @FXML
    public MenuItem prikupljeniPoeni;

    @FXML
    public MenuItem izlaz;
    
    @FXML
    public MenuItem izmeniNalog;
    
    @FXML
    public MenuItem obrisiNalog;

    @FXML
    public void prikaziAutoraIgre() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Informacije o autoru:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText("Autor ovog programa je: Danica Zdravkovic 3709/22.\n\n" + "Predmet: Softverski proces.");
        infoAlert.showAndWait();
    }


    @FXML
    public void izlazIzPrograma() {
        Platform.exit();
        System.exit(0);
    }
    GUIKontrolerMeni kngui;
    Korisnik ulogovanKorisnik;
    Stage stage;
    PostavkaIgre postavkaIgre;

    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        try {
            kngui = new GUIKontrolerMeni(this);
            postavkaIgre = new PostavkaIgre(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void postaviOsnovniNivo() {
        postavkaIgre.postaviOsnovniNivo();
        nivo = "osnovni";
    }

    @FXML
    public void postaviNapredniNivo() {
        postavkaIgre.postaviNapredniNivo();
        nivo = "napredni";
    }

    void postaviStage(Stage stage) {
        this.stage = stage;
    }

    public void zatvoriFormu() {
        stage.close();
    }

    public void postaviUlogovanogKorisnika(Korisnik kor) {
        this.ulogovanKorisnik = kor;
    }

    public Korisnik vratiUlogovanogKorisnika() {
        return ulogovanKorisnik;
    }

    public void postaviIgru(Matrica matrica) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        for (int x = 0; x < matrica.getMatrica().length; x++) {
            for (int y = 0; y < matrica.getMatrica().length; y++) {
                String nazivDugmeta = "p" + x + y;
                Class cls = this.getClass();
                Field field = cls.getDeclaredField(nazivDugmeta);
                TextField b = (TextField) field.get(this);
                if (matrica.getMatrica()[x][y] == '/') {
                    b.setText(" ");
                } else {
                    b.setText(matrica.getMatrica()[x][y] + "");
                    b.setDisable(true);

                }
            }

        }
    }

    void prikaziPoruku(String poruka) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Poruka:");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(poruka);
        infoAlert.showAndWait();
    }

    @FXML
    public void zavrsiIgru() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String podaciZaMatricu = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                String nazivDugmeta = "p" + x + y;
                Class cls = this.getClass();
                Field field = cls.getDeclaredField(nazivDugmeta);
                TextField b = (TextField) field.get(this);
                String vrednostPolja = b.getText().trim();
//                System.out.println(nazivDugmeta);
//                System.out.println(vrednostPolja);
                if (vrednostPolja.equals("")) {
                    prikaziPoruku("Nisu unute vrednosti svih polja!");
                    return;
                }
                if (!DaLiJeCifra(vrednostPolja)) {
                    prikaziPoruku("Sva polja moraju da sadrze cifre 1-9!");
                    return;
                }
                podaciZaMatricu += vrednostPolja;
            }
        }

        Matrica matricaPolja = new Matrica(podaciZaMatricu);

        //konvertovanje stringa u int
        int[][] matricaBrojevi = new int[9][9];
        for (int i = 0; i < matricaPolja.getMatrica().length; i++) {
            for (int j = 0; j < matricaPolja.getMatrica().length; j++) {
                matricaBrojevi[i][j] = Integer.parseInt(matricaPolja.getMatrica()[i][j] + "");
            }
        }

        //cifre u istoj kolini i istom redu ne smeju biti jednake
        for (int i = 0; i < matricaBrojevi.length; i++) {
            for (int j = 0; j < matricaBrojevi.length; j++) {
                if (!proveriValidnostUnosaElemenata(matricaBrojevi, matricaBrojevi[i][j], i, j)) {
                    prikaziPoruku("Nisu uneta polja na ispravan nacin, pokusaj ponovo!");
                    return;
                }
            }
        }

        //kvadrat 3x3 mora da sadrzi razlicite cifre
        if ((matricaBrojevi[0][0] + matricaBrojevi[0][1] + matricaBrojevi[0][2] + matricaBrojevi[1][0] + matricaBrojevi[1][1] + matricaBrojevi[1][2] + matricaBrojevi[2][0] + matricaBrojevi[2][1] + matricaBrojevi[2][2] != 45)
                || (matricaBrojevi[3][0] + matricaBrojevi[3][1] + matricaBrojevi[3][2] + matricaBrojevi[4][0] + matricaBrojevi[4][1] + matricaBrojevi[4][2] + matricaBrojevi[5][0] + matricaBrojevi[5][1] + matricaBrojevi[5][2] != 45)
                || (matricaBrojevi[6][0] + matricaBrojevi[6][1] + matricaBrojevi[6][2] + matricaBrojevi[7][0] + matricaBrojevi[7][1] + matricaBrojevi[7][2] + matricaBrojevi[8][0] + matricaBrojevi[8][1] + matricaBrojevi[8][2] != 45)
                || (matricaBrojevi[0][3] + matricaBrojevi[0][4] + matricaBrojevi[0][5] + matricaBrojevi[1][3] + matricaBrojevi[1][4] + matricaBrojevi[1][5] + matricaBrojevi[2][3] + matricaBrojevi[2][4] + matricaBrojevi[2][5] != 45)
                || (matricaBrojevi[3][3] + matricaBrojevi[3][4] + matricaBrojevi[3][5] + matricaBrojevi[4][3] + matricaBrojevi[4][4] + matricaBrojevi[4][5] + matricaBrojevi[5][3] + matricaBrojevi[5][4] + matricaBrojevi[5][5] != 45)
                || (matricaBrojevi[6][3] + matricaBrojevi[6][4] + matricaBrojevi[6][5] + matricaBrojevi[7][3] + matricaBrojevi[7][4] + matricaBrojevi[7][5] + matricaBrojevi[8][3] + matricaBrojevi[8][4] + matricaBrojevi[8][5] != 45)
                || (matricaBrojevi[0][6] + matricaBrojevi[0][7] + matricaBrojevi[0][8] + matricaBrojevi[1][6] + matricaBrojevi[1][7] + matricaBrojevi[1][8] + matricaBrojevi[2][6] + matricaBrojevi[2][7] + matricaBrojevi[2][8] != 45)
                || (matricaBrojevi[6][6] + matricaBrojevi[6][7] + matricaBrojevi[6][8] + matricaBrojevi[7][6] + matricaBrojevi[7][7] + matricaBrojevi[7][8] + matricaBrojevi[8][6] + matricaBrojevi[8][7] + matricaBrojevi[8][8] != 45)
                || (matricaBrojevi[3][6] + matricaBrojevi[3][7] + matricaBrojevi[3][8] + matricaBrojevi[4][6] + matricaBrojevi[4][7] + matricaBrojevi[4][8] + matricaBrojevi[5][6] + matricaBrojevi[5][7] + matricaBrojevi[5][8] != 45)) {
            prikaziPoruku("Zbir u svakom kvadratu nije 45.");
            return;
        }
        if (nivo.equals("osnovni")) {
            int poeni = ulogovanKorisnik.getBrojPoena()+1;
            ulogovanKorisnik.setBrojPoena(poeni);
        }
        if (nivo.equals("napredni")) {
            int poeni = ulogovanKorisnik.getBrojPoena()+3;
            ulogovanKorisnik.setBrojPoena(poeni);
        }
        System.out.println("Broj poena korisnika je: "+ulogovanKorisnik.getBrojPoena());
        kngui.upisiPoene(ulogovanKorisnik);
        ocistiPolja();

    }

    boolean DaLiJeCifra(String vrednostPolja) {
        return (vrednostPolja.equals("1") || vrednostPolja.equals("2") || vrednostPolja.equals("3")
                || vrednostPolja.equals("4") || vrednostPolja.equals("5") || vrednostPolja.equals("6")
                || vrednostPolja.equals("7") || vrednostPolja.equals("8") || vrednostPolja.equals("9"));
    }

    private boolean proveriValidnostUnosaElemenata(int[][] matricaBrojevi, int element, int red, int kolona) {
        //provera da li posotji isti el u koloni
        int brojac = 0;
        for (int i = 0; i < matricaBrojevi.length; i++) {
            if (matricaBrojevi[i][kolona] == element) {
                brojac++;
            }
            if (brojac > 1) {
                return false;
            }
        }
        brojac = 0;
        for (int i = 0; i < matricaBrojevi.length; i++) {
            if (matricaBrojevi[red][i] == element) {
                brojac++;
            }
            if (brojac > 1) {
                return false;
            }
        }
        return true;
    }

//    void postaviSoket(Socket soket) {
//        this.soket = soket;
//    }

    private void ocistiPolja() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
     for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                String nazivDugmeta = "p" + x + y;
                Class cls = this.getClass();
                Field field = cls.getDeclaredField(nazivDugmeta);
                TextField b = (TextField) field.get(this);
                b.setText("");
                
            }
        }   
    }
//    public Socket vratiSoket(){
//        return this.soket;
//    }

    public Korisnik getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    @FXML
    public void obrisiNalog() {
        kngui.obrisiKorisnika(ulogovanKorisnik);
    }
}
