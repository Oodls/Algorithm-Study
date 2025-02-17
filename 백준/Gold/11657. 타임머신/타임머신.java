import java.io.*;
import java.util.*;

public class Main {
    static long[] result;
    static ArrayList<Edge> edgeList;
    static final long MAX_VALUE = 5000000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 도시 수
        int m = Integer.parseInt(st.nextToken()); // 버스 노선(엣지) 수
        result = new long[n+1];
        result[1] = 0;
        for (int i=2; i<=n; i++){ // 최단경로 배열 초기화
            result[i] = MAX_VALUE;
        }
        edgeList = new ArrayList<>();
        for (int i=0; i<m; i++){ // 엣지리스트 구현
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st1.nextToken()); // 시작 노드
            int e = Integer.parseInt(st1.nextToken()); // 도착 노드
            int w = Integer.parseInt(st1.nextToken()); // 가중치
            edgeList.add(new Edge(s,e,w));
        }
        
        for (int i=0; i<n-1; i++){ // 벨만포드 알고리즘
            for (int j=0; j<m; j++){
                Edge edge = edgeList.get(j);
                if (result[edge.start] != MAX_VALUE &&
                    result[edge.end] > result[edge.start] + edge.weight)
                {
                    result[edge.end] = result[edge.start] + edge.weight;
                }
            }
        }

        for (int i=0; i<m; i++){ // 마지막 N번째의 순회 함으로 음의 사이클 판별
            Edge edge = edgeList.get(i);
            
            if (result[edge.start] != MAX_VALUE &&
                result[edge.end] > result[edge.start] + edge.weight) 
                // N개의 엣지를 사용한 최단경로가 발견됨 = 음의 사이클 존재
            {
                bw.write("-1");
                bw.newLine();
                bw.flush();
                return;
            }
        }
        for (int i=2; i<=n; i++){
            if (result[i] != MAX_VALUE){
                bw.write(String.valueOf(result[i]));
                bw.newLine();
            } else {
                bw.write("-1");
                bw.newLine();
            }
            
        }
        bw.flush();
        
    }
}
class Edge {
    int start;
    int end;
    int weight;

    public Edge(int s, int e, int w){
        start = s;
        end = e;
        weight = w;
    }
}

/* 문제 정리
N개의 도시 
M개의 버스 (A(시작도시), B(도착도시), C(시간)) 단 시간이 음수인 경우가 있다.

첫째 줄에 도시 개수 N (<=500), 버스 개수 M(<=6000) 이 주어진다.
그 이후 M개의 줄로 버스 노선 정보가 쭉 주어짐
단 C는 -10,000 <= C <= 10,000

가질수 있는 최대 경로값은
499 * 10,000 = 4,990,000
무한대 값은 5,000,000 으로 설정
*/

/*
놓친점 : 음의 사이클이 있는 경우 판정은 마지막 출력으로 -1이 나오겠지만,
계속 비교 연산으로 작은 값을 넣기 때문에 최소 값으로 500 * 6000 * -10000 = -30억의 수가 나오게 됨
따라서 언더플로우가 발생한다.
*/