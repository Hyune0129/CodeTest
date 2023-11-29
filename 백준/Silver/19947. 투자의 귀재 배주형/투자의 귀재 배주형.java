import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int MAX_Y = 10;
        int[] array = new int[MAX_Y + 1];
        for(int index = 0; index <= MAX_Y; index++) {
            array[index] = 0;
        }
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt(); // 초기비용
        int Y = sc.nextInt(); // 투자 기간
        array[0] = H;

        for(int i = 0; i < MAX_Y; i++) { // i = [0, 9]
            array[i+1] = Math.max(array[i+1], (int)(array[i] * 1.05));
            if(i + 3 <= MAX_Y) {
                array[i + 3] = Math.max(array[i + 3], (int)(array[i] * 1.2));
            }
            if(i + 5 <= MAX_Y) {
                array[i + 5] = Math.max(array[i + 5], (int)(array[i] * 1.35));
            }
        }
        System.out.println(array[Y]);
    }
}