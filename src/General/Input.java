package General;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Input {
    public static Scanner input;

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
            return matrix;

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
}
