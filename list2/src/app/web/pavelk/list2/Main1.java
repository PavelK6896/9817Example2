package app.web.pavelk.list2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();

        for (int i = 0; i < 100000; i++) {
            list1.add(i);
            list2.add(i);
        }

        long time1 = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            list1.remove(list1.size() / 2);
        }

        long time2 = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            list2.remove(list2.size() / 2);
        }

        long time3 = System.nanoTime();

        long time4;
        long time5;
        System.out.println(time4 = (time2 - time1));
        System.out.println(time5 = (time3 - time2));
        System.out.println(time5 / time4);
    }
}
