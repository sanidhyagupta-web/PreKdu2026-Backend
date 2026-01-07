package Task1;

public class ProducerConsumerDemo {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        // Producers
        Thread p1 = new Thread(new MessageSender(queue, "Sender-1"));
        Thread p2 = new Thread(new MessageSender(queue, "Sender-2"));
        Thread p3 = new Thread(new MessageSender(queue, "Sender-3"));

        // Consumers
        Thread c1 = new Thread(new MessageReceiver(queue, "Receiver-1"));
        Thread c2 = new Thread(new MessageReceiver(queue, "Receiver-2"));
        Thread c3 = new Thread(new MessageReceiver(queue, "Receiver-3"));

        // Start threads
        p1.start();
        p2.start();
        p3.start();

        c1.start();
        c2.start();
        c3.start();
    }
}
