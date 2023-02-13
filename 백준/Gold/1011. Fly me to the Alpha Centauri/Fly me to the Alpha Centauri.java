import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        long distance;
        long[] x = new long[T];
        long[] y = new long[T];
        long count, j, k;
        for(int i = 0; i<T; i++){
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        for(int i = 0; i<T; i++){
            distance = y[i] - x[i];
            count = 0;
            j = 0;
            k = 0;
            while(true)
            {
                //00 10 11 31 33 63 66 (10 6) ...;
                if(distance <= (j<<1)){
                    break;
                }
                else {
                    count += 2;
                    k++;
                    j += k;
                }
            }
            if(distance <= ((j<<1)-k)){
                count--;
            }
            System.out.println(count);
        }
    }
}