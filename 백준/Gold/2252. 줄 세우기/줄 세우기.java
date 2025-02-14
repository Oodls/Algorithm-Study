import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> lookUpMap = new HashMap<Integer, Integer>(); // O(1) 조회용
        
        Queue<Integer> nodeList = new LinkedList<Integer>();
        
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++){ // 인접리스트 초기화
            adjacencyList.add(new ArrayList<>());
            if (i > 0) {
                lookUpMap.put(i, 0);
            }
        }

        for (int i = 0; i < m; i++){ // 인접리스트 완성, 진입차수 map 완성
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int temp = lookUpMap.get(b);

            adjacencyList.get(a).add(b);
            lookUpMap.put(b, temp+1); // 진입차수 가산
        }

        for (int i = 1; i <= n; i ++){
            if (lookUpMap.get(i) == 0){
                nodeList.add(i); // 우선순위 큐에 초기 노드 삽입
            }
        }
        
        for (int i = 0; i < n; i ++){
            int v = nodeList.poll();
            bw.write(String.valueOf(v) + " ");
            for (int j = 0; j < adjacencyList.get(v).size(); j++){
                int num = adjacencyList.get(v).get(j);
                int temp = lookUpMap.get(num);
                lookUpMap.put(num, temp - 1);
                if (temp - 1 == 0){
                    nodeList.add(num);
                }
            }
        }
        bw.flush();
    }
}

class Node {
    int node;
    int count;

    public Node(int node, int count) {
        this.node = node;
        this.count = count;
    }
}
