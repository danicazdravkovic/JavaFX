/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SO;

import DomenskiObjekat.Korisnik;
import TransferObjekat.GenerickiTransferObjekat;

/**
 *
 * @author nodas
 */
public class UpisiPoeneDK extends OpsteIzvrsenjeSO {

    GenerickiTransferObjekat gto;

    public void upisiPoene(GenerickiTransferObjekat gto) {
        this.gto = gto;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {

        Korisnik korisnik = (Korisnik) gto.getDK();
//            String whereUslov = " WHERE korisnickoIme like '" + korisnik.getKorisnickoIme() + "' AND sifra like '" + korisnik.getSifra()+"' ";
        boolean s = bbp.updateRecord(gto.getDK());
        gto.setSignal(s);
        return s;
    }

}
