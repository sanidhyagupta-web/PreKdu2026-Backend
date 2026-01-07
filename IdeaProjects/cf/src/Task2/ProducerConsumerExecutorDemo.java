package Task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerExecutorDemo {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        // Separate executor pools
        ExecutorService producerPool = Executors.newFixedThreadPool(3);
        ExecutorService consumerPool = Executors.newFixedThreadPool(3);

        // Submit producers
        for (int i = 1; i <= 3; i++) {
            producerPool.submit(new MessageSender(queue, "Sender-" + i));
        }

        // Submit consumers
        for (int i = 1; i <= 3; i++) {
            consumerPool.submit(new MessageReceiver(queue, "Receiver-" + i));
        }

        // Graceful shutdown
        producerPool.shutdown();
        consumerPool.shutdown();

        try {
            producerPool.awaitTermination(1, TimeUnit.MINUTES);
            consumerPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All producers and consumers have finished.");
    }
}

