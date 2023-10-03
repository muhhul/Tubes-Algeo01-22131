
import java.util.Map;
import java.util.Scanner;

import General.*;
import Special.*;

public class Main {
    public static Scanner input;

    public static void main(String[] args) {
        int pilMenu = 0, pilSub = 0, pilMetode = 0, m = 0, n = 0;
        double a, b, x;
        boolean operation = true;
        double[][] matriks = null;
        String luaran;
        Map<String, Object> argument;

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
                    System.out.println("            Metode");
                    Fungsi.cetakBatas();
                    System.out.println("1. Metode Eliminasi Gauss");
                    System.out.println("2. Metode Eliminasi Gauss-Jordan");
                    System.out.println("3. Metode Matriks Balikan");
                    System.out.println("4. Kaidah Cramer");
                    Fungsi.cetakBatas();
                    System.out.print("Pilihan: ");
                    pilSub = input.nextInt();
                    pilMetode = Fungsi.metodeInput();
                    if (pilMetode == 1) {
                        System.out.print("Jumlah baris matriks: ");
                        n = input.nextInt();
                        System.out.print("Jumlah baris kolom: ");
                        m = input.nextInt();
                        matriks = Input.inputMatriksKeyboard(n, m);
                    } else {
                        matriks = Input.inputMatriksFile();
                    }
                    switch (pilSub) {
                        case 1:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splGauss(matriks)));
                            break;
                        case 2:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splGaussJ(matriks)));
                            break;
                        case 3:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splInvers(matriks)));
                            break;
                        case 4:
                            luaran = luaran.concat(Konversi.splKeString(SPL.splCramer(matriks)));
                            break;
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
                        System.out.println("            Metode");
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
                        System.out.println("            Metode");
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
                                luaran = luaran.concat("Matriks singular sehingga tidak memiliki balikan.");
                            } else {
                                luaran = luaran
                                        .concat(Konversi.matriksKeString(InversMatriks.inverseGaussJordan(matriks)));
                            }
                            break;
                        case 2:
                            if (InversMatriks.inverseAdjoint(matriks) == null) {
                                luaran = luaran.concat("Matriks singular sehingga tidak memiliki balikan.");
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
                    x = 0;
                    pilMetode = Fungsi.metodeInput();
                    switch (pilMetode) {
                        case 1:
                            System.out.print("Jumlah titik: ");
                            n = input.nextInt();
                            System.out.print("Nilai x yang akan ditaksir: ");
                            n = input.nextInt();
                            matriks = Input.inputMatriksKeyboard(n, 2);
                            break;
                        case 2:
                            argument = Input.inputPolinomFile();
                            matriks = (double[][]) argument.get("matriks");
                            x = (double) argument.get("x");
                            break;
                        default:
                            System.out.println("Input salah!");
                    }
                    luaran = luaran.concat(Konversi.polinomKeString(Interpolasi.InterpolasiPolinom(matriks, x)));
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 5:
                    a = 0;
                    b = 0;
                    pilMetode = Fungsi.metodeInput();
                    switch (pilMetode) {
                        case 1:
                            matriks = Input.inputMatriksKeyboard(4, 4);
                            do {
                                Fungsi.clearScreen();
                                System.out.println("Masukkan nilai a dan b.");
                                System.out.print("Nilai a = ");
                                a = input.nextDouble();
                                System.out.print("Nilai b = ");
                                b = input.nextDouble();
                                if (a < 0 || a > 1 || b < 0 || b > 1) {
                                    System.out.println("Nilai a dan b harus dalam rentang [0..1]");
                                }
                            } while (a < 0 || a > 1 || b < 0 || b > 1);
                            break;
                        case 2:
                            argument = Input.inputBicubicFile();
                            matriks = (double[][]) argument.get("matrix");
                            a = (double) argument.get("a");
                            b = (double) argument.get("b");
                            break;
                        default:
                            System.out.println("Input salah!");
                            break;
                    }
                    luaran = luaran.concat(Konversi.bicubicKeString(Interpolasi.interpolasiBicubic(matriks, a, b)));
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 6:
                    x = 0;
                    pilMetode = Fungsi.metodeInput();
                    switch (pilMetode) {
                        case 1:
                            System.out.print("Jumlah peubah x: ");
                            n = input.nextInt();
                            System.out.print("Jumlah sampel: ");
                            m = input.nextInt();
                            System.out.print("Nilai x yang akan ditaksir:  ");
                            x = input.nextInt();
                            matriks = Input.inputMatriksKeyboard(m, n + 1);
                            break;
                        case 2:
                            argument = Input.inputBicubicFile();
                            matriks = (double[][]) argument.get("matriks");
                            x = (double) argument.get("x");
                            break;
                        default:
                            System.out.println("Input salah!");
                    }
                    luaran = luaran.concat(Konversi.regresiKeString(RegresiLinearBerganda.regresiLinear(matriks, x)));
                    pilMetode = Fungsi.metodeOutput();
                    if (pilMetode == 1) {
                        Output.cetakLuaran(luaran);
                    } else {
                        Output.tulisKeFile(luaran);
                    }
                    break;
                case 7:
                    Fungsi.clearScreen();
                    System.out.println("Selamat Jalan >.<");
                    Fungsi.pause();
                    operation = false;
                    break;
                default:
                    System.out.println("Input salah!");
            }
        }
        input.close();
    }
}