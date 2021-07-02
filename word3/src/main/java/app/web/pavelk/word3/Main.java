package app.web.pavelk.word3;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

/**
 * создание строки из рандомных букв английского алфавита
 */
class Main11 {
    public static void main(String[] args) {
        long l1 = System.nanoTime();
        String s1 = range(0, 100)
                .map(m -> ThreadLocalRandom.current().nextInt(97, 122))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(s1);
        long l2 = System.nanoTime();
        System.out.println(l2 - l1);
        //7772900
    }
}

/**
 * создание строки из рандомных букв английского алфавита
 */
class Main12 {
    public static void main(String[] args) {
        long l1 = System.nanoTime();
        String collect = range(0, 100)
                .map(m -> ThreadLocalRandom.current().nextInt(97, 122))
                .mapToObj(m -> new String(new byte[]{(byte) m}, StandardCharsets.UTF_8))
                .collect(Collectors.joining());
        System.out.println(collect);
        long l2 = System.nanoTime();
        System.out.println(l2 - l1);
        //8759700
    }
}

/**
 * создание строки из рандомных букв русского алфавита
 */
class Main13 {
    public static void main(String[] args) {
        long l1 = System.nanoTime();
        String s1 = range(0, 100)
                .map(m -> ThreadLocalRandom.current().nextInt(1072, 1103)) //1040
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(s1);
        long l2 = System.nanoTime();
        System.out.println(l2 - l1);
        //7570000
    }
}