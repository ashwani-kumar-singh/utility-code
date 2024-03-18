package datastructure;

/*
 Write an implementation to a method that prints all the pairs with given sum and also return count of pairs with
 given sum in a sorted rotated array.
 Example:
 Input:
    int[] arr = {11, 15, 6, 7, 9, 10};
    int sum = 16;

 Output:
    Pairs with given sum: 16
    <6, 10> <7, 9>
    Count of Pairs with given sum: 2
 */

import java.util.HashMap;
import java.util.Map;

class CountPairs {
    private static int countPairsInSortedRotatedArray(int[] arr, int x) {
        // Find the pivot element. Pivot element is largest element of array.
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        int i;
        for (i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1])
                break;
        }
        int l = (i + 1) % n;
        int r = i;
        int count = 0;
        while (l != r) {
            if (arr[l] + arr[r] == x) {
                count++;
                System.out.print("<" + arr[l] + ", " + arr[r] + "> ");
                // This condition is required to be checked, otherwise l and r will cross each
                // other and loop will never terminate.
                if (l == (r - 1 + n) % n) {
                    return count;
                }
                l = (l + 1) % n;
                r = (r - 1 + n) % n;
            }
            // If current pair sum is less, move to the higher sum side.
            else if (arr[l] + arr[r] < x)
                l = (l + 1) % n;
                // If current pair sum is greater, move to the lower sum side.
            else
                r = (n + r - 1) % n;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {11, 15, 6, 7, 9, 10};
        int sum = 16;

        /*case 1: int[] arr = {}; int sum = 16 ; output: no output
        case 2: int[] arr = {16}; int sum = 16; output: no output
        case 3: int[] arr = {15, 1}; int sum = 16; output: <1, 15>
        case 4: int[] arr = {11, 15, 23, -7, 6, 7, 9, 10}; int sum = 16; output: < -7, 23 >, < 6, 10 >, < 7, 9 >
        case 5: int[] arr = {11, 15, 23, 24, -8, -7, 6, 7, 9, 10}; int sum = 16; output: < -8, 24 > < -7, 23 > < 6, 10 > < 7, 9 >
        case 6: int[] arr = {11, 15, 23, 24, -10, -8, -7, -6, 6, 7, 9, 10}; int sum = -16; output: < -10, -6 >
        Time Complexity: O(n)
        Auxiliary Space: O(1)
        */
        System.out.println("Pairs with given sum: " + sum);
        int count = countPairsInSortedRotatedArray(arr, sum);
        System.out.println();
        System.out.println("Count of Pairs with given sum: " + count);
    }
}