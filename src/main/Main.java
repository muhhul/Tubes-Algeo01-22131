package main;

import java.util.Scanner;

import general.Fungsi;
import general.OperasiMatriks;
import special.SPL;

public class Main {

    public static void main(String[] args) {
        Fungsi fungsi = new Fungsi();
        SPL spl = new SPL();
        OperasiMatriks operasi = new OperasiMatriks();

        Scanner input = new Scanner(System.in);
        int pilMenu, pilSub, pilMetode;
        boolean operation = true;
        double[][] matriks = null;
        while (operation) {
            fungsi.menu();
            pilMenu = input.nextInt();
            switch (pilMenu) {
                case 1:
                    fungsi.subMenu();
                    pilSub = input.nextInt();
                    fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            matriks = fungsi.inputFromUser();
                        case 2:
                            matriks = fungsi.inputFromTxt();
                        default:
                            System.out.println("Input salah!");
                    }
                    switch (pilSub) {
                        case 1:
                            spl.splGauss(matriks);
                        case 2:
                            spl.splGaussJ(matriks);
                        case 3:
                            spl.splInvers(matriks);
                        case 4:
                            spl.splCramer(matriks);
                        default:
                            System.out.println("Input salah!");
                    }
                    break;
                case 2:
                    fungsi.subMenu();
                    pilSub = input.nextInt();
                    fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            matriks = fungsi.inputFromUser();
                        case 2:
                            matriks = fungsi.inputFromTxt();
                        default:
                            System.out.println("Input salah!");
                    }
                    switch (pilSub) {
                        case 1:
                            operasi.determinanGauss(matriks);
                        case 2:
                            operasi.determinanGaussJ(matriks);
                        case 3:
                            operasi.determinanKofaktor(matriks);
                        case 4:
                            operasi.determinanKofaktor(matriks);
                        default:
                            System.out.println("Input salah!");
                    }
                    break;
                case 3:
                    fungsi.subMenu();
                    pilSub = input.nextInt();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    operation = false;
                    break;
                default:
                    System.out.println("Input salah!");
            }
        }
        input.close();
    }
}