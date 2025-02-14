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
        
        Queue<Integer> nodeList = new LinkedList<Integer>(); // 위상정렬 큐 선언
        
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
                nodeList.add(i); // 큐에 진입차수 0인 노드 삽입하고 시작
            }
        }
        
        for (int i = 0; i < n; i ++){
            int v = nodeList.poll(); // 큐에 노드 하나 뽑음 (진입차수가 0인 노드)
            bw.write(String.valueOf(v) + " ");
            for (int j = 0; j < adjacencyList.get(v).size(); j++){
                int num = adjacencyList.get(v).get(j);
                int temp = lookUpMap.get(num);
                lookUpMap.put(num, temp - 1); // 진입차수 감산
                if (temp - 1 == 0){ // 진입차수가 0이 되면 큐에 삽입
                    nodeList.add(num); 
                }
            }
        }
        bw.flush();
    }
}

/*
위상 정렬 사용
진입 차수 배열을 사용해서 0이 되는 값을 찾기 위해 순회하는건 시간복잡도가 높아 다른 방법 구상

먼저 처음 하려 했던건, 조회용 hashmap과 정렬용 treemap을 활용해서 comparator로 진입차수 값에 따라 정렬하고자 하였는데,
키값으로 정렬하지 않으면 잘 안되는 문제 발생

따라서 우선순위 큐를 활용해서 계속 진입차수 값이 변경될 때마다 우선순위 큐에 넣어 정렬되도록 했지만, 이는 메모리 낭비가 심해 메모리 초과 발생

마지막으로 map에 넣어두던 노드정보에서 진입 차수가 0이 될 때만 큐에 저장, 이를 통해 해결
*/
