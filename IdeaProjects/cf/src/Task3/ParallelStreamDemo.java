package Task3;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreamDemo {

    public static void main(String[] args) {

        // Step 1: Generate data
        List<Integer> numbers = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .toList();

        // Sequential Stream
        long start = System.currentTimeMillis();
        long seqSum = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long seqTime = System.currentTimeMillis() - start;

        // Parallel Stream
        start = System.currentTimeMillis();
        long parSum = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parTime = System.currentTimeMillis() - start;

        // Results
        System.out.println("Sequential Sum = " + seqSum);
        System.out.println("Sequential Time = " + seqTime + " ms");

        System.out.println("Parallel Sum = " + parSum);
        System.out.println("Parallel Time = " + parTime + " ms");
    }
}

