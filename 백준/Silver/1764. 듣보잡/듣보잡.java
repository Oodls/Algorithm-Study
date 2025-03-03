import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람의 수
        
        Set<String> notHeard = new HashSet<>();
        List<String> result = new ArrayList<>();
        
        // 듣도 못한 사람 입력
        for (int i = 0; i < N; i++) {
            notHeard.add(br.readLine());
        }
        
        // 보도 못한 사람 입력 및 중복 확인
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (notHeard.contains(name)) {
                result.add(name);
            }
        }
        
        // 사전순 정렬
        Collections.sort(result);
        
        // 결과 출력
        System.out.println(result.size());
        for (String name : result) {
            System.out.println(name);
        }
        
        br.close();
    }
}