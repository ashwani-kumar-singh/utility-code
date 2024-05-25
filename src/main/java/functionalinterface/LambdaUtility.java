package functionalinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@FunctionalInterface
interface ShortToByteFunction<K, V> {

    V applyAsByte(K s);

}

/**
 *  * @link <a href="https://www.geeksforgeeks.org/functional-interfaces-java/">...</a>
 *  * <a href="https://www.baeldung.com/java-8-functional-interfaces">...</a>
 *  * <a href="https://1drv.ms/w/s!Am8pK68xxrsT7hRe-fL-r5u6tTTM?e=d0qKoa">...</a>
 */
public class LambdaUtility {

    public static void main(String[] args) {
        multipleFunctionalInterface();
        transformSortToByte();

        //Consumer
        Consumer<String> consumer = (value) -> System.out.println(value);
        consumer.accept("consumer value");
        // BiConsumer
        BiConsumer<String, Integer> biConsumer = (String name, Integer age) -> System.out.println(name + " is " + age + " years old");
        biConsumer.accept("biconsumer name", 12);

        //Predicate
        Predicate<Object> predicate = (value) -> value != null;
        System.out.println("test predicate result: " + predicate.test(new Object()));

        //BiPredicate
        BiPredicate<Object, Object> biPredicate = (a, b) -> a.equals(b);
        System.out.println("test bi-predicate equality result: " + biPredicate.test("cat", "cat"));

        //Function
        Function<Integer, Double> function = (x) -> Math.pow(x, 15);
        System.out.println("test function result: " + function.apply(2));

        BiFunction<String, Integer, Integer> biFunction = (name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000;
        System.out.println("test biFunction result: " + biFunction.apply("Freddy", 1500));

        //andThen -> It returns a composed function wherein the parameterized function will be executed after the first one.
        // If evaluation of either function throws an error, it is relayed to the caller of the composed function.
        BiFunction<String, Integer, Integer> divideBy10 = biFunction.andThen((value) -> value/10);
        System.out.println("test biFunction with andThen result: " + divideBy10.apply("Freddy", 1500));

        //Operator interfaces are special cases of a function that receive and return the same value type.

        //UnaryOperator
        UnaryOperator<String> unaryOperator = (name) -> name.toUpperCase();
        System.out.println("test unaryOperator result: " + unaryOperator.apply("john"));

        //BinaryOperator
        BinaryOperator<Integer> binaryOperator = (i1, i2) -> i1 + i2;
        System.out.println("test binaryOperator result: " + binaryOperator.apply(2, 3));

        //Supplier
        Supplier<Double> supplier = () -> 9d;
        System.out.println("test supplier result: " + supplier.get());

    }

    private static void multipleFunctionalInterface() {
        System.out.println("multipleFunctionalInterface -");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";
        //The Function interface also has a default compose method that allows us to combine several functions into
        // one and execute them sequentially:
        Function<Integer, String> quoteIntToString = quote.compose(intToString);
        System.out.println(list.stream().map(quoteIntToString).collect(Collectors.toList()));
    }

    private static void transformSortToByte() {
        System.out.println("transformSortToByte -");
        short[] array = {(short) 1, (short) 2, (short) 3};
        System.out.println(Arrays.toString(transformArray(array, s -> (byte) (s * 2))));
    }

    private static byte[] transformArray(short[] array, ShortToByteFunction<Short, Byte> function) {
        byte[] transformedArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            transformedArray[i] =  function.applyAsByte(array[i]);
        }
        return transformedArray;
    }

}