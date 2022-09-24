package app.web.pavelk.word2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

class Word2_6 {
    public static void main(String[] args) throws IOException {
        List<Alphabet> listWords = getListWords();
        int num = 0;
        for (Alphabet f : listWords) {
            num++;
            try {
                Files.write(Paths.get("word2/a/dictionary" + num + ".txt"), "".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Word2 w : f.getWords()) {
                try {
                    Files.write(Paths.get("word2/a/dictionary" + num + ".txt"), getWordByte(w), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static byte[] getWordByte(Word2 w) {
        return (w.getWord() + "%" + w.getTranslate() + "\n").getBytes(StandardCharsets.UTF_8);
    }

    private static List<Alphabet> getListWords() throws IOException {
        List<Alphabet> alphabets = new ArrayList<>();
        var key = 0;
        var lines = Files.readAllLines(Paths.get("word2/word2.txt"), Charset.forName("windows-1251"));
        String start = "a";
        Alphabet alphabet = new Alphabet(start, new ArrayList<Word2>());
        for (int i = 0; i < lines.size(); i = i + 2) {
            if (!lines.get(i).startsWith(start)) {

                start = lines.get(i).substring(0, 1);
                alphabets.add(alphabet);
                alphabet = new Alphabet(start, new ArrayList<Word2>());
                key = 0;
            }
            alphabet.getWords().add(new Word2(++key, lines.get(i), lines.get(i + 1)));
        }
        alphabets.add(alphabet);
        return alphabets;
    }
}
