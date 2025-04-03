import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class contest {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of test cases

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            Map<Integer, Integer> map = new HashMap<>();

            for (int i : arr) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }

            if (map.size() > 2) {
                System.out.println("No");
                continue; // Instead of break, move to next test case
            }

            if (map.size() == 1) {
                System.out.println("Yes");
                continue;
            }

            int[] ar = new int[2];
            int k = 0;
            for (int value : map.values()) { // Corrected from `map.getValues()`
                ar[k++] = value;
            }

            if (ar[0] == ar[1] || Math.abs(ar[0] - ar[1]) == 1) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        sc.close();
    }
}
