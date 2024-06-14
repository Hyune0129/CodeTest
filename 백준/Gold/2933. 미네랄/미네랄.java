import java.util.*;
import java.io.*;

class Main {
    static class Cluster {
        List<Position> minerals;

        Cluster() {
            minerals = new ArrayList<>();
        }

        public void fall() {
            // System.out.println(range);
            for (Position pos : minerals) {
                isMineral[pos.r][pos.c] = false;
            }
            int range = getRange();
            for (Position pos : minerals) {
                isMineral[pos.r - range][pos.c] = true;
            }
        }

        private int getRange() {
            int range = 0;
            boolean isTouched = false;
            do {
                range++;
                for (Position pos : minerals) {
                    if (pos.r - range < 0 || isMineral[pos.r - range][pos.c]) {
                        isTouched = true;
                        range--;
                        break;
                    }
                }
            } while (!isTouched);
            // System.out.println(range);
            return range;
        }
    }

    static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, R, C;
    static int[] dc = { -1, 1, 0, 0 };
    static int[] dr = { 0, 0, -1, 1 };
    static boolean[][] isMineral;

    public static void main(String[] args) throws FileNotFoundException {
        // System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        isMineral = new boolean[R][C];
        for (int r = R - 1; r >= 0; r--) {
            char[] charArr = sc.next().toCharArray();
            for (int c = 0; c < C; c++) {
                switch (charArr[c]) {
                    case 'x':
                        isMineral[r][c] = true;
                        break;
                }
            }
        }
        N = sc.nextInt();
        boolean toggle = true;
        for (int n = 0; n < N; n++) {
            int h = sc.nextInt() - 1;
            throwStick(h, toggle);
            toggle = !toggle;
            // printMatrix(isMineral);
        }
        printMatrix(isMineral);

        sc.close();
    }

    private static void throwStick(int height, boolean isLeft) {
        int d = isLeft ? 1 : 0;
        int c = isLeft ? 0 : C - 1;
        for (; c >= 0 && c < C; c += dc[d]) {
            if (isMineral[height][c]) { // break mineral
                isMineral[height][c] = false;
                Position pos = findFallCluster();
                if (pos == null) { // not air-clustered
                    return;
                }
                Cluster airedCluster = getAiredCluster(pos);
                airedCluster.fall();
                break;
            }
        }
    }

    private static Position findFallCluster() {
        // 클러스터 찾기
        // 바닥에서 bfs를 실행하여 미네랄인데 방문안한 것 -> 떠 있는 클러스터
        Queue<Position> queue = new LinkedList<>();
        Position pos;
        boolean[][] isVisited = new boolean[R][C];
        for (int c = 0; c < C; c++) {
            if (isMineral[0][c]) {
                queue.add(new Position(0, c));
                isVisited[0][c] = true;
            }
        }
        while (!queue.isEmpty()) {
            pos = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos.r + dr[d];
                int nc = pos.c + dc[d];
                if (nr < 0 || nr >= R || nc >= C || nc < 0) {
                    continue;
                }
                if (isVisited[nr][nc])
                    continue;
                isVisited[nr][nc] = true;

                if (isMineral[nr][nc]) {
                    queue.add(new Position(nr, nc));
                }
            }
        }

        for (int r = 0; r < R; r++) { // 떠있는 클러스터?
            for (int c = 0; c < C; c++) {
                if (isMineral[r][c] && !isVisited[r][c]) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }

    private static Cluster getAiredCluster(Position start) {
        // bfs로 순회하며 떠 있는 클러스터의 각 col의 최소 높이를 기록
        Cluster cluster = new Cluster();
        Queue<Position> queue = new LinkedList<>();
        Position pos;
        cluster.minerals.add(start);
        boolean[][] isVisited = new boolean[R][C];
        isVisited[start.r][start.c] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            pos = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = dr[d] + pos.r;
                int nc = dc[d] + pos.c;
                if (nr >= R || nr < 0 || nc >= C || nc < 0) // boundary check;
                    continue;
                if (isVisited[nr][nc]) {
                    continue;
                }
                isVisited[nr][nc] = true;
                if (isMineral[nr][nc]) {
                    cluster.minerals.add(new Position(nr, nc));
                    queue.add(new Position(nr, nc));
                }
            }
        }
        return cluster;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (int r = matrix.length - 1; r >= 0; r--) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (isMineral[r][c]) {
                    System.out.print('x');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
}