/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

import com.mysql.jdbc.PreparedStatement;
import static infra_stock.Login_frame.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salah
 */
public class User {
    private String username;
    private String password;
 

    ResultSet rs= null;
    java.sql.PreparedStatement pst=null;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User() {
        this.username = "admin";
        this.password = "admin";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addUser(User user){
        Connection conn2= Connect.ConnectDB("infra2");
        String sql="insert into ? (username, password) values (?,?)";
        try
                    {
                        pst=conn2.prepareStatement(sql);
                        pst.setString(1,username);
                        pst.setString(1,password);
                       
                        pst.execute();
                    }catch(SQLException ex)
        {
            Logger.getLogger(remplir_prod.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
     public  void removeUser(User user){
        Connection conn2= Connect.ConnectDB("infra2");
        String sql="delete from user where username=?";
        try
                    {
                        pst=conn2.prepareStatement(sql);
                        pst.setString(1,user.getUsername());
                        pst.execute();
                    }catch(SQLException ex)
        {
            Logger.getLogger(remplir_prod.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
     
     public String getHash(User user){
         return (user.getUsername()+user.getPassword()).hashCode()+"";
     }
     
     public  void changePass(User user, String password){
        Connection conn2= Connect.ConnectDB("infra2");
        String hash=(user.getUsername()+user.getPassword()).hashCode()+"";
        String sql="delete from user where hash=?";
        try
                    {
                        pst=conn2.prepareStatement(sql);
                        pst.setString(1,hash);
                        pst.execute();
                    }catch(SQLException ex)
        {
            Logger.getLogger(remplir_prod.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
     
          public  boolean exists(User user){
        Connection conn2= Connect.ConnectDB("infra2");
             String sql="select * from user where username=?";
       boolean exist=false;
        try
        {
            pst=conn.prepareStatement(sql);
            
            pst.setString(1,user.getUsername());
            rs=pst.executeQuery();
            
            if (rs.first()){
                return true;
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Login_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exist;
    }
     
}
