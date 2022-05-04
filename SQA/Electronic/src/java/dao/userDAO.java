/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Staff;
import model.User;

/**
 *
 * @author pvand
 */
public class userDAO extends DAO {

    public boolean check_duplicate(String phone) {
        String sql = "SELECT * FROM tbluser WHERE phone= ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
//dang nhap trang quan tri

    public Staff check_user(String username, String password) {
        Staff s = new Staff();
        String sql = "SELECT * FROM tbluser WHERE username= ? and password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s.setName(rs.getString("name"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setPhone(rs.getString("phone"));
                s.setAddress(rs.getString("address"));
                s.setEmail(rs.getString("email"));
                s.setPosition(rs.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
//them khach hang

    public boolean add_client(User c) {
        String sql = "INSERT INTO tbluser(`name`, `phone`, `address`, `email`, `username`, `password`) VALUES (?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getUsername());
            ps.setString(6, c.getPassword());
            int rs = ps.executeUpdate();
            if (rs != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Staff> getListShipper() {
        ArrayList<Staff> slist = new ArrayList<>();
        String sql = "SELECT * FROM `tbluser` WHERE position='shipper'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setPosition(rs.getString("position"));
                s.setUsername(rs.getString("username"));
                s.setEmail(rs.getString("email"));
                s.setPassword(rs.getString("password"));
                s.setName(rs.getString("name"));
                s.setId(rs.getInt("id"));
                s.setPhone(rs.getString("phone"));
                slist.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slist;
    }
}
