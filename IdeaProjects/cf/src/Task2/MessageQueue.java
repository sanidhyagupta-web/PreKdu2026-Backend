package Task2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    private final Deque<String> queue = new ArrayDeque<>();

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    // Producer
    public void put(String message) {
        lock.lock();
        try {
            queue.addLast(message);
            // Signal waiting consumers
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // Consumer
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return queue.removeFirst();
        } finally {
            lock.unlock();
        }
    }
}
