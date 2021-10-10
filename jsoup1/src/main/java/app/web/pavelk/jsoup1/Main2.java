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
import java.util.List;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) throws IOException {
        String regex1 = "[0-9]+";
        String string = Files.readString(Paths.get("jsoup1/t2.html"), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(string);
        Elements width = document.body().getElementsByTag("tr");
        List<String> list = new ArrayList<>();
        for (Element f : width) {
            String text = f.child(0).text();
            if (Pattern.matches(regex1, text)) {
                list.add(f.child(1).text() + "','" + f.child(2).text());
            }
        }
        Files.write(Paths.get("jsoup1/e1.txt"), list, StandardOpenOption.CREATE);
    }
}

