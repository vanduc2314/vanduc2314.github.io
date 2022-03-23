/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pvand
 */
public class Stat extends Product{

    private Double revenue;
    private int total_quantity;

    public Stat() {
    }

    public Stat(Double revenue, int total_quantity, String name, int id, String note, int quantity, Double price, String sku) {
        super(name, id, note, quantity, price, sku);
        this.revenue = revenue;
        this.total_quantity = total_quantity;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

}
