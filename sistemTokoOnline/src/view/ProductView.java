package view;

import model.Product;

import java.util.List;

import static view.MenuLogin.productCon;
import static view.MenuLogin.scanner;

public class ProductView {
    public static void tambahProduk(){
        System.out.print("Masukan nama produk: ");
        String nama = scanner.nextLine();
        System.out.print("Masukan barcode: ");
        String barcode = scanner.nextLine();
        System.out.print("Masukan harga: ");
        int harga = scanner.nextInt();
        System.out.print("Masukan jumlah stok: ");
        int stok = scanner.nextInt();
        Product product = new Product();
        product.setNama(nama);
        product.setBarcode(barcode);
        product.setHarga(harga);
        product.setStock(stok);
        productCon.insert(product);

    }
    public static void updateProduk(){
        lihatProduk();
        System.out.print("Masukan id produk yang akan diubah: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Product product = productCon.get(id);
        System.out.print("Masukan nama produk: ");
        product.setNama(scanner.nextLine());
        System.out.print("Masukan barcode: ");
        product.setBarcode(scanner.nextLine());
        System.out.print("Masukan harga: ");
        product.setHarga(scanner.nextInt());
        System.out.print("Masukan jumlah stok: ");
        product.setStock(scanner.nextInt());

        productCon.update(product);
    }
    public static void hapusProduk(){
        lihatProduk();
        System.out.print("Masukan id produk yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        productCon.delete(productCon.get(id));

    }
    public static void lihatProduk(){
        List<Product> listProduct = productCon.list();
        System.out.println("\n+-----------------------------------------------------------------------+");
        System.out.println("|                           DATA PRODUK                                 |");
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("+-----------------------------------------------------------------------+");
        System.out.println("|\tID Produk\t|\tBarcode\t|\tNama Produk\t|\tHarga (Rp.)\t|\tStock\t|");
        System.out.println("+-----------------------------------------------------------------------+");
        for(Product product: listProduct){
            System.out.printf("|%1$15d|%2$11s|%3$15s|%4$15d|%5$11d|%n", product.getIdProduk(), product.getBarcode(), product.getNama(), product.getHarga(), product.getStock());
        }
        System.out.println("+-----------------------------------------------------------------------+");
    }
}
