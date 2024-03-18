package datastructure;

import java.util.Arrays;

/*
 Write an implementation to a method that return Nth Right Rotated for a given array.

 Example:
 Input:
    arr = [1, 3, 5, 7, 9]
 Output:
    Array after 2 rotation: [9, 1, 3, 5, 7]
*/
class NthRightRotatedArray {
    // Fills temp[] with two copies of arr[]
    static void preprocess(int[] arr, int n, int[] temp) {
        // Store arr[] elements at i and i + n
        for (int i = 0; i < n; i++)
            temp[i] = temp[i + n] = arr[i];
    }

    // Function to right rotate an array k time
    private static int[] rightRotate(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || n == 1) {
            return arr;
        }
        int[] temp = new int[2 * n];
        preprocess(arr, n, temp);
        return rightRotatedHelper(n, k, temp);
    }

    private static int[] rightRotatedHelper(int n, int k, int[] temp) {
        int[] rotated = new int[n];
        int j = 0;
        // Starting position of array after k rotations in temp[] will be (n - k + 1 ) % n
        int start = (n - k) % n;
        // Print array after k rotations
        for (int i = start; i < start + n; i++) {
            rotated[j++] = temp[i];
        }
        return rotated;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        /*case 1: int[] arr = {} ; output: Array after 2 rotation: []
        case 2: int[] arr = {1}; output: Array after 2 rotation: [1]
        Time Complexity: O(n)
        Auxiliary Space: O(2n)
        */
        int k = 2;
        System.out.println("Array after " + k + " rotation: " + Arrays.toString(rightRotate(arr, k)));
        int k1 = 3;
        System.out.println("Array after " + k1 + " rotation: " + Arrays.toString(rightRotate(arr, k1)));
    }
}