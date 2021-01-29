package app.web.pavelk.soclet2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * библиотеки: apache httpclient для запроса, jsoup для парсинга
 * записывает заголовки с главной стрице в файл
 */
public class MainTproger {
    public static void main(String[] args) throws URISyntaxException, IOException {
        final String url1 = "https://tproger.ru/";
        final String file1 = "file/Tproger.txt";
        URIBuilder uriBuilder = new URIBuilder(url1);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpClient closeableHttpClient = HttpClients.custom().build();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();

        if (httpEntity != null) {
            String string = EntityUtils.toString(httpEntity);
            Document document = Jsoup.parse(string);
            Elements main__big__title = document.body().getElementsByClass("news-link");
            Elements main__feed__title = document.body().getElementsByClass("post-text");
            main__big__title.addAll(main__feed__title);

            if (Files.exists(Paths.get(file1)))
                Files.delete(Paths.get(file1));
            Files.write(Paths.get(file1), ("news " + LocalDateTime.now() + "\n").getBytes(), StandardOpenOption.CREATE);
            List<String> collect = main__big__title.stream().map(Element::text).collect(Collectors.toList());
            Files.write(Paths.get(file1), collect, StandardOpenOption.APPEND);
        }
    }
}

//--apache httpclient
//GET / HTTP/1.1
//Host: localhost:8080
//Connection: Keep-Alive
//User-Agent: Apache-HttpClient/4.5.13 (Java/11.0.8)
//Accept-Encoding: gzip,deflate
