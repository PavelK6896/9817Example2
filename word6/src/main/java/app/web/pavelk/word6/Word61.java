package app.web.pavelk.word6;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Word61 {
    public static void main(String[] args) throws IOException {
        String s = Files.readString(Paths.get("word6/k1.21.html"), StandardCharsets.UTF_8);
        Document parse = Jsoup.parse(s);
        Element elementById = parse.getElementById("page-content-wrapper");
        Elements children = elementById.children();

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        int i = 0;
        for (Element f : children) {
            if (f.text().equals("HTTP Request")) {
                list.add(children.get(i - 1).text());
                list2.add(children.get(i + 1).text());
            }
            ++i;
        }

        Files.write(Paths.get("word6/k1.txt"), list, StandardCharsets.UTF_8);
        Files.write(Paths.get("word6/k2.txt"), list2, StandardCharsets.UTF_8);
    }
}
