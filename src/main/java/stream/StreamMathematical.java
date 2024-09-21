package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://www.geeksforgeeks.org/stream-in-java/">...</a>
 * <a href="https://www.baeldung.com/java-stream-sum">...</a>
 * <a href="https://1drv.ms/w/s!Am8pK68xxrsT7hRe-fL-r5u6tTTM?e=kRPdot">...</a>
 */
public class StreamMathematical {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int len = list.size();

        // second largest  number
        System.out.println("second laregst number in the list: "  + list.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());

        // from collection
        Stream<Integer> streamFromList = list.stream();

        // from Array
        Integer[] salary = {3000, 3300, 4500, 7890};
        Stream<Integer> streamFromArray = Arrays.stream(salary);

        //from static method
        Stream<Integer> streamFromStaticMethod = Stream.of(1, 2, 4, 2, 3);

        // stream from stream builder
        Stream.Builder<Integer> streamBuilder = Stream.builder();
        streamBuilder.add(1000).add(3000).add(3123);
        Stream<Integer> streamFromStreamBuilder = streamBuilder.build();

        //from stream iterator
        Stream<Integer> streamFromIterator = Stream.iterate(1000, i -> i + 5000).limit(5);
        // from java 9 Stream<Integer> streamFromIterator = Stream.iterate(1, i-> i<10, i-> i+1);

        accumulatorUsingLambda(list);
        accumulatorUsingInBuilt(list);
        accumulatorUsingCustom(list);
        stringSum();

        sequentialStreamVsParallelStream();

        // peek -> helps you to see the intermediate result of the stream which is getting processed
        Stream<Integer> stream1 = Stream.of(1, 2, 4, 2, 3);
        stream1
                .filter(value -> value > 2)
                .peek(System.out::println)// it will print 3, 4
                .map(value -> -1 * value)
                .collect(Collectors.toList());

        // limit -> truncate the stream, to have no longer than given maxSize
        Stream<Integer> stream2 = Stream.of(1, 2, 4, 2, 3);
        stream2.limit(3);

        // skip -> skip the first n elements of the stream
        Stream<Integer> stream3 = Stream.of(1, 2, 4, 2, 3);
        stream3.skip(1);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 7);
        List<Long> list2 = Arrays.asList(1L, 2L, 3L, 4L, 7L, 5L);
        List<Double> list3 = Arrays.asList(1.1d, 2.5d, 3.1d, 4.5d, 7.23d);
        // mapToInt -> helps to work with primitive "int" data types.
        System.out.println((Integer) list1.stream().mapToInt(Integer::intValue).sum());
        // mapToLong -> helps to work with primitive "long" data types.
        System.out.println((Long) list2.stream().mapToLong(Long::longValue).sum());
        // mapToDouble -> helps to work with primitive "long" data types.
        System.out.println((Double) list3.stream().mapToDouble(Double::doubleValue).sum());

        list1.stream().min(Comparator.naturalOrder());
        list1.stream().max(Comparator.naturalOrder());
        list1.stream().count();
        list1.toArray();
        list1.stream().anyMatch(val -> val > 2);
        list1.stream().allMatch(val -> val > 2);
        list1.stream().findFirst();
        list1.stream().findAny();
        list1.stream().noneMatch(val -> val > 2);

        System.out.println("collectors joining use case: "+ collectorsJoining());


    }

    private static void sequentialStreamVsParallelStream() {
        List<Integer> list = Arrays.asList(23, 45, 67, 89, 28, 40, 20, 56, 89);
        long serialProcessingTimeStart = System.currentTimeMillis();
        list.stream()
                .map(value -> value * value)
                .forEach(System.out::println);
        System.out.println("Sequential processing time taken: " + (System.currentTimeMillis() - serialProcessingTimeStart)
                + " millis");

        long parallelProcessingTimeStart = System.currentTimeMillis();
        list.parallelStream()
                .map(value -> value * value)
                .forEach(System.out::println);
        System.out.println("Parallel processing time taken: " + (System.currentTimeMillis() - parallelProcessingTimeStart)
                + " millis");

    }

    private static void accumulatorUsingLambda(List<Integer> list) {
        System.out.println("accumulatorUsingLambda -");
        System.out.println(list.stream().reduce(0, (a, b) -> a + b));
        System.out.println(list.stream().reduce(1, (a, b) -> a * b));
    }

    private static void accumulatorUsingInBuilt(List<Integer> list) {
        System.out.println("accumulatorUsingInBuilt -");
        System.out.println(list.stream().reduce(0, Integer::sum));
        System.out.println(list.stream().collect(Collectors.summingInt(Integer::intValue)));
        // mapToInt -> helps to work with primitive "int" data types.
        System.out.println((Integer) list.stream().mapToInt(Integer::intValue).sum());
        System.out.println(list.stream().reduce(Integer::min));
        System.out.println(list.stream().reduce(Integer::max));
    }

    private static void accumulatorUsingCustom(List<Integer> list) {
        System.out.println("accumulatorUsingCustom -");
        System.out.println(list.stream().reduce(0, ArithmeticUtils::add));
    }

    public static class ArithmeticUtils {
        static int add(int a, int b) {
            return a + b;
        }
    }

    private static void stringSum() {
        System.out.println("stringSum -");
        String string = "Item1 10 Item2 25 Item3 30 Item4 45";
        System.out.println(Arrays.stream(string.split(" "))
                .filter((s) -> s.matches("\\d+"))
                .mapToInt(Integer::valueOf)
                .sum());
    }

    private static String collectorsJoining() {
        return  (Arrays.asList("1","2","3","4","5","6")).stream()
                .collect(Collectors.joining("---->"));
    }

}
