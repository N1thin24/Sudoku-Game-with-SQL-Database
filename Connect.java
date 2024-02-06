/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import java.sql.*;
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List; 

public class Connect {
    /**
    * Connect to a sample database
    */
   static String url = "jdbc:sqlite:C:\\Users\\nfran\\Desktop\\java\\project\\Sudoku\\Databases\\scores.db";
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
    String sql = "CREATE TABLE IF NOT EXISTS Score (\n"
            + "	id INTEGER PRIMARY KEY AutoIncrement,\n"
            + " name text NOT NULL, \n"
            + "	ScoreVal integer NOT NULL\n"
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
   
   public static void insert(String name, Integer scoreval) {
    String sql = "INSERT INTO Score(name,scoreval) VALUES(?,?)";
    
    try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);
        pstmt.setInt(2, scoreval);
        pstmt.executeUpdate();
        System.out.println("Inserted");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
   
public static String[] MaxSelect(String name) {
    String sql = "SELECT	\n name,	scoreval \n FROM Score \n WHERE \n scoreval = (SELECT MAX(scoreval) FROM Score) and name = '"+name+"'";
    List<String>l = new ArrayList<String>();
    String sa[]={};
    try (Connection conn = DriverManager.getConnection(url);){
        Statement stat=conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
          String s1= "name = " + rs.getString("name");
          String s2= "score = " + rs.getInt("scoreval");
          l.add(s1);
          l.add(s2);
        }
        System.out.println(l);
        sa=l.toArray(sa);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return sa;
}

public static String[] HighScore() {
    String sql = "SELECT	\n name,	scoreval \n FROM Score \n order by scoreval desc limit 5";
    List<String>l = new ArrayList<String>();
    String sa[]={};
    try (Connection conn = DriverManager.getConnection(url);){
        Statement stat=conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
          String s1= "name = " + rs.getString("name");
          String s2= "score = " + rs.getInt("scoreval");
          l.add(s1);
          l.add(s2);
        }
        System.out.println(l);
        sa=l.toArray(sa);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return sa;
}


   
   /**
    * @param args the command line arguments
    */

}
