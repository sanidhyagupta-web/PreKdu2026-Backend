package Task1;

public class MessageReceiver implements Runnable {

    private final MessageQueue queue;
    private final String receiverName;

    public MessageReceiver(MessageQueue queue, String receiverName) {
        this.queue = queue;
        this.receiverName = receiverName;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String message = queue.take();
            System.out.println("Consumed by " + receiverName + ": " + message);

            try {
                Thread.sleep(150); // simulate processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
