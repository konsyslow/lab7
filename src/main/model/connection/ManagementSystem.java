package main.model.connection;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by admin on 19.04.2017.
 */
public class ManagementSystem {
    static{
        PropertyConfigurator.configure("C:\\Users\\admin\\Documents\\lab6.1\\src\\main\\resources\\log4j.properties");
    }
    private static Connection con;
    private static ManagementSystem instance;
    private static DataSource dataSource;

    public ManagementSystem() {
    }

    public static Connection getCon() {
        Locale.setDefault(Locale.ENGLISH);
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentsDS");
                con = dataSource.getConnection();
            } catch (NamingException e) {
                Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch NamingException", e);
            } catch (SQLException e) {
                Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
            }

        return con;
    }
}