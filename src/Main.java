
import java.util.Scanner;

import General.*;
import Special.*;

public class Main {
    public static Scanner input;

    public static void main(String[] args) {
        int pilMenu = 0, pilSub = 0, pilMetode = 0, m = 0, n = 0;
        boolean operation = true;
        double[][] matriks = null;
        String luaran;
        while (operation) {
            luaran = Fungsi.headerOutput();
            input = new Scanner(System.in);
            Fungsi.menu();
            pilMenu = input.nextInt();
            switch (pilMenu) {
                case 1:
                    input = new Scanner(System.in);
                    Fungsi.clearScreen();
                    Fungsi.cetakBatas();
                    System.out.println("Metode");
                    Fungsi.cetakBatas();
                    System.out.println("1. Metode Eliminasi Gauss");
                    System.out.println("2. Metode Eliminasi Gauss-Jordan");
                    System.out.println("3. Metode Matriks Balikan");
                    System.out.println("4. Kaidah Cramer");
                    Fungsi.cetakBatas();
                    System.out.print("Pilihan: ");
                    pilSub = input.nextInt();
                    pilMetode = Fungsi.metodeInput();
                    switch (pilSub) {
                        case 1:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splGauss(matriks)));
                        case 2:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splGaussJ(matriks)));
                        case 3:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splInvers(matriks)));
                        case 4:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splCramer(matriks)));
                        default:
                            System.out.println("Pilih 1, 2, 3, atau 4!");
                    }
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 2:
                    do {
                        input = new Scanner(System.in);
                        Fungsi.clearScreen();
                        Fungsi.cetakBatas();
                        System.out.println("Metode");
                        Fungsi.cetakBatas();
                        System.out.println("1. Metode Ekspansi Kofaktor");
                        System.out.println("2. Metode Reduksi Baris");
                        Fungsi.cetakBatas();
                        System.out.print("Pilihan: ");
                        pilSub = input.nextInt();
                    } while (pilSub != 1 && pilSub != 2);
                    pilMetode = Fungsi.metodeInput();
                    if (pilMetode == 1) {
                        System.out.print("Jumlah baris & kolom matriks: ");
                        n = input.nextInt();
                        matriks = Input.inputMatriksKeyboard(n, n);
                    } else {
                        matriks = Input.inputMatriksFile();
                    }
                    switch (pilSub) {
                        case 1:
                            if (OperasiMatriks.determinanKofaktor(matriks) == null) {
                                luaran = luaran.concat("Matriks tidak memiliki determinan.");
                            } else {
                                luaran = luaran.concat("Determinan matriks = ");
                                luaran = luaran.concat(String.valueOf(OperasiMatriks.determinanKofaktor(matriks)));
                            }
                            break;
                        case 2:
                            if (OperasiMatriks.determinanReduksi(matriks) == null) {
                                luaran = luaran.concat("Matriks tidak memiliki determinan.");
                            } else {
                                luaran = luaran.concat("Determinan matriks = ");
                                luaran = luaran.concat(String.valueOf(OperasiMatriks.determinanReduksi(matriks)));
                            }
                            break;
                        default:
                            System.out.println("Pilih 1 atau 2!");
                            break;
                    }
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 3:
                    do {
                        input = new Scanner(System.in);
                        Fungsi.clearScreen();
                        Fungsi.cetakBatas();
                        System.out.println("Metode");
                        Fungsi.cetakBatas();
                        System.out.println("1. Metode Balikan");
                        System.out.println("2. Metode Adjoin");
                        Fungsi.cetakBatas();
                        System.out.print("Pilihan: ");
                        pilSub = input.nextInt();
                    } while (pilSub != 1 && pilSub != 2);
                    pilMetode = Fungsi.metodeInput();
                    if (pilMetode == 1) {
                        System.out.print("Jumlah baris & kolom matriks: ");
                        n = input.nextInt();
                        matriks = Input.inputMatriksKeyboard(n, n);
                    } else {
                        matriks = Input.inputMatriksFile();
                    }
                    switch (pilSub) {
                        case 1:
                            if (InversMatriks.inverseGaussJordan(matriks) == null) {
                                luaran = luaran.concat("Matriks tidak memiliki balikan.");
                            } else {
                                luaran = luaran
                                        .concat(Konversi.matriksKeString(InversMatriks.inverseGaussJordan(matriks)));
                            }
                            break;
                        case 2:
                            if (InversMatriks.inverseAdjoint(matriks) == null) {
                                luaran = luaran.concat("Matriks tidak memiliki balikan.");
                            } else {
                                luaran = luaran
                                        .concat(Konversi.matriksKeString(InversMatriks.inverseAdjoint(matriks)));

                            }
                            break;
                        default:
                            System.out.println("Pilih 1 atau 2!");
                            break;
                    }
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 4:
                    Fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            System.out.print("Jumlah baris & kolom: ");
                            n = input.nextInt();
                            matriks = Input.inputMatriksKeyboard(n, n);
                        case 2:
                            matriks = Input.inputMatriksFile();
                        default:
                            System.out.println("Input salah!");
                    }
                    luaran = luaran.concat(Konversi.interpolasiKeString(Interpolasi.InterpolasiPolinom(matriks, n)));
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 5:
                    Fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            System.out.print("Jumlah baris & kolom: ");
                            n = input.nextInt();
                            matriks = Input.inputMatriksKeyboard(n, n);
                        case 2:
                            matriks = Input.inputMatriksFile();
                        default:
                            System.out.println("Input salah!");
                    }
                    break;
                case 6:
                    Fungsi.metodeInput();
                    pilMetode = input.nextInt();
                    switch (pilMetode) {
                        case 1:
                            System.out.print("Jumlah baris & kolom: ");
                            n = input.nextInt();
                            matriks = Input.inputMatriksKeyboard(n, n);
                        case 2:
                            matriks = Input.inputMatriksFile();
                        default:
                            System.out.println("Input salah!");
                    }
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