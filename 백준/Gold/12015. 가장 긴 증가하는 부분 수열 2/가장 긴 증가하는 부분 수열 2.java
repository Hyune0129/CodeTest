import java.util.*;
import java.io.*;

// reference : https://www.youtube.com/watch?v=S9oUiVYEq7E
class Main {
    static int[] A, lIndex, rIndex;

    private static int binarySearch(int start, int end, int target) {
        // lIndex find
        int lowBoundary = -1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (A[lIndex[middle]] < target) {
                start = middle + 1;
                lowBoundary = middle;
            } else {
                end = middle - 1;
            }
        }
        return lowBoundary;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        lIndex = new int[n];
        rIndex = new int[n];
        lIndex[0] = 0;
        rIndex[0] = -1;
        int len = 0;
        for (int i = 1; i < n; i++) {
            int index = binarySearch(0, len, A[i]);
            if (index == -1) {
                lIndex[0] = i;
                rIndex[i] = -1;
            } else if (index >= len) {
                rIndex[i] = lIndex[len];
                lIndex[++len] = i;
            } else {
                rIndex[i] = lIndex[index];
                lIndex[index + 1] = i;
            }
        }
        // printArray(lIndex);
        // printArray(rIndex);
        System.out.println(len + 1);
        br.close();
    }

    static void printArray(int[] array) {
        System.out.println();
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}