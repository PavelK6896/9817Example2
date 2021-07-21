package app.web.pavelk.profiler1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Main1 {
    public static void main(String[] args) throws InterruptedException {
        Map<Long, Long> map = new HashMap<>();
        while (true) {
            Thread.sleep(5000);
            Long l = ThreadLocalRandom.current().nextLong();
            for (int i = 0; i < 10; i++) {
                map.put(l, ThreadLocalRandom.current().nextLong());
                map.remove(l);
            }
        }

    }
}

class Main2 {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task performed on: " + new Date() + "\n" +
                        "Thread's name: " + Thread.currentThread().getName());
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1L;
        timer.schedule(task, delay);
        System.out.println("-----------------");
    }
}

class Main3 {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        while (true) {
            if (System.currentTimeMillis() % 4000 == 0) {
                System.gc();
                float usage = (float) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
                System.out.println("Used memory: " + usage + "Mb");
            }
        }
    }
}

class Main4 {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        while (true) {
            Thread.sleep(200);
            System.gc();
            float usage = (float) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
            System.out.println("Used memory: " + usage + "Mb");
        }

    }
}

class Test1 {
    public Test1(Long t1) {
        this.t1 = t1;
    }

    Long t1;
}

class Main5 {
    public static void main(String[] args) throws InterruptedException {
        Long ii = 0L;
        Long dd = 0L;
        Map<Long, Test1> ss = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Test1 t1 = new Test1(ThreadLocalRandom.current().nextLong());
            ss.put(++ii, t1);
        }
        Runtime runtime = Runtime.getRuntime();
        while (true) {
            System.gc();
            float usage = (float) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
            System.out.println("Used memory: " + usage + "Mb");

            Thread.sleep(200);
            System.out.println("=");
            for (int i = 0; i < 50; i++) {
                ss.remove(++dd);
            }
            Thread.sleep(200);
            for (int i = 0; i < 50; i++) {
                Test1 t2 = new Test1(ThreadLocalRandom.current().nextLong());
                ss.put(++ii, t2);
            }
            System.out.println(ss.size());
        }
    }
}

