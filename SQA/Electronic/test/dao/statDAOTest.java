package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import model.Stat;
import model.Stat2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pvand
 */
public class statDAOTest {

    public statDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ts1() {
        String sdate = "2021-10-20";
        String edate = "2021-11-26";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());

    }
        @Test
    public void ts2() {
        String sdate = "2021-10-20";
        String edate = "2021-10-20";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());

    }
    @Test
    public void ts3() {
        String sdate = "2021-11-26";
        String edate = "2021-10-20";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());

    }
        @Test
    public void ts4() {
        String sdate = "2022-04-01";
        String edate = "2022-05-01";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());

    }
        @Test
    public void ts5() {
        String sdate = " 2022-04-01";
        String edate = "2022-05-01";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());

    }
    @Test
    public void ts6() {
        String sdate = "20-10-2021 20:11:21";
        String edate = "2022-05-01";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());
    }
        @Test
    public void ts7() {
        String sdate = "";
        String edate = "";
        int exprs=1;
        statDAO instance = new statDAO();
        ArrayList<Stat> result = instance.getstat(sdate, edate);
        assertEquals(exprs, result.size());
    }
    @Test
    public void ts8() {
        System.out.println("getstat2");
        String sdate = "2021-10-20";
        String edate = "2021-11-26";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
    }
        @Test
    public void ts9() {
        System.out.println("getstat2");
        String sdate = "2021-10-20";
        String edate = "2021-10-20";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
    }
        @Test
    public void ts10() {
        System.out.println("getstat2");
        String sdate = "2021-11-26";
        String edate = "2021-10-20";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
        @Test
    public void ts11() {
        System.out.println("getstat2");
        String sdate = "2022-04-01";
        String edate = "2022-05-01";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
    }
        @Test
    public void ts12() {
        System.out.println("getstat2");
        String sdate = "20-10-2021 20:11:21";
        String edate = "24-11-2021 20:11:21";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
    }
            @Test
    public void ts13() {
        System.out.println("getstat2");
        String sdate = " 2022-04-01";
        String edate = "2022-05-01";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
    }
        @Test
    public void ts14() {
        System.out.println("getstat2");
        String sdate = "";
        String edate = "";
        statDAO instance = new statDAO();
        ArrayList<Stat2> expResult = null;
        ArrayList<Stat2> result = instance.getstat2(sdate, edate);
        assertEquals(expResult, result);
    }
}
