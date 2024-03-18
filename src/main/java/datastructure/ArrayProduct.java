package datastructure;

public class ArrayProduct {

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] b = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        product(a, b);
    }

    static int[][] product(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        if (a[0].length != b.length) {
            System.out.println("First Array columns should be equal to number of row in Second Array");
            return null;
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    result[i][j] = 0;
                    for(int k = 0; k < b.length; k++)
                        result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
