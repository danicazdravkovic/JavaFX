/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TransferObjekat;

import DomenskiObjekat.GeneralDObject;
import java.io.Serializable;

/**
 *
 * @author Sinisa
 */
public class GenerickiTransferObjekat implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;//jer je vise puta kreirana ova klasa
    public GeneralDObject gdo;
    public String poruka;
    public boolean signal; 
    public String nazivOperacije;
    public int currentRecord = -1;

    public void setDK(GeneralDObject gdo) {
        this.gdo = gdo;
    }

    public String getPoruka() {
        return poruka;
    }

    public boolean getSignal() {
        return signal;
    }

    public GeneralDObject getDK() {
        return gdo;
    }

    public void setNazivOperacije(String nazivOperacije) {
        this.nazivOperacije = nazivOperacije;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public void setGdo(GeneralDObject gdo) {
        this.gdo = gdo;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
