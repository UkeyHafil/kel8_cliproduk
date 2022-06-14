package controller;

import DB.ConnectionDB;
import model.History;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryCon {
    public void insert(History history) {
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "INSERT INTO history (id_user,id_produk,kuantitas) VALUES (?,?,?)";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, history.getIdUser());
            ps.setInt(2, history.getIdProduk());
            ps.setInt(3, history.getKuantitas());
            ps.executeUpdate();
            System.out.println("Data has been saved");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot insert into table");
        }
    }

    public void update(History history) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "UPDATE history SET id_user =?, id_produk=?, kuantitas=? WHERE id_history=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, history.getIdUser());
            ps.setInt(2, history.getIdProduk());
            ps.setInt(3, history.getKuantitas());
            ps.setInt(4, history.getIdHistory());
            ps.executeUpdate();
            System.out.println("Data has been update");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot update into table");
        }
    }

    public void delete(History history) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "DELETE FROM history WHERE id_history=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, history.getIdHistory());
            ps.executeUpdate();
            System.out.println("Data has been delete");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot delete into table");
        }
    }
    public History get(int historyId) {
        History history = new History();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM history WHERE id_keranjang=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, historyId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                history.setIdHistory(rs.getInt("id_history"));
                history.setIdUser(rs.getInt("id_user"));
                history.setIdProduk(rs.getInt("id_produk"));
                history.setKuantitas(rs.getInt("kuantitas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table by using productId");
        }
        return history;
    }
    public List<History> list() {
        List<History> listHistory = new ArrayList<History>();
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM history";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                History history = new History();
                history.setIdHistory(rs.getInt("id_history"));
                history.setIdUser(rs.getInt("id_user"));
                history.setIdProduk(rs.getInt("id_produk"));
                history.setKuantitas(rs.getInt("kuantitas"));
                listHistory.add(history);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listHistory;
    }

    public List<History> list(User user) {
        List<History> listHistory = new ArrayList<History>();
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM history where id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                History history = new History();
                history.setIdHistory(rs.getInt("id_history"));
                history.setIdUser(rs.getInt("id_user"));
                history.setIdProduk(rs.getInt("id_produk"));
                history.setKuantitas(rs.getInt("kuantitas"));
                listHistory.add(history);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listHistory;
    }
}
