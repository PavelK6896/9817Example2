package app.web.pavelk.word2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class Main1 {
    public static void main(String[] args) throws IOException {
        var objectMapper = new ObjectMapper();
        var list = new ArrayList<Alphabet>();
        var key = 0;
        var lines = Files.readAllLines(Paths.get("word2/word2.txt"), Charset.forName("windows-1251"));
        String start = "-";
        Alphabet alphabet = null;
        for (int i = 0; i < lines.size(); i = i + 2) {
            if (!lines.get(i).startsWith(start)) {
                start = lines.get(i).substring(0, 1);
                alphabet = new Alphabet(start, new ArrayList<Word2>());
                list.add(alphabet);
            }
            alphabet.getWords().add(new Word2(++key, lines.get(i), lines.get(i + 1)));
        }
        var json = objectMapper.writeValueAsString(list);
        Files.write(Paths.get("word2/word2-1.json"), json.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }
}


class Main2 {
    public static void main(String[] args) throws IOException {
        var objectMapper = new ObjectMapper();
        var key = 0;
        var lines = Files.readAllLines(Paths.get("word2/word2.txt"), Charset.forName("windows-1251"));
        String start = "a";
        Alphabet alphabet = new Alphabet(start, new ArrayList<Word2>());
        for (int i = 0; i < lines.size(); i = i + 2) {
            if (!lines.get(i).startsWith(start)) {
                Files.write(Paths.get("word2/w/" + start + ".js"), objectMapper.writeValueAsString(alphabet)
                        .getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);

                start = lines.get(i).substring(0, 1);
                alphabet = new Alphabet(start, new ArrayList<Word2>());
                key = 0;
            }
            alphabet.getWords().add(new Word2(++key, lines.get(i), lines.get(i + 1)));
        }
        Files.write(Paths.get("word2/w/" + start + ".js"), objectMapper.writeValueAsString(alphabet)
                .getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }
}


class Main3 {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("word2/word2.txt"), Charset.forName("windows-1251"));
        int dic = 1000;
        String s1 = "export const dictionary";
        String s2 = " = [";
        String s3 = "]\n";

        List<String> collect = new ArrayList<>();
        collect.add(s1 + dic + s2);

        for (int j = 0; j < lines.size(); j = j + 2) {
            if (j % 500 == 0 && j != 0) {
                collect.add(s3 + s1 + ++dic + s2);
            }
            collect.add("['" + lines.get(j) + "','" + lines.get(j + 1) + "'],");
        }
        collect.add(s3);

        Files.write(Paths.get("word2/word2-2.js"), collect, StandardOpenOption.CREATE);
    }
}

/**
 * generate json
 */
class Main4 {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("word2/word2.txt"), Charset.forName("windows-1251"));
        lines.replaceAll(f -> f.replace("\"", "'"));
        lines.replaceAll(f -> f.replace("\t", " "));
        int dic = 1000;
        String s1 = " \"dictionary";
        String s2 = "\" : [";
        String s3 = "],\n";

        List<String> collect = new ArrayList<>();
        collect.add("{");
        collect.add(s1 + dic + s2);

        boolean start = true;
        for (int j = 0; j < lines.size(); j = j + 2) {
            if (j % 500 == 0 && j != 0) {
                collect.add(s3 + s1 + ++dic + s2);
            } else {
                if (!start) {
                    String s = collect.get(collect.size() - 1) + ",";
                    collect.set(collect.size() - 1, s);
                } else {
                    start = false;
                }
            }
            collect.add("[\"" + lines.get(j) + "\",\"" + lines.get(j + 1) + "\"]");
        }
        collect.add("]\n");
        collect.add("}");

        Files.write(Paths.get("word2/word2-4.json"), collect, StandardOpenOption.CREATE);
    }
}

/**
 * by the number of letters
 */
class Main5 {
    public static void main(String[] args) throws IOException {
        var lines = Files.readAllLines(Paths.get("word2/word2.txt"), Charset.forName("windows-1251"));
        ArrayList<String> arr2 = new ArrayList<>();
        ArrayList<String> arr3 = new ArrayList<>();
        ArrayList<String> arr4 = new ArrayList<>();
        for (int i = 0; i < lines.size(); i = i + 2) {
            String s = lines.get(i);
            if (s.length() == 2) {
                arr2.add(s + " " + lines.get(i + 1));
            } else if (s.length() == 3) {
                arr3.add(s + " " + lines.get(i + 1));
            } else if (s.length() == 4) {
                arr4.add(s + " " + lines.get(i + 1));
            }
        }
        Files.write(Paths.get("word2/two.txt"), arr2, StandardOpenOption.CREATE);
        Files.write(Paths.get("word2/three.txt"), arr3, StandardOpenOption.CREATE);
        Files.write(Paths.get("word2/four.txt"), arr4, StandardOpenOption.CREATE);
    }
}