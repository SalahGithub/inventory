/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salah
 */
public class remplir_prod {
    public static void main(String[] args) {
        ResultSet rs= null;
         PreparedStatement pst=null;
    
        Connection conn1= Connect.ConnectDB("infra_ancien");
        Connection conn2= Connect.ConnectDB("infra2");
        
        String sql="select * from produits";
       
        try
        {
            pst=conn1.prepareStatement(sql);
            rs=pst.executeQuery();
            
           while(rs.next()){
            String  desig=rs.getString("designation").toUpperCase();
            String  nature=rs.getString("nature");//type_c_n
            String  sous=rs.getString("sous");//90/bfa
            String  famille=rs.getString("famille");//901-902-903...
            String  prix=rs.getString("prixuht");
            String quantity=rs.getString("stock");
            int hash=(desig+sous).toLowerCase().hashCode();
            
            String insert_produit="INSERT IGNORE INTO produit(id, designation, prix, quantity,type_C_N, sous,type_sous) VALUES (?,?,?,?,?,?,?)";//pour inserer dans la table produit
             try
                    {
                        pst=conn2.prepareStatement(insert_produit);

                        pst.setString(1,hash+"");
                        pst.setString(2,desig);
                        pst.setString(3, prix);
                        pst.setString(4,quantity);
                        if(nature.equals("consomable")){
                            pst.setString(5, "C");
                        }else{
                            pst.setString(5, "NC");
                        }
                        
                        
                        
                        if(sous.equals("90")){
                            pst.setString(6,"P90");
                        }else{
                            pst.setString(6, sous);
                        }
                        if(sous.equals("P90")){
                            pst.setString(7, famille);
                        }else{
                            pst.setString(7, null);
                        }
                        
                       
                        pst.execute();
                    }catch(SQLException ex)
        {
            System.out.println(desig);
            Logger.getLogger(remplir_prod.class.getName()).log(Level.SEVERE, null, ex);
        }  
            
            
        }
    }catch (SQLException ex)
        {
            Logger.getLogger(remplir_prod.class.getName()).log(Level.SEVERE, null, ex);
        }   
}}
