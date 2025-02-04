
/*
문제
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

출력
M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
 */

/*
예제 입력
5
4 1 5 2 3
5
1 3 7 9 5

예제 출력
1
1
0
0
1
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int arrlength = Integer.parseInt(br.readLine());
        int[] arr = new int[arrlength];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < arrlength; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int targetLength = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());


        for (int i = 0; i < targetLength; i++) {
            int target = Integer.parseInt(st2.nextToken());
            bw.write(Integer.toString(binarySearch(arr, target)));
            bw.newLine();
        }
        bw.flush();
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return 1;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            }

            if (left == right) {
                if (arr[left] == target) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }
}




