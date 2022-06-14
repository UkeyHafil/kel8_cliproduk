package controller;

import DB.ConnectionDB;
import model.*;
import view.MenuLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static view.MenuLogin.keranjangCon;
import static view.MenuLogin.productCon;


public class KeranjangCon {
    public void insert(Keranjang keranjang) {
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "INSERT INTO keranjang (id_user,id_produk,kuantitas) VALUES (?,?,?)";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, keranjang.getIdUser());
            ps.setInt(2, keranjang.getIdProduk());
            ps.setInt(3, keranjang.getKuantitas());
            ps.executeUpdate();
            System.out.println("Data has been saved");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot insert into table");
        }
    }

    public void update(Keranjang keranjang) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "UPDATE keranjang SET kuantitas=? WHERE id_keranjang=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, keranjang.getKuantitas());
            ps.setInt(2, keranjang.getIdKeranjang());
            ps.executeUpdate();
            System.out.println("Data has been update");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot update into table");
        }
    }

    public void delete(Keranjang keranjang) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "DELETE FROM keranjang WHERE id_keranjang=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, keranjang.getIdKeranjang());
            ps.executeUpdate();
            System.out.println("Data has been delete");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot delete into table");
        }
    }
    public Keranjang get(int keranjangId) {
        Keranjang keranjang = new Keranjang();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM keranjang WHERE id_keranjang=? and id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, keranjangId);
            ps.setInt(2, MenuLogin.userLoggedIn.getIdUser());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                keranjang.setIdKeranjang(rs.getInt("id_keranjang"));
                keranjang.setIdUser(rs.getInt("id_user"));
                keranjang.setIdProduk(rs.getInt("id_produk"));
                keranjang.setKuantitas(rs.getInt("kuantitas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table by using productId");
        }
        return keranjang;
    }

    public List<Product> list(User user) {
        List<Product> listKeranjang = new ArrayList<Product>();
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM keranjang where id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ResultSet rs = ps.executeQuery();
            ProductCon productCon = new ProductCon();
            while(rs.next()){
                Product product = productCon.get(rs.getInt("id_produk"));
                product.setStock(rs.getInt("kuantitas"));
                listKeranjang.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listKeranjang;
    }

    public void insertToChart(int kuantitas, int id){
        Keranjang keranjang = getProductinChart(id);
        if(this.getProductinChart(id).getIdKeranjang()==0){
            if(kuantitas > productCon.get(id).getStock())
                System.out.println("Jumlah kuantitas di keranjang tidak boleh melebihi stock!\nSaat ini di keranjang kamu terdapat " +
                        this.getProductinChart(id).getKuantitas() + " buah kuantitas barang tersebut, kamu hanya bisa menambah" +
                        (productCon.get(id).getStock() - this.getProductinChart(id).getKuantitas()) + " buah kuantitas lagi");
            else {
                keranjang.setIdUser(MenuLogin.userLoggedIn.getIdUser());
                keranjang.setIdProduk(id);
                keranjang.setKuantitas(kuantitas);
                this.insert(keranjang);
            }
        }else{
            if(this.getProductinChart(id).getKuantitas()+kuantitas > productCon.get(id).getStock())
                System.out.println("Jumlah kuantitas di keranjang tidak boleh melebihi stock!\nSaat ini di keranjang kamu terdapat " +
                        this.getProductinChart(id).getKuantitas() + " buah kuantitas barang tersebut, kamu hanya bisa menambah " +
                        (productCon.get(id).getStock() - this.getProductinChart(id).getKuantitas()) + " buah kuantitas lagi");
            else{
                keranjang.setKuantitas(kuantitas + this.getProductinChart(id).getKuantitas());
                this.update(keranjang);
            }
        }
    }

    public List<Keranjang> getUserChart(User user){
        List<Keranjang> listKeranjang = new ArrayList<>();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM keranjang WHERE id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Keranjang keranjang = keranjangCon.get(rs.getInt("id_keranjang"));
                listKeranjang.add(keranjang);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
        }
        return listKeranjang;
    }

    public void checkout(){
        List<Keranjang> list = getUserChart(MenuLogin.userLoggedIn);
        if (list == null ) {
            System.out.println("Keranjang kamu masih kosong!");
        } else {
            for (Keranjang keranjangItem : list) {
                HistoryCon historyCon = new HistoryCon();
                History history = new History();
                history.setIdProduk(keranjangItem.getIdProduk());
                history.setKuantitas(keranjangItem.getIdKeranjang());
                history.setIdUser(keranjangItem.getIdUser());
                historyCon.insert(history);
                this.delete(keranjangItem);
            }
        }
    }

    public Keranjang getProductinChart(int productID) {
        Keranjang keranjang = new Keranjang();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM keranjang where id_produk=? and id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, MenuLogin.userLoggedIn.getIdUser());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                keranjang = keranjangCon.get(rs.getInt("id_keranjang"));
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return keranjang;
    }
}