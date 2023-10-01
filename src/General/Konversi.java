package General;

import java.util.Scanner;

// class ini menyimpan fungsi-fungsi untuk mengkonversi hasil perhitungan menjadi string sesuai format luaran
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

    // konversi hasil spl menjadi string sesuai format
    public static String splKeString(String[] solusi) {
        String luaran = "";
        int i;
        if (solusi[0] != "SPL tidak memiliki solusi.") {
            luaran = luaran.concat("Solusi SPL: \n");
        }
        if (solusi.length == 1) {
            luaran = luaran.concat(solusi[0]);
        } else {
            for (i = 0; i < solusi.length; i++) {
                luaran = luaran.concat("x" + (i + 1) + " = " + solusi[i] + "\n");
            }
        }

        return luaran;
    }

    // konversi polinom interpolasi menjadi persamaan sesuai format
    public static String interpolasiKeString(double[] array) {
        String pangkat, luaran = "";
        int i;
        luaran = luaran.concat("Persamaan polinom interpolasi:\n");
        luaran = luaran.concat("f(x) = ");

        for (i = 1; i <= (int) array[0] + 1; i++) {
            if (i == (int) array[0]) {
                pangkat = "x";

            } else if (i == (int) array[0] + 1) {
                pangkat = "";
            } else {
                pangkat = ("x^" + ((int) array[0] + 1 - i));

            }
            if (array[i] == 0.0) {
                continue;
            } else if (array[i] < 0) {
                array[i] *= -1;
                luaran = luaran.concat(" - " + array[i] + pangkat);
            } else {
                if (i == 1) {
                    luaran = luaran.concat(array[i] + pangkat);
                } else {
                    luaran = luaran.concat(" + " + array[i] + pangkat);
                }
            }
        }
        i = (int) array[0] + 2;
        luaran = luaran.concat("\n\nSolusi:\n");
        luaran = luaran.concat("f(" + array[i] + ") = " + array[i + 1] + "\n");
        return luaran;
    }
}