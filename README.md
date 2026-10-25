# Java 后端练习：JDBC + HTTP 服务器

> 这个仓库是两条独立练习的合集：jdbc01 连数据库，web01 提供 HTTP 访问。

## 一、jdbc01 —— Java 连 MySQL

- JDBCUtils.java —— 读取配置、统一提供数据库连接
- Student —— 它是数据载体（表的一行 → 一个对象），try 是 Test3/Test4 的事
- Test1.java —— 查询 student 全表，每行装成一个 Student 对象
- Test2.java —— SQL 注入演示：' or '1'='1 拼进 SQL，查出了全表
- Test3.java —— 增→改→删一条"孙七"，跑完表自动恢复成 4 行
- Test4.java —— 验证 try-with-resources 的关闭顺序（倒着关）
- Try.java —— 异常处理的 crash / catch 两版对比

### 运行前准备

- IDEA、MySQL Workbench
- JDK 17
- MySQL 8.0.46（本地）
- 驱动 jar：`lib\mysql-connector-j-8.0.33.jar`

### 建库建表

```sql
CREATE DATABASE IF NOT EXISTS school DEFAULT CHARACTER SET utf8mb4;

USE school;

CREATE TABLE IF NOT EXISTS student (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    age  INT
);
```

### 怎么跑

1. 把项目拷到本地（或 GitHub 页面 Download ZIP），用 IDEA 打开这个文件夹即可，不用单独复制文件。

```bash
git clone  <git clone git@github.com:HSH-Marker/java-backend-practice.git>
```

2. 在 MySQL Workbench 里插入 4 条测试数据并验证：

```sql
INSERT INTO student(name, age) VALUES ('张三', 20), ('李四', 21), ('王五', 20), ('赵六', 22);
SELECT * FROM student;
```

3. 配置数据库连接：把 `src/db.properties.example` 复制一份，改名为 `db.properties`，把 `YOUR_PASSWORD` 改成你本机 MySQL 的密码。

4. 在 IDEA 里运行 `src/jdbc01/Test1.java` 的 `main` 方法。

预期输出（5 行）：

```
真正发出去的 SQL = select name, age,id from student
Student{id=1, name='张三', age=20}
Student{id=2, name='李四', age=21}
Student{id=3, name='王五', age=20}
Student{id=4, name='赵六', age=22}
```

## 二、web01 —— 手写 HTTP 服务器

- SimpleHttpServer.java —— Socket 手写 HTTP 服务器，不依赖任何框架

这个程序里**不含任何服务器地址**：它只是监听本机 8080 端口。把源码拷到 ECS 上编译运行后，它就变成了公网服务，公网地址由那台服务器决定。

### 本地运行

1. 在 IDEA 里运行 `src/web01/SimpleHttpServer.java`
2. 浏览器打开 http://localhost:8080
3. 预期看到：你好，这是java程序返回的内容

### 部署到服务器

服务器平时关机，需要时先开机；公网地址 http://8.141.113.103:8080

**① 传源码（本机 Git Bash）**

```bash
scp "C:\Users\10931\IdeaProjects\jdbc\src\web01\SimpleHttpServer.java" \
root@8.141.113.103:/root/web/web01/
```

**② 编译（服务器）**

```bash
ssh root@8.141.113.103
cd /root/web
javac -encoding UTF-8 -d . web01/SimpleHttpServer.java
```

**③ 先前台跑一次，确认能起来（能看到错误信息）**

```bash
java -cp . web01.SimpleHttpServer
```

看到"服务器已启动"就说明没问题，Ctrl+C 停掉，进第 ④ 步。

**④ 后台常驻（正式）**

```bash
nohup java -cp . web01.SimpleHttpServer >> server.log 2>&1 &
```

**看日志**

```bash
tail -f server.log
```

## 踩过的坑

（待补）
