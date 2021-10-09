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
import java.util.List;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) throws IOException {

        String string = Files.readString(Paths.get("jsoup1/t1.html"), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(string);
        Element element = document.body().getElementById("t");
        Elements elements = element.children();

        List<String> collect = elements.stream().map(f -> {
            Elements elementsByClass = f.getElementsByClass("term-name");
            Element element1 = elementsByClass.get(0);
            return element1.child(0).text();
        }).collect(Collectors.toList());
        Files.write(Paths.get("jsoup1/r1.txt"), collect, StandardOpenOption.CREATE);

        List<String> listString = elements.stream().map(f -> {
            Elements elementsByClass = f.getElementsByClass("preview-text");
            Element element1 = elementsByClass.get(0);
            return element1.child(0).text();
        }).collect(Collectors.toList());
        Files.write(Paths.get("jsoup1/r2.txt"), listString, StandardOpenOption.CREATE);
    }
}
