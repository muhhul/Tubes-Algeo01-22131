package general;

public class OperasiMatriks {

    // Membuat sebuah matriks identitas untuk digunakan pada pencarian inverse
    // matriks
    public static double[][] CreateMatrixIdentitas(double[][] matrix) {

        double[][] identitas = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++) {
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

    // Menyalin matriks
    public static double[][] copyMatrix(double[][] matrix) {
        // KAMUS LOKAL
        int i, j;

        // ALGORITMA
        double[][] copas = new double[matrix.length][matrix[0].length];
        
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length;j++) {
                copas[i][j] = matrix[i][j];
            }
        }

        return copas;
    }

    // Mencari hasil determinant dengan menggunakan cara cofactor
    public static double determinanKofaktor(double[][] matrix) {
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
                det += (sign * matrix[0][j] * determinanKofaktor(subMatrix));
            }
            // Mengubah tanda
            sign = -sign;
        }

        return det;
    }

    public static double determinanGauss(double[][] matriks) {
        return 0.0;
    }
    public static double determinanGaussJ(double[][] matriks) {
        return 0.0;
    }
    public static double determinanInvers(double[][] matriks) {
        return 0.0;
    }
    public static double determinanCramer(double[][] matriks) {
        return 0.0;
    }

    public static double[][] multiplyMatrix(double[][] m1, double[][] m2) {
        int rows1 = m1.length;
        int cols1 = m1[0].length;
        int cols2 = m2[0].length;
        
        if (cols1 != m2.length) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication.");
        }
        
        double[][] hasil = new double[rows1][cols2];
        
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                double sum = 0.0;
                for (int k = 0; k < cols1; k++) {
                    sum += m1[i][k] * m2[k][j];
                }
                hasil[i][j] = sum;
            }
        }
        
        return hasil;
    }
    
    public static double[][] matrixCofactor(double[][] matrix) {
        int n = matrix.length;
        double[][] cofactor = new double[n][n]; // Membuat matrix baru berupa cofactor

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] subMatrix = new double[n - 1][n - 1];
                int sub_i = 0;

                for (int k = 0; k < n; k++) {
                    if (k == i)
                        continue; // Jika row sama dengan minor skipp

                    int sub_j = 0;
                    for (int l = 0; l < n; l++) {
                        if (l == j)
                            continue; // Jika kolom sama dengan minor skip

                        subMatrix[sub_i][sub_j] = matrix[k][l];
                        sub_j++;
                    }
                    sub_i++;
                }
                // mengubah tanda, saat i+j genap menjadi positive dan saat i+j ganjil menjadi
                // negative
                // mencari determinant untuk mendapatkan minor
                cofactor[i][j] = (Math.pow(-1, i + j) * determinanKofaktor(subMatrix));
            }
        }

        return cofactor;
    }

    public static double[][] matrixAdjoin(double[][] matrix) {
        // Mendapatkan cofactor dari matrix
        double[][] cofactor = matrixCofactor(matrix);
        // Transpose cofactor untuk menghasilkan matrix adjoin
        double[][] adjoint = Transpose(cofactor);

        return adjoint;
    }


}