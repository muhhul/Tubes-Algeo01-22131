package general;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Fungsi {
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

    // display sub-menu options
    public static void subMenu() {
        clearScreen();
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
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

    // Masukan matrix dari file (.txt)
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
    
    // Salin matrix ke file (.txt)
    public static void salinMatrixToTxt(double[][] matrix) {
        //KAMUS LOKAL
        int i, j;

        //ALGORITMA
        Scanner input = new Scanner(System.in);
        System.out.print("File path yang ingin anda salin matrix: ");
        String FilePath = input.nextLine();
        try {
            FileWriter writer = new FileWriter(FilePath);
            for (i = 0; i < matrix.length; i++) {
                for (j = 0; j <matrix[i].length; j ++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.write("\n");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            input.close();
        }
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
        }
        finally {
            input.close();
        }
        

    }
    
    // Menulis matriks ke layar
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
