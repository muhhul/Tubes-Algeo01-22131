package Special;

public class SPL {
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
    
    public static String[] splGauss(double[][] matriks) {
        return null;
    }

    public static String[] splGaussJ(double[][] matriks) {
        return null;
    }

    public static String[] splInvers(double[][] matriks) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] matrix = new double[rows][cols];

        int n = matrix.length;
        double pengkalian;
        double[][] inverse = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
        for (int j = 0; j < matrix[i].length; j++) {
            if (i == j) {
                inverse[i][j] = 1;
            } else {
                inverse[i][j] = 0;
            }
        }
        String keluaran[] = new String[rows];
        String keluaran2[] = new String[1];
        for (int i = 0; i < n; i++) {
            pengkalian = matrix[i][i];
            if (pengkalian == 0.0f) {
                keluaran2[0] = "Matrix tersebut singular, tidak bisa diinverse. Silahkan gunakan metode lain";
                return keluaran2;
            }

            for (int j = 0; j < n; j++) {
                matrix[i][j] /= pengkalian;
                inverse[i][j] /= pengkalian;
            }

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

        for(int i = 0; i < rows; i++){
            double temp = 0;
            for(int j = 0; j < n; j++){
                temp = temp + (inverse[i][j]*matrix[j][cols-1]);
            }
            keluaran[i] = temp;
        }
        return keluaran;
    }

    public static String[] splCramer(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        double[][] newMatrix = new double[rows][cols-1];
        double[][] temp = new double[rows][cols-1];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols-1; j++){
                newMatrix[i][j] = matrix[i][j];
                temp[i][j] = matrix[i][j];
            }
        }
        String keluaran[] = new String[cols-1];
        String keluaran2[] = new String[1];
        for(int i = 0; i < cols-1; i++){
            if(determinanKofaktor(newMatrix) == 0.0f){
                keluaran2[0] = "Determinan matriks nol, tidak ada solusi unik. Silahkan gunakan metode lain";
                return keluaran2;
            }
            double detM1 = determinanKofaktor(newMatrix);
            for(int j = 0; j < rows; j++){
                temp[j][i] = matrix[j][cols-1];
            } 
            double detM2 = determinanKofaktor(temp);
            for(int j = 0; j < rows; j++){
                temp[j][i] = matrix[j][i];
            } 
            keluaran[i] = (detM2/detM1);
        }
        return keluaran;
    }
}
