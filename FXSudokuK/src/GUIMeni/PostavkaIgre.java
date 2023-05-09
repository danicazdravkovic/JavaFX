/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMeni;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 *
 * @author nodas
 */
public class PostavkaIgre {

    public int[][] mat = new int[9][9];

    FXMLDocumentController fxdc;

    static Random generatorElemenata = new Random();

    public PostavkaIgre(FXMLDocumentController fxdc) {
        super();
        this.fxdc = fxdc;

//        this.fxdc.postaviSlikuBroja(this.fxdc.vratiSliku(String.valueOf(brojac)), 0, 0);
    }

    String procitajOsnovniNivo() {
        try {
            File myObj = new File("src/GUIMeni/osnovniNivo.txt");
            Scanner myReader = new Scanner(myObj);
            String podaci = "";
            while (myReader.hasNextLine()) {
                podaci += myReader.nextLine() + " ";

            }
//            System.out.println(podaci);

            myReader.close();
            return podaci;
//
        } catch (Exception e) {
            System.out.println("Nije uspesno procitan tekstualni fajl sa osnovnim nivom.");
            e.printStackTrace();
        }
        return "";
    }

    ArrayList<Matrica> popuniMatrice(String podaci) {
        String[] matriceTxt = podaci.split("=================");//matrica po matrica iz txt fajla
        ArrayList<Matrica> popunjeneMatrice = new ArrayList<>();

        for (int i = 0; i < matriceTxt.length; i++) {
            Matrica m = new Matrica(matriceTxt[i]);
            popunjeneMatrice.add(m);
//            System.out.println(matriceTxt[i]);
        }
        return popunjeneMatrice;
    }

    String procitajNapredniNivo() {
        try {
            File myObj = new File("src/GUIMeni/napredniNivo.txt");
            Scanner myReader = new Scanner(myObj);
            String podaci = "";
            while (myReader.hasNextLine()) {
                podaci += myReader.nextLine() + " ";

            }
            myReader.close();
            return podaci;
        } catch (Exception e) {
            System.out.println("Nije uspesno procitan tekstualni fajl sa osnovnim nivom.");
            e.printStackTrace();
        }
        return "";
    }

    void postaviOsnovniNivo() {
        try {
            String podaci = procitajOsnovniNivo();
            ArrayList<Matrica> popunjeneMatrice = popuniMatrice(podaci);
            Random random = new Random();
//            fxdc.postaviIgru(popunjeneMatrice.get(0));
            fxdc.postaviIgru(popunjeneMatrice.get(random.nextInt(popunjeneMatrice.size())));

//        for (Matrica pm : popunjeneMatrice) {
//            pm.ispisiMatricu();
//        }
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(PostavkaIgre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PostavkaIgre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PostavkaIgre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void postaviNapredniNivo() {
        try {
            String podaci = procitajNapredniNivo();
            ArrayList<Matrica> popunjeneMatrice = popuniMatrice(podaci);
            Random random = new Random();
            fxdc.postaviIgru(popunjeneMatrice.get(random.nextInt(popunjeneMatrice.size())));
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(PostavkaIgre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PostavkaIgre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PostavkaIgre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
