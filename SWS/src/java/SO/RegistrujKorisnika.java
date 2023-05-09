/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import DomenskiObjekat.GeneralDObject;
import DomenskiObjekat.Korisnik;
import TransferObjekat.GenerickiTransferObjekat;
import java.util.List;

/**
 *
 * @author nodas
 */
public class RegistrujKorisnika extends OpsteIzvrsenjeSO {

    GenerickiTransferObjekat gto;

    public void registrujKorisnika(GenerickiTransferObjekat gto) {
        this.gto = gto;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {
        Korisnik k = gto.gdo;
        boolean s = false;

        String where = " WHERE korisnickoIme LIKE '" + k.getKorisnickoIme() + "' ";
        List<GeneralDObject> lista = bbp.findRecord(gto.gdo, where);
       
        if (lista.isEmpty()) {
            s = bbp.insertRecord(gto.gdo);
            gto.setSignal(s);
        }

        return s;

    }

}
