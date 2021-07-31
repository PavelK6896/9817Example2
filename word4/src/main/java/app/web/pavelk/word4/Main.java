package app.web.pavelk.word4;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.jsoup.Jsoup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class Main1 {
    public static void main(String[] args) throws IOException {
        EpubReader epubReader = new EpubReader();
        Book book = epubReader.readEpub(new FileInputStream("word4/b.epub"));
        byte[] bytes1 = Jsoup.parse(new String(book.getContents().get(10).getData())).text().getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = "123456789".getBytes(StandardCharsets.UTF_8);
        List<Byte> bytes3 = new ArrayList<>();
        for (byte b : bytes2) {
            bytes3.add(b);
        }
        List<Byte> bytes4 = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes1) {
            if (!bytes3.contains(b)) {
                bytes4.add(b);
                sb.append(b);
            }
        }
        final byte[] res = new byte[bytes4.size()];
        for (int i = 0; i < bytes4.size(); i++) {
            res[i] = bytes4.get(i);
        }
        System.out.println(new String(res));
    }
}

class Main2 {
    public static void main(String[] args) throws IOException {
        EpubReader epubReader = new EpubReader();
        Book book = epubReader.readEpub(new FileInputStream("word4/b.epub"));

        byte[] bytes = "1234567890^:-".getBytes(StandardCharsets.UTF_8);
        ArrayList<Integer> characters = new ArrayList<>();
        for (byte c : bytes) {
            characters.add((int) c);
        }

        FileOutputStream fileOutputStream = new FileOutputStream("word4/b/0b.txt");

        List<Resource> Resource = book.getContents();
        int size = 0;
        for (Resource r : Resource) {
            size++;
            fileOutputStream.write(Jsoup.parse(new String(r.getData()))
                    .text()
                    .chars()
                    .filter(f -> !characters.contains(f))
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString().getBytes(StandardCharsets.UTF_8));

            if (size % 100 == 0) {
                fileOutputStream.close();
                fileOutputStream = new FileOutputStream("word4/b/" + size + "b.txt");
            }
        }
        fileOutputStream.close();
    }
}
