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



/*
생각했던 흐름

우선 입력값이 1조까지 주어지기 때문에
배열을 만들어서 처리하는것은 불가능.

그래서 처음 생각한 방법은 주어진 수는 1조이므로 그의 제곱근 1000000(백만) 까지 불린 배열을 만들고
에라토스테네스의 체로 1000000까지의 소수를 모두 구한다. 그 소수들 중 n의 약수인 경우 소인수를 오일러피를 사용해서 서로소 개수를 구했다.

그러나

1000000보다 큰 소인수가 하나 남아있을 수 있어, 그부분을 고려하지 못했다.
그래서 제곱근보다 큰 소인수가 남아있는 경우를 처리.

그러나 그냥 소인수 분해 사용하는게 제일 시간적, 공간적으로 이득이다
*/
