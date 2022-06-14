package view;

import model.History;
import model.Product;

import java.util.List;

import static view.MenuLogin.historyCon;
import static view.MenuLogin.productCon;

public class HistoryView {
    public static void lihatRiwayat(){
        int totalPesanan = 0;
        List<History> listHistory = historyCon.list(MenuLogin.userLoggedIn);
        System.out.println("\n+---------------------------------------------------------------------------+");
        System.out.println("|                         HISTORY BELANJA KAMU                              |");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|\tID Produk\t|\tBarcode\t|\tNama Produk\t|\tHarga (Rp)\t| \tKuantitas\t|");
        System.out.println("+---------------------------------------------------------------------------+");
        for(History history: listHistory){
            Product product = productCon.get(history.getIdProduk());
            System.out.printf("|%1$15d|%2$11s|%3$15s|%4$15d|%5$15d|\n", product.getIdProduk(), product.getBarcode(), product.getNama(), product.getHarga(), product.getStock());
        }
        System.out.println("+---------------------------------------------------------------------------+");
    }

    public static void lihatRiwayatAllUser(){
        int totalPesanan = 0;
        List<History> listHistory = historyCon.list();
        System.out.println("\n+---------------------------------------------------------------------------+");
        System.out.println("|                         HISTORY BELANJA USER                              |");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|\tID Produk\t|\tBarcode\t|\tNama Produk\t|\tHarga (Rp)\t| \tKuantitas\t|");
        System.out.println("+---------------------------------------------------------------------------+");
        for(History history: listHistory){
            Product product = productCon.get(history.getIdProduk());
            System.out.printf("|%1$15d|%2$11s|%3$15s|%4$15d|%5$15d|\n", product.getIdProduk(), product.getBarcode(), product.getNama(), product.getHarga(), product.getStock());
        }
        System.out.println("+---------------------------------------------------------------------------+");
    }
}
