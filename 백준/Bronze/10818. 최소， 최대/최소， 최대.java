
/*
시간 제한	메모리 제한
1 초	    256 MB

문제
N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에는 N개의 정수를 공백으로 구분해서 주어진다. 모든 정수는 -1,000,000보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.

출력
첫째 줄에 주어진 정수 N개의 최솟값과 최댓값을 공백으로 구분해 출력한다.

예제 입력
5
20 10 35 30 7

예제 출력
7 35
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Solution sol = new Solution();
        sol.solve(list);
    }
}

class Solution {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solve(ArrayList<Integer> list) throws IOException {
        list.sort(Integer::compareTo);
        bw.write(String.valueOf(list.get(0)) + " ");
        bw.write(String.valueOf(list.get(list.size() - 1)));
        bw.flush();
        bw.close();
    }
}