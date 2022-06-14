package controller;

import DB.ConnectionDB;
import model.Product;
import model.User;
import model.Wishlist;
import view.MenuLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static view.MenuLogin.productCon;


public class WishlistCon {
    public void insert(Wishlist wishlist) {
        if(!isProductinWishlist(wishlist.getIdProduk())) {
            try {
                Connection conDB = ConnectionDB.getConnection();
                String sql = "INSERT INTO wishlist (id_user,id_produk) VALUES (?,?)";
                PreparedStatement ps = conDB.prepareStatement(sql);
                ps.setInt(1, wishlist.getIdUser());
                ps.setInt(2, wishlist.getIdProduk());
                ps.executeUpdate();
                System.out.println("Data has been saved");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Cannot insert into table");
            }
        }else System.out.println("Produk sudah ada di dalam wishlist kamu!");
    }

    public void update(Wishlist wishlist) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "UPDATE history SET id_user =?, id_produk=? WHERE id_wishlist=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, wishlist.getIdUser());
            ps.setInt(2, wishlist.getIdProduk());
            ps.setInt(3, wishlist.getIdWishlist());
            ps.executeUpdate();
            System.out.println("Data has been update");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot update into table");
        }
    }

    public void delete(Wishlist wishlist) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "DELETE FROM wishlist WHERE id_wishlist=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, wishlist.getIdWishlist());
            ps.executeUpdate();
            System.out.println("Data has been delete");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot delete into table");
        }
    }
    public Wishlist get(int wishlistID) {
        Wishlist wishlist = new Wishlist();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM wishlist WHERE id_wishlist=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, wishlistID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                wishlist.setIdWishlist(rs.getInt("id_wishlist"));
                wishlist.setIdUser(rs.getInt("id_user"));
                wishlist.setIdProduk(rs.getInt("id_produk"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table by using productId");
        }
        return wishlist;
    }

    public List<Product> list(User user) {
        List<Product> listWishlist = new ArrayList<Product>();
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM wishlist where id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product product = productCon.get(rs.getInt("id_produk"));
                listWishlist.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listWishlist;
    }

    public boolean isProductinWishlist(int productID) {
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM wishlist where id_produk=? and id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, MenuLogin.userLoggedIn.getIdUser());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getInt("id_produk") != 0;
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return false;
    }

}
