import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visitArr;
    static int[] evenArr;
    static Queue<Integer> queue;
    static boolean isEven;
    
    static ArrayList<ArrayList<Integer>> adjacencyList;
    
    static String checkGraph(){
        visitArr = new boolean[adjacencyList.size()];
        evenArr = new int[adjacencyList.size()];
        isEven = false;
        queue = new LinkedList<Integer>(); 
        for (int i=1; i<visitArr.length; i++) { // 방문배열 크기만큼 확인
            if (isEven){
                break;
            }
            else if (!visitArr[i]){ // 사이클이 없고, 방문 배열에 비어있는게 있으면 그걸로 bfs 시작
                queue.add(i);
                evenArr[1] = 1;
                visitArr[i] = true;
                bfs(i);
            }
        }
        
        return isEven? "NO":"YES";
    }

    static void bfs(int now) {
        while(queue.peek() != null){ // queue가 빌 때까지
            for (int i = 0; i < adjacencyList.get(now).size(); i++){
                int vertex = adjacencyList.get(now).get(i);
                if (!visitArr[vertex]){
                    evenArr[vertex] = (evenArr[now] + 1) % 2;
                    visitArr[vertex] = true;
                    queue.add(vertex);
                } else {
                    if (evenArr[now] == evenArr[vertex]){
                        isEven = true;
                        return;
                    }
                }
            }
            queue.poll();
            if (queue.peek() == null) {
                break;
            }
            now = queue.peek();
        } 
    }


    public static void main(String[] args) throws IOException{
        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        
        for (int i=0; i<k; i++){ // 테스트 케이스 수 만큼 반복
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st1.nextToken());
            int e = Integer.parseInt(st1.nextToken());
            adjacencyList = new ArrayList<>(v+1);
            for (int l=0; l<=v; l++){ // ArrayList 내부 인접리스트 초기화 선언
                adjacencyList.add(new ArrayList<Integer>());
            }
            
            for(int j=0; j<e; j++){ // 인접리스트 값 채우기
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st2.nextToken());
                int b = Integer.parseInt(st2.nextToken());
                adjacencyList.get(a).add(b); 
                adjacencyList.get(b).add(a);
            }
            bw.write(checkGraph()); // 테스트 케이스 결과 출력
            bw.newLine(); 
        }
        bw.flush();
        
    }
}

/*
이분 그래프 판별은 사이클이 있냐 없냐의 문제
서로 연결된 노드는 각기 다른 집합에 속해야 하는데, 이는 사이클이 없어야 함을 의미한다. (트리형태 이어야함)
사이클 판별인 유니온 파인드를 사용하거나 
집합 배열 사용 하면서 BFS or DFS 시 이전 방문 노드와 방문 노드의 집합 비교, 같으면 사이클 판정

고려해야 할 점은 
그래프가 분리되어 있는 경우
*/ 
