package app.web.pavelk.soclet2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * библиотеки: jsoup для запроса и парсинга
 * записывает заголовки заголовки с главной стрице в файл
 */
public class MainHabr {
    public static void main(String[] args) throws IOException {
        final String file1 = "file/Habr.txt";
        Document document = Jsoup.connect("https://habr.com/ru/").get();
        Elements elementsByClass = document.body().getElementsByClass("post__title_link");
        if (Files.exists(Paths.get(file1)))
            Files.delete(Paths.get(file1));
        Files.write(Paths.get(file1), ("news " + LocalDateTime.now() + "\n").getBytes(), StandardOpenOption.CREATE);
        List<String> collect = elementsByClass.stream().map(Element::text).collect(Collectors.toList());
        Files.write(Paths.get(file1), collect, StandardOpenOption.APPEND);
    }
}

//--jsoup
//GET / HTTP/1.1
//Accept-Encoding: gzip
//User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36
//Host: localhost:8080
//Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2
//Connection: keep-alive
