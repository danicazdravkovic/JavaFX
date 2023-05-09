/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BrokerBazePodataka;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import DomenskiObjekat.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class BrokerBazePodatakaImpl extends BrokerBazePodataka {

    Connection conn = null;

    @Override
    public boolean makeConnection() {
        String Url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Url = "jdbc:mysql://127.0.0.1:3306/sudoku_igra";
            conn = DriverManager.getConnection(Url, "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean insertRecord(GeneralDObject odo) {
        String upit = "INSERT INTO " + odo.getClassName() + " VALUES (" + odo.getAtrValue() + ")";
        //System.out.println(upit);
        return executeUpdate(upit);
    }

    public PreparedStatement insert(GeneralDObject gdo) throws SQLException {
        String upit = "INSERT INTO " + gdo.getClassName() + " "
                + gdo.columnsForInsert() + " VALUES(" + gdo.getAtrValue2() + ")";
        System.out.println(upit);
        PreparedStatement ps = conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    @Override
    public boolean deleteRecord(GeneralDObject odo) {
        String upit = "DELETE FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(upit);
    }

    @Override
    public boolean deleteRecords(GeneralDObject odo, String where) {
        String upit = "DELETE FROM " + odo.getClassName() + " " + where;
        return executeUpdate(upit);
    }

    @Override
    public boolean updateRecord(GeneralDObject odo, GeneralDObject odoold) {
        String upit = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odoold.getWhereCondition();
        return executeUpdate(upit);
    }

    @Override
    public boolean updateRecord(GeneralDObject odo) {
        String upit = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odo.getWhereCondition();
//        System.out.println(upit);
        return executeUpdate(upit);
    }

    public boolean executeUpdate(String upit) {
        Statement st = null;
        boolean signal = false;
        try {
            st = conn.prepareStatement(upit);
            int rowcount = st.executeUpdate(upit);
            if (rowcount > 0) {
                signal = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
            signal = false;
        } finally {
            close(null, st, null);
        }
        return signal;
    }

    @Override
    public GeneralDObject findRecord(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            if (signal == true) {
                odo = odo.getNewRecord(rs);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;
    }

    @Override
    public List<GeneralDObject> findRecord(GeneralDObject odo, String where) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " " + where;
        List<GeneralDObject> ls = new ArrayList<>();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            while (rs.next()) {
                ls.add(odo.getNewRecord(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return ls;
    }

    @Override
    public boolean commitTransation() {
        try {
            conn.commit();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rollbackTransation() {
        try {
            conn.rollback();
        } catch (SQLException esql) {
            return false;
        }

        return true;
    }

    @Override
    public void closeConnection() {
        close(conn, null, null);
    }

    public void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int getRecordsNumber(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        int recordsNumber = 0;
        String upit = "SELECT * FROM " + odo.getClassName();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            if (rs.last()) {
                recordsNumber = rs.getRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return recordsNumber;
    }

    @Override
    public GeneralDObject getRecord(GeneralDObject odo, int index) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName();
        upit = upit + " order by " + odo.getNameByColumn(0) + " ASC LIMIT " + index + ",1";
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            if (signal == true) {
                odo = odo.getNewRecord(rs);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            odo = null;
            Logger.getLogger(BrokerBazePodatakaImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;

    }
}
