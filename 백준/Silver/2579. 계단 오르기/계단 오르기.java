import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        int[] stairArr = new int[n+1];
        int[] dp = new int[n+1];
        for (int i=1; i<=n; i++){
            int stair = Integer.parseInt(br.readLine());
            stairArr[i] = stair;
        }

        dp[1] = stairArr[1];
        if (n > 1) {
            dp[2] = stairArr[1] + stairArr[2];    
        }
        

        for (int i=3; i<=n; i++){
            dp[i] = Math.max(dp[i-3]+stairArr[i-1]+stairArr[i],dp[i-2]+stairArr[i]);
        }

        bw.write(String.valueOf(dp[n]));
        bw.newLine();
        bw.flush();
    }
}
/*
계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다. 
<그림 1>과 같이 각각의 계단에는 일정한 점수가 쓰여 있는데 계단을 밟으면 그 계단에 쓰여 있는 점수를 얻게 된다.

예를 들어 <그림 2>와 같이 시작점에서부터 
첫 번째, 두 번째, 네 번째, 여섯 번째 계단을 밟아 도착점에 도달하면 총 점수는 10 + 20 + 25 + 20 = 75점이 된다.

계단 오르는 데는 다음과 같은 규칙이 있다.

계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 
즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.

연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.

마지막 도착 계단은 반드시 밟아야 한다.

따라서 첫 번째 계단을 밟고 이어 
두 번째 계단이나, 세 번째 계단으로 오를 수 있다. 
하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 
첫 번째, 두 번째, 세 번째 계단을 연속해서 모두 밟을 수는 없다.

입력
입력의 첫째 줄에 계단의 개수가 주어진다.

둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 
계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.

출력
첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값을 출력한다.

각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 작성하시오.

6
10
20
15
25
10
20
*/

/*
dp를 풀려면 가장 큰 범위의 문제를 작은 범위의 문제로 나눠야 함.
도착점에 도착한다는 가정에서
마지막 계단은 반드시 밟아야 함.
고려해야 하는 경우의 수는 두가지.
두칸을 점프해서 온 경우와 
한칸을 점프해서 온 경우

한칸을 점프해서 온 경우 세칸 연속 방문 조건에 위배 되지 않으려면
dp[n-3] + stair[n-1] + stair[n]
두칸 점프해서 온 경우 
dp[n-2] + stair[n]

그 경우 중 더했을 때 최대 값이 되는 경우를 선택
*/