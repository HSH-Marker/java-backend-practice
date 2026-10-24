package jdbc01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Test1 {
    public static void main(String[] args)  {
        String sql = "select name, age,id from student ";//sql语句
        System.out.println("真正发出去的 SQL = " + sql);
        List<Student> list = new ArrayList<>();
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            try(ResultSet rs = pstmt.executeQuery()
            ){
                while (rs.next()) {
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    int age=rs.getInt("age");
                    list.add(new Student(id,name,age));
                }
            }
        } catch (SQLException e) {
            System.out.println("数据库出问题了：" + e.getMessage());
        }
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
