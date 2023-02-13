import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long sum = 0;
        int[] dice = new int[6]; // 0-5 / 2-3 / 1-4
        for(int i =0; i<dice.length; i++){
            dice[i] = scanner.nextInt();
        }
        if(N == 1) // 최소 => 주사위의 합 - 주사위 면 중 최댓값
        {
            int max = 0;
            for(int i = 0; i<dice.length; i++){
                if(max < dice[i])
                    max = dice[i];
                sum += dice[i];
            }
            sum -= max;
            System.out.println(sum);
        }
        else { // N이 2 이상
            int[] minPair = new int[3]; // 페어(마주보는 면) 중 작은 것
            minPair[0] = Math.min(dice[0], dice[5]);
            minPair[1] = Math.min(dice[2], dice[3]);
            minPair[2] = Math.min(dice[1], dice[4]);
            Arrays.sort(minPair); // 작은 면들 중에서도 오름차순으로
            sum += (minPair[0] + minPair[1] + minPair[2]) * 4; // 윗부분 꼭짓점
            sum += (minPair[0] + minPair[1]) * ((2*N)-3) * 4; // 윗부분 모서리 + 옆부분 모서리
            sum += minPair[0] * ((long)Math.pow((N-2),2)+(N-2)) * 4; // 옆부분 나머지
            sum += minPair[0] * ((long)Math.pow((N-2),2));             // 윗부분 나머지
            System.out.println(sum);
        }
    }
}