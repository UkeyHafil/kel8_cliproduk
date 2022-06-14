package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    static String DB="jdbc:mysql://localhost/db_tokoonline"; //
    static String user="root";
    static String pass="";
    private static Connection con;

    public static Connection getConnection() {
        if(con==null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = DriverManager.getConnection(DB,user,pass);
            } catch (Exception e) {
                System.out.println("Database connection is failed");
            }
        }
        return con;
    }
}
