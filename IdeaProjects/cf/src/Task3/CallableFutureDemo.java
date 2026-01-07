package Task3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureDemo {

    public static void main(String[] args) throws Exception {

        int N = 100;

        // Step 1: Create ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Step 2: Create Callable task
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += i;
            }
            Thread.sleep(2000); // simulate long computation
            return sum;
        };

        // Step 3: Submit task and get Future
        Future<Integer> future = executor.submit(task);

        System.out.println("Task submitted...");
        System.out.println("Doing some other work...");

        // Step 4: Retrieve result (BLOCKING call)
        Integer result = future.get();  // blocks until computation completes
        System.out.println("Result = " + result);

        // Step 5: Shutdown executor
        executor.shutdown();
    }
}

