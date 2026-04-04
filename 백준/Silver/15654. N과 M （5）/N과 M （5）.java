import java.io.IOException;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N, M;
    static int[] inputNums;
    static int[] sequence;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        inputNums = new int[N];
        visited = new boolean[N];
        sequence = new int[M];

        for (int i=0; i<N; i++) {
            inputNums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputNums);

        backTracking(0);
        System.out.println(sb.toString());
    }

    private static void backTracking(int depth) {
        if (depth == M) {
            for (int val: sequence) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i=0; i<N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = inputNums[i];
                backTracking(depth + 1);
                
                visited[i] = false;
            }
        }
    }
}


/*
    n개의 자연수,
    자연수 m

 첫째 줄에 n과 m이 주어짐

 둘째줄에는 n개의 수가 주어짐

가능한 조합 찾는 방식
1초


n이 8이므로 백트래킹

사전순 정렬을 위해 백트래킹 전에 숫자 배열 정렬 후 진행
*/