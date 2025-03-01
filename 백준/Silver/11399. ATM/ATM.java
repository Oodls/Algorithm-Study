import java.io.*;
import java.util.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    
    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int[] arr= new int[n];
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = 0;
        int recent = 0;
        for (int i=0; i<n; i++){
            sum += arr[i] + recent;
            recent = arr[i] + recent;
        }

        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}

/*
예제 입력
5
3 1 4 3 2

예제 출력
32
*/