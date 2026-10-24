package jdbc01;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static  String url ;
    private static  String user;
    private static  String password;

    static {
        // InputStream implements AutoCloseable → 可以用 try-with-resources（第3章）
        try (InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties")) {

            if (is == null) {
                throw new RuntimeException("没找到 db.properties：它必须放在 src 根目录下");
            }

            Properties prop = new Properties();
            prop.load(is);                                    // 把文件读进 prop 这张表

            url      = prop.getProperty("url");
            user     = prop.getProperty("user");
            password = prop.getProperty("password");

            Class.forName(prop.getProperty("driver"));        // 驱动类名也搬进配置了

        } catch (Exception e) {
            System.out.println("初始化 JDBC 配置失败：" + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}