package special;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import general.Fungsi;
import general.dsdsds;

public class InterpolasiBicubicSpline {

    // Masukan matrix dari keyboard
    public static Map<String, Object> inputFromUser() {
        // KAMUS Lokal
        int n = 4, i, j;
        double a = 0, b = 0;
        Scanner masuk = null;
        double[][] matrix = null;

        // ALGORITMA
        try {
            // Masukin ukuran matriks
            masuk = new Scanner(System.in);

            matrix = new double[n][n];
            System.out.println();

            // Masukin elemen pada matriks
            System.out.println("Masukkan elemen-elemen matriks 4x4: ");
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++)
                    matrix[i][j] = masuk.nextDouble();

            do {
                System.out.println("Masukkan nilai a dan b.");
                System.out.print("Nilai a = ");
                a = masuk.nextDouble();
                System.out.print("Nilai b = ");
                b = masuk.nextDouble();
            } while (a < 0 || a > 1 || b < 0 || b > 1);

        } catch (Exception e) {
        } finally {
            masuk.close();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("matrix", matrix);
        result.put("a", a);
        result.put("b", b);

        return result;
    }

    // Masukan matrix dari file txt
    public static Map<String, Object> inputFromTxt() {

        // Kamus Lokal
        String filePath, line;
        int rows = 4, cols = 4;
        double a = 0, b = 0;

        // ALGORTIMA
        Scanner path = new Scanner(System.in);
        System.out.print("Path to txt file: "); // Masukan file path
        filePath = path.nextLine();
        path.close();
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

            Fungsi.displayMatrix(matrix);
            System.out.println("a = " + a);
            System.out.println("b = " + b);

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

    public static double[] getCoefficient(double[][] matrix) {

        // KAMUS LOKAL
        int i, j;
        double[] coefficient;
        double[][] X;

        // ALGORITMA

        double[] Y = { matrix[1][1], matrix[1][2], matrix[2][1], matrix[2][2] };

        X = dsdsds.inverseGaussJordan(matrix);

        coefficient = new double[4];

        for (i = 0; i < matrix.length; i++) {
            coefficient[i] = 0;
            for (j = 0; j < matrix[i].length; j++) {
                coefficient[i] += X[i][j] * Y[j];
            }
        }

        return coefficient;

    }

    public static void fungsiBicubic(double[][] matrix, double a, double b) {

        // Mendapatkan a dari rumus a = (X^-1)(Y)
        double[] coefficient = getCoefficient(matrix);

        double result = coefficient[0] + coefficient[1] * a + coefficient[2] * b + coefficient[3] * a * b;

        // Fungsi bicubic spline interplotation
        // Hasil dari f(a,b)
        System.out.printf("f(%.2f,%.2f) = %.2f + %.2f(%.2f) + %.2f(%.2f) + %.2f(%.2f)(%.2f) = %.2f", a, b,
                coefficient[0], coefficient[1], a, coefficient[2], b, coefficient[3], a, b, result);

    }

    public static void main(String[] args) {
        // KAMUS
        double[][] matrix;
        double a, b;

        // ALGORITMA
        Scanner input = new Scanner(System.in);
        Map<String, Object> getMatrix = null;

        // Metode mengambil matrix
        while (true) {
            System.out.print("Input matrix via file/user: ");
            String via = input.nextLine();
            if (via.equals("user")) {
                getMatrix = inputFromUser();
                break;
            } else if (via.equals("file")) {
                getMatrix = inputFromTxt();
                if (getMatrix != null) {
                    break;
                }
                System.out.println("Nilai a dan b harus dalam rentang [0..1]");
                System.exit(0);
            } else {
                System.out.println("Input salah harap untuk input hanya \"file\" atau \"user\".");
            }
        }

        input.close();

        matrix = (double[][]) getMatrix.get("matrix");
        a = (double) getMatrix.get("a");
        b = (double) getMatrix.get("b");

        fungsiBicubic(matrix, a, b);

    }
}