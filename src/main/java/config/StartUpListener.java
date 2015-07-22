package config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.*;

@WebListener()
public class StartUpListener implements ServletContextListener {
    private static final String CREATE_BLOG_TABLE_SQL = "CREATE TABLE BLOG (title TEXT)";
    private static final String INSERT_BLOG_SQL = "INSERT INTO BLOG (title) VALUES (?)";

    public void contextInitialized(ServletContextEvent sce) {
        Connection con = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test");
//            Statement st = con.createStatement();
//            st.execute(CREATE_BLOG_TABLE_SQL);
            PreparedStatement pst = con.prepareStatement(INSERT_BLOG_SQL);
            pst.setString(1, "Food");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(con != null) try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
