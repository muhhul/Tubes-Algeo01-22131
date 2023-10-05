package General;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Input {
    public static Scanner input;
    public static String dir = "..\\test\\case\\";

    public static double[][] inputMatriksKeyboard(int row, int col) {
        // KAMUS Lokal
        int i, j;
        input = new Scanner(System.in);
        double[][] matrix = new double[row][col];

        // ALGORITMA
        try {
            // Masukin elemen pada matriks
            System.out.println("Masukkan elemen-elemen matriks: ");
            for (i = 0; i < row; i++) {
                for (j = 0; j < col; j++) {
                    matrix[i][j] = input.nextDouble();
                }
            }
        } catch (Exception e) {
        }
        return matrix;
    }

    // Masukan matrix dari file txt
    public static double[][] inputMatriksFile() {
        // Kamus Lokal
        String namaFile, filePath, line;
        int rows = 0, cols = 0;
        File f;
        // ALGORTIMA
        input = new Scanner(System.in);
        do {
            System.out.print("Nama file: "); // Masukan file path
            namaFile = input.nextLine();
            filePath = dir + namaFile + ".txt";
            f = new File(filePath);
            if (!f.exists() || f.isDirectory()) {
                System.out.println("File tidak ditemukan.");
                Fungsi.pause();
            }
        } while (!f.exists() || f.isDirectory());
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
            return matrix;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> inputPolinomFile() {
        // Kamus Lokal
        double x;
        String namaFile, filePath, line;
        int rows = 0;
        File f;
        // ALGORTIMA
        input = new Scanner(System.in);
        do {
            System.out.print("Nama file: "); // Masukan file path
            namaFile = input.nextLine();
            filePath = dir + namaFile + ".txt";
            f = new File(filePath);
            if (!f.exists() || f.isDirectory()) {
                System.out.println("File tidak ditemukan.");
                Fungsi.pause();
            }
        } while (!f.exists() || f.isDirectory());
        try {
            // Memulai untuk mengukur matriks
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Mengukur ukuran matriks pada txt file
            while ((line = bufferedReader.readLine()) != null) {
                rows++;
            }

            bufferedReader.close();
            fileReader.close();

            // Memulai untuk membaca elemen matriks
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);

            double[][] matriks = new double[rows - 1][2];

            // Membaca elemen pada matriks dan menyimpannya pada variabel matriks
            int i = 0, j;
            while ((line = bufferedReader.readLine()) != null && i < rows - 1) {
                String[] elemen = line.split(" ");
                for (j = 0; j < 2; j++) {
                    matriks[i][j] = Float.parseFloat(elemen[j]);
                }

                i++;
            }
            String[] elemen = line.split(" ");

            x = Double.parseDouble(elemen[0]);
            Map<String, Object> result = new HashMap<>();
            result.put("matriks", matriks);
            result.put("x", x);
            bufferedReader.close();
            fileReader.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> inputRegresiFile() {
        // Kamus Lokal
        double x;
        String namaFile, filePath, line;
        int rows = 0, cols = 0;

        File f;
        // ALGORTIMA
        input = new Scanner(System.in);
        do {
            System.out.print("Nama file: "); // Masukan file path
            namaFile = input.nextLine();
            filePath = dir + namaFile + ".txt";
            f = new File(filePath);
            if (!f.exists() || f.isDirectory()) {
                System.out.println("File tidak ditemukan.");
                Fungsi.pause();
            }
        } while (!f.exists() || f.isDirectory());
        try {
            // Memulai untuk mengukur matriks
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Mengukur ukuran matriks pada txt file
            while ((line = bufferedReader.readLine()) != null) {
                String[] elemen = line.split(" ");
                cols = elemen.length+1; //diubah
                rows++;
            }

            bufferedReader.close();
            fileReader.close();

            // Memulai untuk membaca elemen matriks
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);

            double[][] matriks = new double[rows - 1][cols];

            // Membaca elemen pada matriks dan menyimpannya pada variabel matriks
            int i = 0, j;
            while ((line = bufferedReader.readLine()) != null && i < rows - 1) {
                String[] elemen = line.split(" ");
                for (j = 0; j < cols; j++) {
                    matriks[i][j] = Float.parseFloat(elemen[j]);
                }

                i++;
            }
            String[] elemen = line.split(" ");

            x = Double.parseDouble(elemen[0]);
            Map<String, Object> result = new HashMap<>();
            result.put("matriks", matriks);
            result.put("x", x);
            bufferedReader.close();
            fileReader.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static double[][] inputInterpolasiKeyboard() {
        int n;
        input = new Scanner(System.in);
        double[][] matriks = null;
        // ALGORITMA
        try {
            // Masukin ukuran matriks
            input = new Scanner(System.in);
            System.out.print("Masukkan banyak titik: ");
            n = Integer.parseInt(input.nextLine());
            matriks = inputMatriksKeyboard(n, 2);
        } catch (Exception e) {
        }
        return matriks;
    }

    public static Map<String, Object> inputBicubicFile() {

        // Kamus Lokal
        String filePath, fileName, line;
        int rows = 4, cols = 4;
        double a = 0, b = 0;
        File f;
        // ALGORTIMA
        input = new Scanner(System.in);
        do {
            System.out.print("Nama file: "); // Masukan file path
            fileName = input.nextLine();
            filePath = dir + fileName + ".txt";
            f = new File(filePath);
            if (!f.exists() || f.isDirectory()) {
                System.out.println("File tidak ditemukan.");
                Fungsi.pause();
            }
        } while (!f.exists() || f.isDirectory());
        try {
            // Memulai untuk mengukur matriks
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
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
                    matrix[i][j] = Double.parseDouble(elemen[j]);
                }
                i++;
            }
            String[] elemen = line.split(" ");

            a = Double.parseDouble(elemen[0]);
            b = Double.parseDouble(elemen[1]);

            /*
             * Fungsi.displayMatrix(matrix);
             * System.out.println("a = " + a);
             * System.out.println("b = " + b);
             */

            bufferedReader.close();
            fileReader.close();

            Map<String, Object> result = new HashMap<>();
            result.put("matrix", matrix);
            result.put("a", a);
            result.put("b", b);

            if (a < 0 || a > 1 || b < 0 || b > 1) {
                return null;
            } else {
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
