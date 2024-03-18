package stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * - https://mkyong.com/java8/java-8-flatmap-example/
 * - https://howtodoinjava.com/java8/stream-map-vs-flatmap/
 * In Java 8, Stream can hold different data types, for examples:
 * Stream<String[]>
 * Stream<Set<String>>
 * Stream<List<String>>
 * Stream<List<Object>>
 * the Stream operations (filter, sum, distinct…) and collectors do not support it,
 * so, we need flatMap() to do the following conversion
 */
public class FlatMap {
  public static void main(String[] args) {
    flatMapHelper1();
    flatMapHelper2();
    flatMapHelper3();
    distinctWords();
  }

  private static void flatMapHelper1() {
    String[][] data = new String[][] {{"a", "b"}, {"c", "d"}, {"e", "f"}};
    Arrays.stream(data).flatMap(Arrays::stream).filter("a"::equals).forEach(System.out::println);
  }

  private static void flatMapHelper2() {
    Student obj1 = new Student();
    obj1.setName("Ashwani");
    obj1.addBook("Java 8 in Action");
    obj1.addBook("Spring Boot in Action");
    obj1.addBook("Effective Java (2nd Edition)");

    Student obj2 = new Student();
    obj2.setName("Ankit");
    obj2.addBook("Learning Python, 5th Edition");
    obj2.addBook("Effective Java (2nd Edition)");

    List<Student> list = new ArrayList<>();
    list.add(obj1);
    list.add(obj2);
    list.stream().map(Student::getBook).flatMap(Collection::stream).distinct()
        .collect(Collectors.toList()).forEach(System.out::println);
  }

  private static void flatMapHelper3() {
    int[] intArray = {1, 2, 3, 4, 5, 6};
    Stream.of(intArray).flatMapToInt(Arrays::stream).forEach(System.out::println);
  }

  private static void distinctWords() {
    String file = "<file_location>/utilities-project/src/main/java/htmlparser/StringToHtml.html";
    Stream<String> str = null;
    try {
      str = Files.lines(Paths.get(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert str != null;
    str.map(s -> s.split("\\s+")).flatMap(Arrays::stream).distinct()
        .collect(Collectors.toList()).forEach(System.out::println);
  }


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
private static class Student {
  private String name;
  private Set<String> book;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getBook() {
    return book;
  }

  public void setBook(Set<String> book) {
    this.book = book;
  }

  void addBook(String book) {
    if (this.book == null) {
      this.book = new HashSet<>();
    }
    this.book.add(book);
  }
}

}
