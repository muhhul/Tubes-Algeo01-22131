package Special;

import java.util.Scanner;

import General.Fungsi;
import General.Konversi;

public class SPL {
    public static Scanner keyboard;

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

    public static String[] splGauss(double[][] matrix) {
        System.out.println(Konversi.matriksKeString(matrix));
        Fungsi.pause();
        int rows = matrix.length;
        int cols = matrix[0].length;
        String[] keluaran = new String[cols - 1];
        String[] keluaran2 = new String[1];

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

        for (int r = 0; r < rows; r++) {
            for (int k = 0; k < rows; k++) {
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i < cols; i++) {
                    if (matrix[r][i] != 0) {
                        break;
                    }
                    count1++;
                }
                for (int i = 0; i < cols; i++) {
                    if (matrix[k][i] != 0) {
                        break;
                    }
                    count2++;
                }
                if (count2 > count1) {
                    double[] temp = matrix[r];
                    matrix[r] = matrix[k];
                    matrix[k] = temp;
                }
            }
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

        int trivial = 0;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][cols - 1] != 0) {
                trivial = 1;
            }
        }

        if (matrix[rows - 1][cols - 2] == 0 && matrix[rows - 1][cols - 1] != 0) {
            keluaran2[0] = "SPL tidak memiliki solusi.";
            return keluaran2;
        } else if (((matrix[rows - 1][cols - 2] == 0 && matrix[rows - 1][cols - 1] == 0) || rows != cols - 1)
                && trivial == 1)

        {
            String[][] temppp = new String[cols - 1][3];
            for (int i = 0; i < cols - 1; i++) {
                int huruf = 97 + i;
                char temp = (char) huruf;
                temppp[i][0] = "" + temp;
                temppp[i][1] = "1";
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols - 1; j++) {
                    if (matrix[i][j] != 0) {
                        temppp[j][0] = "";
                        temppp[j][1] = "0";
                        temppp[j][2] = Double.toString(matrix[i][j]);
                        break;
                    }
                }
            }
            int count = cols - 2;
            for (int i = rows - 1; i >= 0; i--) {
                double nilsem = 0;
                if (temppp[count][1] == "0") {
                    nilsem = (matrix[i][cols - 1] / (Double.valueOf(temppp[count][2])));
                    int cek = 0;
                    for (int j = 0; j < cols - 1; j++) {
                        if (j != count) {
                            if (matrix[i][j] != 0) {
                                matrix[i][j] = matrix[i][j] * (-1);
                                if (temppp[j][1] == "0") {
                                    if ((matrix[i][j] / (Double.valueOf(temppp[count][2])))
                                            * Double.valueOf(temppp[j][0]) > 0) {
                                        nilsem = nilsem + ((matrix[i][j] / (Double.valueOf(temppp[count][2])))
                                                * Double.valueOf(temppp[j][0]));
                                    } else {
                                        nilsem = nilsem + ((matrix[i][j] / (Double.valueOf(temppp[count][2])))
                                                * Double.valueOf(temppp[j][0]));
                                    }
                                } else {
                                    temppp[count][1] = "1";
                                    if (matrix[i][j] > 0 && temppp[count][0] != "") {
                                        temppp[count][0] = temppp[count][0] + " + ";
                                    }
                                    temppp[count][0] = temppp[count][0]
                                            + Double.toString(matrix[i][j] / (Double.valueOf(temppp[count][2])))
                                            + temppp[j][0];
                                }
                            }
                        }
                    }
                    for (int k = 0; k < cols - 1; k++) {
                        if (matrix[i][k] != 0) {
                            cek = 1;
                        }
                    }
                    if (cek == 0) {
                        count++;
                    } else if (nilsem < 0) {
                        temppp[count][0] = "" + temppp[count][0] + Double.toString(nilsem);
                    } else if (nilsem != 0 || (nilsem == 0 && temppp[count][0] == "")) {
                        temppp[count][0] = "" + Double.toString(nilsem) + temppp[count][0];
                    }
                    count--;
                } else {
                    count--;
                    i++;
                }
            }
            for (int i = 0; i < cols - 1; i++) {
                keluaran[i] = temppp[i][0];
            }
        } else {
            if (trivial == 0) {
                for (int i = 0; i < cols - 1; i++) {
                    keluaran[i] = "0.0";
                }
            } else {
                for (int i = 0; i < cols - 1; i++) {
                    keluaran[i] = String.valueOf(solution[i]);
                }
            }
        }
        return keluaran;
    }

    public static String[] splGaussJ(double[][] matriks) {
        int rows = matriks.length;
        int cols = matriks[0].length;
        String[] keluaran = new String[cols - 1];
        String[] keluaran2 = new String[1];
        int n = matriks.length;
        double pengkalian;
        for (int r = 0; r < rows; r++) {
            int maxRow = r;
            for (int k = r + 1; k < rows; k++) {
                if (Math.abs(matriks[k][r]) > Math.abs(matriks[maxRow][r])) {
                    maxRow = k;
                }
            }
            double[] temp = matriks[r];
            matriks[r] = matriks[maxRow];
            matriks[maxRow] = temp;
        }
        for (int i = 0; i < n; i++) {
            pengkalian = matriks[i][i];
            for (int j = 0; j < cols; j++) {
                if (matriks[i][i] != 0.0f) {
                    matriks[i][j] /= pengkalian;
                }
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    pengkalian = matriks[k][i];
                    for (int j = 0; j < cols; j++) {
                        if (Math.abs(matriks[k][j] - pengkalian * matriks[i][j]) < 0.00000001) {
                            matriks[k][j] = 0;
                        } else {
                            matriks[k][j] -= pengkalian * matriks[i][j];
                        }
                    }
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int k = 0; k < rows; k++) {
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i < cols; i++) {
                    if (matriks[r][i] != 0) {
                        break;
                    }
                    count1++;
                }
                for (int i = 0; i < cols; i++) {
                    if (matriks[k][i] != 0) {
                        break;
                    }
                    count2++;
                }
                if (count2 > count1) {
                    double[] temp = matriks[r];
                    matriks[r] = matriks[k];
                    matriks[k] = temp;
                }
            }
        }
        int trivial = 0;
        for (int i = 0; i < rows; i++) {
            if (matriks[i][cols - 1] != 0) {
                trivial = 1;
            }
        }

        if (matriks[rows - 1][cols - 2] == 0 && matriks[rows - 1][cols - 1] != 0) {
            keluaran2[0] = "SPL tidak memiliki solusi.";
            return keluaran2;
        } else if (((matriks[rows - 1][cols - 2] == 0 && matriks[rows - 1][cols - 1] == 0) || rows != cols - 1)
                && trivial == 1) {
            String[][] temppp = new String[cols - 1][3];
            for (int i = 0; i < cols - 1; i++) {
                int huruf = 97 + i;
                char temp = (char) huruf;
                temppp[i][0] = "" + temp;
                temppp[i][1] = "1";
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols - 1; j++) {
                    if (matriks[i][j] != 0) {
                        temppp[j][0] = "";
                        temppp[j][1] = "0";
                        temppp[j][2] = Double.toString(matriks[i][j]);
                        break;
                    }
                }
            }
            int count = cols - 2;
            for (int i = rows - 1; i >= 0; i--) {
                double nilsem = 0;
                if (temppp[count][1] == "0") {
                    nilsem = (matriks[i][cols - 1] / (Double.valueOf(temppp[count][2])));
                    int cek = 0;
                    for (int j = 0; j < cols - 1; j++) {
                        if (j != count) {
                            if (matriks[i][j] != 0) {
                                matriks[i][j] = matriks[i][j] * (-1);
                                if (temppp[j][1] == "0") {
                                    if ((matriks[i][j] / (Double.valueOf(temppp[count][2])))
                                            * Double.valueOf(temppp[j][0]) > 0) {
                                        nilsem = nilsem + ((matriks[i][j] / (Double.valueOf(temppp[count][2])))
                                                * Double.valueOf(temppp[j][0]));
                                    } else {
                                        nilsem = nilsem + ((matriks[i][j] / (Double.valueOf(temppp[count][2])))
                                                * Double.valueOf(temppp[j][0]));
                                    }
                                } else {
                                    temppp[count][1] = "1";
                                    if (matriks[i][j] > 0 && temppp[count][0] != "") {
                                        temppp[count][0] = temppp[count][0] + " + ";
                                    }
                                    temppp[count][0] = temppp[count][0]
                                            + Double.toString(matriks[i][j] / (Double.valueOf(temppp[count][2])))
                                            + temppp[j][0];
                                }
                            }
                        }
                    }
                    for (int k = 0; k < cols - 1; k++) {
                        if (matriks[i][k] != 0) {
                            cek = 1;
                        }
                    }
                    if (cek == 0) {
                        count++;
                    } else if (nilsem < 0) {
                        temppp[count][0] = "" + temppp[count][0] + Double.toString(nilsem);
                    } else if (nilsem != 0 || (nilsem == 0 && temppp[count][0] == "")) {
                        temppp[count][0] = "" + Double.toString(nilsem) + temppp[count][0];
                    }
                    count--;
                } else {
                    count--;
                    i++;
                }
            }
            for (int i = 0; i < cols - 1; i++) {
                keluaran[i] = temppp[i][0];
            }
        } else {
            for (int i = 0; i < cols - 1; i++) {
                keluaran[i] = String.valueOf(matriks[i][cols - 1]);
            }
        }
        return keluaran;
    }

    public static String[] splInvers(double[][] matriks) {
        int rows = matriks.length;
        int cols = matriks[0].length;

        int n = matriks.length;
        double pengkalian;
        double[][] inverse = new double[matriks.length][matriks[0].length];
        for (int i = 0; i < matriks.length; i++)
            for (int j = 0; j < matriks[i].length; j++) {
                if (i == j) {
                    inverse[i][j] = 1;
                } else {
                    inverse[i][j] = 0;
                }
            }
        String keluaran[] = new String[rows];
        String keluaran2[] = new String[1];
        for (int i = 0; i < n; i++) {
            pengkalian = matriks[i][i];
            if (pengkalian == 0.0f) {
                keluaran2[0] = "Matriks bersifat singular, tidak ada solusi unik. Silahkan gunakan metode lain";
                return keluaran2;
            }
            for (int j = 0; j < n; j++) {
                matriks[i][j] /= pengkalian;
                inverse[i][j] /= pengkalian;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    pengkalian = matriks[k][i];
                    for (int j = 0; j < n; j++) {
                        matriks[k][j] -= pengkalian * matriks[i][j];
                        inverse[k][j] -= pengkalian * inverse[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            double temp = 0;
            for (int j = 0; j < n; j++) {
                temp = temp + (inverse[i][j] * matriks[j][cols - 1]);
            }
            keluaran[i] = String.valueOf(temp);
        }
        return keluaran;
    }

    public static String[] splCramer(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] newMatrix = new double[rows][cols - 1];
        double[][] temp = new double[rows][cols - 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                newMatrix[i][j] = matrix[i][j];
                temp[i][j] = matrix[i][j];
            }
        }
        String keluaran[] = new String[cols - 1];
        String keluaran2[] = new String[1];
        for (int i = 0; i < cols - 1; i++) {
            if (determinanKofaktor(newMatrix) == 0.0f) {
                keluaran2[0] = "Matriks bersifat singular, tidak ada solusi unik. Silahkan gunakan metode lain";
                return keluaran2;
            }
            double detM1 = determinanKofaktor(newMatrix);
            for (int j = 0; j < rows; j++) {
                temp[j][i] = matrix[j][cols - 1];
            }
            double detM2 = determinanKofaktor(temp);
            for (int j = 0; j < rows; j++) {
                temp[j][i] = matrix[j][i];
            }
            keluaran[i] = String.valueOf(detM2 / detM1);
        }
        return keluaran;
    }
}
