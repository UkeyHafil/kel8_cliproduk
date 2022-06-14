package view;

import com.mysql.jdbc.EscapeTokenizer;
import controller.*;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MenuLogin {
    public static User userLoggedIn = new User();
    public static KeranjangCon keranjangCon = new KeranjangCon();
    public static Scanner scanner = new Scanner(System.in);
    public static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    public static BufferedReader input = new BufferedReader(inputStreamReader);
    public static WishlistCon wishlistCon = new WishlistCon();
    public static HistoryCon historyCon = new HistoryCon();
    public static UserCon userCon = new UserCon();
    public static ProductCon productCon = new ProductCon();
    public static void showMenu(){
        int pilih = 1;
        while(pilih != 0) {
            System.out.println("============== SILAHKAN LOG IN / REGISTER ==============");
            System.out.print("1. Login\n2. Register\n0. Quit\nMasukan pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1:
                    Login.showMenu();
                    break;
                case 2:
                    Register.showMenu();
                    break;
                case 0:
                    System.out.println("Terimakasih sudah berbelanja!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }
}
