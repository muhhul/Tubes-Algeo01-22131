package Special;

import General.OperasiMatriks;


import General.Fungsi;
import General.InversMatriks;

public class Interpolasi {

    // Mencari interpolasi polinom
    public static double[] InterpolasiPolinom(double[][] matrix, double nilai) {
        int rows = matrix.length;
        int cols = rows+1;//diubah

        double[][] newmatrix = new double[rows][cols];
        for(int i = 0; i < rows; i++){
            double temp = matrix[i][0];
            for(int j = 0; j < cols; j++){
                if(j==0){
                    newmatrix[i][j]=1;
                }else if(j==rows){
                    temp = matrix[i][1];
                    newmatrix[i][j]=temp;
                }else{
                    newmatrix[i][j]=Math.pow(temp, j);

                }
            }
        }

        for (int i = 0; i < rows; i++) {
            int maxRow = i;
            for (int k = i + 1; k < rows; k++) {
                if (Math.abs(newmatrix[k][i]) > Math.abs(newmatrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            double[] temp = newmatrix[i];
            newmatrix[i] = newmatrix[maxRow];
            newmatrix[maxRow] = temp;
        }

        for (int i = cols - 1; i >= 0; i--) {
            newmatrix[0][i] = newmatrix[0][i] / newmatrix[0][0];
        }
        int x = 1, y = 1;
        for (int i = 1; i < rows; i++) {
            int tempp = 0;
            for (int l = 0; l < x; l++) {
                if (newmatrix[i][l] != 0) {
 
                    double temp = newmatrix[i][l] / newmatrix[l][l];
                    for (int j = l; j < cols; j++) {
                        if (Math.abs(newmatrix[i][j] - (newmatrix[l][j] * temp)) <= 0.00000000001) {
                            newmatrix[i][j] = 0;
                        } else {

                            newmatrix[i][j] = newmatrix[i][j] - (newmatrix[l][j] * temp);
                        }
                    }
                } else {
                    tempp++;
                }
            }
            if (newmatrix[i][x] != 0) {
                for (int k = cols - 1; k >= x; k--) {

                    newmatrix[i][k] = newmatrix[i][k] / newmatrix[i][x];
                }
            }
            if (newmatrix[i][x] != 1 && x != cols - 1) {
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

                sum = sum + (newmatrix[i][j] * solution[j]);
            }

            solution[i] = (newmatrix[i][cols - 1] - sum) / newmatrix[i][i];
        }

        double hasil = 0;
        double[] keluaran = new double[rows+3];
        keluaran[0]=rows-1;
        int j = 1;
        for (int i = rows; i >=1; i--) {

            keluaran[j] = Math.round(solution[i-1] * 1000000.0) / 1000000.0;

            hasil = hasil + (Math.pow(nilai, i-1) * (solution[i-1]));
            j++;
        }
        hasil = Math.round(hasil * 1000000.0) / 1000000.0;
        keluaran[rows+1] = nilai;
        keluaran[rows+2] = hasil;
        Fungsi.pause();
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

        //f(a,b) = k * a^i + b^j
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
