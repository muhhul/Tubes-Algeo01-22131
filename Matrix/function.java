package Matrix;
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
            System.out.println();
            
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
        for (int i = 0; i <matrix.length; i++)
            for (int j = 0; j<matrix[i].length;j++){
                if (i == j) {
                    identitas[i][j] = 1;
                } else {
                    identitas[i][j] = 0;
                }
            }
        
        return identitas;
    }

 
}