import java.util.*;
import java.io.*;

// reference : https://www.youtube.com/watch?v=S9oUiVYEq7E
class Main {
    static int[] rIndex;
    static int[] lIndex;
    static int[] A;
    static int N;

    private static int binarySearch(int start, int end, int target) {
        // return lowerIndex
        int middle;
        int lowBound = -1;
        while (start <= end) {
            middle = (start + end) / 2;
            if (A[lIndex[middle]] < target) {
                lowBound = middle;
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return lowBound;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        lIndex = new int[N];
        rIndex = new int[N];
        lIndex[0] = 0;
        rIndex[0] = -1;
        int len = 0;
        for (int i = 1; i < N; i++) {
            int index = binarySearch(0, len, A[i]);
            lIndex[index + 1] = i;
            if (index == -1) {
                rIndex[i] = -1;
            } else {
                rIndex[i] = lIndex[index];
            }
            if (index == len) {
                len++;
            }
        }
        int[] ans = new int[len + 1];
        int idx = len;
        int ptr = lIndex[len];
        while (ptr != -1) {
            ans[idx--] = A[ptr];
            ptr = rIndex[ptr];
        }
        System.out.println(len + 1);
        // printArray(lIndex);
        // printArray(rIndex);
        printArray(ans);
        br.close();
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        // System.out.println();
    }
}