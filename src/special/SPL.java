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
        return null;
    }

    public static String[] splCramer(double[][] matrix) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris matriks: ");
        int rows = matrix.length;
        System.out.print("Masukkan jumlah kolom matriks: ");
        int cols = matrix[0].length;

        double[][] matrix = new double[rows][cols];
        System.out.println("Masukkan elemen-elemen matriks:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = keyboard.nextDouble();
            }
        }
        
        double[][] newMatrix = new double[rows][cols-1];
        double[][] temp = new double[rows][cols-1];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols-1; j++){
                newMatrix[i][j] = matrix[i][j];
                temp[i][j] = matrix[i][j];
            }
        }

        System.out.println("Matriks yang diinputkan:");
        
        for(int i = 0; i < cols-1; i++){
            if(determinanKofaktor(newMatrix) == 0.0f){
                throw new ArithmeticException("Determinan matriks nol, tidak ada solusi unik.");
            }
            double detM1 = determinanKofaktor(newMatrix);
            for(int j = 0; j < rows; j++){
                temp[j][i] = matrix[j][cols-1];
            } 
            double detM2 = determinanKofaktor(temp);
            for(int j = 0; j < rows; j++){
                temp[j][i] = matrix[j][i];
            } 

            System.out.println("x[" + i + "] = " + (detM2/detM1));
        }
        return null;
    }
}
