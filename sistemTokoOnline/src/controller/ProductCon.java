package controller;

import DB.ConnectionDB;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductCon {
    public void insert(Product product) {
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "INSERT INTO produk (barcode,nama,harga,stock) VALUES (?,?,?,?)";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, product.getBarcode());
            ps.setString(2, product.getNama());
            ps.setInt(3, product.getHarga());
            ps.setInt(4, product.getStock());
            ps.executeUpdate();
            System.out.println("Data has been saved");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot insert into table");
        }
    }

    public void update(Product product) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "UPDATE produk SET barcode =?, nama=?, harga=?, stock=? WHERE id_produk=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, product.getBarcode());
            ps.setString(2, product.getNama());
            ps.setDouble(3, product.getHarga());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getIdProduk());
            ps.executeUpdate();
            System.out.println("Data has been update");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot update into table");
        }
    }

    public void delete(Product product) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "DELETE FROM produk WHERE id_produk=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, product.getIdProduk());
            ps.executeUpdate();
            System.out.println("Data has been delete");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot delete into table");
        }
    }
    public Product get(int productId) {
        Product product = new Product();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM produk WHERE id_produk=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                product.setIdProduk(rs.getInt("id_produk"));
                product.setBarcode(rs.getString("barcode"));
                product.setNama(rs.getString("nama"));
                product.setHarga(rs.getInt("harga"));
                product.setStock(rs.getInt("stock"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table by using productId");
        }
        return product;
    }

    public List<Product> list() {
        List<Product> listProduct = new ArrayList<Product>();
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM produk";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setIdProduk(rs.getInt("id_produk"));
                product.setBarcode(rs.getString("barcode"));
                product.setNama(rs.getString("nama"));
                product.setHarga(rs.getInt("harga"));
                product.setStock(rs.getInt("stock"));
                listProduct.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listProduct;
    }
}
