import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[] input;
    static int[] process;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        input = new int[N];
        process = new int[M];
        visited = new boolean[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(input);
        backTracking(0);
        bw.flush();
    }

    private static void backTracking(int depth) throws Exception {
        if (depth == M) {
            for (int i=0; i<M; i++) {
                bw.write(String.valueOf(process[i])+" ");
            }
            bw.newLine();
            return;
        }
        int prev = -1;
        for(int i=0; i<N; i++) {
            if (!visited[i] && prev != input[i]) {
                visited[i] = true;
                prev = input[i];
                process[depth] = input[i];
                backTracking(depth+1);
                visited[i] = false;
            }
        }   
    } 
}
