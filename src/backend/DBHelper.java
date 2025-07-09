package backend;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
        
public class DBHelper {
    private static Connection koneksi;

    public static void bukaKoneksi() {
        if (koneksi == null) {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("dbperpus");
            data.setUser("root");
            data.setPassword("");
            try {
                koneksi = data.getConnection();
                System.out.println("sudah connect");
            }
            catch (SQLException ex) {
                System.out.println("belum connect");
            }
        }
    }
    
    public static int insertQueryGetId(String query) {
        bukaKoneksi();
        int num = 0;
        int result = -1;
        
        try {
            Statement stmt = koneksi.createStatement();
            num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (rs.next()) {
                result = rs.getInt(1);
            }
            
            rs.close();
            stmt.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }
    
    public static boolean executeQuery(String query) {
        bukaKoneksi();
        boolean result = false;
        
        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query);
            
            result = true;
            
            stmt.close();
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static ResultSet selectQuery(String query) {
        bukaKoneksi();
        ResultSet rs = null;
        
        try {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
}
