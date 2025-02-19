import java.io.*;
import java.util.*;

public class Main {
    static int[] rankArr; // Rank 배열 (유니온 파인드 트리 최적화용)
    static int[] unionFindArr; // 유니온파인드 배열
    static ArrayList<Edge> edgeList; // 엣지리스트
    static int result = 0; // 최소신장트리 가중치 합

    static int v;
    static int e;

    static boolean union(int parent, int child, boolean isRankEqual){
        int foundParent = find(parent);
        int foundChild = find(child);
        if (find(parent) == find(child)){ // 사이클이 존재하는 경우
            return false;
        }

        if (isRankEqual){
            unionFindArr[foundChild] = foundParent;
            rankArr[parent] += 1;
        } else {
            unionFindArr[foundChild] = foundParent;
        }
        return true;
    }

    static int find(int a) {
        if (unionFindArr[a] == a){ // 대표노드 조회 시
            return a; // 대표노드값 리턴
        }
        // 찾지 못했을 시
        int found = find(unionFindArr[a]); // 재귀 호출
        
        // 대표노드 조회 완료 후 재귀 호출 특성을 이용한 최신화 작업
        unionFindArr[a] = found;
        return found;
    }

    static class Edge {
        int a;
        int b;
        int w;

        public Edge(int a, int b, int w){
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st1.nextToken());
        e = Integer.parseInt(st1.nextToken());

        rankArr = new int[v+1];
        unionFindArr = new int[v+1];
        for (int i=1; i<=v; i++){ // rank배열과 union-find배열 값 초기화
            rankArr[i] = 0;
            unionFindArr[i] = i;
        }
        
        edgeList = new ArrayList<Edge>(e); // 엣지리스트 선언
        for (int i=0; i<e; i++){ // 엣지리스트 값 채우기
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int w = Integer.parseInt(st2.nextToken());
            edgeList.add(new Edge(a,b,w));
        }

        Collections.sort(edgeList,(a,b) -> { // 엣지리스트 가중치 기준으로 정렬
            if (a.w != b.w) {
                return a.w - b.w;
            } else if(a.a != b.a) {
                return a.a - b.a;
            } else {
                return a.b - b.b;
            }
        });

        for (int i=0; i<e; i++){
            Edge curEdge = edgeList.get(i);
            boolean isUnioned;
            if (rankArr[find(curEdge.a)] > rankArr[find(curEdge.b)]){
                isUnioned = union(curEdge.a,curEdge.b,false);
            } else if (rankArr[find(curEdge.a)] < rankArr[find(curEdge.b)]){
                isUnioned = union(curEdge.b,curEdge.a,false);
            } else {
                isUnioned = union(curEdge.a,curEdge.b,true);
            }

            if (isUnioned){
                result += curEdge.w;
            }
        }
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
    }
}

/*백준 1197 : 최소 신장 트리 (골드 4)
그래프가 주어졌을 때 그 그래프의 최소 스패닝 트리를 구하기

첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
(최소신장트리 알고리즘은 O(ElogE)이므로 1초 제한시간 안에 해결 가능)
다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A,B,C가 주어진다.
A 와 B번 정점이 가중치 C로 연결되어있다는 의미. C는 음수일 수도 있으며, 절댓값이 1000000을 넘지 않는다.

첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다. 
따라서 long타입으로 안써도 될듯.



필요한것.
rank배열
unionfind 배열
edgelist
result (최소신장트리 가중치 합)
*/