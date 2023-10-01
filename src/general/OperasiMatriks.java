package General;
import java.util.Scanner;

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
            for (j = 0; j < matrix[i].length; j++) {
                copas[i][j] = matrix[i][j];
            }
        }

        return copas;
    }

    // Mencari hasil determinant dengan menggunakan cara cofactor
    public static Double determinanKofaktor(double[][] matriks) {

        int n = matriks.length; //
        // Basis
        if (n == 1) {
            return matriks[0][0];
        } else if (matriks.length != matriks[0].length) {
            return null;
        }

        double det = 0.0;
        int sign = 1;

        for (int j = 0; j < n; j++) {
            if (matriks[0][j] != 0) {
                double[][] subMatrix = new double[n - 1][n - 1];
                int sub_i = 0;
                for (int i = 1; i < n; i++) {
                    int sub_j = 0;
                    for (int k = 0; k < n; k++) {
                        if (k != j) {
                            // Membuat subMatrix
                            subMatrix[sub_i][sub_j] = matriks[i][k];
                            sub_j++;
                        }
                    }
                    sub_i++;
                }
                // Rekursif untuk mendapatkan nilai determinant jika belum n = 1
                det += (sign * matriks[0][j] * determinanKofaktor(subMatrix));
            }
            // Mengubah tanda
            sign = -sign;
        }

        return det;
    }

    public static Double determinanReduksi(double[][] matriks) {
        if (matriks.length != matriks[0].length) {
            return null;
        }
        int i, j, k, x;
        double[] temp;
        double m, n, p, det, sum;
        det = 1;
        sum = 1;
        for (i = 0; i < matriks.length; i++) {
            x = i;
            while (x < matriks.length && matriks[x][i] == 0) {
                x++;
            }
            if (x == matriks.length) {
                return null;
            } else if (x != i) {
                temp = matriks[i];
                matriks[i] = matriks[x];
                matriks[x] = temp;
                det *= -1;
            }

            for (j = i + 1; j < matriks.length; j++) {
                m = matriks[i][i];
                n = matriks[j][i];
                for (k = 0; k < matriks.length; k++) {
                    p = (m * matriks[j][k]) - (n * matriks[i][k]);
                    matriks[j][k] = p;
                }
                sum *= m;
            }
        }
        for (i = 0; i < matriks.length; i++) {
            det *= matriks[i][i];
        }
        return det / sum;
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
    
    // Menghasilkan matriks Hilbert
    public static void matrixHilbert() {
        // KAMUS LOKAL
        int i, j;
        Scanner S;

        // ALGORITMA
        S = new Scanner(System.in);
        System.out.print("Masukkin nilai N = ");
        int N = S.nextInt();
        double[][] Hilbert = new double[N][N];
        double[][] B = new double[N][1];

        // Initialize Hilbert matrix
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                Hilbert[i][j] = 1.0 / (i + j + 1);
            }
        }

        // Initialize B matrix
        for (i = 0; i < N; i++) {
            if (i == 0) {
                B[i][0] = 1.0; 
            } else {
                B[i][0] = 0.0; 
            }
        }

        // Display Matrix Hilbert and B berseblahan
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                System.out.printf("%.4f ", Hilbert[i][j]);
            }
            System.out.print(" | "); 
            System.out.printf("%.4f ", B[i][0]);
            System.out.println(); 
        }
    }


}