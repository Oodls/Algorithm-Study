import java.io.*;
import java.util.*;

class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        Queue<long[]> q = new LinkedList<>();
        q.offer(new long[]{A, 1});
        
        while (!q.isEmpty()) {
            long [] cur = q.poll();
            long val = cur[0];
            long cnt = cur[1];

            if ( val == B ) {
                bw.write(String.valueOf(cnt));
                bw.flush();
                return;
            }
            if ( val > B ) {
                continue;
            }

            q.offer(new long[]{val*2, cnt+1});
            q.offer(new long[]{val*10+1, cnt+1});
        }

        bw.write("-1");
        bw.flush();
    }
}