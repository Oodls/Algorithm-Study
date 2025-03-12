import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        isDescending = new boolean[m];
        recur(0);
        bw.flush();        
    }

    static int n;
    static int m;
    static int[] arr;
    static boolean[] isDescending;

    static public void recur(int num) throws IOException{
        if (num == m){
            for(int j=0; j<m; j++){
                bw.write(arr[j] + " ");                                                
            }
            bw.newLine();
            return;
        }
        for (int i=1; i<=n; i++){
            if(num ==0){
                arr[num] = i;
                recur(num+1);
            }
            else {
                if(arr[num-1] > i){
                    continue;
                }
                else {
                    arr[num] = i;
                    recur(num+1);
                }
            }
        }
    }
}
/*
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 M개를 고른 수열
같은 수를 여러 번 골라도 된다.
고른 수열은 비내림차순이어야 한다.
*/

/*
1. num이 m이 되면 출력 후 종료
2. n만큼 반복하며 recur(num) 실행

- 내림차순 여부 boolean 배열 사용
- 순열 배열 int 배열

*/