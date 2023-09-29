package General;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Output {
    public static Scanner input;
    public static FileWriter WR;
    public static String dir = "test\\result\\";
    public static String path = "";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void cetakLuaran(String luaran) {
        Fungsi.clearScreen();
        System.out.println(luaran + "\n");
        Fungsi.pause();
    }
    // Salin matrix ke file (.txt)
    /*
     * public static void salinMatrixToTxt(double[][] matrix) {
     * // KAMUS LOKAL
     * int i, j;
     * 
     * // ALGORITMA
     * Scanner input = new Scanner(System.in);
     * System.out.print("File path yang ingin anda salin matrix: ");
     * String FilePath = input.nextLine();
     * try {
     * FileWriter writer = new FileWriter(FilePath);
     * for (i = 0; i < matrix.length; i++) {
     * for (j = 0; j < matrix[i].length; j++) {
     * writer.write(matrix[i][j] + " ");
     * }
     * writer.write("\n");
     * }
     * 
     * } catch (Exception e) {
     * e.printStackTrace();
     * } finally {
     * input.close();
     * }
     * }
     */

    // membuat file dengan nama waktu pembuatan file
    public static Date CreateFile() {
        String temp;
        String directory = System.getProperty("user.dir");
        directory = directory.substring(directory.lastIndexOf("\\") + 1);
        if (directory.equals("bin")) {
            dir = "..\\test\\result\\";
        } else {
            dir = "..\\test\\result\\";
        }
        Date date = new Date();
        temp = dir + dateFormat.format(date);
        System.out.println(temp);
        File file = new File(temp + ".txt");
        try {
            file.createNewFile();
            path = file.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Cannot create file");
        }
        return date;
    }

    // Fungsi untuk menambahkan string ke file
    public static void tulisKeFile(String konten) {
        try {
            Date date = CreateFile();
            path = dir + dateFormat.format(date) + ".txt";
            WR = new FileWriter(path);
            WR.write("File dibuat pada: " + dateFormat.format(date) + "\n");
            WR.write(konten);
            WR.close();
            System.out.println("Penulisan file berhasil!");
        } catch (IOException e) {
            System.out.println("Terjadi error dalam penulisan file!");
        }
        Fungsi.pause();
    }
}
