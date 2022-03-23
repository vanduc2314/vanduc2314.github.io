/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author pvand
 */
public class deli_invoice {
    private int id;
    private String name;
    private String address;
    private String phone;
    private double fee;
    private Staff s= new Staff();
    private String note;
    private Date cdate;
    private Date deli_date;
    private String status;

    public deli_invoice() {
    }

    public deli_invoice(String name, String address, double fee, String note, Date cdate, Date deli_date, String status) {
        this.name = name;
        this.address = address;
        this.fee = fee;
        this.note = note;
        this.cdate = cdate;
        this.deli_date = deli_date;
        this.status=status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Staff getS() {
        return s;
    }

    public void setS(Staff s) {
        this.s = s;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getDeli_date() {
        return deli_date;
    }

    public void setDeli_date(Date deli_date) {
        this.deli_date = deli_date;
    }
    
}
