/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produits{
    
	private String id_produit;
	private String designation;
	private float prix;
	private int quantite;
	private String type_produit;
	private String type_c_nc;
	private String sous;
	private int id_type_sous;
	private String nom_type_sous;
	
	public Produits(String id, String designation, float prix, int quantite,
				   String type_produit, String type_c_nc, String sous,
				   int id_type_sous, String nom_type_sous){
		   
		   this.id_produit = id;
		   this.designation = designation;
		   this.prix = prix;
		   this.quantite = quantite;
		   this.type_produit = type_produit;
		   this.type_c_nc = type_c_nc;
		   this.sous = sous;
		   this.id_type_sous = id_type_sous;
                   this.nom_type_sous = nom_type_sous;
		   	     	   
	}
	
	public Produits(String id, String designation, float prix, int quantite,
				   String type_produit, String type_c_nc, String sous){
		   
		   this.id_produit = id;
		   this.designation = designation;
		   this.prix = prix;
		   this.quantite = quantite;
		   this.type_produit = type_produit;
		   this.type_c_nc = type_c_nc;
		   this.sous = sous;
		   	     	   
	}

    public String getDesignation() {
        return designation;
    }

    public String getId_produit() {
        return id_produit;
    }

    public int getId_type_sous() {
        return id_type_sous;
    }

    public String getNom_type_sous() {
        return nom_type_sous;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getSous() {
        return sous;
    }

    public String getType_c_nc() {
        return type_c_nc;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_c_nc(String type_c_nc) {
        this.type_c_nc = type_c_nc;
    }

    
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setId_produit(String id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_type_sous(int id_type_sous) {
        this.id_type_sous = id_type_sous;
    }

    public void setNom_type_sous(String nom_type_sous) {
        this.nom_type_sous = nom_type_sous;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setSous(String sous) {
        this.sous = sous;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

        //  add product
    public static void addProduit( String designation, float prix, float quantite,
                   String type_c_nc, String sous, int id_type_sous, int retour, int quanti_min){

         ResultSet rs= null;
         PreparedStatement pst=null;
    
         
        
       java.sql.Connection conn2= Connect.ConnectDB("infra2");
            
         String insert_pro="INSERT INTO `produit`(`id`, `designation`, `prix`, `quantity`, `type_C_N`, `sous`, `type_sous`, `retounable`,`quant_min`) VALUES (?,?,?,?,?,?,?,?,?)";//pour inserer dans la table produit
         
            try {
                pst=conn2.prepareStatement(insert_pro);
                pst.setString(1, (designation+sous).toLowerCase().hashCode()+"");
                 pst.setString(2, designation);
                pst.setFloat(3, prix);
                pst.setFloat(4, quantite);
                pst.setString(5, type_c_nc);
                pst.setString(6, sous);
                pst.setInt(7, id_type_sous);
                pst.setInt(8, retour);
                pst.setInt(9, quanti_min);
                System.out.println("id of add product : "+(designation+sous).toLowerCase().hashCode());
                pst.execute(); 
                conn2.close();
            } catch (SQLException ex) {
                Logger.getLogger(Produits.class.getName()).log(Level.SEVERE, null, ex);
            }
          
             
    }

    
       public static void addProduitBFA( String designation, float prix, float quantite,
                   String type_c_nc, String sous, int retour, int quanti_min){

         ResultSet rs= null;
         PreparedStatement pst=null;
    
         java.sql.Connection conn2= Connect.ConnectDB("infra2");
        
       
            
         String insert_pro="INSERT INTO `produit`(`id`, `designation`, `prix`, `quantity`, `type_C_N`, `sous`, `retounable`,`quant_min`) VALUES (?,?,?,?,?,?,?,?)";//pour inserer dans la table produit
         
            try {
                pst=conn2.prepareStatement(insert_pro);
                pst.setString(1, (designation+sous).toLowerCase().hashCode()+"");
                 pst.setString(2, designation);
                pst.setFloat(3, prix);
                pst.setFloat(4, quantite);
                pst.setString(5, type_c_nc);
                pst.setString(6, sous);
               
                pst.setInt(7, retour);
                pst.setInt(8, quanti_min);
                pst.execute(); 
            } catch (SQLException ex) {
                Logger.getLogger(Produits.class.getName()).log(Level.SEVERE, null, ex);
            }
          
             
    }


}
