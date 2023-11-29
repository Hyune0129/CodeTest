import java.util.Scanner;

public class Main {

    static boolean solve(int N, int M, int[] sizes, int x){
        // 강의를 x 이하의 길이들로 가능할 경우 true 리턴
        int count = 0;
        int index = 0;
        int sum;
        while(count < M){
            sum = 0;
            while (sum + sizes[index] <= x) {
                sum += sizes[index++];
                if (index == N) {
                    return true;
                }
            }
            count++;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] sizes = new int[N];
        for(int i = 0 ; i < N; i++) {
            sizes[i] = sc.nextInt();
        }
        int start = 1;
        int end = 100000 * 10000;
        int mid = 0;
        while(start < end) {
            mid = (start + end) / 2;
            if(solve(N, M, sizes, mid)){ // 가능하니 더 최소로?
                end = mid; // 지금 가능한 최대의 수
            } else { // 불가능하니 더 높은값 찾기
                start = mid + 1;
            }
        }
        System.out.println(end);
    }
}