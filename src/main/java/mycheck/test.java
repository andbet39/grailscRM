package mycheck;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class test {

    public static String executeCheck(String connectString,String user, String _password, String query){

        String jdbcUrl = connectString;


        String userid = user;
        String password = _password;
        Statement stmt;
        ResultSet rset;

        try {

            Connection conn;
            OracleDataSource ds;
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid,password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("\nExecuting query: " + query);
            rset = stmt.executeQuery(query);
            System.out.println("\n query done");

            while (rset.next()) {
                return rset.getString(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
            return null;
    }




    public static void connect(){

        String jdbcUrl = "jdbc:oracle:thin:@(DESCRIPTION=" +
                "   (LOAD_BALANCE=on)" +
                "   (ADDRESS=(PROTOCOL=TCP)(HOST=dbcrmdbs60)(PORT=1548))" +
                "   (ADDRESS=(PROTOCOL=TCP)(HOST=dbcrmdbs61)(PORT=1548))" +
                "   (CONNECT_DATA=(SERVICE_NAME=CRMDB)))";


        String userid = "SIEBEL";
        String password = "SIEBEL";
        String query ="";
        Statement stmt;
        ResultSet rset;

        try {

            Connection conn;
            OracleDataSource ds;
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            conn = ds.getConnection(userid,password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            query = "SELECT COD_OPZIONE FROM CX_ANAGRAFIC_RA";
            System.out.println("\nExecuting query: " + query);
            rset = stmt.executeQuery(query);
            System.out.println("\n query done");

            while (rset.next()) {
                System.out.println(rset.getString(1));
            }
        }catch(Exception e){
                e.printStackTrace();
        }

    }
}
