package app.web.pavelk.soclet2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * библиотеки: java для запроса, jsoup для парсинга
 * записывает заголовки с главной стрице в файл
 */
public class MainItWorld {
    public static void main(String[] args) throws IOException {
        final String file1 = "file/ItWorld.txt";
        HttpURLConnection connection = (HttpURLConnection) new URL("https://www.it-world.ru/").openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        if (Files.exists(Paths.get(file1)))
            Files.delete(Paths.get(file1));
        String string = new String(inputStream.readAllBytes());
        Document document = Jsoup.parse(string);
        Elements main__big__title = document.body().getElementsByClass("title");
        Files.write(Paths.get(file1), ("news " + LocalDateTime.now() + "\n").getBytes(), StandardOpenOption.CREATE);
        List<String> collect = main__big__title.stream().map(Element::text).collect(Collectors.toList());
        Files.write(Paths.get(file1), collect, StandardOpenOption.APPEND);
    }
}

//-- java
//GET / HTTP/1.1
//User-Agent: Java/11.0.8
//Host: localhost:8080
//Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2
//Connection: keep-alive
