package datastructure;

/*
     Write an implementation to a method that returns Run Length Encoding String for a given String.
     Note: If count of consecutive character is greater than 2 then only do the length encoding else
     leave the characters as it is.

 Example:
    Input: str = "wwwwaaadexxxxxxywwwkk";
    Output: Compressed String: w4a3dex6yw3kk

 */

public class StringCompression {
    private static String compress(String str) {
        if(str == null || "".equals(str)){
            return str;
        }
        int n = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // Count occurrences of current character
            int count = 1;
            while (i < n - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            if (count > 2) {
                sb.append(str.charAt(i));
                sb.append(count);
            } else {
                while(count > 0){
                    sb.append(str.charAt(i));
                    count --;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "wwwwaaade   xxxxxxywwwkk";
        /*
          case 1: str = null; output: Compressed String: null
          case 2: str = ""; output: Compressed String: ""
          case 3: str = " "; output: Compressed String: " "
          case 4: str = "  "; output: Compressed String: "  "
          case 5: str = "   "; output: Compressed String: " 3"
          case 6: str = "wwwwaaade   xxxxxxywwwkk"; output: Compressed String: "w4a3de 3x6yw3kk"
          Time Complexity: O(n)
          Auxiliary Space: O(1)
         */
        System.out.println("Compressed String: " + compress(str));
    }
}
