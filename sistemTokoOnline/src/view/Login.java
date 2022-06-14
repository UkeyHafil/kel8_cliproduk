package view;

import controller.UserCon;

import static view.MenuLogin.scanner;
import static view.MenuLogin.userLoggedIn;

public class Login {
    public static void showMenu(){
        int pilih = 1;
        while(pilih != 0) {
            System.out.println("============== SILAHKAN LOG IN ==============");
            System.out.print("Masukan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukan password: ");
            String password = scanner.nextLine();
            UserCon userCon = new UserCon();
            if(userCon.auth(username,password)){
                MenuLogin.userLoggedIn = userCon.get(username);
                if (userLoggedIn.getAkses() == 1) {
                    MenuUser.showMenu();
                } else {
                    MenuAdmin.showMenu();
                }
                pilih = 0;
            }else{
                System.out.println("Username atau password salah!");
            }
        }
    }
}
