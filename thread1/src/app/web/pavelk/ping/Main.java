package app.web.pavelk.ping;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (object) {
                    System.out.println("ping");
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (object) {
                    System.out.println("pong");
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.setDaemon(true);
        thread1.start();
        thread2.setDaemon(true);
        thread2.start();

        Thread.sleep(1000);
    }
}
