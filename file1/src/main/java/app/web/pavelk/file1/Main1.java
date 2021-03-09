package app.web.pavelk.file1;

import org.apache.any23.encoding.TikaEncodingDetector;
import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

class Main1 {
    public static void main(String[] args) {

        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                FileOutputStream fileOutputStream = new FileOutputStream("file1/test.zip");
                FileOutputStream fileOutputStream2 = new FileOutputStream("file1/test2.txt");
        ) {
            zipOutputStream.putNextEntry(new ZipEntry("zipTest.txt"));
            for (int i = 0; i < 1000; i++) {
                zipOutputStream.write(Util.string.getBytes());
                fileOutputStream2.write(Util.string.getBytes());
            }
            zipOutputStream.closeEntry();
            fileOutputStream.write(byteArrayOutputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Main2 {
    public static void main(String[] args) {
        try (
                FileInputStream fileInputStream = new FileInputStream("file1/test2.txt");
                FileInputStream fileInputStream2 = new FileInputStream("file1/test.zip");
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("file1/test.zip"))
        ) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                System.out.println(zipEntry.getName());
                System.out.println(zipEntry.getCrc());
                System.out.println(zipEntry.getCompressedSize());
                System.out.println(zipEntry.getExtra());
                System.out.println(zipEntry.getCreationTime());
                System.out.println(zipEntry.getTimeLocal());
                System.out.println(zipInputStream.readAllBytes().length);
            }
            System.out.println(fileInputStream2.readAllBytes().length);
            System.out.println(fileInputStream.readAllBytes().length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Main3 {
    public static void main(String[] args) {
        try (
                FileOutputStream fileOutputStream1 = new FileOutputStream("file1/test1.txt");
                FileOutputStream fileOutputStream2 = new FileOutputStream("file1/test2.txt");
                FileOutputStream fileOutputStream3 = new FileOutputStream("file1/test3.txt");
                FileOutputStream fileOutputStream4 = new FileOutputStream("file1/test4.txt");
                FileOutputStream fileOutputStream5 = new FileOutputStream("file1/test5.txt");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream1, StandardCharsets.UTF_8);
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(fileOutputStream2, Charset.forName("windows-1251"));
        ) {

            final SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
            final Charset charset = Charset.forName("windows-1251");
            final Charset charset1 = stringCharsetSortedMap.get("windows-1251");
            System.out.println(charset);
            stringCharsetSortedMap.forEach((d, f) -> System.out.println(d + " " + f));
            System.out.println(charset.equals(charset1));

            for (int i = 0; i < 1000; i++) {
                outputStreamWriter.write(Util.string);
                outputStreamWriter2.write(Util.string);
                fileOutputStream3.write(Util.string.getBytes(Charset.forName("windows-1251")));
                fileOutputStream4.write(Util.string.getBytes(StandardCharsets.ISO_8859_1));
                fileOutputStream5.write(Util.string.getBytes(StandardCharsets.US_ASCII));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Main4 {
    public static void main(String[] args) {

        try (
                FileInputStream fileInputStream1 = new FileInputStream("file1/test1.txt");
                FileInputStream fileInputStream2 = new FileInputStream("file1/test2.txt");
                FileInputStream fileInputStream3 = new FileInputStream("file1/test3.txt");
                FileInputStream fileInputStream4 = new FileInputStream("file1/test4.txt");
                FileInputStream fileInputStream5 = new FileInputStream("file1/test5.txt");
                InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, StandardCharsets.UTF_8);
                InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2, Charset.forName("windows-1251"));
                InputStreamReader inputStreamReader3 = new InputStreamReader(fileInputStream3, StandardCharsets.US_ASCII);
                InputStreamReader inputStreamReader4 = new InputStreamReader(fileInputStream4);
                InputStreamReader inputStreamReader5 = new InputStreamReader(fileInputStream5, Charset.forName("windows-1251"));
                BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                BufferedReader bufferedReader3 = new BufferedReader(inputStreamReader3);
                BufferedReader bufferedReader4 = new BufferedReader(inputStreamReader4);
                BufferedReader bufferedReader5 = new BufferedReader(inputStreamReader5);
        ) {

            System.out.println(inputStreamReader1.getEncoding());
            System.out.println(inputStreamReader2.getEncoding());
            System.out.println(inputStreamReader3.getEncoding());
            System.out.println(inputStreamReader4.getEncoding());
            System.out.println(inputStreamReader5.getEncoding());

            System.out.println(bufferedReader1.readLine());
            System.out.println(bufferedReader2.readLine());
            System.out.println(bufferedReader3.readLine());
            System.out.println(bufferedReader4.readLine());
            System.out.println(bufferedReader5.readLine());

            Pattern p = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);

            Matcher m = p.matcher(bufferedReader5.readLine());
            Matcher r = p.matcher(bufferedReader1.readLine());
            System.out.println(m.matches());
            System.out.println(r.matches());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Main5 {
    public static void main(String[] args) {

        try (
                FileInputStream fileInputStream1 = new FileInputStream("file1/test1.txt");
                FileInputStream fileInputStream2 = new FileInputStream("file1/test2.txt");
                FileInputStream fileInputStream3 = new FileInputStream("file1/test3.txt");
                FileInputStream fileInputStream4 = new FileInputStream("file1/test4.txt");
                FileInputStream fileInputStream5 = new FileInputStream("file1/test5.txt");
                InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1);
                InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);
                InputStreamReader inputStreamReader3 = new InputStreamReader(fileInputStream3);
                InputStreamReader inputStreamReader4 = new InputStreamReader(fileInputStream4);
                InputStreamReader inputStreamReader5 = new InputStreamReader(fileInputStream5);
                BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                BufferedReader bufferedReader3 = new BufferedReader(inputStreamReader3);
                BufferedReader bufferedReader4 = new BufferedReader(inputStreamReader4);
                BufferedReader bufferedReader5 = new BufferedReader(inputStreamReader5);
        ) {

            long time1 = System.nanoTime();
            CharsetDetector charsetDetector1 = new CharsetDetector();
            charsetDetector1.setText(bufferedReader1.readLine().getBytes());

//            charsetDetector1.setText(bufferedReader2.readLine().getBytes());
//            charsetDetector1.setText(bufferedReader3.readLine().getBytes());
//            charsetDetector1.setText(bufferedReader4.readLine().getBytes());
//            charsetDetector1.setText(bufferedReader5.readLine().getBytes());
            System.out.println(charsetDetector1.detect().getName());
            Arrays.asList(charsetDetector1.detectAll()).forEach(System.out::println);

            CharsetDetector charsetDetector2 = new CharsetDetector();
            charsetDetector2.setText(bufferedReader2.readLine().getBytes());
            System.out.println(charsetDetector2.detect().getName());
            Arrays.asList(charsetDetector2.detectAll()).forEach(System.out::println);

            CharsetDetector charsetDetector3 = new CharsetDetector();
            charsetDetector3.setText(bufferedReader3.readLine().getBytes());
            System.out.println(charsetDetector3.detect().getName());

            CharsetDetector charsetDetector4 = new CharsetDetector();
            charsetDetector4.setText(bufferedReader4.readLine().getBytes());
            System.out.println(charsetDetector4.detect().getName());

            CharsetDetector charsetDetector5 = new CharsetDetector();
            charsetDetector5.setText(bufferedReader5.readLine().getBytes());
            System.out.println(charsetDetector5.detect().getName());

            CharsetDetector charsetDetector6 = new CharsetDetector();
            System.out.println(bufferedReader5.readLine());
            final String string = charsetDetector6.getString(bufferedReader5.readLine().getBytes(), "utf-8");
            System.out.println(string);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Main6 {
    public static void main(String[] args) {
        try (
                FileInputStream fileInputStream1 = new FileInputStream("file1/test1.txt");
                FileInputStream fileInputStream2 = new FileInputStream("file1/test2.txt");
                FileInputStream fileInputStream3 = new FileInputStream("file1/test3.txt");
                FileInputStream fileInputStream4 = new FileInputStream("file1/test4.txt");
                FileInputStream fileInputStream5 = new FileInputStream("file1/test5.txt");
        ) {

            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(fileInputStream1)));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(fileInputStream2)));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(fileInputStream3)));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(fileInputStream4)));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(fileInputStream5)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Main7 {
    public static void main(String[] args) {
        try (
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("file1/test1.txt")));
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("file1/test2.txt")));
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new FileInputStream("file1/test3.txt")));
                BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(new FileInputStream("file1/test4.txt")));
                BufferedReader bufferedReader5 = new BufferedReader(new InputStreamReader(new FileInputStream("file1/test5.txt")));
        ) {
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bufferedReader1.readLine().getBytes()))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bufferedReader2.readLine().getBytes()))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bufferedReader3.readLine().getBytes()))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bufferedReader4.readLine().getBytes()))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bufferedReader5.readLine().getBytes()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Main8 {
    public static void main(String[] args) {
        try (
                FileInputStream fileInputStream1 = new FileInputStream("file1/test1.txt");
                FileInputStream fileInputStream2 = new FileInputStream("file1/test2.txt");
                FileInputStream fileInputStream3 = new FileInputStream("file1/test3.txt");
                FileInputStream fileInputStream4 = new FileInputStream("file1/test4.txt");
                FileInputStream fileInputStream5 = new FileInputStream("file1/test5.txt");
        ) {
            final byte[] bytes1 = fileInputStream1.readNBytes(100);
            final byte[] bytes2 = fileInputStream2.readNBytes(100);
            final byte[] bytes3 = fileInputStream3.readNBytes(100);
            final byte[] bytes4 = fileInputStream4.readNBytes(100);
            final byte[] bytes5 = fileInputStream5.readNBytes(100);

            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bytes1))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bytes2))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bytes3))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bytes4))));
            System.out.println(Charset.forName(new TikaEncodingDetector().guessEncoding(new ByteArrayInputStream(bytes5))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
