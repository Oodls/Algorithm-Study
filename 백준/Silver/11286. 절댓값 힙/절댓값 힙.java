

import java.io.*;
import java.util.PriorityQueue;

/* 문제
절댓값 힙은 다음과 같은 연산을 지원하는 자료구조이다.

배열에 정수 x (x ≠ 0)를 넣는다.
배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
프로그램은 처음에 비어있는 배열에서 시작하게 된다.
 */
/* 입력
첫째 줄에 연산의 개수 N(1≤N≤100,000)이 주어진다.
다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다.
만약 x가 0이 아니라면 배열에 x라는 값을 넣는(추가하는) 연산이고,
x가 0이라면 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우이다.
입력되는 정수는 -231보다 크고, 231보다 작다.
 */
// 출력
// 입력에서 0이 주어진 회수만큼 답을 출력한다. 만약 배열이 비어 있는 경우인데 절댓값이 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.

/*
예시 입력
18
1
-1
0
0
0
1
1
-1
-1
2
-2
0
0
0
0
0
0
0
 */

/*
예시 출력
-1
1
0
-1
-1
1
1
-2
2
0
 */



class Solution {
    public void solve() throws IOException {

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);

            if (absA == absB){  // 절대값이 같은 경우
                return a-b; // 원래 값으로 비교 a가 크면 양수 return 
                // b가 크면 음수 리턴, 양수라면 a가 더 크므로 b의 뒤에 있어야 함을 의미
            }
            return absA - absB; // 절대값으로 비교 a의 절대값이 크면 양수리턴, 작으면 음수 리턴. 

        });

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0){
                pq.add(num);
            }else{
                if (pq.isEmpty()){
                    bw.write("0\n");
                }else {
                    bw.write(pq.poll() + "\n");
                }
            }
        }
        bw.flush();

    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        s.solve();
    }
}