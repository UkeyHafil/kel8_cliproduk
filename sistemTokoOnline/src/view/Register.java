package view;

import controller.UserCon;
import model.User;

import static view.MenuLogin.scanner;

public class Register {
    public static void showMenu(){
        int pilih = 1;
        while(pilih != 0){
            System.out.println("============== SILAHKAN REGISTER ==============");
            System.out.println("Masukan username:");
            String username = scanner.nextLine().trim();
            System.out.println("Masukan password:");
            String password = scanner.nextLine();
            System.out.println("Repeat password:");
            String konfirmasi = scanner.nextLine();
            UserCon userCon = new UserCon();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAkses(1);
            if(password.equals(konfirmasi)){
                userCon.insert(user);
                pilih=0;
            }else System.out.println("Password tidak sesuai!");
        }
    }
}
