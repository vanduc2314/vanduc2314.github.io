/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pvand
 */
public class Bill {

    private int id;
    private String status;
    private String note;
    private Customer c = new Customer();
    private Staff s;
    private String paymentype;
    private Date cdate;
    private Date pdate;
    private Double amount;
    private Double discount;
    private ArrayList<bproduct> bproduct = new ArrayList<>();
    private ArrayList<deli_invoice> dinv=new ArrayList<>();

    public Bill() {
    }

    public Bill(int id, String status, Customer c, Date createdate, Double amount, ArrayList<bproduct> p,ArrayList<deli_invoice> dinv, String payment,String note, Double discount ) {
        this.note=note;
        this.discount=discount;
        this.paymentype=payment;
        this.bproduct = p;
        this.id = id;
        this.status = status;
        this.c=c;
        this.cdate = createdate;
        this.amount = amount;
        this.dinv=dinv;
    }

    public Bill(int id, String status, String c, Date createdate, Double amount) {
        this.id = id;
        this.status = status;
        this.c.setName(c);
        this.cdate = createdate;
        this.amount = amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public ArrayList<deli_invoice> getDinv() {
        return dinv;
    }

    public void setDinv(ArrayList<deli_invoice> dinv) {
        this.dinv = dinv;
    }

    public ArrayList<bproduct> getBproduct() {
        return bproduct;
    }

    public void setBproduct(ArrayList<bproduct> bproduct) {
        this.bproduct = bproduct;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    public Staff getS() {
        return s;
    }

    public void setS(Staff s) {
        this.s = s;
    }

    public String getPaymentype() {
        return paymentype;
    }

    public void setPaymentype(String paymentype) {
        this.paymentype = paymentype;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

}
