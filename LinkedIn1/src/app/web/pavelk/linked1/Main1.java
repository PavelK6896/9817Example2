package app.web.pavelk.linked1;

import java.io.Externalizable;
import java.io.RandomAccessFile;
import java.sql.DatabaseMetaData;
import java.util.*;

class Main1 {
    public static void main(String[] args) {
        String str = "abcde";
        str.trim();
        str.toUpperCase();
        str.substring(3, 4);
        System.out.println(str.substring(3, 4));
        System.out.println(str);
    }
}

class Main2 {
    interface q1 {
        default void method() {
            System.out.println("q1");
        }
    }

    interface q2 {
        default void method() {
            System.out.println("q2");
        }
    }

    static class Q3 implements q1, q2 {
        public void method() {
            q1.super.method(); //вызываем q1 method через супер
        }
    }

    public static void main(String[] args) {
        Q3 q3 = new Q3();
        q3.method();
    }
}

class Main3 {
    public static void main(String[] args) {
        System.out.println("sdfs" instanceof String);
        System.out.println("sdfs".getClass().getSimpleName() == "String");
    }
}


class Main4 {
    public static void main(String[] args) {
        String str = "strawberries";
        System.out.println(str.substring(2, 5));
    }
}


class Main5 {

    static class C1 {
        Object mes() {
            return "ggg11";
        }
    }

    static class C2 extends C1 {
        Object mes() {
            return "ggg22";
        }
    }

    public static void main(String[] args) {
        System.out.println(new C1().mes());
        System.out.println(new C2().mes());
    }
}


class Main6 {
    public static void main(String[] args) {

        List list = new ArrayList<>();
        list.add("ddd");
        list.add(2);

        System.out.println(list.get(0) instanceof Object);
        System.out.println(list.get(1) instanceof Integer);
    }
}


class Main7 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i = i++) {
            i += 1;
            System.out.println("qq" + i);

        }

        System.out.println("");
    }
}

class Main8 {
    public static void main(String[] args) {

        List<Boolean> list = new ArrayList<>();
        list.add(true);
        list.add(Boolean.parseBoolean("FALSE"));
        list.add(Boolean.TRUE);

        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}

class Main9 {
    public static void main(String[] args) {

        List<Boolean> list = new ArrayList<>();
        list.add(true);
        list.add(false);
        list.add(true);
        list.add(false);
        Collections.sort(list);
        System.out.println(list);
    }
}

class Main10 {
    public static void main(String[] args) {
        try {
            System.out.println("try");
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");

        }
    }
}

class Main11 {
    public static void main(String[] args) {
//        overloading перегрузка
//        achieve polymorphism
        System.out.println("achieve polymorphism overloading перегрузка");
    }
}

class Main12 {
    static int count = 0;

    public static void main(String[] args) {
        try {
            System.out.println("A");
            main(null);
        } catch (Exception e) {
            System.out.println("B");
        } finally {
            System.out.println("C");
        }
        //Exception in thread "main" java.lang.StackOverflowError
    }
}

class Main13 {
    public static void main(String[] args) {
//      method  another new instance
        // Class.newInstance() вегда без параметров
        // Constructor.newInstance()
        System.out.println("");
    }
}

class Main14 {
    public static void main(String[] args) {
        try {
            System.out.println("1");
            Dfs();
        } catch (Exception e) {
            System.out.println("2");
        } finally {
            System.out.println("3");
        }
    }

    public static void Dfs() {
        new Error(); // без параметров
    }
}

class Main15 {
    public static void main(String[] args) {

        String str = "sdfsdf";
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i + 1));

        }
        //Exception in thread "main" java.lang.StringIndexOutOfBoundsException
    }
}


class Main16 {
    public static void main(String[] args) {
//        directly непосредственно
        System.out.println("");
    }
}

class Main17 {
    public static void main(String[] args) {

        String s1 = "fsddfs";
        String s2 = "sdfsd";

        System.out.println(s1.matches(s2));
        System.out.println(s1);
    }
}

class Main18 {
    public static void main(String[] args) {

        String s1 = "Hello World!";
        System.out.println(s1.substring(6, 12));
        System.out.println(s1.substring(12, 6));
    }
}


class Main19 {
    public static void main(String[] args) {

        System.out.println(12 % 5 == 0);
        System.out.println(12 % 5 != 0);
    }
}

class Main20 {
    public static void main(String[] args) {

        double pi = Math.PI;


        System.out.println("");
    }
}

class Main21 {
    public static void main(String[] args) {
        LinkedList<Integer> i = new LinkedList<>();
        i.add(4);
        i.add(40);
        i.add(12);
        System.out.println(i);
    }
}


class Main22 {
    public static void main(String[] args) {


//        rather than action s
//        what would be the best way to allow you to use simply
        //CONSTRUCT blueptint
        //duck called
        //instantiated
        //divisible
        System.out.println("apple".compareTo("banana"));
    }
}


class Main23 {
    public static void main(String[] args) {

        String[] array = {"abc", "2", "10", "0"};
        List<String> l = Arrays.asList(array);
        Collections.sort(l);

        System.out.println(l);
    }
}


class Main24 {
    public static void main(String[] args) {


        System.out.println(Class.class);
        System.out.println(char.class);
        System.out.println(int[].class);
        System.out.println(void.class);
    }
}


class Main25 {
    public static void main(String[] args) {

        System.out.println("");
    }
}


class Main26 {
    public static void main(String[] args) {
        System.out.println("");
    }
}


class Main27 {
    public static void main(String[] args) {
        System.out.println("");
    }
}


class Main28 {
    public static void main(String[] args) {
        System.out.println("");
    }
}

class Main29 {
    public static void main(String[] args) {
        System.out.println("");
    }
}

class Main30 {
    public static void main(String[] args) {
        System.out.println("");
    }
}


