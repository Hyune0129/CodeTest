import java.util.Scanner;
public class Main {
    static boolean check(String N, int[][] checkList){
        int num,index;
        index = 0;
        for(int i =0; i<N.length(); i++){
            num =  N.charAt(i)-'0';
            index = checkList[num][index];
            if(index == -1) return false;
        }
        return index == 0 || index == 4 || index == 5;
    }
    public static void main(String[] args) {
        int[][] checkList = {{7,2,3,3,7,6,3,-1},
                            {1,-1,-1,4,5,5,0,0}}; // -1 = False
        Scanner scanner = new Scanner(System.in);
        String n;
        int T = scanner.nextInt();
        for(int i = 0; i<T; i++){
            n = scanner.next();
            if(check(n, checkList)){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
    }
}