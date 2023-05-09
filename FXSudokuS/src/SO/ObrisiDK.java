/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SO;

import TransferObjekat.GenerickiTransferObjekat;

/**
 *
 * @author nodas
 */
public class ObrisiDK extends OpsteIzvrsenjeSO{
 GenerickiTransferObjekat gto;

    public void obrisiKorisnika(GenerickiTransferObjekat gto) {
        this.gto = gto;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {

        boolean s = bbp.deleteRecord(gto.getDK());
        gto.setSignal(s);
        return s;
    }   
}
