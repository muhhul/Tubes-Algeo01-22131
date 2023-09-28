package special;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import general.Fungsi;
import general.inversMatrix;
import general.OperasiMatriks;

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


    public static void fungsiBicubic(double[][] matrix, double a, double b) {
        //KAMUS LOKAL
        int i, j, k = 0, Bi, Bj, x, y;
        double hasil = 0.0;
        
        //ALGORITMA
        double[][] Mbicubic = new double[16][16];
        double[][] Y = new double[16][1];

        // Konstruksi matrix bicubic interpolation
        Bi = 0;
        for (x = -1; x < 3; x++){
            for (y = -1; y < 3; y++) {
                Bj = 0;
                for( i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        Mbicubic[Bi][Bj] = Math.pow(x, i) * Math.pow(y, j);
                        Bj++;
                    }
                }
                Bi++;
            }
        }

        // Inverse matrix Bicubic 
        double [][] MbicubicInverse = inversMatrix.inverseGaussJordan(Mbicubic);

        // Matrix 4x4 to matrix 16x1
        for(i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                Y[k][0] = matrix[i][j];
                k++;
            }
        }

        // Mengalikan inverse bicubic dengan matrix 16x1 untuk mendapatkan koefisian (a) ---> Y x X^(-1) = a 
        double[][] koefisien = OperasiMatriks.multiplyMatrix(MbicubicInverse, Y);

        k = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                hasil += koefisien[k][0] * Math.pow(a, i) * Math.pow(b, j);
                k++;
            }
        }

        System.out.printf("Hasil dari fungsi bicubic f(%.2f,%.2f) = %.2f\n", a, b, hasil);
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
            System.out.println("Input matrix via:\n1. File (.txt)\n2. User");
            System.out.print("Via = ");
            String via = input.nextLine();
            if (via.equals("1")) {
                getMatrix = inputFromUser();
                break;
            } else if (via.equals("2")) {
                getMatrix = inputFromTxt();
                if (getMatrix != null) {
                    break;
                }
                System.out.println("Nilai a dan b harus dalam rentang [0..1]");
                System.exit(0);
            } else {
                System.out.println("Input salah harap untuk input hanya \"1\" atau \"2\".");
            }
        }

        input.close();

        matrix = (double[][]) getMatrix.get("matrix");
        a = (double) getMatrix.get("a");
        b = (double) getMatrix.get("b");

        fungsiBicubic(matrix, a, b);

    }
}