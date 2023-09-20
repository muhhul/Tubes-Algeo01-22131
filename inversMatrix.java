import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;


public class inversMatrix {
    
    // Masukan matrix dari keyboard
    public static float[][] inputFromUser() {
        // KAMUS Lokal
        int n, i, j;
        Scanner masuk = null;
        float[][] matrix = null;

        // ALGORITMA
        try 
        {
            // Masukin ukuran matriks
            masuk = new Scanner(System.in);
            System.out.print("Masukkan ukuran matriks square: ");
            n = masuk.nextInt();

            matrix = new float[n][n];
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
    public static float[][] inputFromTxt() {

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

            float[][] matrix = new float[rows][cols];
            
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
    
    // Menulis matriks
    public static void displayMatrix(float[][] matrix) {
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
    public static float[][] CreateMatrixIdentitas(float[][] matrix) {

        float[][] identitas = new float[matrix.length][matrix[0].length];
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
    
    // Melakukan operasi inverse terhadap matrix
    public static float[][] inverse(float[][] matrix) {
        // KAMUS LOKAL
        int i, j, k, l; 
        float Diagonal, Pengkalian;

        // ALGORTIMA
        float[][] inverse = CreateMatrixIdentitas(matrix);

        // Menggunakan  Gaussian elimination
        for (i = 0; i < matrix.length; i++) {
            Diagonal = matrix[i][i];
            
            // Mengalikan row dan elemen seterusnya
            Pengkalian = 1.0f / Diagonal;
            for (j = 0; j < matrix.length; j++) {
                matrix[i][j] *= Pengkalian;
                inverse[i][j] *= Pengkalian;
            }
            
            // Eliminasi elemen lain
            for (k = 0; k < matrix.length; k++) {
                if (k != i) {
                    Pengkalian = matrix[k][i];
                    for (l = 0; l < matrix.length; l++) {
                        matrix[k][l] -= matrix[i][l] * Pengkalian;
                        inverse[k][l] -= inverse[i][l] * Pengkalian;
                    }
                }
            }
        }
        
        return inverse;
    }
    
    public static void main (String[] args) {
        
        Scanner input = new Scanner(System.in);

        float[][] matrix = null;
        
        while (true) {
            System.out.print("Input matrix via file/user: ");
            String via = input.nextLine();
            if (via.equals("file") ){
                matrix = inputFromTxt();
                break;
            } else if (via.equals("user")) {
                matrix = inputFromUser();
                break;
            } else {
                System.out.println("Input salah harap untuk input hanya \"file\" atau \"user\".");
            }
        }
        input.close();
        
        System.out.println();
        System.out.println("Matriks yang ingin diinverse: ");
        displayMatrix(matrix);
        float[][] identitas = CreateMatrixIdentitas(matrix);
        
        System.out.println();
        System.out.println("Matriks Identias: ");
        displayMatrix(identitas);
        
        System.out.println();
        float[][] inverse = inverse(matrix);
        System.out.println("Inverse Matriks: ");
        displayMatrix(inverse);
    }   
    
}