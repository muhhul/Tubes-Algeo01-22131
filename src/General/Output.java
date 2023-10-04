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

    // membuat file dengan nama waktu pembuatan file
    public static Date buatFile() {
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
            Date date = buatFile();
            path = dir + dateFormat.format(date) + ".txt";
            WR = new FileWriter(path);
            WR.write("File dibuat pada: " + dateFormat.format(date) + "\n");
            WR.write(konten);
            WR.close();
            Fungsi.clearScreen();
            System.out.println("Penulisan file berhasil!");
            System.out.println("Lokasi file : " + path + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi error dalam penulisan file!");
        }
        Fungsi.pause();
    }
}
