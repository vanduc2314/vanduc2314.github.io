/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Stat2 extends Staff{
    private double doanhthu;
    private double lai;
    private double von;

    public Stat2() {
    }

    public Stat2(double doanhthu, double lai, double von) {
        this.doanhthu = doanhthu;
        this.lai = lai;
        this.von = von;
    }

    public double getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(double doanhthu) {
        this.doanhthu = doanhthu;
    }

    public double getLai() {
        return lai;
    }

    public void setLai(double lai) {
        this.lai = lai;
    }

    public double getVon() {
        return von;
    }

    public void setVon(double von) {
        this.von = von;
    }
    
    
    
}
