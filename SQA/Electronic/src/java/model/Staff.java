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
public class Staff extends User{
    private String position;

    public Staff() {
    }




    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}
