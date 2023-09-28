package General;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fungsi {
    public static Scanner input;
    public static FileWriter fw;
    public static String path = "";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    // Fungsi mengubah matriks menjadi string sesuai dengan format print
    public static String matriksKeString(double[][] M) {
        String hasil = "";
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                hasil += M[i][j] + " ";
            }
            hasil += "\n";
        }
        return hasil;
    }

    public static void CreateFile() {
        String dir;
        String directory = System.getProperty("user.dir");
        directory = directory.substring(directory.lastIndexOf("\\") + 1);
        Date date = new Date();

        if (directory.equals("bin")) {
            dir = "..\\test\\result\\";
        } else {
            dir = "\\test\\result\\";
        }
        File file = new File(dir + dateFormat.format(date) + ".txt");

        try {
            file.createNewFile();
            path = file.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Cannot create file");
        }
    }

    // Fungsi untuk menambahkan string ke file
    public static void tulisKeFile(String konten) {
        try {
            CreateFile();
            Date date = new Date();
            path = "test\\result\\" + dateFormat.format(date) + ".txt";
            fw = new FileWriter(path);
            fw.write("File dibuat pada: " + dateFormat.format(date) + "\n");
            fw.write(konten);
            fw.close();
            System.out.println("Penulisan file berhasil!");
        } catch (IOException e) {
            System.out.println("Terjadi error dalam penulisan file!");
        }
    }

    // clear cmd
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // display menu options
    public static void menu() {
        clearScreen();
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar\n");
        System.out.print("Pilihan:");
    }

    public static void metodeInput() {
        clearScreen();
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.print("Pilihan:");
    }

    // Masukan matrix dari keyboard
    public static double[][] inputFromUser() {
        // KAMUS Lokal
        int n, i, j;
        Scanner masuk = null;
        double[][] matrix = null;

        // ALGORITMA
        try {
            // Masukin ukuran matriks
            masuk = new Scanner(System.in);
            System.out.print("Masukkan ukuran matriks square: ");
            n = masuk.nextInt();

            matrix = new double[n][n];
            System.out.println();

            // Masukin elemen pada matriks
            System.out.println("Masukkan elemen-elemen matriks: ");
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++)
                    matrix[i][j] = masuk.nextFloat();
        } catch (Exception e) {
        } finally {
            masuk.close();
        }
        return matrix;
    }

    // Masukan matrix dari file txt
    public static double[][] inputFromTxt() {

        // Kamus Lokal
        String filePath, line;
        int rows = 0, cols = 0;

        // ALGORTIMA
        Scanner path = new Scanner(System.in);
        System.out.print("Path to txt file: "); // Masukan file path
        filePath = path.nextLine();
        path.close();
        try {

            // Memulai untuk mengukur matriks
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Mengukur ukuran matriks pada txt file
            while ((line = bufferedReader.readLine()) != null) {
                String[] elemen = line.split(" ");
                cols = elemen.length;
                rows++;
            }

            bufferedReader.close();
            fileReader.close();

            // Memulai untuk membaca elemen matriks
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);

            double[][] matrix = new double[rows][cols];

            // Membaca elemen pada matriks dan menyimpannya pada variabel matriks
            int i = 0, j;
            while ((line = bufferedReader.readLine()) != null && i < rows) {
                String[] elemen = line.split(" ");
                for (j = 0; j < cols; j++) {
                    matrix[i][j] = Float.parseFloat(elemen[j]);
                }

                i++;
            }

            bufferedReader.close();
            fileReader.close();
            System.out.println("Matrix:");
            displayMatrix(matrix);
            return matrix;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    // Menulis matriks
    public static void displayMatrix(double[][] matrix) {
        if (matrix == null) {
            System.out.println("Matrix kosong.");
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

}
