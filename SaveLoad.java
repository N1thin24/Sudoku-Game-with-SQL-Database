import java.sql.*;
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List; 

public class SaveLoad {
    /**
    * Connect to a sample database
    */
   static String url = "jdbc:sqlite:C:\\Users\\nfran\\Desktop\\java\\project\\Sudoku\\Databases\\savefile.db";
   public static void connect() {
       Connection conn = null;
       
       try {
           // db parameters
           // create a connection to the database
           conn = DriverManager.getConnection(url);
           
           System.out.println("Connection to SQLite has been established.");
           
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       } finally {
           try {
               if (conn != null) {
                   conn.close();
               }
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
       }
   }

  
   public static void createNewTable() {
    // SQLite connection string
    
    // SQL statement for creating a new table
    String sql = "CREATE TABLE IF NOT EXISTS SaveRows (\n"
            + "	id INTEGER PRIMARY KEY ,\n"
            + " c1 text NOT NULL, \n"
            + " c2 text NOT NULL, \n"
            + " c3 text NOT NULL, \n"
            + " c4 text NOT NULL, \n"
            + " c5 text NOT NULL, \n"
            + " c6 text NOT NULL, \n"
            + " c7 text NOT NULL, \n"
            + " c8 text NOT NULL, \n"
            + " c9 text NOT NULL \n"
            + ");";
    
    try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
        // create a new table
        stmt.execute(sql);
        System.out.println("Created");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
   }
   
   public static void insert(Integer id, Integer c1,Integer c2,Integer c3,Integer c4,Integer c5,Integer c6,Integer c7,Integer c8,Integer c9) {
    String sql = "INSERT OR REPLACE INTO SaveRows(id,c1,c2,c3,c4,c5,c6,c7,c8,c9) VALUES(?,?,?,?,?,?,?,?,?,?)";
    
    try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.setInt(2, c1);
        pstmt.setInt(3, c2);
        pstmt.setInt(4, c3);
        pstmt.setInt(5, c4);
        pstmt.setInt(6, c5);
        pstmt.setInt(7, c6);
        pstmt.setInt(8, c7);
        pstmt.setInt(9, c8);
        pstmt.setInt(10, c9);
        pstmt.executeUpdate();
        System.out.println("Inserted");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public static int[][] Load() {
    String sql = "SELECT	\n c1,c2,c3,c4,c5,c6,c7,c8,c9 \n FROM SaveRows \n order by id asc";
    int[][] sa = new int[9][9];
    try (Connection conn = DriverManager.getConnection(url);){
        Statement stat=conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        for(int i=0;i<9;i++){
          rs.next();
          sa[i][0]= rs.getInt("c1");
          sa[i][1]= rs.getInt("c2");
          sa[i][2]= rs.getInt("c3");
          sa[i][3]= rs.getInt("c4");
          sa[i][4]= rs.getInt("c5");
          sa[i][5]= rs.getInt("c6");
          sa[i][6]= rs.getInt("c7");
          sa[i][7]= rs.getInt("c8");
          sa[i][8]= rs.getInt("c9");

         
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return sa;
}}