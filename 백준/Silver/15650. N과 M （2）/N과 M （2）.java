import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        arr = new int[m];
        recur(0);
        bw.flush();
    }

    static boolean[] visited;
    static int[] arr;
    static int n;
    static int m;
    
    static public void recur(int num) throws IOException{
        if (num == m){
            for (int i=0; i<m; i++){
                bw.write(arr[i]+" ");
            }
            bw.newLine();
            return;
        }

        for (int i=1; i<=n; i++){
            if (!visited[i]){
                if (num == 0){ // 초기 시행의 경우
                    visited[i] = true;
                    arr[num] = i;
                    recur(num+1);
                }
                else if (arr[num-1] < i){ // 오름차순 여부 확인
                    visited[i] = true;
                    arr[num] = i;
                    recur(num+1);
                }
                visited[i] = false; // 방문배열 복구
            }            
        }
    }
}
/*
백트래킹
1. num이 M과 같아지면 출력 후 종료
2. visit 여부 확인
3. 오름차순 여부 확인
4. 재귀 종료 후 visit여부와 결과 배열 빼주기
*/