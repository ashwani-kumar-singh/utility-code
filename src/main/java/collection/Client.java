package collection;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        HashSetDemo<String> set = new HashSetDemo<>();
        set.add("apple");
        set.add("orange");
        set.add("grapes");
        set.add("banana");

        System.out.println(set.contains("apple"));
        System.out.println(set.remove("orange"));
        Iterator<String> itr = set.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
        set.clear();
        System.out.println("isEmpty ->" + set.isEmpty());

    }
}
