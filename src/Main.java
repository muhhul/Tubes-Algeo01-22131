

import java.util.Scanner;

import General.*;
import Special.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilMenu, pilSub, pilMetode;
        boolean operation = true;
        double[][] matriks = null;
        while (operation) {
            Fungsi.menu();
            pilMenu = input.nextInt();
            switch (pilMenu) {
                case 1:
                    Fungsi.clearScreen();
                    System.out.println("1. Metode Eliminasi Gauss");
                    System.out.println("2. Metode Eliminasi Gauss-Jordan");
                    System.out.println("3. Metode Matriks Balikan");
                    System.out.println("4. Kaidah Cramer");
                    System.out.print("Pilihan:");
                    pilSub = input.nextInt();
                    Fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            matriks = Fungsi.inputFromUser();
                        case 2:
                            matriks = Fungsi.inputFromTxt();
                        default:
                            System.out.println("Input salah!");
                    }
                    switch (pilSub) {
                        case 1:
                            SPL.splGauss(matriks);
                        case 2:
                            SPL.splGaussJ(matriks);
                        case 3:
                            SPL.splInvers(matriks);
                        case 4:
                            SPL.splCramer(matriks);
                        default:
                            System.out.println("Input salah!");
                    }
                    break;
                case 2:
                    Fungsi.clearScreen();
                    System.out.println("1. Metode Eliminasi Gauss");
                    System.out.println("2. Metode Eliminasi Gauss-Jordan");
                    System.out.println("3. Metode Matriks Balikan");
                    System.out.println("4. Kaidah Cramer");
                    System.out.print("Pilihan:");
                    pilSub = input.nextInt();
                    Fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            matriks = Fungsi.inputFromUser();
                        case 2:
                            matriks = Fungsi.inputFromTxt();
                        default:
                            System.out.println("Input salah!");
                    }
                    switch (pilSub) {
                        case 1:
                            OperasiMatriks.determinanKofaktor(matriks);
                        case 2:
                            OperasiMatriks.determinanReduksi(matriks);
                        default:
                            System.out.println("Input salah!");
                    }
                    break;
                case 3:
                    Fungsi.clearScreen();
                    System.out.println("1. Metode Balikan");
                    System.out.println("2. Metode Adjoin");
                    System.out.print("Pilihan:");
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