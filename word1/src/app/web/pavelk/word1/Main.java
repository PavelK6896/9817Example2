package app.web.pavelk.word1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> list = Files.readAllLines(Paths.get("word1/src/app/web/pavelk/word1/text.txt"));
        List<String> collect = list.stream().map(l -> {
            String[] split = l.split(" -- ");
            return "['" + split[0] + "','" + split[1] + "'],";
        }).collect(Collectors.toList());
        Files.write(Paths.get("word1/src/app/web/pavelk/word1/new1.txt"), collect, CREATE);
    }

}

class Main2 {
    public static void main(String[] args) throws IOException {

        List<String> list = Files.readAllLines(Paths.get("word1/src/app/web/pavelk/word1/text.txt"));
        int dic = 10;
        String s1 = "export const dictionary";
        String s2 = " = [";
        String s3 = "]\n";

        List<String> collect = new ArrayList<>();
        collect.add(s1 + dic + s2);

        for (int j = 0; j < list.size(); j++) {
            String[] split = list.get(j).split(" -- ");
            if (j % 100 == 0 && j != 0) {
                collect.add(s3 + s1 + ++dic + s2);
            }
            collect.add("['" + split[0] + "','" + split[1] + "'],");
        }

        collect.add(s3);
        Files.write(Paths.get("word1/src/app/web/pavelk/word1/new2.txt"), collect, CREATE);
    }
}
