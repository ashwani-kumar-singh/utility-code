package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@FunctionalInterface
interface ShortToByteFunction<K, V> {

  V applyAsByte(K s);

}


/**
 * https://www.baeldung.com/java-8-functional-interfaces#:~:text=Any%20interface%20with%20a%20SAM,still%20have%20multiple%20default%20methods.
 */
public class LambdaUtility {

  public static void main(String[] args) {
    multipleFunctionalInterface();
    transformSortToByte();
    twoArityFunction();
  }

  private static void multipleFunctionalInterface() {
    System.out.println("multipleFunctionalInterface -");
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    Function<Integer, String> intToString = Object::toString;
    Function<String, String> quote = s -> "'" + s + "'";
    Function<Integer, String> quoteIntToString = quote.compose(intToString);
    System.out.println(list.stream().map(quoteIntToString).collect(Collectors.toList()));
  }

  private static void transformSortToByte(){
    System.out.println("transformSortToByte -");
    short[] array = {(short) 1, (short) 2, (short) 3};
    System.out.println(Arrays.toString(transformArray(array, s -> (byte) ((short)s * 2))));
  }

  private static byte[] transformArray(short[] array, ShortToByteFunction function) {
    byte[] transformedArray = new byte[array.length];
    for (int i = 0; i < array.length; i++) {
      transformedArray[i] = (byte) function.applyAsByte(array[i]);
    }
    return transformedArray;
  }

  private static void twoArityFunction(){
    System.out.println("twoArityFunction - ");
    Map<String, Integer> salaries = new HashMap<>();
    salaries.put("John", 40000);
    salaries.put("Freddy", 30000);
    salaries.put("Samuel", 50000);
    salaries.replaceAll((name, oldValue) ->
        name.equals("Freddy") ? oldValue : oldValue + 1);
    System.out.println(salaries.values());

  }

}