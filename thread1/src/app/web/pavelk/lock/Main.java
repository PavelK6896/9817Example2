package app.web.pavelk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService ex = Executors.newFixedThreadPool(4, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        Counter counter = new Counter(100l);

        ex.submit(
                () -> {
                    while (true) {
                        System.out.println(counter.plus());
                    }
                }
        );

        ex.submit(
                () -> {
                    while (true) {
                        System.out.println(counter.minus());
                    }
                }
        );

        Thread.sleep(2000);
        ex.submit(
                () -> {
                    while (true) {
                        System.out.println(counter.plus());
                    }
                }
        );
        Thread.sleep(500);
        ex.shutdown();

    }

    static class Counter {
        final Lock lock = new ReentrantLock();
        Long l;

        public Counter(Long l) {
            this.l = l;
        }

        public Long plus() {
            try {
                lock.lock();
                return l++;
            } finally {
                lock.unlock();
            }
        }

        public Long minus() {
            try {
                lock.lock();
                return l--;
            } finally {
                lock.unlock();
            }
        }
    }


}
