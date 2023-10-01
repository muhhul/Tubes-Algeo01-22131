package Special;

public class RegresiLinearBerganda {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Masukkan banyak kasus(baris): ");
    int rows = keyboard.nextInt();
    System.out.print("Masukkan banyak variabel x + y(kolom): ");
    int cols = keyboard.nextInt();

    double[][] matrix = new double[rows][cols];
    System.out.println("Masukkan variabel x diikuti y untuk setiap barisnya");
    for(int i = 0; i < rows; i++){
        for(int j = 0; j < cols; j++){
                matrix[i][j] = keyboard.nextDouble();
        }
    }

    double[][] newMatrix = new double[cols][cols+1];
    for(int i = 0; i < cols; i++){
        for(int j = 0; j < cols+1; j++){
            for(int k = 0; k < rows; k++){
                if(i==0 && j==0){
                    newMatrix[i][j]++;
                }else if(j==cols){
                    if(i==0){
                        newMatrix[i][j]=newMatrix[i][j]+matrix[k][cols-1];
                    }else{
                        newMatrix[i][j]=newMatrix[i][j]+(matrix[k][cols-1]*matrix[k][i-1]);
                    }
                }else{
                    if(i==0){
                        newMatrix[i][j]=newMatrix[i][j]+matrix[k][j-1];
                    }else if(j==0){
                        newMatrix[i][j]=newMatrix[i][j]+matrix[k][i-1];
                    }else{
                        newMatrix[i][j]=newMatrix[i][j]+(matrix[k][i-1]*matrix[k][j-1]);
                    }
                }
            }
        }
    }

    for(int i = 0; i < cols; i++){
        int maxRow = i;
        for(int k = i + 1; k < cols; k++){
            if(Math.abs(newMatrix[k][i]) > Math.abs(newMatrix[maxRow][i])){
                maxRow = k;
            }
        }
        double[] temp = newMatrix[i];
        newMatrix[i] = newMatrix[maxRow];
        newMatrix[maxRow] = temp;
    }
    
    System.out.println("Matriks yang diinputkan:");
    for(int i = 0; i < cols; i++){
        for(int j = 0; j < cols+1; j++){
            System.out.print(newMatrix[i][j] + "\t");
        }
        System.out.println();
    }
    rows = cols;
    cols = cols+1;
    for(int i = cols-1; i >= 0; i--){
        newMatrix[0][i] =  newMatrix[0][i]/newMatrix[0][0];
    }
    int x=1,y=1;
    for(int i = 1; i < rows; i++){
        int tempp=0;
        for(int l = 0; l < x; l++){
            if(newMatrix[i][l] != 0){
                System.out.println("Matriks yang:");
                double temp = newMatrix[i][l]/newMatrix[l][l];
                for(int j = l; j<cols;j++){
                    System.out.println("Matriks yang diinputkan:");
                    for(int a = 0; a < rows; a++){
                        for(int b = 0; b < cols; b++){
                            System.out.print(newMatrix[a][b] + "\t");
                        }
                        System.out.println();
                    }
                    if(Math.abs(newMatrix[i][j] - (newMatrix[l][j]*temp)) <= 0.00000000001){
                        newMatrix[i][j]=0;
                    }else{
                        newMatrix[i][j] = newMatrix[i][j] - (newMatrix[l][j]*temp);
                    }
                }
            }else{
                tempp++;
            }
        }
        if(newMatrix[i][x] != 0){
            for(int k = cols-1; k >= x; k--){
                newMatrix[i][k] =  newMatrix[i][k]/newMatrix[i][x];
            }
        }
        if(newMatrix[i][x]!=1 && x!=cols-1){
            i--;
            if(x==cols-1){
                x=i;
            }else{
                y++;
                x++;
            }
        }else{
            y++;
            x=i+1;
        }
    }

    double[] solution = new double[cols-1];
    for(int i = rows - 1; i >= 0; i--){
        double sum = 0.0;
        for(int j = i + 1; j < cols-1; j++){
            sum = sum + (newMatrix[i][j] * solution[j]);
        }
        solution[i] = (newMatrix[i][cols-1] - sum) / newMatrix[i][i];
    }

    DecimalFormat df = new DecimalFormat("#.####");
    System.out.print("y = " + df.format(solution[0]) + " ");

    if(newMatrix[rows-1][cols-2] == 0 && newMatrix[rows-1][cols-1]!=0){
        System.out.println("spl tidak memiliki solusi");
    }else{
        for(int i = 1; i < cols-1; i++){
            if(solution[i]>=0){
                System.out.print("+ " + df.format(solution[i]) + " X" + i + " ");
            }else{
                System.out.print("- " + df.format(Math.abs(solution[i])) + " X" + i + " ");
            }
        }
    }double hasil =0 ;
    for(int i = 0; i < cols-1; i++){
        hasil = hasil + (Math.pow(x, i)*solution[i]);
    }
}
