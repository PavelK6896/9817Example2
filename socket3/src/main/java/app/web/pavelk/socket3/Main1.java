package app.web.pavelk.socket3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * записывает заголовки новостей с сайтов в файл
 */
public class Main1 {
    public static void main(String[] args) throws InterruptedException, IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Object object = new Object();
        final String file1 = "news.txt";
        Map<String, String> map1 = Map.of(
                "https://tproger.ru/", "news-link",
                "https://www.it-world.ru/", "title",
                "https://habr.com/ru/", "post__title_link");

        List<Callable<Object>> list3 = new ArrayList<>();

        map1.forEach(
                (k, v) -> {
                    list3.add((Callable<Object>) () -> {
                        Document document = null;
                        try {
                            document = Jsoup.connect(k).get();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Elements elementsByClass = document.body().getElementsByClass(v);
                        try {
                            synchronized (object) {
                                Files.write(Paths.get(file1), (k + LocalDateTime.now() + "\n").getBytes(),
                                        StandardOpenOption.APPEND);
                                Files.write(Paths.get(file1),
                                        elementsByClass.stream().map(Element::text).collect(Collectors.toList()),
                                        StandardOpenOption.APPEND);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    });
                }
        );

        Files.write(Paths.get(file1), ("news " + LocalDateTime.now() + "\n").getBytes(), StandardOpenOption.CREATE);
        executorService.invokeAll(list3);
        System.out.println("end");
        executorService.shutdown();
    }
}
