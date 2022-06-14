package controller;

import DB.ConnectionDB;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserCon {

    public boolean auth(String username, String password){
        User user = this.get(username);
        if(user.getUsername()!=null) return (user.getPassword().equals(password));
        else return false;
    }

    public void insert(User user) {
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "INSERT INTO user (username, password, akses) VALUES (?,?,?)";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAkses());
            ps.executeUpdate();
            System.out.println("Data has been saved");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Data cannot insert into table");
        }
    }
    
    public void update(User user) {
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "UPDATE user SET username=?, password=?, akses=? WHERE id_user";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAkses());
            ps.executeUpdate();
            System.out.println("Data has been updated");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Data cannot update from table");
        }
    }
    
    public void delete(User user) {
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "DELETE FROM user WHERE id_user=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.executeUpdate();
            System.out.println("Data has been deleted");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Data cannot update from table");
        }
    }


    public User get(String username) {
        User user = new User();
        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM user WHERE username=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAkses(rs.getInt("akses"));

            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Cannot get data from table by using userId");
        }
        return user;
    }


    public List<User> list() {
        List<User> listUser = new ArrayList<User>();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM user";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAkses(rs.getInt("akses"));
                listUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listUser;
    }
}
