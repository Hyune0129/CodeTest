import java.util.Scanner;

public class Main {
    static long clearX(int digit){ // 해당 번째 수를 제외한 나머지를 초기화할 수 있게 해당 번째수의 초기화값을 생성
                                    // ex : digit = 2 -> return 10 , digit = 3 -> return 210
        if(digit == 0){
            return 0;
        }
        return ((digit - 1) * (long)Math.pow(10, digit-1)) + clearX(digit - 1);
    }
    static int checkX(int digit, long maxNum, long X){ // 감소하는 수가 맞는지 check. 감소하는수라면 -1 return
                                                        // 아닐 시에는 해당 digit return
        if(digit == 0)
            return -1;
        long digitNum = X / (long)(Math.pow(10,digit - 1));
        if(digitNum >=maxNum)
            return digit;
        return checkX(digit-1, digitNum, X - (digitNum * (int)Math.pow(10,digit - 1)));
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int index = 0; // index번째 감소하는 수
        long X = 1;  // 해당 초기값
        int temp;
        if(N != 0) { // 0일시 따로 처리
            for (int digit = 1; digit < 11; digit++) { // 9876543210 --> digit수가 최대 10
                while (X <= (Math.pow(10, digit) - 1)) {
                    temp = checkX(digit, 10, X);
                    if (temp == -1) {
                        index++;
                        if (N == index) break;
                        X++;
                    } else { // 초기화                             ex 330
                        X -= (X % (int) Math.pow(10, temp));//      300
                        X += clearX(temp);                  //      310
                        X += Math.pow(10, temp);            //      410
                    }
                }
                if (index == N) {
                    break;
                }
            }
            if (index == N) {
                System.out.println(X);
            } else
                System.out.println(-1);
        }
        else
            System.out.println(0);
    }
}