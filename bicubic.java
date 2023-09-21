import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class bicubic {

     // Masukan matrix dari keyboard
     public static Map<String, Object> inputFromUser() {
        // KAMUS Lokal
        int n = 4, i, j;
        float a = 0, b = 0;
        Scanner masuk = null;
        float[][] matrix = null;

        // ALGORITMA
        try 
        {
            // Masukin ukuran matriks
            masuk = new Scanner(System.in);

            matrix = new float[n][n];
            System.out.println();
            
            // Masukin elemen pada matriks
            System.out.println("Masukkan elemen-elemen matriks 4x4: ");
            for (i = 0 ; i < n; i++)
                for (j = 0; j < n; j++)
                    matrix[i][j] = masuk.nextFloat();
            
            do {
            System.out.println("Masukkan nilai a dan b.");
            System.out.print("Nilai a = ");
            a = masuk.nextFloat();
            System.out.print("Nilai b = ");
            b = masuk.nextFloat();
            } while (a < 0 || a > 1 || b < 0 || b > 1);

        }
                catch (Exception e) {
        }
        finally {
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
        int rows = 5, cols = 4;
        float a = 0, b = 0;

        //ALGORTIMA
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
            fileReader =  new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);

            float[][] matrix = new float[rows][cols];
            
            // Membaca elemen pada matriks dan menyimpannya pada variabel matriks
            int i = 0, j;
            while ((line = bufferedReader.readLine()) != null && i < rows) {
                String[] elemen = line.split(" ");
                if (i == rows-1) {
                    a = Float.parseFloat(elemen[0]);
                    b = Float.parseFloat(elemen[1]);
                } else {
                for (j = 0; j < cols; j++) {
                    matrix[i][j] = Float.parseFloat(elemen[j]);
                }
                }

                i++;
            }
            
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
        } {
            
        }

        return null;

    }

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

    public static float[][] inverse(float[][] matrix) {
        // KAMUS LOKAL
        int n = matrix.length;
        float pengkalian;

        // Membuat sebuah matrix identitas
        float[][] inverse = CreateMatrixIdentitas(matrix);
        
        // Menggunakan  Gaussian elimination
        for (int i = 0; i < n; i++) {
            pengkalian = matrix[i][i];
            
            // Cek apakah matriks singular atau tidak
            if (pengkalian == 0.0f) {
                throw new ArithmeticException("Matrix tersebut singular, tidak bisa diinverse.");
            }
            
            // Mengalikan row dan elemen seterusnya
            for (int j = 0; j < n; j++) {
                matrix[i][j] /= pengkalian;
                inverse[i][j] /= pengkalian;
            }
            
            // Eliminasi elemen lain
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    pengkalian = matrix[k][i];
                    for (int j = 0; j < n; j++) {
                        matrix[k][j] -= pengkalian * matrix[i][j];
                        inverse[k][j] -= pengkalian * inverse[i][j];
                    }
                }
            }
        }
        
        return inverse;
    }
    
    public static void displayMatrix(float[][] matrix) {
        if (matrix == null) {
            System.out.println("Matrix kosong.");
            return;
        }
        for (int i = 0; i < matrix.length-1; i++) {
            for (int j = 0; j < matrix[i].length; j++){ 
            System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static float[] getCoefisien(float[][] matrix) {

        // KAMUS LOKAL


        // ALGORITMA

        float[][] X = inverse(matrix);

        float[] Y = {matrix[1][1], matrix[1][2], matrix[2][1], matrix[2][2]};

        float[] a = new float[4];

        

    }
public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Object> getMatrix = null;

        
        while (true) {
            System.out.print("Input matrix via file/user: ");
            String via = input.nextLine();
            if (via.equals("user") ){
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

        float[][] matrix = (float[][])getMatrix.get("matrix");
        float a = (float) getMatrix.get("a");
        float b = (float) getMatrix.get("b");
        displayMatrix(matrix);
        System.out.println("a = " +a);
        System.out.println("b = " +b);


    }
}