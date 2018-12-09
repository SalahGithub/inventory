package infra_stock;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Connect
{

    Connection conn=null;
    
    public static Connection ConnectDB(String base){
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex)
        {
            System.err.println(ex);
        }
        
         
        try
        {
          
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+base+"?useUnicode=yes&characterEncoding=UTF-8","root","");
             // JOptionPane.showMessageDialog(null,"connection established...");
        return conn;
        }
        catch (Exception e){
            e.printStackTrace();
        
            JOptionPane.showMessageDialog(null, "connection failed");
        return null;
        }
        
    }  
}
