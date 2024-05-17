package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsWildcards {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        ints.add(3);
        ints.add(5);
        ints.add(10);
        double sum = sum(ints);
        System.out.println("Sum of ints=" + sum);

        List<Double> doubles = new ArrayList<>();
        doubles.add(3.5);
        doubles.add(5.13);
        doubles.add(10.0);
        sum = sum(doubles);
        System.out.println("Sum of doubles=" + sum);

        List<Object> objects = new ArrayList<>();
        objects.add("amit");
        objects.add("clas 5");
        objects.add(100);
        printData(objects);
    }

    // Java Generics Upper Bounded Wildcard
    public static double sum(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    //Java Generics Unbounded Wildcard
    public static void printData(List<?> list) {
        for (Object obj : list) {
            System.out.print(obj + "::");
        }
    }

    //Java Generics Lower bounded Wildcard
    public static void addIntegers(List<? super Integer> list) {
        Integer value = 50;
        list.add(value);
    }


}
