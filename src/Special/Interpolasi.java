package Special;

import General.OperasiMatriks;
import General.InversMatriks;

public class Interpolasi {

    // Mencari interpolasi polinom
    public static double[] InterpolasiPolinom(double[][] matrix, double nilai) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            int maxRow = i;
            for (int k = i + 1; k < rows; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            double[] temp = matrix[i];
            matrix[i] = matrix[maxRow];
            matrix[maxRow] = temp;
        }

        for (int i = cols - 1; i >= 0; i--) {
            matrix[0][i] = matrix[0][i] / matrix[0][0];
        }
        int x = 1, y = 1;
        for (int i = 1; i < rows; i++) {
            int tempp = 0;
            for (int l = 0; l < x; l++) {
                if (matrix[i][l] != 0) {
                    double temp = matrix[i][l] / matrix[l][l];
                    for (int j = l; j < cols; j++) {
                        if (Math.abs(matrix[i][j] - (matrix[l][j] * temp)) <= 0.00000000001) {
                            matrix[i][j] = 0;
                        } else {
                            matrix[i][j] = matrix[i][j] - (matrix[l][j] * temp);
                        }
                    }
                } else {
                    tempp++;
                }
            }
            if (matrix[i][x] != 0) {
                for (int k = cols - 1; k >= x; k--) {
                    matrix[i][k] = matrix[i][k] / matrix[i][x];
                }
            }
            if (matrix[i][x] != 1 && x != cols - 1) {
                i--;
                if (x == cols - 1) {
                    x = i;
                } else {
                    y++;
                    x++;
                }
            } else {
                y++;
                x = i + 1;
            }
        }

        double[] solution = new double[cols - 1];
        for (int i = rows - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < cols - 1; j++) {
                sum = sum + (matrix[i][j] * solution[j]);
            }
            solution[i] = (matrix[i][cols - 1] - sum) / matrix[i][i];
        }

        double hasil = 0;
        double[] keluaran = new double[rows];
        for (int i = 0; i < rows; i++) {
            keluaran[i] = solution[i];
            hasil = hasil + (Math.pow(nilai, i) * solution[i]);
        }
        keluaran[rows] = hasil;
        return keluaran;
    }

    // Mencari interpolasi bicubic spline
    public static double[] interpolasiBicubic(double[][] matrix, double a, double b) {
        // KAMUS LOKAL
        int i, j, k = 0, Bi, Bj, x, y, p;
        double hasil = 0.0;

        // ALGORITMA
        double[][] Mbicubic = new double[16][16];
        double[][] Y = new double[16][1];

        // Konstruksi matrix bicubic interpolation
        Bi = 0;
        for (p = 0; p < 4; p++) {
            for (y = 0; y < 2; y++) {
                for (x = 0; x < 2; x++) {
                    Bj = 0;
                    for (i = 0; i < 4; i++) {
                        for (j = 0; j < 4; j++) {
                            int ki, kj, Q;
                            if (p == 1) { // jika p = 1 maka gunakan rumus i x X^(i-1) x Y^j dengan i = [1..3] j =
                                          // [0..3]
                                ki = (j == 0) ? -1 : j - 1;
                                kj = i;
                                Q = (j == 0) ? 0 : j;
                            } else if (p == 2) { // jika p = 2 maka gunakan rumus j x X^i x Y^(j-1) dengan i = [0..3] j
                                                 // = [1..3]
                                ki = j;
                                kj = (i == 0) ? -1 : i - 1;
                                Q = (i == 0) ? 0 : i;
                            } else if (p == 3) { // jika p = 3 maka gunakan rumus ixj X^(i-1) x Y^(j-1) dengan i =
                                                 // [0..3] j = [0..3]
                                ki = j - 1;
                                kj = i - 1;
                                Q = i * j;
                            } else { // jika p = 0 maka gunakan rumus X^i x Y^j dengan i = [0..3] j = [0..3]
                                ki = j;
                                kj = i;
                                Q = 1;
                            }

                            Mbicubic[Bi][Bj] = (ki >= 0 && kj >= 0) ? Q * Math.pow(x, ki) * Math.pow(y, kj) : 0;

                            Bj++;
                        }
                    }
                    Bi++;
                }
            }
        }

        // Inverse matrix Bicubic
        double[][] MbicubicInverse = InversMatriks.inverseGaussJordan(Mbicubic);

        // Matrix 4x4 to matrix 16x1
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                Y[k][0] = matrix[i][j];
                k++;
            }
        }

        // Mengalikan inverse bicubic dengan matrix 16x1 untuk mendapatkan koefisian (a)
        // ---> Y x X^(-1) = a
        double[][] koefisien = OperasiMatriks.multiplyMatrix(MbicubicInverse, Y);

        k = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                hasil += koefisien[k][0] * Math.pow(a, i) * Math.pow(b, j);
                k++;
            }
        }


        double[] balikan = { a, b, hasil };
        return balikan;
    }

   
}
