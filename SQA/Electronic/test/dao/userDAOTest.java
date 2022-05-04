/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import model.Staff;
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class userDAOTest {
    
    

    /**
     * Test of check_duplicate method, of class userDAO.
     */
    @Test
    public void TU1() {
        
        userDAO ud = new userDAO();
        boolean check = ud.check_duplicate("0322652852");
        // TODO review the generated test code and remove the default call to fail.
        assertFalse(check);
    }
    
    @Test
    public void TU2() {
        
        userDAO ud = new userDAO();
        boolean check = ud.check_duplicate("0904144688");
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(check);
    }
    
    @Test
    public void TU3() {
        userDAO ud = new userDAO();
        Staff check = ud.check_user("1223", "123");
        assertEquals(check.getAddress(), "2972 Westheimer Rd. Santa Ana, Illinois 85486 ");
        assertEquals(check.getEmail(), "vanduc2314@gmail.com");
        assertEquals(check.getPassword(), "123");
        assertEquals(check.getPhone(), "(505) 222-0125");
        assertEquals(check.getPosition(), "inventory staff");
        assertEquals(check.getUsername(), "1223");
        assertEquals(check.getName(), "Eleanor Pena");
    }
    
    @Test
    public void TU4() {
        
        userDAO ud = new userDAO();
        Staff check = ud.check_user("1223", "9123");
        assertNull(check.getAddress());
        assertNull(check.getEmail());
        assertNull(check.getPassword());
        assertNull(check.getPhone());
        assertNull(check.getPosition());
        assertNull(check.getUsername());
        assertNull(check.getName());
    }
    
    @Test
    public void TU5() {
        
        userDAO ud = new userDAO();
        Staff check = ud.check_user("vudz", "123");
        assertNull(check.getAddress());
        assertNull(check.getEmail());
        assertNull(check.getPassword());
        assertNull(check.getPhone());
        assertNull(check.getPosition());
        assertNull(check.getUsername());
        assertNull(check.getName());
    }
    
    @Test
    public void TU6() {
        
        userDAO ud = new userDAO();
        Staff check = ud.check_user("vudz", "vudz");
        assertNull(check.getAddress());
        assertNull(check.getEmail());
        assertNull(check.getPassword());
        assertNull(check.getPhone());
        assertNull(check.getPosition());
        assertNull(check.getUsername());
        assertNull(check.getName());
    }
    
    @Test
    public void TU7() {
        
        userDAO ud = new userDAO();
        Staff check = ud.check_user("", "123");
        assertNull(check.getAddress());
        assertNull(check.getEmail());
        assertNull(check.getPassword());
        assertNull(check.getPhone());
        assertNull(check.getPosition());
        assertNull(check.getUsername());
        assertNull(check.getName());
    }
    
    @Test
    public void TU8() {
        
        userDAO ud = new userDAO();
        Staff check = ud.check_user("1223", "");
        assertNull(check.getAddress());
        assertNull(check.getEmail());
        assertNull(check.getPassword());
        assertNull(check.getPhone());
        assertNull(check.getPosition());
        assertNull(check.getUsername());
        assertNull(check.getName());
    }
    
    @Test
    public void TU9() {
        
        userDAO ud = new userDAO();
        Staff check = ud.check_user(" 1223", "123");
        assertNull(check.getAddress());
        assertNull(check.getEmail());
        assertNull(check.getPassword());
        assertNull(check.getPhone());
        assertNull(check.getPosition());
        assertNull(check.getUsername());
        assertNull(check.getName());
    }
    
    @Test
    public void TU10() {
        
        
        String name = "Duong Hoang Vũ";
        String phone = "0383251220";
        String address = "Ha Noi";
        String password = "123";
        String email = "vu@gmail.com";
        String username  = "vudz";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertTrue(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU11() {

        String name = null;
        String phone = "0383251220";
        String address = "Ha Noi";
        String password = "123";
        String email = "vu@gmail.com";
        String username  = "vudz";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertFalse(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU12() {

        String name = "Duong Hoang Vu";
        String phone = null;
        String address = "Ha Noi";
        String password = "123";
        String email = "vu@gmail.com";
        String username  = "vudz";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertFalse(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU13() {

        String name = "Duong Hoang Vu";
        String phone = "0383251220";
        String address = "Ha Noi";
        String password = "123";
        String email = null;
        String username  = "vudz";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertTrue(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU14() {

        String name = "Duong Hoang Vu";
        String phone = "0383251220";
        String address = null;
        String password = "123";
        String email = "vu@gmail.com";
        String username  = "vudz";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertFalse(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU15() {

        String name = "Duong Hoang Vu";
        String phone = "0383251220";
        String address = "Ha Noi";
        String password = null;
        String email = "vu@gmail.com";
        String username  = "vudz";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertFalse(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU16() {

        String name = "Duong Hoang Vu";
        String phone = "0383251220";
        String address = "Ha Noi";
        String password = "123";
        String email = "vu@gmail.com";
        String username  = null;
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertFalse(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU17() {

        String name = "Duong Hoang Vu";
        String phone = "0383251220";
        String address = "Ha Noi";
        String password = "123";
        String email = "vu@gmail.com";
        String username  = "ducvan";
        userDAO ud = new userDAO();
        User u = new User(name, phone, address, password, email, username);
        Connection con = ud.conn;
        try{
            con.setAutoCommit(false);
            boolean check = ud.add_client(u);
            assertFalse(check);
            
        }catch(Exception e){
            e.printStackTrace();
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
    public void TU18() {
           userDAO ud = new userDAO();
           ArrayList<Staff> check = ud.getListShipper();
           String[] ShipperNames = {"Nguyen Que Chi", "Le Ngoc Minh", "Nguyen Van Long"};
           assertEquals(check.size(), 3);
           for(Staff i: check)
           {
               if(!(i.getPosition().equals("shipper")))
               {
                   fail("Have"+ i.getName()+ "a staff who is not a shipper");
               }
           }
           for(String i: ShipperNames)
           {
               if(!(isExist(check, i)))
                {
                    fail("Not have shipper "+ShipperNames);
                }
           }
    }
    
    public boolean isExist (ArrayList<Staff> listShipper, String shipperName)
    {
        for(Staff i : listShipper)
        {
            if(i.getName().equals(shipperName))
            {
                return true;
            }
        }
        return false;
    }

    
}
