import java.io.IOException;

import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[] inputNums;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputNums = new int[N];
        sequence = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            inputNums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(inputNums);

        backTracking(0, 0);
        System.out.println(sb.toString());
    }

    private static void backTracking(int depth, int index) {
        if (depth == M) {
            for (int val: sequence) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        int before = -1;
        for (int i=index; i<N; i++) {
            if (before != inputNums[i]) {
                sequence[depth] = inputNums[i];
                before = inputNums[i];
                backTracking(depth + 1, i);
            }
        }
    }
}


/*

비내림차순이어야 하므로 input 배열을 sort 한 후 백트래킹 진행
중복이 가능하기 때문에 재귀 진행 시 현재 바라보는 index부터 순회 하도록

같은 값이 입력으로 주어지는 경우 같은 수열이 여러번 출력되는 것을 방지하기 위해 
백트래킹 진행 시 이전에 바라본 값과 같은 경우는 재귀 진행하지 않도록

dfs사용

M번째 depth에서 sb에 문자열 병합

백트래킹 종료 후 최종 결과 출력
*/