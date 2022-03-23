/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Stat;
import model.Stat2;

/**
 *
 * @author pvand
 */
public class statDAO extends DAO {

    public ArrayList<Stat> getstat(String sdate, String edate) {
                Timestamp start = Timestamp.valueOf(sdate + " 00:00:00");
        Timestamp end = Timestamp.valueOf(edate + " 23:59:59");
        
        ArrayList<Stat> slist = new ArrayList<>();

        String sql = "SELECT tblproduct.id pid, tblproduct.name name, amount, sl,price,sku from  (select tblProductID, sum(price) amount,sum(quantity) sl from  (select a.id, price,quantity,tblBillID,tblProductID from (select id from tblbill where cdate BETWEEN ? and ? ) a join bproduct on bproduct.tblBillID=a.id) b\n" +
"                             group by (b.tblProductID)) c,tblproduct\n" +
"                               where tblproduct.id=c.tblProductID";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Stat s = new Stat();
                 s.setId(rs.getInt("pid"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getDouble("price"));
                s.setTotal_quantity(rs.getInt("sl"));
                s.setRevenue(rs.getDouble("amount"));
                s.setSku(rs.getString("sku"));
                slist.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slist;
    }
    
    public ArrayList<Stat2> getstat2(String sdate, String edate) {
                Timestamp start = Timestamp.valueOf(sdate + " 00:00:00");
        Timestamp end = Timestamp.valueOf(edate + " 23:59:59");
        
        ArrayList<Stat2> slist = new ArrayList<>();

        String sql = "select tbluser.id, tbluser.name, count(b.StaffID) bill_number,  sum(b.von) von, sum(b.lai) lai, sum(b.doanhthu) doanhthu\n" +
"from tbluser ,(select tblbill.id bill_id, sum(bproduct.price*bproduct.quantity) doanhthu, sum(tblproduct.importprice*bproduct.quantity) von, \n" +
"				(sum(bproduct.price*bproduct.quantity)-sum(tblproduct.importprice*bproduct.quantity)) lai, tblbill.StaffID StaffID\n" +
"				from tblbill, bproduct, tblproduct\n" +
"				where tblbill.id = bproduct.tblBillID \n" +
"				and bproduct.tblProductID = tblproduct.id\n" +
"				group by tblbill.id) b \n" +
"where tbluser.id = b.StaffID and b.bill_id in (select id from tblbill where cdate BETWEEN ? and ?)\n" +
"group by tbluser.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Stat2 s = new Stat2();
                 s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setLai(rs.getDouble("lai"));
                s.setVon(rs.getDouble("von"));
                s.setDoanhthu(rs.getDouble("doanhthu"));
                slist.add(s);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slist;
    }
}
