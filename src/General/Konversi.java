package General;

import java.util.Scanner;

public class Konversi {
    public static Scanner input;

    // Fungsi mengubah matriks menjadi string sesuai dengan format print
    // Menulis matriks ke layar
    public static String matriksKeString(double[][] matrix) {
        String luaran = "";
        String temp;

        if (matrix == null) {
            return ("Matriks kosong.");
        } else {
            int panjang = Fungsi.maxLengthMatriks(matrix);
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    temp = String.format("%" + panjang + "." + panjang + "s | ", String.valueOf(matrix[row][col]));
                    luaran = luaran.concat(temp);
                }
                luaran = luaran.concat("\n");
            }
            return luaran;
        }

    }
}
