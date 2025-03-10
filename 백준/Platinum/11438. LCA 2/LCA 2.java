import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] lca;
    static int[] depth;
    static List<Integer>[] adj;
    static int N, LOG;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // log2(N) 값 계산
        LOG = (int) (Math.log(N) / Math.log(2)) + 1;

        // 초기화
        lca = new int[LOG][N + 1];
        depth = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 트리 입력 받기
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        // 트리의 깊이 및 1번째 부모 노드 설정 (BFS)
        bfs(1);

        // DP 테이블 채우기 (희소 테이블 생성)
        for (int i = 1; i < LOG; i++) {
            for (int j = 1; j <= N; j++) {
                if (lca[i - 1][j] != 0) {
                    lca[i][j] = lca[i - 1][lca[i - 1][j]];
                }
            }
        }

        // 쿼리 처리
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(getLCA(a, b)) + "\n");
        }
        bw.flush();
    }

    // BFS를 이용하여 깊이 설정 및 1번째 부모 저장
    static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        depth[root] = 1; // 루트 깊이는 1

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adj[cur]) {
                if (depth[next] == 0) { // 방문하지 않은 경우만
                    depth[next] = depth[cur] + 1;
                    lca[0][next] = cur; // 첫 번째 부모 저장
                    queue.add(next);
                }
            }
        }
    }

    // LCA 구하기
    static int getLCA(int a, int b) {
        // 높이 맞추기 (항상 a가 더 깊은 노드가 되도록)
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0) {
                a = lca[i][a];
            }
        }

        // LCA 찾기
        if (a == b) return a; // 같은 경우 그대로 반환

        for (int i = LOG - 1; i >= 0; i--) {
            if (lca[i][a] != 0 && lca[i][a] != lca[i][b]) {
                a = lca[i][a];
                b = lca[i][b];
            }
        }

        return lca[0][a]; // LCA 반환
    }
}
