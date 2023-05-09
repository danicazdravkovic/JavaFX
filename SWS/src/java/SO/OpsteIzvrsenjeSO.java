/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SO;

import BrokerBazePodataka.BrokerBazePodataka;
import BrokerBazePodataka.BrokerBazePodatakaImpl;
import DomenskiObjekat.GeneralDObject;

/**
 *
 * @author user
 */
public abstract class OpsteIzvrsenjeSO {

    public BrokerBazePodataka bbp = new BrokerBazePodatakaImpl();
    int recordsNumber;
    int currentRecord = -1;
    GeneralDObject gdo;

    public boolean opsteIzvrsenjeSO() {
        bbp.makeConnection();
        boolean signal = izvrsiSO();
        if (signal == true) {
            bbp.commitTransation();
        } else {
            bbp.rollbackTransation();
        }
        bbp.closeConnection();
        return signal;
    }

    abstract public boolean izvrsiSO();
}
