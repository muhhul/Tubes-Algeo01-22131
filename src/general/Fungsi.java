package General;

import java.io.FileWriter;
import java.util.Scanner;

public class Fungsi {
    public static Scanner input;

    // clear cmd
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // mencetak batas
    public static void cetakBatas() {
        System.out.println("==============================");
    }

    // display menu options
    public static void menu() {
        clearScreen();
        cetakBatas();
        System.out.println("             MENU             ");
        cetakBatas();
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linear Berganda");
        System.out.print("7. Keluar\n");
        cetakBatas();
        System.out.print("Pilihan: ");
    }

    public static int metodeInput() {
        int pilMetode = 0;
        input = new Scanner(System.in);
        try {
            do {
                clearScreen();
                cetakBatas();
                System.out.println("         Metode Input");
                cetakBatas();
                System.out.println("1. Keyboard");
                System.out.println("2. File");
                cetakBatas();
                System.out.print("Pilihan: ");
                pilMetode = input.nextInt();
                if (pilMetode != 1 && pilMetode != 2) {
                    System.out.println("Pilih 1 atau 2!");
                    pause();
                }
            } while (pilMetode != 1 && pilMetode != 2);
        } catch (Exception e) {
        }
        return pilMetode;
    }

    public static int metodeOutput() {
        int pilMetode = 0;
        input = new Scanner(System.in);
        try {
            do {
                clearScreen();
                cetakBatas();
                System.out.println("        Metode Output");
                cetakBatas();
                System.out.println("1. Keyboard");
                System.out.println("2. File");
                cetakBatas();
                System.out.print("Pilihan: ");
                pilMetode = input.nextInt();
                if (pilMetode != 1 && pilMetode != 2) {
                    System.out.println("Pilih 1 atau 2!");
                    pause();
                }
            } while (pilMetode != 1 && pilMetode != 2);
        } catch (Exception e) {
        }
        return pilMetode;
    }

    public static void pause() {
        Scanner scanner = null;
        try {
            System.out.println("Press Enter To Continue...");
            scanner = new Scanner(System.in);
            scanner.nextLine();
        } catch (Exception e) {
        }
    }

    // mencetak header 'output'
    public static void cetakHeaderOutput() {
        cetakBatas();
        System.out.println("OUTPUT");
        cetakBatas();
    }

    // mengembalikan string header 'output'
    public static String headerOutput() {
        return "==============================\n             OUTPUT             \n==============================\n\n";
    }

    // Salin hasil ke file (.txt)
    public static void salinToTxt(double result) {
        Scanner input = new Scanner(System.in);
        System.out.print("File path yang ingin anda salin matrix: ");
        String FilePath = input.nextLine();
        try {
            FileWriter writer = new FileWriter(FilePath);
            writer.write(Double.toString(result));
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            input.close();
        }

    }

    // mencari elemen dengan panjang terbesar dari sebuah matriks
    static int maxLengthMatriks(double mat[][]) {
        int max = 0;
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                if (String.valueOf(mat[i][j]).length() > max) {
                    max = String.valueOf(mat[i][j]).length();
                }
            }
        }
        return max;
    }

}
