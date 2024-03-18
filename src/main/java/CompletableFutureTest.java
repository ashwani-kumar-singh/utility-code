import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

  @SneakyThrows
  public static void main(String[] args) {
    /*CompletableFuture<String> future = new CompletableFuture<>();
    future.thenAccept(str -> {
      System.out.println("Ek aur " + str);
    });
    future.thenAccept(System.out::println);

    future.complete("abcd");*/
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("abcd");

    //Thread.sleep(1000);
    future.thenAccept(System.out::println);
  }

}
