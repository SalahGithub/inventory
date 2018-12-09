/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;

import org.apache.commons.csv.CSVParser;

import org.apache.commons.csv.CSVRecord;
import sun.text.normalizer.UTF16;
/**
 *
 * @author Salah
 */
public class test_1 {
    
    public static void main(String[] args) {
                
      /*  CSVParser csvParser;
     
            
            
        
        try {
            //BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Salah\\Documents\\NetBeansProjects\\excel\\bfa 10 octobre.csv"));
            BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream("C:\\Users\\Salah\\Documents\\NetBeansProjects\\excel\\p90_nc.csv"), "cp1252"));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("Num","CHAP", "NATURE  DU  MATERIEL","QUANTITE","P.U","DECOMPTE").withIgnoreHeaderCase().withDelimiter(';').withIgnoreEmptyLines().withTrim());
        System.out.println("Name : ");
        int j=0;
        
                for (CSVRecord csvRecord: csvParser) {
                    if (j==0) {
                        j=1;
                        continue;
                    }
            // Accessing Values by Column Index

            String name = csvRecord.get(4);

            //Accessing the values by column header name
            int chap=Integer.parseInt(csvRecord.get("CHAP")) ;
            String desig = csvRecord.get("NATURE  DU  MATERIEL").toUpperCase();
            
            float quant = Float.parseFloat( csvRecord.get("QUANTITE"));
          float prix = Float.parseFloat( csvRecord.get("P.U"));

            //Printing the record 

            //System.out.println("Record Number - " + csvRecord.getRecordNumber());

            System.out.println("Name : " + name);
 
            System.out.println("quan : " + chap);
            System.out.println("quan : " + quant);
            System.out.println("prix :" + prix);

            Produits.addProduit(desig, prix, quant, "NC", "P90",  chap, 0,0,conn2);
    
            System.out.println("\n\n");

        }
        } catch (IOException ex) {
            Logger.getLogger(test_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    
       */
        System.out.println(("aaabp90").toLowerCase().hashCode() );
        
    }
}
