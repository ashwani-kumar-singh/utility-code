package datastructure;

/*
  Write an implementation to a method that arrange and return the numbers to form the
  largest number with given array of numbers in string.

  Example:
    Input: str = ["54", "546", "548", "60"];
    Output: largest number formed: 6054854654

 */

import java.util.List;
import java.util.Vector;

class LargestNumber {

    static String computeLargest(List<String> arr) {
        if(arr == null || arr.size() == 0){
            return null;
        }

        if(arr.size() == 1){
            return arr.get(0);
        }
        StringBuilder sb = new StringBuilder();
        arr.sort((X, Y) -> {
            String XY = X + Y;
            String YX = Y + X;
            return XY.compareTo(YX) > 0 ? -1 : 1;
        });
        arr.forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> list = new Vector<>();
        list.add("54");
        list.add("546");
        list.add("548");
        list.add("60");
        /*
        case 1: list = null; output: largest number formed: null
        case 2: list = []; output: largest number formed: null
        case 3: list = [54]; output: largest number formed: 54
         */
        System.out.println("largest number formed: " + computeLargest(list));
    }
}