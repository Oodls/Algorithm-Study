import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        
        int[] dp = new int[n+1];
        
        dp[1] = 0;
        
        for(int i=2; i<=n; i++){
            if (i % 6 == 0){
                dp[i] = Math.min(dp[i-1]+1, Math.min(dp[i/2]+1, dp[i/3]+1));
            }
            else if (i % 3 == 0){
                dp[i] = Math.min(dp[i-1]+1, dp[i/3]+1);
            } 
            else if (i % 2 == 0){
                dp[i] = Math.min(dp[i-1]+1, dp[i/2]+1);
            }
            else {
                dp[i] = dp[i-1]+1;
            }
        } 
        
        bw.write(String.valueOf(dp[n]));
        bw.newLine();
        bw.flush();
    }
}
/*
수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

입력
첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.

출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

10의 경우 3 출력
2의 경우 1 출력

1 = 0  
2 = 1  (2/1)
3 = 1  (3/1)
4 = 2  (4/2/2)
5 = 3  ((5-1)/2/2)
6 = 2  ((6/3/2))
7 = 3  ((7-1)/3/2)
8 = 3  (8/2/2/2)
9 = 2  (9/3/3)
10 = 3 ((10-1)/3/3)
*/

/*
1) 6으로 나누어 떨어질 때
    dp[n] = Math.min(dp[n-1]+1, dp[n/2]+1, dp[n/3]+1)
2) 3으로만 나누어 떨어질 때
    dp[n] = Math.min(dp[n-1]+1, dp[n/3]+1)
3) 2로만 나누어 떨어질 때 
    dp[n] = Math.min(dp[n-1]+1, dp[n/2]+1)
4) 3과 2 그 어떤 것으로도 나누어 떨어지지 않을 때
    dp[n] = dp[n-1] + 1
*/

// dp[n] = Math.max(dp[n-1])