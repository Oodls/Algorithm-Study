import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Long n = Long.parseLong(br.readLine());
        int sqrt = (int)Math.sqrt(n);
        Long result = n;
        Long temp = n;
        
        for (long i = 2; i <= sqrt; i++) {
            if (temp % i == 0) {  // i가 소인수인 경우
                result = result - (result / i);  // 오일러 피 함수 계산
                while (temp % i == 0) {  // i로 나눌 수 있는 만큼 나누기
                    temp /= i;
                }
            }
        }
        
        if (temp > 1) {  // sqrt보다 큰 소인수가 남아있는 경우
            result = result - (result / temp);
        }
        
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
    }
}