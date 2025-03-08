import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    static final int NUM = 10007;
    
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];

        arr[1] = 1;
        if (n >= 2) {
            arr[2] = 2;
        }

        for (int i = 3; i <= n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % NUM;
        }

        bw.write(String.valueOf(arr[n]));
        bw.newLine();
        bw.flush();
    }
}

/*
2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.



입력
첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

출력
첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
*/