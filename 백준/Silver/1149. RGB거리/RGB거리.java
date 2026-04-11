import java.io.IOException;
import java.io.*;
import java.util.*;
import java.math.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] input = new int[N][3];
        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[N][3];
        dp[0][0] = input[0][0];
        dp[0][1] = input[0][1];
        dp[0][2] = input[0][2];
        for (int i=1; i<N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+input[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+input[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+input[i][2]; 
        }

        bw.write(String.valueOf(Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2]))));
        bw.flush();
    }
}

/*
dp 사용
*/