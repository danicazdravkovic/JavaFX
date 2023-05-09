/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import DomenskiObjekat.GeneralDObject;
import DomenskiObjekat.Korisnik;
import SO.AzurirajDK;
import SO.ObrisiDK;
import SO.PrijavaDK;
import SO.UpisiPoeneDK;
import TransferObjekat.GenerickiTransferObjekat;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class GenerickiKontrolerServer {

    static ServerSocket ss;
    static List<Klijent> lkl = new ArrayList<>();

    public GenerickiKontrolerServer() {
    }

    public static void main(String arg[]) throws Exception {
        GenerickiKontrolerServer gks = new GenerickiKontrolerServer();
        gks.izvrsiGenerickiKontroler();
    }

    void izvrsiGenerickiKontroler() throws Exception {
        ss = new ServerSocket(8189);
        System.out.println("Server je pokrenut.");
        while (true) {
            Socket soketS = ss.accept();
            Klijent kl = new Klijent(soketS);
            lkl.add(kl);
        }
    }
}

class Klijent extends Thread {

    public Klijent(Socket soketS1) {
        soketS = soketS1;
        start();
    }

    public void run() {
        try {

            GenerickiTransferObjekat tok;

            while (true) {
                out = new ObjectOutputStream(soketS.getOutputStream());
                in = new ObjectInputStream(soketS.getInputStream());

                tok = (GenerickiTransferObjekat) in.readObject();


                if (tok.nazivOperacije.equals("prijaviKorisnika")) {
                    prijavaDK(tok);

                }
                if (tok.nazivOperacije.equals("upisiPoene")) {
                    upisiPoeneDK(tok);

                }
                 if (tok.nazivOperacije.equals("izmeniKorisnika")) {
                    izmeniPodatkeDK(tok);

                }

                  if (tok.nazivOperacije.equals("obrisiKorisnika")) {
                    obrisiDK(tok);

                }

                out.writeObject(tok);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Socket soketS;
    ObjectOutputStream out;
    ObjectInputStream in;

    int brojKlijenta;

    private GenerickiTransferObjekat prijavaDK(GenerickiTransferObjekat gto) {

        new PrijavaDK().prijavaKorisnika(gto);

        return gto;
    }
    
     private GenerickiTransferObjekat upisiPoeneDK(GenerickiTransferObjekat gto) {

        new UpisiPoeneDK().upisiPoene(gto);

        return gto;
    }

    private GenerickiTransferObjekat izmeniPodatkeDK(GenerickiTransferObjekat gto) {
        new AzurirajDK().azurirajKorisnika(gto);
        return gto;
    }
     private GenerickiTransferObjekat obrisiDK(GenerickiTransferObjekat gto) {
        new ObrisiDK().obrisiKorisnika(gto);
        return gto;
    }
}
