package app.web.pavelk.word7;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Get words from .fb2 file.
 */
public class Main {
    static final String NAME = "b1";
    public static final String DIR = "word7/";

    public static void main(String[] args) throws IOException {
        String file = Files.readString(Paths.get(DIR + NAME + ".fb2"), StandardCharsets.UTF_8);
        Document parse = Jsoup.parse(file);
        Elements children = parse.getElementsByTag("section");
        String text = children.text();
        String replace = text.replaceAll("([\\\\().?\"_,\\-'!&/0-9\u2014\u2019\u2026\u2018\u2013])", " ");
        String regex = "\\b.*?[A-Z].*?\\b";
        //\b -это граница слова. Он соответствует началу и концу слова
        //. Соответствует любому символу,
        //* соответствует предыдущему символу 0 или более раз,
        //? Делает предыдущий * не жадным, поэтому он соответствует как можно меньшему количеству символов вместо всей строки

        Set<String> collect = Arrays.stream(replace.split("(?!')\\W"))
                .filter(f -> f.length() > 3)
                .filter(f -> !Pattern.matches(regex, f))
//                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        saveOnTenThousand(collect);
        Files.write(Paths.get(DIR + NAME + ".txt"), collect, StandardOpenOption.CREATE);
    }

    private static void saveOnTenThousand(Set<String> collect) {
        try {
            Path path = Paths.get(DIR + NAME);
            if (Files.exists(path)) {
                try (Stream<Path> walk = Files.walk(path)) {
                    walk.filter(Files::isRegularFile)
                            .map(Path::toFile)
                            .forEach(File::delete);
                }
            } else {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sizeAll = 0;
        int number = 1;
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        for (String f : collect) {
            sizeAll += f.length();
            list.add(f);
            i++;
            if (sizeAll > 7_550 || i >= 990) {
                try {
                    Files.write(Paths.get(DIR + NAME + "/" + number++ + ".txt"), list, StandardOpenOption.CREATE);
                    list.clear();
                    sizeAll = 0;
                    i = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!list.isEmpty()) {
            try {
                Files.write(Paths.get(DIR + NAME + "/" + number + ".txt"), list, StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

