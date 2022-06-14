package view;

import static view.MenuLogin.scanner;
import static view.MenuLogin.userCon;

public class MenuAdmin {
    public static void showMenu(){
        int pilih = 1;
        while(pilih != 0) {
            System.out.println("============== SELAMAT DATANG! ============");
            System.out.println("1. Tambah Produk\n" +
                    "2. Lihat Produk\n" +
                    "3. Update Produk\n" +
                    "4. Delete Produk\n" +
                    "5. History Pembelian\n" +
                    "6. Ubah Password\n" +
                    "0. Log out\n");
            System.out.print("Masukan pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih){
                case 1:
                    ProductView.tambahProduk();
                    break;
                case 2:
                    ProductView.lihatProduk();
                    break;
                case 3:
                    ProductView.updateProduk();
                    break;
                case 4:
                    ProductView.hapusProduk();
                    break;
                case 5:
                    HistoryView.lihatRiwayatAllUser();
                    break;
                case 6:
                    System.out.println("Masukan password baru: ");
                    String password = scanner.nextLine();
                    System.out.println("Repeat password: ");
                    String konfirmasi = scanner.nextLine();
                    if(password.equals(konfirmasi)){
                        MenuLogin.userLoggedIn.setPassword(password);
                        userCon.update(MenuLogin.userLoggedIn);
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }
}
