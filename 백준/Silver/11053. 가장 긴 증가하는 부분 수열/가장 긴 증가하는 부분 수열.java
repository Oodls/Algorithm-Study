import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        dp[0] = 1;
        int max = 0;
        for (int i=0; i<N; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++) {
                if(input[i] > input[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(dp[i], max);
        }

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
