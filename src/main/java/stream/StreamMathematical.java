package stream;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * https://www.baeldung.com/java-stream-sum
 */
public class StreamMathematical {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    accumulatorUsingLambda(list);
    accumulatorUsingInBuilt(list);
    accumulatorUsingCustom(list);
/*    Item item1 = new Item(1, 10);
    Item item2 = new Item(2, 15);
    Item item3 = new Item(3, 25);
    Item item4 = new Item(4, 40);
    List<Item> items = Arrays.asList(item1, item2, item3, item4);
    accumulatorsOnObject(items);*/
    stringSum();
    Supplier<Double> lazyValue = () -> {
      Uninterruptibles.sleepUninterruptibly(100000000, TimeUnit.MILLISECONDS);
      return 9d;
    };
    squareLazy(lazyValue);
  }

  private static void accumulatorUsingLambda(List<Integer> list){
    System.out.println("accumulatorUsingLambda -");
    System.out.println(list.stream().reduce(0, (a, b)-> a+b));
    System.out.println(list.stream().reduce(1, (a, b)-> a*b));
  }

  private static void accumulatorUsingInBuilt(List<Integer> list){
    System.out.println("accumulatorUsingInBuilt -");
    System.out.println(list.stream().reduce(0, Integer::sum));
    System.out.println(list.stream().collect(Collectors.summingInt(Integer::intValue)));
    System.out.println((Integer) list.stream().mapToInt(Integer::intValue).sum());
    System.out.println(list.stream().reduce(Integer::min));
    System.out.println(list.stream().reduce( Integer::max));
  }

  private static void accumulatorUsingCustom(List<Integer> list){
    System.out.println("accumulatorUsingCustom -");
    System.out.println(list.stream().reduce(0, ArithmeticUtils::add));
  }

  public static class ArithmeticUtils {
    static int add(int a, int b) {
      return a + b;
    }
  }

 /* private static void accumulatorsOnObject(List<Item> items){
    System.out.println("accumulatorsOnObject -");
    System.out.println(items.stream().map(Item :: getPrice).reduce(0, ArithmeticUtils::add));
    System.out.println(items.stream().map(Item :: getPrice).reduce(0,  Integer::sum));
    System.out.println(items.stream().map(Item :: getPrice).reduce(0, (a, b)-> a+b));
    System.out.println(items.stream().map(Item :: getPrice).collect(Collectors.summingInt(Integer::intValue)));
    System.out.println(items.stream().mapToInt(Item :: getPrice).sum());
  }*/

 /* @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class Item {
    private int id;
    private Integer price;
  }*/

  private static void stringSum(){
    System.out.println("stringSum -");
    String string = "Item1 10 Item2 25 Item3 30 Item4 45";
    System.out.println(Arrays.stream(string.split(" "))
        .filter((s) -> s.matches("\\d+"))
        .mapToInt(Integer::valueOf)
        .sum());
  }

  private static void squareLazy(Supplier<Double> lazyValue) {
    System.out.println("squareLazy -");
    System.out.println(Math.pow(lazyValue.get(), 2));
  }

}
