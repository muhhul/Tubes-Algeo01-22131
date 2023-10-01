package Special;

public class RegresiLinearBerganda {
    public static double[] regresiLinear(double[][] matriks, double nilai) {
        int rows = matriks.length;
        int cols = matriks[0].length;
        double[][] newMatrix = new double[cols][cols + 1];
        double[] equation = new double[cols];
        double[] solution = new double[cols + 2];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols + 1; j++) {
                for (int k = 0; k < rows; k++) {
                    if (i == 0 && j == 0) {
                        newMatrix[i][j]++;
                    } else if (j == cols) {
                        if (i == 0) {
                            newMatrix[i][j] = newMatrix[i][j] + matriks[k][cols - 1];
                        } else {
                            newMatrix[i][j] = newMatrix[i][j] + (matriks[k][cols - 1] * matriks[k][i - 1]);
                        }
                    } else {
                        if (i == 0) {
                            newMatrix[i][j] = newMatrix[i][j] + matriks[k][j - 1];
                        } else if (j == 0) {
                            newMatrix[i][j] = newMatrix[i][j] + matriks[k][i - 1];
                        } else {
                            newMatrix[i][j] = newMatrix[i][j] + (matriks[k][i - 1] * matriks[k][j - 1]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < cols; i++) {
            int maxRow = i;
            for (int k = i + 1; k < cols; k++) {
                if (Math.abs(newMatrix[k][i]) > Math.abs(newMatrix[maxRow][i])) {
                    maxRow = k;
                }
            }
            double[] temp = newMatrix[i];
            newMatrix[i] = newMatrix[maxRow];
            newMatrix[maxRow] = temp;
        }
        rows = cols;
        cols = cols + 1;
        for (int i = cols - 1; i >= 0; i--) {
            newMatrix[0][i] = newMatrix[0][i] / newMatrix[0][0];
        }
        int x = 1, y = 1;
        for (int i = 1; i < rows; i++) {
            int tempp = 0;
            for (int l = 0; l < x; l++) {
                if (newMatrix[i][l] != 0) {
                    System.out.println("Matriks yang:");
                    double temp = newMatrix[i][l] / newMatrix[l][l];
                    for (int j = l; j < cols; j++) {
                        System.out.println("Matriks yang diinputkan:");
                        for (int a = 0; a < rows; a++) {
                            for (int b = 0; b < cols; b++) {
                                System.out.print(newMatrix[a][b] + "\t");
                            }
                            System.out.println();
                        }
                        if (Math.abs(newMatrix[i][j] - (newMatrix[l][j] * temp)) <= 0.00000000001) {
                            newMatrix[i][j] = 0;
                        } else {
                            newMatrix[i][j] = newMatrix[i][j] - (newMatrix[l][j] * temp);
                        }
                    }
                } else {
                    tempp++;
                }
            }
            if (newMatrix[i][x] != 0) {
                for (int k = cols - 1; k >= x; k--) {
                    newMatrix[i][k] = newMatrix[i][k] / newMatrix[i][x];
                }
            }
            if (newMatrix[i][x] != 1 && x != cols - 1) {
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
        for (int i = rows - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < cols - 1; j++) {
                sum = sum + (newMatrix[i][j] * equation[j]);
            }
            equation[i] = (newMatrix[i][cols - 1] - sum) / newMatrix[i][i];
        }
        for (int k = 0; k < equation.length; k++) {
            solution[k] = equation[k];
        }
        double hasil = 0;
        for (int i = 0; i < cols - 1; i++) {
            hasil = hasil + (Math.pow(x, i) * equation[i]);
        }
        solution[solution.length - 2] = nilai;
        solution[solution.length - 1] = hasil;
        return solution;
    }
}