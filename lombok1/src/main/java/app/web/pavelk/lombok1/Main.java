package app.web.pavelk.lombok1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class Main1 {
    public static void main(String[] args) {
        System.out.println("--");

        D1 qq = D1.builder().s1("]]").s2("qq").build();
        System.out.println(qq);
        qq = qq.withS1("qq");
        System.out.println(qq);
        qq = qq.withF1(F1.builder().q1("zz").build());
        System.out.println(qq);

        qq = qq.withS1("ee");
        System.out.println(qq);

        qq = qq.withF1(F1.builder().q1("aa").build());
        System.out.println(qq);

        qq = qq.withL1(new ArrayList<>(List.of(1L, 2L)));
        System.out.println(qq);
    }
}


class Main2 {
    public static void main(String[] args) {
        D1 qq = new D1("1", "2", new F1("qq"), List.of());
        System.out.println("--");
        System.out.println(qq);

        Arrays.asList(D1.class.getDeclaredFields()).forEach(f -> {
            if (f.getType().equals(String.class)) {
                if (f.getName().equals("s1")) {
                    try {
                        f.setAccessible(true);
                        f.set(qq, "object1");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (f.isAnnotationPresent(An1.class)) {
                    System.out.println("an1");
                }
                if (f.isAnnotationPresent(An2.class)) {
                    System.out.println("an2");
                    try {
                        f.setAccessible(true);
                        f.set(qq, "an2");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (f.isAnnotationPresent(An3.class)) {
                System.out.println("an3");
                System.out.println(f.getType());
            }
        });

        System.out.println(qq.withS1("d"));
        System.out.println(qq);
    }
}

class Main3 {
    public static void main(String[] args) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();

        D1 d1 = D1.builder()
                .s1("{" + 1 + "}")
                .s2(Character.toString(ThreadLocalRandom.current().nextInt(1072, 1103)))
                .f1(F1.builder().q1(String.valueOf((char) ThreadLocalRandom.current().nextInt(97, 122))).build())
                .l1(LongStream.range(0, ThreadLocalRandom.current().nextLong(10, 100)).boxed().collect(Collectors.toList()))
                .build();

        System.out.println(d1.getL1().size());
        System.out.println(d1.getS2());

        Files.write(Paths.get("lombok1/j1.js"), objectMapper.writeValueAsString(d1).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        Thread.sleep(1_000);
        var bytes = Files.readAllBytes(Paths.get("lombok1/j1.js"));
        D1 d11 = objectMapper.readValue(bytes, D1.class);

        System.out.println(d11.getL1().size());
        System.out.println(d11.getS2());

    }
}


class Main4 {
    public static void main(String[] args) throws IOException, InterruptedException {

        var objectMapper = new ObjectMapper();
        var collect = IntStream.range(0, ThreadLocalRandom.current().nextInt(10, 20))
                .mapToObj((i) ->
                        D1.builder()
                                .s1("{" + i + "}")
                                .s2(Character.toString(ThreadLocalRandom.current().nextInt(1072, 1103)))
                                .f1(F1.builder().q1(String.valueOf((char) ThreadLocalRandom.current().nextInt(97, 122))).build())
                                .l1(LongStream.range(0, ThreadLocalRandom.current().nextLong(1, 10)).boxed().collect(Collectors.toList()))
                                .build()
                ).collect(Collectors.toList());

        System.out.println(collect.size());
        System.out.println(collect.get(0).getS2());
        System.out.println(collect.get(5).getS2());
        System.out.println(collect.get(5).getL1().size());


        Files.write(Paths.get("lombok1/j2.js"), objectMapper.writeValueAsString(collect).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        Thread.sleep(1_000);
        var bytes = Files.readAllBytes(Paths.get("lombok1/j2.js"));
        List<D1> list = objectMapper.readValue(bytes, new TypeReference<List<D1>>() {
        });

        System.out.println(list.size());
        System.out.println(list.get(0).getS2());
        System.out.println(list.get(5).getS2());
        System.out.println(list.get(5).getL1().size());


    }
}









