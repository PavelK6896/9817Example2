package app.web.pavelk.jsoup1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3 {

    public static final String W = "data-word";
    public static final String T = "data-translation";

    public static void main(String[] args) throws IOException {
        String string = Files.readString(Paths.get("jsoup1/t3.html"), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(string);
        Elements width = document.body().getElementsByTag("tr");
        List<String> list2 = new ArrayList<>();
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (Element f : width) {
            Elements elementsByAttribute = f.getElementsByAttribute(W);
            Elements elementsByAttribute1 = f.getElementsByAttribute(T);
            map.put(elementsByAttribute.get(0).attr(W).replace("-", " "), elementsByAttribute1.get(0).attr(T));
            list2.add(elementsByAttribute.get(0).attr(W).replace("-", " ") + "','" + elementsByAttribute1.get(0).attr(T));
        }
        map.forEach((key, value) -> list.add(key + "','" + value));
        Files.write(Paths.get("jsoup1/e2.txt"), list2, StandardOpenOption.CREATE);
        Files.write(Paths.get("jsoup1/e3.txt"), list, StandardOpenOption.CREATE);
    }
}