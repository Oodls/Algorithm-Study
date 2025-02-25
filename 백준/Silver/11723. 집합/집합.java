import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 비트마스킹을 사용하여 집합 S를 표현 (더 효율적인 방법)
        int s = 0;
        
        int m = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < m; i++) {
            String[] operation = br.readLine().split(" ");
            String cmd = operation[0];
            
            switch (cmd) {
                case "add":
                    int x1 = Integer.parseInt(operation[1]);
                    s |= (1 << (x1 - 1));  // x를 S에 추가
                    break;
                    
                case "remove":
                    int x2 = Integer.parseInt(operation[1]);
                    s &= ~(1 << (x2 - 1));  // x를 S에서 제거
                    break;
                    
                case "check":
                    int x3 = Integer.parseInt(operation[1]);
                    // x가 S에 있는지 확인하고 결과 출력
                    if ((s & (1 << (x3 - 1))) != 0) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                    
                case "toggle":
                    int x4 = Integer.parseInt(operation[1]);
                    s ^= (1 << (x4 - 1));  // x가 있으면 제거, 없으면 추가
                    break;
                    
                case "all":
                    s = (1 << 20) - 1;  // S를 {1, 2, ..., 20}으로 설정 (2^20 - 1)
                    break;
                    
                case "empty":
                    s = 0;  // S를 공집합으로 설정
                    break;
            }
        }
        
        System.out.print(sb);
    }
}