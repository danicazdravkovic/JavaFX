/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMeni;

/**
 *
 * @author nodas
 */
public class Matrica {

    char[][] matrica;
    int red;
    int kolona;
//    int brojac = 0;

    public Matrica(String podaciZaMatricu) {
        matrica = new char[9][9];
        red = 0;
        kolona = 0;
        podaciZaMatricu=podaciZaMatricu.replace(" ", "");
        for (int m = 0; m < podaciZaMatricu.length(); m++) {
//            System.out.println(podaciZaMatricu.charAt(m));

            matrica[red][kolona] =podaciZaMatricu.charAt(m);
            kolona++;
//            brojac++;
//            System.out.println("BROJAC:" +brojac);
            if (kolona == 9) {
                red++;
                kolona = 0;
            }

        }
    }

    public char[][] getMatrica() {
        return matrica;
    }

    public void setMatrica(char[][] matrica) {
        this.matrica = matrica;
    }

    public void ispisiMatricu() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrica[i][j]);
            }
            System.out.println("");
        }
    }
}
