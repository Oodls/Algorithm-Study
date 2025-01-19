/*
문제
방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

출력
첫째 줄에 연결 요소의 개수를 출력한다.

예제 입력
6 5
1 2
2 5
5 1
3 4
4 6

예제 출력
2





예제 입력
6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3

예제 출력
1
 */

import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st1.nextToken());
        int edgeNum = Integer.parseInt(st1.nextToken());

        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(); // 인접리스트 선언

        for (int i = 0; i < nodeNum + 1; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeNum; i++) {
            st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adjacencyList.get(a).add(b);
            adjacencyList.get(b).add(a);
        }

        Solution sol = new Solution();
        sol.solve(adjacencyList, nodeNum, edgeNum);
    }
}

class Solution {
    ArrayList<ArrayList<Integer>> adjacencyList;
    boolean [] visited;
    int count = 0;
    public void solve(ArrayList<ArrayList<Integer>> a, int node, int edge) throws IOException {
        adjacencyList = a; // 인접 리스트 얕은 복사
        visited = new boolean[node+1]; // 방문 리스트 사이즈 초기화



        for (int i = 1; i < node + 1; i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    public void dfs(int visit){
        visited[visit] = true;
        ArrayList<Integer> adj = adjacencyList.get(visit);
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[adj.get(i)]) {
                dfs(adj.get(i));
            }
        }
    }

    public int isAllSearched() {
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return 0;
    }



}
