package General;

import java.util.Scanner;

public class InversMatriks {
    public static Scanner input;

    // Melakukan operasi inverse terhadap matrix dengan menggunakan metode Gauss
    // Jordan
    public static double[][] inverseGaussJordan(double[][] matrix) {
        // KAMUS LOKAL
        int n = matrix.length;
        double pengkalian;
        double[][] inverse, cMatrix;

        // Membuat sebuah matrix identitas
        cMatrix = OperasiMatriks.copyMatrix(matrix);
        inverse = OperasiMatriks.CreateMatrixIdentitas(cMatrix);

        // Menggunakan Gauss-Jordan elimination
        for (int i = 0; i < n; i++) {
            // Cari baris dengan elemen terbesar di kolom i sebagai pivot
            int maxRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(cMatrix[j][i]) > Math.abs(cMatrix[maxRow][i])) {
                    maxRow = j;
                }
            }

            // Tukar baris i dengan baris maxRow jika perlu
            if (maxRow != i) {
                // Tukar baris cMatrix
                double[] temp = cMatrix[i];
                cMatrix[i] = cMatrix[maxRow];
                cMatrix[maxRow] = temp;

                // Tukar baris matriks identitas
                temp = inverse[i];
                inverse[i] = inverse[maxRow];
                inverse[maxRow] = temp;
            }

            pengkalian = cMatrix[i][i];

            // Cek apakah matriks singular atau tidak
            if (pengkalian == 0.0f) {
                return null;
            }

            // Mengalikan row dan elemen seterusnya
            for (int j = 0; j < n; j++) {
                cMatrix[i][j] /= pengkalian;
                inverse[i][j] /= pengkalian;
            }

            // Eliminasi elemen lain
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    pengkalian = cMatrix[k][i];
                    for (int j = 0; j < n; j++) {
                        cMatrix[k][j] -= pengkalian * cMatrix[i][j];
                        inverse[k][j] -= pengkalian * inverse[i][j];
                    }
                }
            }
        }

        return inverse;
    }

    // Melakukan operasi inverse terhadap matrix dengan menggunakan metode Adjoint
    public static double[][] inverseAdjoint(double[][] matrix) {
        double determinant = OperasiMatriks.determinanKofaktor(matrix);

        if (Math.abs(determinant) < 1e-10) {
            return null;
        }

        double pengkalian = 1 / determinant;
        double[][] Adjoin = OperasiMatriks.matrixAdjoin(matrix);
        double[][] inverse = new double[matrix.length][matrix.length];
        int i, j;

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                // Membuat agar tidak terdapat negative zero (-0.0)
                if (Math.abs(Adjoin[i][j]) < 1e-10) {
                    inverse[i][j] = 0.0;
                } else {
                    inverse[i][j] = pengkalian * Adjoin[i][j];
                }
            }
        }

        return inverse;
    }


}