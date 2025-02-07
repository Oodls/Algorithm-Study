import java.io.*;
import java.util.*;

public class Main {

    /*
    문제
    M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
    
    입력
    첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000) M이상 N이하의 소수가 하나 이상 있는 입력만 주어진다.
    
    출력
    한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.
    */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] arr = new boolean[end+1];
        for (int i = 1; i <= end; i++){
            arr[i] = true;
        }
        arr[1] = false; // 1은 소수가 아니므로 제외
        
        // 에라토스테네스의 체 (2부터 주어진 최대값의 제곱근까지 그 배수를 제외)
        for (int i = 2; i <= Math.sqrt(end); i++) {
            if (arr[i]){
                int point = i;
                for (int j = 1; j <= end / i - 1; j++) {
                    point += i;
                    arr[point]=false;
                }
            }
        }

        // 결과 출력
        for (int i=start; i <= end; i++){
            if (arr[i]){
                bw.write(String.valueOf(i));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
