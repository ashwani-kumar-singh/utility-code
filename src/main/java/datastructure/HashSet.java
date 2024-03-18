package datastructure;
/**
 * Write a Generic Class HashSet<E> to implement your own HashSet using any one data structure
 * other than HashSet.Generic HashSet should have below basic functionality.
 *
 * 1. An add(E e) method to add elements to Generic Hashset and that returns boolean value to
 *    identify whether element is added successfully or not. Time Complexity O(1)
 *
 * 2. A contains(E e) method to check a given element exists in Generic Hashset or not and return
 *    boolean value based on element existence in Hashset. Tile Complexity O(1)
 *
 * 3. An iterator method that returns Iterator<E> on the Generic Hashset to iterate over elements
 *    of hashset. O(n)
 *
 */

import java.util.HashMap;
import java.util.Iterator;

public class HashSet<E> {
  private final transient HashMap<E,Object> map;
  private static final Object PRESENT = new Object();

  HashSet(){
    map = new HashMap<>();
  }

  public boolean add(E e){
    return map.put(e, PRESENT) == null;
  }

  public boolean contains(E e){
    return map.containsKey(e);
  }

  public Iterator<E> iterator(){
    return map.keySet().iterator();
  }

  public static void main(String[] args) {
    HashSet<String> saarcCountries = new HashSet<>();
    saarcCountries.add("Afghanistan");
    saarcCountries.add("Bangladesh");
    saarcCountries.add("Bhutan");
    saarcCountries.add("India");
    saarcCountries.add("Maldives");
    saarcCountries.add("Nepal");
    saarcCountries.add("Pakistan");
    saarcCountries.add("Sri Lanka");

    Iterator<String> itr = saarcCountries.iterator();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }
    System.out.println(saarcCountries.contains("Japan"));
  }
}
