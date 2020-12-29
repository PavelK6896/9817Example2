package app.web.pavelk.encode1;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encode1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("123-231- cRdd просто текст", "UTF-8"));
        System.out.println(URLEncoder.encode("123-231- cRdd просто текст", "UTF-8").replace('+', ' '));
    }
}
