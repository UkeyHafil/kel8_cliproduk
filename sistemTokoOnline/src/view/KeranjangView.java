package view;

import model.Product;
import java.util.List;
import static view.MenuLogin.keranjangCon;

public class KeranjangView {
    public static void lihatKeranjang(){
        int totalPesanan = 0;
        List<Product> listKeranjang = keranjangCon.list(MenuLogin.userLoggedIn);
        System.out.println("\n+---------------------------------------------------------------------------+");
        System.out.println("|                          ISI KERANJANG KAMU                               |");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|\tID Produk\t|\tBarcode\t|\tNama Produk\t|\tHarga (Rp)\t| \tKuantitas\t|");
        System.out.println("+---------------------------------------------------------------------------+");
        for(Product product: listKeranjang){
            totalPesanan += product.getHarga()*product.getStock();
            System.out.printf("|%1$15d|%2$11s|%3$15s|%4$15d|%5$15d|\n", product.getIdProduk(), product.getBarcode(), product.getNama(), product.getHarga(), product.getStock());
        }
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("Total pesanan kamu: Rp" + totalPesanan);
    }

}
