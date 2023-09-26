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
        String opsi = masukan.nextLine();
        if (opsi.equals("3")){

        }
        else if (opsi.equals("7")){
        System.exit(0);
        }
        else if (opsi.equals("5")){

        }




        masukan.close();
        }
    }
}
