import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class LinkedBlockingQueueTest {

  private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

  public static void main(String[] args) throws InterruptedException {
    IntStream.range(0, 10000).forEach(i -> queue.add(i));

    for (int i = 0; i < 10; i++) {
      CompletableFuture.supplyAsync(LinkedBlockingQueueTest::drainViaLoopPoll)
          .thenAccept(System.out::println);
    }

    /*IntStream.range(0, 10000).forEach(i -> queue.add(i));

    for (int i = 0; i < 10; i++) {
      CompletableFuture.supplyAsync(LinkedBlockingQueueTest::drainViaDrainTo)
          .thenAccept(System.out::println);
    }*/

    sleep(5000);

  }

  private static List<Integer> drainViaLoopPoll() {
    List<Integer> result = new ArrayList<>();
    while (!queue.isEmpty()) {
      Integer queueVal = queue.poll();
      if (Objects.nonNull(queueVal)) {
        result.add(queueVal);
      }
    }
    return result;

  }

  private static List<Integer> drainViaDrainTo() {
    List<Integer> result = new ArrayList<>();
    queue.drainTo(result);
    return result;
  }

  @SneakyThrows
  private static void sleep(int ms) {
   // Thread.sleep(ms);
  }

}
