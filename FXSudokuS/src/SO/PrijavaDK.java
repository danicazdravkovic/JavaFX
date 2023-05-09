/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SO;

import DomenskiObjekat.GeneralDObject;
import DomenskiObjekat.Korisnik;
import TransferObjekat.GenerickiTransferObjekat;
import java.util.List;


/**
 *
 * @author user
 */
public class PrijavaDK extends OpsteIzvrsenjeSO {

    GenerickiTransferObjekat gto;

    public void prijavaKorisnika(GenerickiTransferObjekat gto) {
        this.gto = gto;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {

        Korisnik neuloKorisnik = (Korisnik) gto.getDK();
        String whereUslov = " WHERE korisnickoIme like '" + neuloKorisnik.getKorisnickoIme() + "' AND sifra like '" + neuloKorisnik.getSifra() + "' ";
        List<GeneralDObject> korisnici = bbp.findRecord(gto.getDK(), whereUslov);
        if (korisnici.isEmpty()) {
            gto.setSignal(false);
            return false;

        }
        gto.setDK(korisnici.get(0));//prvi pronadjen
        gto.setSignal(true);
        gto.setPoruka("Korisnik je pronadjen.");

        return gto.getSignal();
    }

   
}
