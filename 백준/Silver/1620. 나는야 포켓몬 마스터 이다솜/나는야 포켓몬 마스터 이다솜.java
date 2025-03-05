import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // N: 포켓몬 개수, M: 문제 개수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 포켓몬 이름으로 번호를 찾기 위한 맵
        Map<String, Integer> pokemonNameToNumber = new HashMap<>();
        
        // 포켓몬 번호로 이름을 찾기 위한 맵
        Map<Integer, String> pokemonNumberToName = new HashMap<>();
        
        // N개의 포켓몬 이름 입력 받기
        for (int i = 1; i <= N; i++) {
            String pokemonName = br.readLine().trim();
            pokemonNameToNumber.put(pokemonName, i);
            pokemonNumberToName.put(i, pokemonName);
        }
        
        // 결과 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        // M개의 문제 풀기
        for (int i = 0; i < M; i++) {
            String query = br.readLine().trim();
            
            // 숫자로 들어온 경우 (포켓몬 이름 출력)
            try {
                int number = Integer.parseInt(query);
                sb.append(pokemonNumberToName.get(number)).append("\n");
            } 
            // 문자로 들어온 경우 (포켓몬 번호 출력)
            catch (NumberFormatException e) {
                sb.append(pokemonNameToNumber.get(query)).append("\n");
            }
        }
        
        // 최종 결과 출력
        System.out.print(sb.toString());
    }
}