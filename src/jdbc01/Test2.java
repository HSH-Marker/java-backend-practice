package jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String input = "' or '1'='1";
        String sql = "select name, age from student where name ='" + input + "'";
        Statement stmt =conn.createStatement();
        System.out.println("真正发出去的 SQL = " + sql);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name=rs.getString("name");
            int age=rs.getInt("age");
            System.out.println(" "+name+" "+age);
        }
        stmt.close();
        conn.close();
    }

}
