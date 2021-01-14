package app.web.pavelk.socket1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            int c;

            final String IP_ADDRESS1 = "127.0.0.1";
            final String IP_ADDRESS2 = "google.com";
            final String IP_ADDRESS3 = "twitter.com";
            final String IP_ADDRESS4 = "geekbrains.ru";

            String IP_AD = IP_ADDRESS1;

            final String url1 = "/storage2/f";
            final String url2 = "/";

            final int PORT = 8080;
            final int PORT1 = 445;
            final int PORT2 = 443;
            final int PORT3 = 80;
            System.out.println("-----------------***----------");

            Socket socket = new Socket(IP_AD, PORT3);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            System.out.println("Start!" + socket.toString());
            String s = new String(
                    "GET " + url1 + " HTTP/1.1\r\n" +
                            "Host: " + IP_AD + "\r\n\r\n"
            );
            out.write(s.getBytes()); // запрос

            while ((c = in.read()) != -1) { // ответ
                System.out.print((char) c);
            }

            Thread.sleep(500);
            socket.close();
            System.out.println("close!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
