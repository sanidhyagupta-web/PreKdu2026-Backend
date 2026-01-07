package Task1;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();

    // Producer
    public synchronized void put(String message) {
        queue.add(message);
        // Signal all waiting consumers that a message is available
        notifyAll();
    }

    // Consumer
    public synchronized String take() {
        while (queue.isEmpty()) {
            try {
                // Wait until a producer adds a message
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }
}

