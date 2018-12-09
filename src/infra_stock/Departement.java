/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

/**
 *
 * @author Salah
 */
public class Departement {
    private String id;
    private String nom;
    private String num_depar;
    private String num_tel;
    private String nom_resp;
    private String info;
    
     public Departement(String nom, String num_depar, String num_tel, String nom_resp, String info) {
        this.nom = nom;
        this.num_depar = num_depar;
        this.num_tel = num_tel;
        this.nom_resp = nom_resp;
        this.info = info;
    }

    public Departement(String nom, String num_depar, String num_tel, String nom_resp) {
        this.nom = nom;
        this.num_depar = num_depar;
        this.num_tel = num_tel;
        this.nom_resp = nom_resp;
    }

    public Departement(String nom, String num_depar, String nom_resp) {
        this.nom = nom;
        this.num_depar = num_depar;
        this.nom_resp = nom_resp;
    }
    
   /****getters setters****/

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNum_depar() {
        return num_depar;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getNom_resp() {
        return nom_resp;
    }

    public String getInfo() {
        return info;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNum_depar(String num_depar) {
        this.num_depar = num_depar;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setNom_resp(String nom_resp) {
        this.nom_resp = nom_resp;
    }

    public void setInfo(String info) {
        this.info = info;
    }

   
}
