package jdbc01;

import java.sql.*;

public class Test3 {
    public static void main(String[] args) throws Exception {
        String sql="insert into student(name,age) values(?,?)";
        String sql1="update student set age = ? where name=?";
        String sql2="delete from student where  name = ?";
        String sql3="select *from student";
        try(Connection conn= JDBCUtils.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            PreparedStatement ps2=conn.prepareStatement(sql2);
            Statement sm=conn.createStatement();

        ){
            ps.setString(1,"孙七");
            ps.setInt(2,23);
            int n=ps.executeUpdate();
            System.out.println(n);
            ps1.setInt(1,24);
            ps1.setString(2,"孙七");
            int n1=ps1.executeUpdate();
            System.out.println(n1);
            ps2.setString(1,"孙七");
            int n2=ps2.executeUpdate();
            System.out.println(n2);
            try(ResultSet rs = sm.executeQuery(sql3);){
                while (rs.next()) {
                    String name=rs.getString("name");
                    int age=rs.getInt("age");
                    System.out.println(" "+name+" "+age);
                }
            }
        }

    }
}
