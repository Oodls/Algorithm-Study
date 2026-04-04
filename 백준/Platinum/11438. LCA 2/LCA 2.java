import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] lca;
    static int[] depth;
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();
    
    
    static public int getLCA(int a, int b){
        int dif = depth[a] - depth[b];
        int newA = -1;
        int newB = -1;
        if (dif == 0) { // 높이가 같으면
            newA = a;
            newB = b;
        } 
        else { // 아니라면 a가 더 깊다.
            // 우선 a를 b와 높이 맞추기 (aDepth * 2^k > bDepth를 만족하는 최소 K값 찾아서 a의 2^k부모 찾기,
            int k = 0;
            int num = 1;
            newB = b;
            while (true){ // 높이 맞추기
                num *= 2;
                if (num < dif){
                    k++;
                } 
                else if (num == dif){
                    k++;
                    newA = lca[k][a];
                    break;
                }
                else {
                    newA = a;
                    // 하나씩 부모 찾아올라가며 b와 높이 맞추기)
                    for (int i=k; i>=0; i--){
                        if (depth[lca[i][newA]] >= depth[newB]){ // 2^i번째 부모도 b보다 낮으면
                            newA = lca[i][newA];
                        }
                    }
                    break;
                }
            }
        }
        // 높이 맞춘 후에는 꼭대기에서부터 부모 노드의 값이 달라지는 2^k번째 부모 찾기 그게 lca
        
        if (newA == newB){// 만약에 높이 맞춘 직후에 두 값이 같다면 lca
            return newA;
        }
        int size = lca.length - 1;
        
        for (int i=size; i>=0; i--){
            if (lca[i][newA] != lca[i][newB]) { // 2^i번째 조상의 값이 다르면
                newA = lca[i][newA];
                newB = lca[i][newB];
            }
        }
        return lca[0][newA];
    }


    public static void BFS(int start){
        /*
        1. 인접리스트 탐색
            인접리스트 길이만큼 큐에 다 넣기
            큐에 넣은 애들은 visited 리스트 방문처리

            큐에 넣을 때 깊이 체크
        */
        queue.add(start);
        visited[start] = true;
        depth[start] = 1;
        while(!queue.isEmpty()){
            int curNode = queue.poll();
            ArrayList<Integer> curAdj = adj.get(curNode);
            for (int i=0; i<curAdj.size(); i++){
                int adjNode = curAdj.get(i);
                if (!visited[adjNode]){
                    visited[adjNode] = true;
                    depth[adjNode] = depth[curNode] + 1;
                    lca[0][adjNode] = curNode;
                    queue.add(adjNode);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        int num = 1;
        int k = 0;
        for (int i=0; i<n; i++){ // lca 2차원 배열 높이가 되는 최적 k값 찾기
            num *= 2;
            if (num < n){
                k++;
            }
            else {
                break;
            }
        }
        lca = new int[k+1][n+1];
        depth = new int[n+1];
        depth[1] = 1;
        visited = new boolean[n+1];

        adj = new ArrayList<>();
        for (int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }
        
        for (int i=1; i<=n-1; i++){ // 인접리스트 생성
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        BFS(1);
        
        for (int i=1; i<=k; i++){ // dp 개념으로 lca 2차원 배열 채우기
            for (int j=1; j<=n; j++){
                lca[i][j] = lca[i-1][lca[i-1][j]];
            }
        }
        

        int caseNum = Integer.parseInt(br.readLine());
        for (int i=0; i<caseNum; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());

            if (depth[a]-depth[b] > 0){ // 항상 깊은값이 먼저 들어감
                bw.write(String.valueOf(getLCA(a,b)));
                bw.newLine();
            } else {
                bw.write(String.valueOf(getLCA(b,a)));
                bw.newLine();
            }
            
            
        }
        bw.flush();  
    }
}
/*
문제
N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.

두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.

입력
첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 
그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.

출력
M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.

  

lca[k][n] = lca[k-1][lca[k-1][n]]
*/
