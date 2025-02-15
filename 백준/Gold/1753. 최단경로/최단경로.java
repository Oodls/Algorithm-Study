import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 200000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int vCount = Integer.parseInt(st.nextToken());
        int eCount = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Edge>> adjacencyList = new ArrayList<>();
        for (int i=0; i<=vCount; i++){ // 인접리스트 내부 arraylist 초기화
            adjacencyList.add(new ArrayList<>());
        }
        //엣지 입력값 형태 (u, v, w) u: 출발노드 , v: 도착노드 , w: 가중치
        for (int i=0; i<eCount; i++){ // 엣지리스트 읽고, 인접리스트 반영
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st1.nextToken());
            int v = Integer.parseInt(st1.nextToken());
            int w = Integer.parseInt(st1.nextToken());
            Edge edge = new Edge(v, w); // 가중치를 담은 Edge 객체 생성
            adjacencyList.get(u).add(edge); // 출발노드 인접리스트에 Edge 객체 삽입
        }

        boolean[] visited = new boolean[vCount+1]; // 방문배열 선언
        int[] minCostWay = new int[vCount+1]; // 최단경로 저장용 배열
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> { //comparator 선언
            if (a.weight != b.weight){
                return a.weight - b.weight;   
            } else {
                return a.node - b.node;
            }
            
        }); // 상수 시간 최소값 조회용 우선순위 큐
        for (int i=1; i<=vCount; i++){ 
            if (i == start){
                pq.offer(new Edge(i, 0));
                minCostWay[i] = 0;
            } else {
                pq.offer(new Edge(i, MAX_VALUE)); // 초기 값 무한대 설정
                minCostWay[i] = MAX_VALUE;
            }
            
        }

        // Pq가 빌때까지 반복, 최소값 뽑으면서 업데이트. 만약 방문배열에 체크되어있다면 스킵, 방문배열 업데이트 
        while(!pq.isEmpty()){
            Edge curEdge = pq.poll();
            int curNode = curEdge.node;
            int curWeight = curEdge.weight;
            if (visited[curNode]) {
                continue;
            }
            for (Edge edge : adjacencyList.get(curNode)){ // 현재 선택 노드의 연결 노드 탐색
                if (minCostWay[edge.node] > curWeight + edge.weight){
                    // 만약 연결노드 값이 선택노드 값 + 연결 가중치보다 크면 업데이트
                    int newWeight = curWeight + edge.weight;
                    minCostWay[edge.node] = newWeight;
                    pq.offer(new Edge(edge.node, newWeight));
                }
            }
            
        }
        
        // 끝까지 초기값이라면 INF 출력하도록, 
        for (int i = 1; i <= vCount; i++){
            if (minCostWay[i] != MAX_VALUE){
                bw.write(String.valueOf(minCostWay[i]));
                bw.newLine();
            } else {
                bw.write("INF");
                bw.newLine();
            }
        }
        bw.flush();
    }
}
class Edge {
    int node;
    int weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}




/*
최소값을 가진 노드 조회용으로 PriorityQueue 를 사용하여 
단순 배열 사용할때는 노드 전체 순회 후 최소값 가진 노드의 연결 노드를 찾아 연산하는 과정에서
최소값 노드를 O(1)으로 찾을 수 있게 하여
시간 복잡도를 O(V^2)에서 O(E logV) 로 단축했다.

또한 처음엔 Integer.MAX_VALUE로 초기 무한대 값으로 설정했지만
if (minCostWay[edge.node] > curWeight + edge.weight) 과정에서 
오버 플로우가 발생해 음수값으로 변환되어 비교 연산에 문제가 생긴다.

따라서 논리적으로 가능한 노드 간 최단거리 최대값 (edgeweight 최대값 * (노드 최대 개수 - 1)) 보다 10 큰
10 * 19999 + 10  = 200000를 MAX_VALUE로 설정했다.
*/
