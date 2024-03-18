package abstracttest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MainClass {

  private static Map<String, AbstractClass> map = new HashMap<>();

  public static void main(String[] args) {
    Stream.of(ImplementationClass1.getInstance(), ImplementationClass2.getInstance())
        .forEach(abstractClass -> map.putIfAbsent(abstractClass.get(), abstractClass));
    System.out.println(map);
  }

}
