/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;

import java.sql.Date;

/**
 *
 * @author Salah
 */
public class C12 {
    private String id;
    private String id_emplo;
    private int id_depar;
    private int num_c12;
    private Date date;
    private String type_r_l;
    private String remarque;
    private String type_c12_bfa_90;
    private String C_CN;

    public C12(String id, String id_emplo, int id_depar, int num_c12, Date date, String type_r_l, String remarque, String type_c12_bfa_90, String C_CN) {
        this.id = id;
        this.id_emplo = id_emplo;
        this.id_depar = id_depar;
        this.num_c12 = num_c12;
        this.date = date;
        this.type_r_l = type_r_l;
        this.remarque = remarque;
        this.type_c12_bfa_90 = type_c12_bfa_90;
        this.C_CN = C_CN;
    }

    public C12(String id, int id_depar, int num_c12, Date date, String type_r_l, String remarque, String type_c12_bfa_90, String C_CN) {
        this.id = id;
        this.id_depar = id_depar;
        this.num_c12 = num_c12;
        this.date = date;
        this.type_r_l = type_r_l;
        this.remarque = remarque;
        this.type_c12_bfa_90 = type_c12_bfa_90;
        this.C_CN = C_CN;
    }

    public C12(String id, String id_emplo, int id_depar, int num_c12, Date date, String type_r_l, String type_c12_bfa_90, String C_CN) {
        this.id = id;
        this.id_emplo = id_emplo;
        this.id_depar = id_depar;
        this.num_c12 = num_c12;
        this.date = date;
        this.type_r_l = type_r_l;
        this.type_c12_bfa_90 = type_c12_bfa_90;
        this.C_CN = C_CN;
    }

    public C12(String id, int id_depar, int num_c12, Date date, String type_r_l, String type_c12_bfa_90, String C_CN) {
        this.id = id;
        this.id_depar = id_depar;
        this.num_c12 = num_c12;
        this.date = date;
        this.type_r_l = type_r_l;
        this.type_c12_bfa_90 = type_c12_bfa_90;
        this.C_CN = C_CN;
    }

    public String getId() {
        return id;
    }

    public String getId_emplo() {
        return id_emplo;
    }

    public int getId_depar() {
        return id_depar;
    }

    public int getNum_c12() {
        return num_c12;
    }

    public Date getDate() {
        return date;
    }

    public String getType_r_l() {
        return type_r_l;
    }

    public String getRemarque() {
        return remarque;
    }

    public String getType_c12_bfa_90() {
        return type_c12_bfa_90;
    }

    public String getC_CN() {
        return C_CN;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId_emplo(String id_emplo) {
        this.id_emplo = id_emplo;
    }

    public void setId_depar(int id_depar) {
        this.id_depar = id_depar;
    }

    public void setNum_c12(int num_c12) {
        this.num_c12 = num_c12;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType_r_l(String type_r_l) {
        this.type_r_l = type_r_l;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setType_c12_bfa_90(String type_c12_bfa_90) {
        this.type_c12_bfa_90 = type_c12_bfa_90;
    }

    public void setC_CN(String C_CN) {
        this.C_CN = C_CN;
    }
    
    
}
