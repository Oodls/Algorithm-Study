import java.io.*;
import java.util.*;

public class Main {
    static int[][] adjacencyMatrix;
    static final int MAX_VALUE = 400; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        adjacencyMatrix = new int[n+1][n+1]; // 인접행렬 선언
        for (int i=1; i<=n; i++){ // 인접행렬 값 초기화
            adjacencyMatrix[i][i] = 0;
            for(int j=1; j<=n; j++){
                if (i != j){
                    adjacencyMatrix[i][j] = MAX_VALUE;
                }
            }
        }

        for (int i=0; i<k; i++){ // 엣지 입력값 받기
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());

            adjacencyMatrix[a][b] = 1; // 주어진 엣지에 따라 인접행렬 값 반영
        }

        for (int m=1; m<=n; m++){ // 플로이드 워셜 알고리즘
            for (int s=1; s<=n; s++){
                for (int e=1; e<=n; e++){
                    adjacencyMatrix[s][e] = Math.min(adjacencyMatrix[s][e], adjacencyMatrix[s][m] + adjacencyMatrix[m][e]);
                }
            }
        }

        int s = Integer.parseInt(br.readLine());

        for (int i=0; i<s; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken()); 
            if (a == b){
                bw.write("0");
                bw.newLine();
                break;
            }
            
            if (adjacencyMatrix[a][b] != MAX_VALUE){
                bw.write("-1");
                bw.newLine();
            } 
            else if (adjacencyMatrix[b][a] != MAX_VALUE) {
                bw.write("1");
                bw.newLine();
            }
            else {
                bw.write("0");
                bw.newLine();
            }
        }
        bw.flush();
    }
}

/*
첫 줄에 사건(노드)의 개수 n (400 이하의 자연수), 전후관계(엣지)의 개수 k (50000 이하의 자연수) 주어짐
노드 최대 수가 400이므로 플로이드 워셜 사용 가능

그리고 전후관계 쭉 나오다가

사건의 전후관계를 알고싶은 사건 쌍 수 s개(50000 이하)가 주어짐

우선 가중치는 없으므로 일단 엣지마다 거리는 1로 간주
주어진 엣지를 통해 인접행렬 채우고
플로이드 워셜로 나머지 모든 경로 업데이트

전후관계 확인은
a와 b가 주어졌을때
인접행렬에서 a에서 b로 도달 가능하면 a가 우선
b에서 a로 도달 가능하면 b가 우선
둘다 도달 불가능 하면 유추 불가능
*/