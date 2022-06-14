package view;

import model.Product;

import java.util.List;

import static view.MenuLogin.wishlistCon;

public class WishlistView {

    public static void lihatWishlist(){
        List<Product> wishList = wishlistCon.list(MenuLogin.userLoggedIn);
        System.out.println("\n+-----------------------------------------------------------+");
        System.out.println("|                   ISI WISHLIST KAMU                       |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|\tID Produk\t|\tBarcode\t|\tNama Produk\t|\tHarga (Rp)\t|");
        System.out.println("+-----------------------------------------------------------+");
        for(Product product: wishList){
            System.out.printf("|%1$15d|%2$11s|%3$15s|%4$15d|\n", product.getIdProduk(), product.getBarcode(), product.getNama(), product.getHarga());
        }
        System.out.println("+-----------------------------------------------------------+");

    }
}
