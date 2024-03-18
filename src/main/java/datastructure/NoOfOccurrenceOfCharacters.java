package datastructure;
/*
 Write an implementation to a method that prints count of occurrences of each character in a given string.

 Example:
 Input:
    str =  "Let's Code!!"
 Output:
    Occurrences of each character -
    character:   , occurrence: 1
    character: ! , occurrence: 2
    character: s , occurrence: 1
    character: C , occurrence: 1
    character: t , occurrence: 1
    character: d , occurrence: 1
    character: e , occurrence: 2
    character: ' , occurrence: 1
    character: L , occurrence: 1
    character: o , occurrence: 1
*/

import java.util.HashMap;
import java.util.Map;

class NoOfOccurrenceOfCharacters {
    static void printCharacterOccurrence(String str) {
        if(str == null || "".equals(str)){
            throw new RuntimeException("Given String can not be Null or empty!");
        }
        Map<Character, Integer> countMap = new HashMap<>();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            countMap.put(str.charAt(i), countMap.getOrDefault(str.charAt(i), 0) + 1);
        }
        countMap.forEach((k, v) -> System.out.println("character: " + k + " , " + "occurrence: " + v));
    }

    public static void main(String[] args) {
        System.out.println("Occurrences of each character in a given string -");
        String str = "Let's Code!!";
       /* case1: str = null ; output : Exception in thread "main" java.lang.RuntimeException: String can not be Null!
        case2: str = "" ; output : Exception in thread "main" java.lang.RuntimeException: String can not be Null!
        case3: str = " " ; output: Occurrences of each character in a given string -
                                    character:   , occurrence: 1
        Time Complexity: O(n)
        Auxiliary Space: O(n)
        */
        printCharacterOccurrence(str);
    }
}