package app.web.pavelk.list2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * divide list into parts
 */
public class Main2 {
    static final int PART = 8;

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        List<Long> newList = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            list.add(ThreadLocalRandom.current().nextLong(1, 8));
        }
        int start = 0;
        int distance = 0;
        int i = list.size() / PART;
        for (int j = 0; j < i; j++) {
            distance += PART;
            newList.addAll(list.subList(start, distance));
            start = distance;
        }
        newList.addAll(list.subList(start, (distance + (list.size() % PART))));

        System.out.println(newList);
        System.out.println(newList.size());
        System.out.println(list);
        System.out.println(list.size());
    }
}
