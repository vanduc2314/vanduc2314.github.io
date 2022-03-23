/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author pvand
 */
public class Customer extends User {

    private Double point;

    public Customer() {
        point=0.0;
    }
    
    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

}
