package app.web.pavelk.word6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Word62 {
    public static void main(String[] args) throws IOException {

        List<String> list1 = Files.readAllLines(Paths.get("word6/k1.txt"), StandardCharsets.UTF_8);
        List<String> list2 = Files.readAllLines(Paths.get("word6/k2.txt"), StandardCharsets.UTF_8);
        List<String> list3 = Files.readAllLines(Paths.get("word6/k3.txt"), StandardCharsets.UTF_8);

        List<String> list4 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list4.add("['" + list2.get(i) + "','" + list3.get(i) + "','" + list1.get(i).replace("'", "\"") + "'],");
        }

        Files.write(Paths.get("word6/k4.txt"), list4, StandardCharsets.UTF_8);
    }
}
