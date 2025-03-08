import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;

    public static int getC(int n, int r){
        int a;
        int b;
        if (arr[n][r] != -1){
            return arr[n][r];
        }
        if (arr[n-1][r] == -1){
            a = getC(n-1, r);
        } else {
            a = arr[n-1][r];
        }
        if (arr[n-1][r-1] == -1){
            b = getC(n-1, r-1);
        } else {
            b = arr[n-1][r-1];
        }
            
        return a+b;
    }
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        

        for (int i=0; i<=n; i++){
            for (int j=0; j<=n; j++){
                if (j == 0 || j == i){
                    arr[i][j] = 1;
                } 
                else if (j == 1) {
                    arr[i][j] = i;
                } 
                else {
                    arr[i][j] = -1;
                }
            }
        }

        bw.write(String.valueOf(getC(n,r)));
        bw.newLine();
        bw.flush();
    }
}
/*
5C2 = 4C3 + 4C2
C[n][r] = C[n-1][r] + C[n-1][r-1]

Dp배열 초기화
[i][1] = 1
[i][0] = 1
[i][i] = 1
*/