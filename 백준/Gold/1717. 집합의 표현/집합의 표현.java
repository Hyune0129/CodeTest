// union Find
// reference : https://blog.naver.com/ndb796/221230967614
import java.util.*;
import java.io.*;

class Main{
    static int[] union;
    static int n, m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        union = new int[n+1];
        int temp,a,b;
        for(int i=1; i<=n; i++) {
            union[i] = i;
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            temp = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            switch (temp) {
                case 0:
                    unionParent(a, b);
                    break;
                case 1:
                    if(findUnion(a, b)) {
                        bw.write("YES" + "\n");
                    } else {
                        bw.write("NO" + "\n");
                    }
                    break;
            }
        }
        // printArray(union);
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getParent(int num) {
        if(num == union[num])
            return num;
        int parent = getParent(union[num]);
        union[num] = parent;
        return parent;
    }

    private static void unionParent(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);
        if(aParent < bParent) union[bParent] = aParent;
        else union[aParent] = bParent;
    }

    private static boolean findUnion(int a, int b) {
        return getParent(a) == getParent(b);
    }

    private static void printArray(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}