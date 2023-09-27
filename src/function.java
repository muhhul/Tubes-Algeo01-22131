package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class function {
    // Masukan matrix dari keyboard
    public static double[][] inputFromUser() {
        // KAMUS Lokal
        int n, i, j;
        Scanner masuk = null;
        double[][] matrix = null;

        // ALGORITMA
        try 
        {
            // Masukin ukuran matriks
            masuk = new Scanner(System.in);
            System.out.print("Masukkan ukuran matriks square: ");
            n = masuk.nextInt();

            matrix = new double[n][n];
            
            // Masukin elemen pada matriks
            System.out.println("Masukkan elemen-elemen matriks: ");
            for (i = 0 ; i < n; i++)
                for (j = 0; j < n; j++)
                    matrix[i][j] = masuk.nextFloat();
        }
                catch (Exception e) {
        }
        finally {
            masuk.close();
        }
        return matrix;
    }
    // Masukan matrix dari file txt
    public static double[][] inputFromTxt() {

        // Kamus Lokal
        String filePath, line;
        int rows = 0, cols = 0;

        //ALGORTIMA
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
            fileReader =  new FileReader(filePath);
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
    // Membuat sebuah matriks identitas untuk digunakan pada pencarian inverse matriks
    public static double[][] CreateMatrixIdentitas(double[][] matrix) {

        double[][] identitas = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j< matrix[i].length;j++){
                if (i == j) {
                    identitas[i][j] = 1;
                } else {
                    identitas[i][j] = 0;
                }
            }
        
        return identitas;
    }
    // Mengembalikan hasil tranpose matrix
    public static double[][] Transpose(double[][] matrix) {
        // KAMUS LOKAL
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] temp = new double[cols][rows];
        
        // ALGORITMA
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
    
        return temp;
    }
    // Mencari hasil determinant dengan menggunakan cara cofactor
    public static double determinant(double[][] matrix) {
        int n = matrix.length; // 
        
        // Basis
        if (n == 1) {
            return matrix[0][0];
        }
    
        double det = 0.0;
        int sign = 1;
    
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] != 0) {
                double[][] subMatrix = new double[n - 1][n - 1];
                int sub_i = 0;
                for (int i = 1; i < n; i++) {
                    int sub_j = 0;
                    for (int k = 0; k < n; k++) {
                        if (k != j) {
                            // Membuat subMatrix
                            subMatrix[sub_i][sub_j] = matrix[i][k];
                            sub_j++;
                        }
                    }
                    sub_i++;
                }
                // Rekursif untuk mendapatkan nilai determinant jika belum n = 1
                det += (sign * matrix[0][j] * determinant(subMatrix));
            }
            // Mengubah tanda
            sign = -sign;
        }
    
        return det;
    }
    // Membuat matrix cofactor
    public static double[][] matrixCofactor(double[][] matrix) {
        int n = matrix.length;
        double[][] cofactor = new double[n][n]; // Membuat matrix baru berupa cofactor
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] subMatrix = new double[n - 1][n - 1];
                int sub_i = 0;
    
                for (int k = 0; k < n; k++) {
                    if (k == i) continue; // Jika row sama dengan minor skipp
    
                    int sub_j = 0;
                    for (int l = 0; l < n; l++) {
                        if (l == j) continue; // Jika kolom sama dengan minor skip
    
                        subMatrix[sub_i][sub_j] = matrix[k][l];
                        sub_j++;
                    }
                    sub_i++;
                }
                // mengubah tanda, saat i+j genap menjadi positive dan saat i+j ganjil menjadi negative
                // mencari determinant untuk mendapatkan minor
                cofactor[i][j] = (Math.pow(-1, i + j) * determinant(subMatrix));
            }
        }
    
        return cofactor;
    }
    // Membuat matrix adjoin
    public static double[][] matrixAdjoin(double[][] matrix) {
        // Mendapatkan cofactor dari matrix
        double[][] cofactor = matrixCofactor(matrix);
        // Transpose cofactor untuk menghasilkan matrix adjoin
        double[][] adjoint = Transpose(cofactor);
    
        return adjoint;
    }
    // Copas matrix
    public static double[][] copyMatrix(double[][] matrix) {
        // KAMUS LOKAL
        int i, j;

        // ALGORITMA
        double[][] copas = new double[matrix.length][matrix[0].length];
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                copas[i][j] = matrix[i][j];
            }
        }
        return copas;
    }


}