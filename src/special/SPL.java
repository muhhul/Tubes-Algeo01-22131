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
    
    public static String[] splGauss(double[][] matrix) {
        return null;
    }

    public static String[] splGaussJ(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        String[] keluaran = new String[cols-1]
        String[] keluaran2 = new String[1]
        int n = matrix.length;
        double pengkalian;
        for(int r = 0; r < rows; r++){
            int maxRow = r;
            for(int k = r + 1; k < rows; k++){
                if(Math.abs(matrix[k][r]) > Math.abs(matrix[maxRow][r])){
                    maxRow = k;
                }
            }
            double[] temp = matrix[r];
            matrix[r] = matrix[maxRow];
            matrix[maxRow] = temp;
        }
        for (int i = 0; i < n; i++) {
            pengkalian = matrix[i][i];
            for (int j = 0; j < cols; j++) {
                if(matrix[i][i]!=0.0f){
                    matrix[i][j] /= pengkalian;
                }
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    pengkalian = matrix[k][i];
                    for (int j = 0; j < cols; j++) {
                        if(Math.abs(matrix[k][j] - pengkalian * matrix[i][j])<0.00000001){
                            matrix[k][j]=0;
                        }else{
                            matrix[k][j] -= pengkalian * matrix[i][j];
                        }
                    }
                }
            }
        }
        for(int r = 0; r < rows; r++){
            for(int k = 0; k < rows; k++){
                int count1=0;
                int count2=0;
                for(int i = 0; i < cols; i++){
                    if(matrix[r][i]!=0){
                        break;
                    }count1++;
                }for(int i = 0; i < cols; i++){
                    if(matrix[k][i]!=0){
                        break;
                    }count2++;
                }if(count2>count1){
                    double[] temp = matrix[r];
                    matrix[r] = matrix[k];
                    matrix[k] = temp;
                }
            }
        }
        int trivial = 0;
        for(int i = 0;i<rows;i++){
            if(matrix[i][cols-1]!=0){
                trivial = 1;
            }
        }

        if(matrix[rows-1][cols-2] == 0 && matrix[rows-1][cols-1]!=0){
            keluaran2[0] = "spl tidak memiliki solusi";
            return keluaran2;
        }else if(((matrix[rows-1][cols-2] == 0 && matrix[rows-1][cols-1]==0) || rows!=cols-1)&&trivial==1){
            String[][] temppp = new String[cols-1][3];
            for(int i = 0; i < cols-1; i++){
                int huruf = 97+i;
                char temp = (char) huruf;
                temppp[i][0]=""+temp;
                temppp[i][1]="1";
            }
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols-1; j++){
                    if(matrix[i][j]!=0){
                        temppp[j][0]="";
                        temppp[j][1]="0";
                        temppp[j][2]=Double.toString(matrix[i][j]);
                        break;
                    }
                }
            }
            int count = cols-2;
            for(int i = rows-1; i >= 0; i--){
                double nilsem = 0;
                if(temppp[count][1]=="0"){
                    nilsem = (matrix[i][cols-1]/(Double.valueOf(temppp[count][2])));
                    int cek =0;
                    for(int j = 0; j < cols-1; j++){
                        if(j!=count){
                            if(matrix[i][j]!=0){
                                matrix[i][j] = matrix[i][j] * (-1);
                                if(temppp[j][1]=="0"){
                                    if((matrix[i][j]/(Double.valueOf(temppp[count][2])))*Double.valueOf(temppp[j][0])>0){
                                        nilsem=nilsem+((matrix[i][j]/(Double.valueOf(temppp[count][2])))*Double.valueOf(temppp[j][0]));
                                    }else{
                                        nilsem=nilsem+((matrix[i][j]/(Double.valueOf(temppp[count][2])))*Double.valueOf(temppp[j][0]));
                                    }
                                }else{
                                    temppp[count][1] = "1";
                                    if(matrix[i][j]>0&&temppp[count][0]!=""){
                                        temppp[count][0] = temppp[count][0] + " + ";
                                    }
                                    temppp[count][0] = temppp[count][0] + Double.toString(matrix[i][j]/(Double.valueOf(temppp[count][2]))) + temppp[j][0];
                                }
                            }
                        }
                    }
                    for(int k = 0;k<cols-1;k++){
                        if(matrix[i][k]!=0){
                            cek = 1;
                        }
                    }
                    if(cek==0){
                        count++;
                    }
                    else if(nilsem<0){
                        temppp[count][0] = ""+temppp[count][0] + Double.toString(nilsem); 
                    }else if(nilsem!=0 || (nilsem==0&&temppp[count][0]=="")){
                        temppp[count][0] = ""+Double.toString(nilsem) + temppp[count][0];
                    }
                    count--;
                }else{
                    count--;
                    i++;
                }
            }
            for(int i = 0; i < cols-1; i++){
                keluaran[i] = temppp[i][0];
            }
        }else{
            for(int i = 0; i < cols-1; i++){
                keluaran[i] = matrix[i][cols-1];
            }
        }
        return keluaran;
    }

    public static String[] splInvers(double[][] matrix) {
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
