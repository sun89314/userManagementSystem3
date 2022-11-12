package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutils {
//    private static DataSource ds;
//    static {
//
//        try {
//            Properties ps = new Properties();
//
//            ClassLoader cs = JDBCutils.class.getClassLoader();
//            //System.out.println(cs);
//            InputStream is = cs.getResourceAsStream("druid.properties");
//            //System.out.println(is);
//            ps.load(is);
//            //ps.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
//            ds = DruidDataSourceFactory.createDataSource(ps);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    private static DataSource ds;
    static{
        try {
            Properties pros = new Properties();
            InputStream is = JDBCutils.class.getClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);

            ds = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {

        return ds.getConnection();
    }

    public static DataSource getDataScource(){
        return ds;
    }


    public static void close(Statement statement, Connection conn){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void close(Statement statement, Connection conn, ResultSet rs){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
