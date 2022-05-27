/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.sun.org.apache.xpath.internal.operations.Div;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.management.Query.div;
import javax.swing.ListModel;
import model.Bill;
import model.Product;
import model.Staff;
import model.bproduct;
import model.deli_invoice;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author ndchu
 */
public class billDAOTest {
    // Code TS1
    billDAO bd;
    
    public billDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bd = new billDAO();
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void TB1() {
         ArrayList<Bill> billList = bd.getListbill();
         assertEquals(billList.size(), 5);
     }
     
    // Code TS2
     @Test
     public void TB2() {
         String code = "1";
         Bill bill = bd.getbillbycode(code);
         assertEquals(bill.getId(), 1);
         assertEquals(bill.getStatus(), "order");
         assertNull(bill.getNote());
         assertEquals(bill.getC().getPoint(), 0.0 , 1);
         assertEquals(bill.getS().getName(),"duc");
         assertEquals(bill.getPaymentype(), "COD");
         assertNull(bill.getPdate());
         assertEquals(bill.getAmount(), 2657.9700000000003, 1);
         assertEquals(bill.getDiscount(), 0.0, 1);
         assertEquals(bill.getBproduct().size(), 3);
         assertEquals(bill.getDinv().size(), 1);
     }
     
    // Code TS3
     @Test
     public void TB3() {
         String code = "6";
         Bill bill = bd.getbillbycode(code);
         assertEquals(bill.getId(), 0);
         assertNull(bill.getStatus());
         assertNull(bill.getNote());
         assertNull(bill.getC().getName());
         assertNull(bill.getS());
         assertNull(bill.getPaymentype());
         assertNull(bill.getCdate());
         assertNull(bill.getPdate());
         assertNull(bill.getAmount());
         assertNull(bill.getDiscount());
         assertEquals(bill.getBproduct().size(), 0);
         assertEquals(bill.getDinv().size(), 0);
     }
     
     // Code TS4
     @Test
     public void TB4() {
         String code = "abc";
         Bill bill = bd.getbillbycode(code);
         assertEquals(bill.getId(), 0);
         assertNull(bill.getStatus());
         assertNull(bill.getNote());
         assertNull(bill.getC().getName());
         assertNull(bill.getS());
         assertNull(bill.getPaymentype());
         assertNull(bill.getCdate());
         assertNull(bill.getPdate());
         assertNull(bill.getAmount());
         assertNull(bill.getDiscount());
         assertEquals(bill.getBproduct().size(), 0);
         assertEquals(bill.getDinv().size(), 0);
     }
     
      @Test
     public void TB5() {
         String code = "5";
         Bill bill = bd.getbillbycode(code);
         assertEquals(bill.getId(), 5);
         assertEquals(bill.getStatus(), "complete");
         assertNull(bill.getNote());
         assertEquals(bill.getC().getPoint(), 0.0 , 1);
         assertEquals(bill.getPaymentype(), "COD");
         assertEquals(bill.getAmount(), 566.99, 1);
         assertEquals(bill.getDiscount(), 0.0, 1);
         assertEquals(bill.getBproduct().size(), 1);
         assertEquals(bill.getDinv().size(), 0);
     
     }
     
     @Test
    public void TB6() {
        Connection con = DAO.conn;
        String testStatus = "complete";
        String button = "approve";
        String testId = "4";
        try{
            Bill b = bd.getbillbycode(testId);
            b.setStatus(testStatus);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            Bill newb = bd.getbillbycode(testId);
            assertTrue(check);
            assertEquals(b.getStatus(),newb.getStatus());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void TB7() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        String button = "complete";
        String testId = "3";
        try{
            Bill b = bd.getbillbycode(testId);
            b.getDinv().get(0).setStatus(testStatus);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            Bill newb = bd.getbillbycode(testId);
            Staff shipper = newb.getDinv().get(0).getS();
            assertTrue(check);
            assertEquals(b.getDinv().get(0).getStatus(), "delivered");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    
    
    @Test
    public void TB8() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        int shipperID = 3;
        String button = "select";
        String testId = "3";
        try{
            Bill b = bd.getbillbycode(testId);
            b.getDinv().get(0).getS().setId(shipperID);
            b.getDinv().get(0).setStatus(testStatus);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            Bill newb = bd.getbillbycode(testId);
            Staff shipper = newb.getDinv().get(0).getS();
            assertTrue(check);
            assertEquals(shipper.getName(),"Nguyen Que Chi");
            assertEquals(b.getDinv().get(0).getStatus(), "delivered");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void TB9() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        String button = "print";
        String testId = "3";
        try{
            Bill b = bd.getbillbycode(testId);
            b.getDinv().get(0).setStatus(testStatus);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            Bill newb = bd.getbillbycode(testId);
            Staff shipper = newb.getDinv().get(0).getS();
            assertTrue(check);
            assertEquals(b.getDinv().get(0).getStatus(), "delivered");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void TB10() {
        Connection con = DAO.conn;
        String testStatus = "complete";
        String button = "approve";
        String testId = "6";
        try{
            Bill b = bd.getbillbycode("3");
            b.setId(6);
            b.setStatus(testStatus);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            assertFalse(check);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void TB11() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        String button = "complete";
        String testId = "6";
        try{
           Bill b = bd.getbillbycode("3");
            b.setId(6);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            assertFalse(check);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    
    
    @Test
    public void TB12() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        int shipperID = 3;
        String button = "select";
        String testId = "6";
        try{
            Bill b = bd.getbillbycode("3");
            b.setId(6);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            assertFalse(check);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void TB13() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        String button = "print";
        String testId = "6";
        try{
            Bill b = bd.getbillbycode("3");
            b.setId(6);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            System.out.println(check);
            assertFalse(check);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void TB14() {
        Connection con = DAO.conn;
        String testStatus = "delivered";    
        String button = "abcd";
        String testId = "6";
        try{
            Bill b = bd.getbillbycode("3");
            b.setId(6);
            con.setAutoCommit(false);
            boolean check = bd.updatebill(b, button);
            System.out.println(check);
            assertFalse(check);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{
                con.rollback();
                con.setAutoCommit(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    
    
    
    @Test
    public void TB15() {
        String startDate = "2021-11-01";
        String endDate = "2021-11-30";
        String pid = "1";
        ArrayList<Bill> lb = bd.getbillbyproduct(startDate, endDate, pid);
        assertNotNull(lb);
        assertEquals(2, lb.size());
        assertEquals(1000,99 , lb.get(1).getBproduct().get(0).getPrice());
        assertEquals(3, lb.get(1).getId());
    } 
    
    @Test
    public void TB16() {
        String startDate = "2021-12-01";
        String endDate = "2021-12-30";
        String pid ="1";
        ArrayList<Bill> lb = bd.getbillbyproduct(startDate, endDate, pid);
        assertEquals(0, lb.size());   
     }    
}
