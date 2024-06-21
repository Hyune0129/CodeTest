import java.io.*;

class Main {
    static int k;
    static String[] nums;
    // bitmask dp matrix [bit][mod] = number of case
    static long[][] dp;
    static int[] mods;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nums = new String[n];
        mods = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = br.readLine();
        }
        k = Integer.parseInt(br.readLine());
        dp = new long[1 << n][k];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            mods[i] = getModByString(nums[i]);
        }
        for (int i = 0; i < (1 << n); i++) { // 사용한 집합 bit
            for (int j = 0; j < k; j++) { // 나머지
                for (int l = 0; l < n; l++) { // l번째 집합 사용
                    if ((i & (1 << l)) != 0) { // visited
                        continue;
                    }
                    int mod = getMod(j, l);
                    dp[i | (1 << l)][mod] += dp[i][j];
                }
            }
        }

        long p = dp[(1 << n) - 1][0];
        long q = 0;
        for (int i = 0; i < k; i++) {
            q += dp[(1 << n) - 1][i];
        }
        long g = gcd(p, q);
        if (p == 0) {
            System.out.println("0/1");
        } else {
            System.out.println((p / g) + "/" + (q / g)); // p
        }
        br.close();
    }

    private static int getMod(int num, int index) {
        // num의 뒤에 집합[index]의 수를 합쳐 mod
        int ans = num;
        int i = nums[index].length();
        while (i >= 5) {
            ans *= 100000;
            ans %= k;
            i -= 5;
        }
        for (; i > 0; i--) {
            ans *= 10;
            ans %= k;
        }
        return (ans + mods[index]) % k;
    }

    private static int getModByString(String str) {
        // num뒤에 str붙이는 순열 mod
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            ans *= 10;
            ans += str.charAt(i) - '0';
            ans %= k;
        }
        return ans;
    }

    private static long gcd(long a, long b) { // 유클리드
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

}