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
public class AzurirajDK extends OpsteIzvrsenjeSO {

    GenerickiTransferObjekat gto;

    public void azurirajKorisnika(GenerickiTransferObjekat gto) {
        this.gto = gto;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {

        boolean s = bbp.updateRecord(gto.getDK());
        gto.setSignal(s);
        return s;
    }

}
