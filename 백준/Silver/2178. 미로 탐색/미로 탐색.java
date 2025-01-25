
/*
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 */

/*
4 6
101111
101010
101011
111011

15
 */


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Coordinate, ArrayList<Coordinate>> adj = new HashMap<Coordinate, ArrayList<Coordinate>>();
        Map<Coordinate, Boolean> visited = new HashMap<Coordinate, Boolean>();
        Queue<Coordinate> q = new LinkedList<Coordinate>();
        int[][] plane = new int[N+2][M+2]; // 인접 리스트 구하는 중 outofbound에러 방지용 padding

        Coordinate[][] corArr = new Coordinate[N+1][M+1]; // 좌표 객체 재활용을 위한 배열
        Map<Coordinate, Integer> distance = new HashMap<Coordinate, Integer>();

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            String[] lines = line.split("");
            for (int j = 1; j <= M; j++) {
                plane[i][j] = Integer.parseInt(lines[j-1]);
                corArr[i][j] = new Coordinate(j, i);
                visited.put(corArr[i][j], false);
            }
        }

        distance.put(corArr[1][1], 1);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                adj.put(corArr[i][j], new ArrayList<Coordinate>());
                for (int k = 0; k < 4; k++) {
                    switch (k) {
                        case 0:
                            if (plane[i-1][j] == 1) {
                                adj.get(corArr[i][j]).add(corArr[i-1][j]);
                            }
                            break;
                        case 1:
                            if (plane[i][j-1] == 1) {
                                adj.get(corArr[i][j]).add(corArr[i][j-1]);
                            }
                            break;
                        case 2:
                            if (plane[i][j+1] == 1) {
                                adj.get(corArr[i][j]).add(corArr[i][j+1]);
                            }
                            break;
                        case 3:
                            if (plane[i+1][j] == 1) {
                                adj.get(corArr[i][j]).add(corArr[i+1][j]);
                            }
                            break;
                    }
                }
            }
        }

        q.add(corArr[1][1]);
        visited.put(corArr[1][1], true);
        while (!q.isEmpty()) {
            Coordinate currentNode = q.poll();
            ArrayList<Coordinate> adjList =  adj.get(currentNode);

            if (adjList != null) {
                for (Coordinate c : adjList) {
                    if (!visited.get(c)) {
                        distance.put(c, distance.get(currentNode) + 1);
                        q.add(c);
                        visited.put(c, true);
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(distance.get(corArr[N][M]) + "\n");
        bw.flush();
        bw.close();
    }
}

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinate c) {
        return this.x == c.x && this.y == c.y;
    }
}
