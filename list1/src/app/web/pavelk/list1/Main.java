package app.web.pavelk.list1;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list3 = new ArrayListCopy();
        System.out.println(list3);
        for (int i = 0; i < 155; i++) {
            list3.add("" + i);
        }
        list3.remove(10);
        list3.remove(20);
        System.out.println(list3);
        System.out.println(list3.size());
        System.out.println(list3.get(10));
        System.out.println(list3.get(20));
        list3.clear();
        System.out.println(list3);


        List<String> list4 = new LinkedListCopy();
        list4.add("1");
        list4.add("2");
        list4.add("3");
        list4.add("23");
        list4.add("4343");
        System.out.println(list4);
        list4.remove("2");
        list4.remove("4343");
        System.out.println(list4);
        System.out.println(list4.get(2));
        System.out.println(list4.get(55));
        list4.clear();
        System.out.println(list4);
    }
}
