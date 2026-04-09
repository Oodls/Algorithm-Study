import java.io.*;
import java.util.*;

class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int count = 1;
        boolean flag = true;

        while (A != B) {
            count = count + 1;
            if (A > B){
                flag = false;
                break;
            } else if (B % 10 == 1) {
                B /= 10;
                continue;
            } else if (B % 2 == 0) {
                B /= 2;
                continue;
            } else {
                flag = false;
                break;
            }
        }

        if (flag) {
            bw.write(String.valueOf(count));   
        } else {
            bw.write("-1");
        }
        bw.flush();
    }
}