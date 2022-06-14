package view;

import model.Wishlist;

import static view.MenuLogin.*;

public class MenuUser {

    public static void showMenu(){
        int pilih = 1;
        while(pilih != 0){
            System.out.println("\n============== SELAMAT DATANG! ============");
            System.out.print("""
                    1. Lihat Produk
                    2. Lihat Keranjang
                    3. Lihat Wishlist
                    4. Check Out
                    5. Lihat Riwayat Belanja
                    6. Ubah Password
                    0. Log Out
                    Masukan pilihan:\s""");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1:
                    int id = -1;
                    while (id!=0){
                        ProductView.lihatProduk();
                        System.out.print("""

                                Masukan id barang yang akan dimasukan kedalam keranjang atau wishlist!
                                *silahkan input 0 bila ingin kembali
                                Masukan pilihan:\s""");
                        id = scanner.nextInt();
                        if(id==0) break;
                        int kuantitas;
                        if((productCon.get(id).getIdProduk())!=0) {
                            System.out.print("""
                                    =========================================
                                    1. Masukan ke keranjang
                                    2. Masukan ke wishlist
                                    Masukan pilihan:\s""");
                            int pilih2 = scanner.nextInt();
                            switch (pilih2) {
                                case 1 -> {
                                    System.out.print("Masukan kuantitas: ");
                                    kuantitas = scanner.nextInt();
                                    keranjangCon.insertToChart(kuantitas, id);
                                }
                                case 2 -> {
                                    Wishlist wishlist = new Wishlist();
                                    wishlist.setIdUser(MenuLogin.userLoggedIn.getIdUser());
                                    wishlist.setIdProduk(id);
                                    wishlistCon.insert(wishlist);
                                }
                                default -> System.out.println("Pilihan tidak valid!");
                            }
                        }else System.out.println("ID produk tidak valid!");
                    }
                    break;
                case 2:
                    KeranjangView.lihatKeranjang();
                    System.out.println("""
                            Checkout?
                            1. Ya
                            0. Tidak
                            Masukan pilihan:\s""");
                    int pilih2 = scanner.nextInt();
                    if(pilih2==1) keranjangCon.checkout();
                    break;
                case 3:
                    WishlistView.lihatWishlist();
                    id = 0;
                    while (id!=0){
                        WishlistView.lihatWishlist();
                        System.out.print("""
                                Masukan id barang yang akan dimasukan kedalam keranjang satu-persatu!
                                *input 0 bila sudah selesai
                                Masukan pilihan:\s""");
                        id = scanner.nextInt();
                        System.out.print("Masukan kuantitas: ");
                        int kuantitas = scanner.nextInt();
                        if(kuantitas > productCon.get(id).getStock()) System.out.println("Jumlah kuantitas tidak boleh melebihi stock!");
                        else keranjangCon.insertToChart(kuantitas,id);
                    }
                    break;
                case 4:
                    keranjangCon.checkout();
                    break;
                case 5:
                    HistoryView.lihatRiwayat();
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
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }

        }
    }
}
