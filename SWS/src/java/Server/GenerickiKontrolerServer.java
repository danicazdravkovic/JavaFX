/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import SO.RegistrujKorisnika;
import TransferObjekat.GenerickiTransferObjekat;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author nodas
 */
@WebService(serviceName = "GenerickiKontrolerServer")
public class GenerickiKontrolerServer {

    /**
     * This is a sample web service operation
     */
    public GenerickiTransferObjekat registrujKorisnika(GenerickiTransferObjekat gto) {
        new RegistrujKorisnika().registrujKorisnika(gto);
        return gto;
    }
}
