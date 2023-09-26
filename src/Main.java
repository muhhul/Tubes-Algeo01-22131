package src;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        Scanner masukan = new Scanner(System.in);
        while (true) {
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaaan Linier\r\n" + //
                "2. Determinan\r\n" + //
                "3. Matriks balikan\r\n" + //
                "4. Interpolasi Polinom\r\n" + //
                "5. Interpolasi Bicubic Spline\r\n" + //
                "6. Regresi linier berganda\r\n" + //
                "7. Keluar\r\n" + //
                "");
        System.out.print("Option = ");
        String opsi = masukan.nextLine();
        // Matriks balikan
        if (opsi.equals("3")){
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("1. Metode Gauss Jordan\n2. Metode Adjoin");
                opsi = in.nextLine();
                if (opsi.equals("1")) {
                    inversMatrix.inverseGaussJordan(null);
                    break;
                }
                else if (opsi.equals("2")) {
                    inversMatrix.inverseAdjoint(null);
                    break;
                } else {
                System.out.println("Input salah harap mengulang kembali");
            }
        }
            in.close();
        }
        else if (opsi.equals("7")){
        System.exit(0);
        }
        // Interpolasi Bicubic Spline
        else if (opsi.equals("5")){

        }



        masukan.close();
        }
    }
}
