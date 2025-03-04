import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());  // 저장된 사이트 주소의 수
        int M = Integer.parseInt(st.nextToken());  // 비밀번호를 찾으려는 사이트 주소의 수
        
        // 사이트 주소와 비밀번호를 저장할 HashMap
        HashMap<String, String> passwords = new HashMap<>();
        
        // N개의 사이트 주소와 비밀번호 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String password = st.nextToken();
            passwords.put(site, password);
        }
        
        // M개의 사이트 주소에 대한 비밀번호 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String site = br.readLine();
            sb.append(passwords.get(site)).append("\n");
        }
        
        System.out.print(sb.toString());
        br.close();
    }
}