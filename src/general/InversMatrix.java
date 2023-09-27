package general;


public class InversMatrix {

    // Melakukan operasi inverse terhadap matrix dengan menggunakan metode Gauss
    // Jordan
    public static double[][] inverseGaussJordan(double[][] matrix) {
        // KAMUS LOKAL
        int n = matrix.length;
        double pengkalian;

        // Membuat sebuah matrix identitas
        double[][] inverse = operasiMatriks.CreateMatrixIdentitas(matrix);

        // Menggunakan Gauss-Jordan elimination
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

    // Melakukan operasi inverse terhadap matrix dengan menggunakan metode Adjoint
    public static double[][] inverseAdjoint(double[][] matrix) {
        // KAMUS LOKAL
        double determinant = operasiMatriks.determinanKofaktor(matrix);
        double pengkalian = 1 / determinant;
        double[][] Adjoin = operasiMatriks.matrixAdjoin(matrix);
        double[][] inverse = new double[matrix.length][matrix.length];
        int i, j;
        
        // ALGORITMA
        // A^-1 = 1/det(matrix) x Adj(matrix)
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                inverse[i][j] = pengkalian * Adjoin[i][j];
            }
        }

        return inverse;

    }
}