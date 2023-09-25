package Matrix;
import java.util.Scanner;

public class inversMatrix {

    // Melakukan operasi inverse terhadap matrix
    public static double[][] inverse(double[][] matrix) {
        // KAMUS LOKAL
        int n = matrix.length;
        double pengkalian;

        // Membuat sebuah matrix identitas
        double[][] inverse = function.CreateMatrixIdentitas(matrix);
        

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
    
    public static void main (String[] args) {
        
        Scanner input = new Scanner(System.in);

        double[][] matrix = null;
        
        while (true) {
            System.out.print("Input matrix via file/user: ");
            String via = input.nextLine();
            if (via.equals("file") ){
                matrix = function.inputFromTxt();
                break;
            } else if (via.equals("user")) {
                matrix = function.inputFromUser();
                break;
            } else {
                System.out.println("Input salah harap untuk input hanya \"file\" atau \"user\".");
            }
        }
        input.close();
        
        double[][] identitas = function.CreateMatrixIdentitas(matrix);
        
        System.out.println();
        System.out.println("Matriks Identias: ");
        function.displayMatrix(identitas);
        
        System.out.println();
        double[][] inverse = inverse(matrix);
        System.out.println("Inverse Matriks: ");
        function.displayMatrix(inverse);
    }   
    
}