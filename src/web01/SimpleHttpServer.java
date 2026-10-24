package web01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("服务器已启动：http://localhost:8080");
        while (true) {
            try (Socket socket = server.accept()) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                String requestLine = in.readLine();
                System.out.println("收到请求：" + requestLine);
                String line;
                while ((line = in.readLine()) != null && !line.isEmpty()) {

                }
                String html = "<!DOCTYPE html><html><head><meta charset='UTF-8'>"
                        + "<title>我的第一个服务器</title></head>"
                        + "<body><h1>你好，这是java程序返回的内容</h1></body></html>";
                byte[] body = html.getBytes(StandardCharsets.UTF_8);
                OutputStream out = socket.getOutputStream();
                String headers = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type:text/html;charset=UTF-8\r\n"
                        +"content-Length:"+body.length+"\r\n"
                        + "Connection: close\r\n"
                        + "\r\n";
                out.write(headers.getBytes(StandardCharsets.UTF_8));
                out.write(body);
                out.flush();
            } catch (IOException e) {
                System.out.println("这次连接没处理完：" + e.getMessage());
            }
        }
    }
}
