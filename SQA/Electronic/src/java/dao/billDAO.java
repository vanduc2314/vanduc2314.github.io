/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DAO.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Bill;
import model.Customer;
import model.bproduct;
import model.deli_invoice;

/**
 *
 * @author pvand
 */
public class billDAO extends DAO {

    public ArrayList<Bill> getListbill() {
        ArrayList<Bill> bill = new ArrayList<>();

        String sql = "select * from (select tblbill.id bcode,tbluser.name cname,cdate,status from tblbill,tbluser\n"
                + "where tblbill.CustomerID =tbluser.id)b ,(select tblbillID, sum(price) amount from bproduct group by (tblbillID))s\n"
                + "where b.bcode=s.tblbillID";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill b = new Bill(rs.getInt("bcode"), rs.getString("status"), rs.getString("cname"), rs.getTimestamp("cdate"), rs.getDouble("amount"));
                System.out.println(b);
                bill.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    public Bill getbillbycode(String code) {
        Bill b = new Bill();
        ArrayList<bproduct> listp = new ArrayList<>();
        ArrayList<deli_invoice> listdinv = new ArrayList<>();
        String sql = "select * from(select bcode,cname,phone,cdate,status,amount,discount,payment_type,note,quantity sell,bproduct.tblProductID as pid, bproduct.price sprice from (select * from (select tblbill.id bcode,tbluser.name cname,tbluser.phone,cdate,status,discount,payment_type,note from tblbill,tbluser\n"
                + "                where tblbill.CustomerID =tbluser.id)b ,(select tblbillID, sum(price) amount from bproduct group by (tblbillID))s\n"
                + "                              where b.bcode=s.tblbillID and b.bcode=?) bill, bproduct where bill.bcode = bproduct.tblBillID) e, tblproduct where e.pid = tblproduct.id";
        String sql2 = "select newd.id, newd.tblbillID,newd.ShipperID,fee,ddate, status, cdate dcdate,newd.address daddress,newd.name dname, tbluser.name shipper, tbluser.phone sphone, newd.phone  from (\n"
                + "                             SELECT *\n"
                + "                   FROM tbldeli_inv\n"
                + "                   where tbldeli_inv.tblbillID=?\n"
                + "                ORDER BY tbldeli_inv.cdate DESC\n"
                + "                LIMIT 1) newd left join tbluser on tbluser.id=newd.ShipperID";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setName(rs.getString("cname"));
                c.setPhone(rs.getString("phone"));
                b = new Bill(rs.getInt("bcode"), rs.getString("status"), c, rs.getTimestamp("cdate"), rs.getDouble("amount"), listp, listdinv, rs.getString("payment_type"), rs.getString("note"), rs.getDouble("discount"));
                bproduct p = new bproduct();
                p.getP().setName(rs.getString("name"));
                p.getP().setId(rs.getInt("pid"));
                p.setQuantity(rs.getInt("sell"));
                p.setPrice(rs.getDouble("sprice"));
                p.getP().setPrice(rs.getDouble("price"));
                listp.add(p);

            }
            ps = conn.prepareStatement(sql2);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                deli_invoice d = new deli_invoice();
                d.setName(rs.getString("dname"));
                d.setFee(rs.getDouble("fee"));
                d.setCdate(rs.getTimestamp("dcdate"));
                d.setAddress(rs.getString("daddress"));
                d.getS().setName(rs.getString("shipper"));
                d.getS().setPhone(rs.getString("sphone"));
                d.setPhone(rs.getString("phone"));
                d.setId(rs.getInt("id"));
                d.setStatus(rs.getString("status"));
                listdinv.add(d);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean updatebill(Bill b, String button) {
        String sql1 = "UPDATE `tblbill` SET `status`=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, b.getStatus());
            ps.setInt(2, b.getId());
            int rs = ps.executeUpdate();
            if (rs != 0) {
                if (button.equals("approve")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (button.equals("select")) {
            String sql = "UPDATE `tbldeli_inv` SET `ShipperID`=?,`status`=? WHERE id=?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, b.getDinv().get(0).getS().getId());
                ps.setInt(3, b.getDinv().get(0).getId());
                ps.setString(2, b.getDinv().get(0).getStatus());
                int rs = ps.executeUpdate();
                if (rs != 0) {
                    System.out.println("Updated");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (button.equals("complete") || button.equals("print")) {
            String sql = "UPDATE `tbldeli_inv` SET `status`=? WHERE id=?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, b.getDinv().get(0).getStatus());
                ps.setInt(2, b.getDinv().get(0).getId());
                int rs = ps.executeUpdate();
                if (rs != 0) {
                    System.out.println("Updated");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<Bill> getbillbyproduct(String sdate, String edate, String pid) {
        ArrayList<Bill> bill = new ArrayList<>();
        Timestamp start = Timestamp.valueOf(sdate + " 00:00:00");
        Timestamp end = Timestamp.valueOf(edate + " 23:59:59");
        String sql = "select b.id bid,price,quantity,tblProductID,cdate from (SELECT * FROM `bproduct` WHERE tblProductID=?) a join (select *from tblbill where cdate BETWEEN ? and ?)b on a.tblBillID=b.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pid);
            ps.setTimestamp(2, start);
            ps.setTimestamp(3, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill b= new Bill();
                b.setId(rs.getInt("bid"));
                b.setCdate(rs.getDate("cdate"));
                bproduct bp= new bproduct();
                bp.setPrice(rs.getDouble("price"));
                bp.setQuantity(rs.getInt("quantity"));
                b.getBproduct().add(bp);
                bill.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
}
