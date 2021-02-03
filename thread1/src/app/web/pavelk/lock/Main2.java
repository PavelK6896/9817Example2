package app.web.pavelk.lock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.invokeAll(Arrays.asList(
                () -> {
                    Thread.sleep(1000);
                    System.out.println("1s");
                    return true;
                },
                () -> {
                    Thread.sleep(2000);
                    System.out.println("2s");
                    return true;
                }));
        System.out.println("end");
        executorService.shutdown();
    }
}


class Main3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.invokeAll(Arrays.asList(
                (Callable<Object>) () -> {
                    Thread.sleep(1000);
                    System.out.println("1s");
                    return true;
                },
                () -> {
                    Thread.sleep(2000);
                    System.out.println("2s");
                    return true;
                }));


        List<Callable<Object>> callables = Arrays.asList((Callable<Object>) () -> {
                    Thread.sleep(1000);
                    System.out.println("1s");
                    return true;
                },
                (Callable<Object>) () -> {
                    Thread.sleep(2000);
                    System.out.println("2s");
                    return true;
                });

        System.out.println("end");
        executorService.shutdown();
    }
}
