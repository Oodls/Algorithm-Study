import java.io.*;
import java.util.*;

public class Main {
    static Node[] tree;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;
    static Queue<Integer> bfsQueue = new LinkedList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node {
        int parent;
        int depth;

        public Node(int p, int d){
            parent = p;
            depth = d;
        }
    }

    static void lca(int a, int b) throws IOException{
        int aPoint = a;
        int bPoint = b;
        int result = 0;
        while(true){
            if (tree[aPoint].depth > tree[bPoint].depth){ // a가 더 깊은 경우
                aPoint = tree[aPoint].parent;
            } else if (tree[aPoint].depth < tree[bPoint].depth) { // b가 깊은 경우
                bPoint = tree[bPoint].parent;
            } else { // 깊이가 같은 경우
                if (aPoint == bPoint) {
                    bw.write(String.valueOf(aPoint));
                    bw.newLine();
                    break;
                } else {
                    aPoint = tree[aPoint].parent;
                    bPoint = tree[bPoint].parent;
                }
            }
            
        }
    }

    static void bfs (int n) {
        int depth = 1;
        int prev = 0;
        bfsQueue.add(n);
        visited[n] = true;
        tree[n] = new Node(0,1);

        while(!bfsQueue.isEmpty()){
            int prevNode = bfsQueue.poll();
            for (int i=0; i<adjList.get(prevNode).size(); i++){
                if (!visited[adjList.get(prevNode).get(i)]){
                    visited[adjList.get(prevNode).get(i)] = true;
                    int found = adjList.get(prevNode).get(i);
                    bfsQueue.add(found);
                    tree[found] = new Node(prevNode, tree[prevNode].depth+1);
                    int newDepth = tree[prevNode].depth+1;
                    //System.out.println(found + " 탐색: " + "부모= "+ prevNode +"깊이= "+ newDepth);
                }
            }
        }
    } 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        int n = Integer.parseInt(br.readLine()); 
        for (int i=0; i<=n; i++) { // 인접리스트 초기화
            adjList.add(new ArrayList<>());
        }

        tree = new Node[n+1];
        visited = new boolean[n+1]; // BFS용 방문배열 초기화
        
        for (int i=0; i<n-1; i++){ // 인접리스트 완성하기
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        
        bfs(1);

        int numOfQuery = Integer.parseInt(br.readLine());
        for (int i=0; i<numOfQuery; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());

            lca(a,b);
        }

        bw.flush();
        bw.close();
    }
}
/*
첫째 줄에 노드의 개수 N(2 ≤ N ≤ 50,000)개가 주어짐
다음 N-1개의 줄에는 에지가 주어짐
그 다음 줄에는 LCA 질의 M(1 ≤ M ≤ 10,000)개가 주어짐

M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다
*/