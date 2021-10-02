package app.web.pavelk.word5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


class Main1 {
    public static void main(String[] args) throws IOException {
        String regex3 = "^\\d{2}:\\d{2}:\\d{2},\\d{3}\\s-->\\s\\d{2}:\\d{2}:\\d{2},\\d{3}";
        String example1 = "00:01:02,395 --> 00:01:03,771";
        System.out.println(example1.length());
        System.out.println(Pattern.matches(regex3, example1));
        List<String> lines = Files.readAllLines(Paths.get("word5/en1.srt"), StandardCharsets.UTF_8);

        StringBuilder sb = new StringBuilder();
        Map<String, String> linesRuNew = new HashMap<>();
        Boolean turn = Boolean.FALSE;
        List<String> linesNew = new ArrayList<>();
        System.out.println("==========");
        for (String f : lines) {
            if (Boolean.TRUE.equals(turn)) {
                sb.append(f).append(" ");
            }
            if (Pattern.matches(regex3, f)) {
                turn = true;
            } else if (f.isBlank()) {
                turn = false;
                linesNew.add(sb.toString());
                sb.setLength(0);
            }
        }
        linesNew.add(sb.toString());
        Files.write(Paths.get("word5/r1en.txt"), linesNew, StandardOpenOption.CREATE);

    }
}


class Main2 {
    public static void main(String[] args) throws IOException {
        String regex3 = "^\\d{2}:\\d{2}:\\d{2},\\d{3}\\s-->\\s\\d{2}:\\d{2}:\\d{2},\\d{3}";
        String example1 = "00:01:02,395 --> 00:01:03,771";
        System.out.println(example1.length());
        System.out.println(Pattern.matches(regex3, example1));

        // Charset.forName("windows-1251")
        List<String> lines = Files.readAllLines(Paths.get("word5/f/en1.srt"), StandardCharsets.UTF_8);
        List<String> linesRu = Files.readAllLines(Paths.get("word5/f/ru1.srt"), StandardCharsets.UTF_8);

        StringBuilder sb = new StringBuilder();
        Map<String, String> linesRuNew = new HashMap<>();
        String key = "";
        Boolean turn = Boolean.FALSE;
        for (String f : linesRu) {
            if (Boolean.TRUE.equals(turn)) {
                sb.append(f).append(" ");
            }
            if (Pattern.matches(regex3, f)) {
                turn = true;
                key = f.substring(0, 8);
                System.out.println(key);
            } else if (f.isBlank()) {
                turn = false;
                linesRuNew.put(key, sb.toString());
                sb.setLength(0);
            }
        }

        List<String> linesNew = new ArrayList<>();
        turn = Boolean.FALSE;
        sb.setLength(0);
        System.out.println("==========");
        for (String f : lines) {
            if (Boolean.TRUE.equals(turn)) {
                sb.append(f).append(" ");
            }
            if (Pattern.matches(regex3, f)) {
                turn = true;
                key = f.substring(0, 8);
                System.out.println(key);
            } else if (f.isBlank()) {
                turn = false;
                linesNew.add(sb.toString() + "  " + linesRuNew.get(key));
                sb.setLength(0);
            }
        }
        linesNew.add(sb.toString() + "  " + linesRuNew.get(key));
        Files.write(Paths.get("word5/r1.txt"), linesNew, StandardOpenOption.CREATE);

    }
}
