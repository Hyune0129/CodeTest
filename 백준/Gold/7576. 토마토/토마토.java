import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static class Cord{ // 좌표 class
        public int x; // 1~n
        public int y; // 1~m
        public int num; // 1~(n+m-1)
        Cord(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int day = 1;
        int M = scanner.nextInt(); // 가로
        int N = scanner.nextInt(); // 세로
        int temp , nx, ny;
        int space = 0; // box의 남아 있는 공간
        ArrayDeque<Cord> cords = new ArrayDeque<>(); // stack
        int[][] box = new int[N+2][M+2]; // box외부를 -1로 만들기 위한 크기 2칸 추가
        int[] xDirections = {-1,1,0,0}; // 동서남북
        int[] yDirections = {0,0,-1,1}; // 동서남북
        for(int i = 0; i<N+2; i++){
            Arrays.fill(box[i],-1); // 모든 값 -1로 초기화
        }
        for(int i = 1; i<= N; i++){
            for(int j = 1; j<=M; j++){
                temp = scanner.nextInt();
                if(temp ==  1) {
                    cords.add(new Cord(j, i, 1));
                }
                else if(temp == 0)
                    space++;
                box[i][j] = temp;
            }
        }
        while(!cords.isEmpty()){
            Cord cord = cords.pollLast();
            for(int i=0; i<4; i++){
                ny = cord.y + yDirections[i];
                nx = cord.x + xDirections[i];
                if(cord.num+1 < box[ny][nx]){ // 상자의 빈칸 or 더 작은 횟수로 가능할때
                    box[ny][nx] = cord.num + 1;
                    cords.addFirst(new Cord(nx, ny, cord.num + 1));
                    day = Math.max(day, cord.num+1);
                }
                else if(box[ny][nx] == 0){
                    box[ny][nx] = cord.num + 1;
                    cords.addFirst(new Cord(nx, ny, cord.num + 1));
                    space--;
                    day = Math.max(day, cord.num+1);
                }
            }
        }
        if(space != 0)
            System.out.println(-1);
        else
            System.out.println(day - 1); // 횟수이므로, max == 일차 + 1
    }
}