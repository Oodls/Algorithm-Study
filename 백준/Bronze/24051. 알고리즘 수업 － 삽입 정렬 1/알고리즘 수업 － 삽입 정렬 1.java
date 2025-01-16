
/*
오늘도 서준이는 삽입 정렬 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
N개의 서로 다른 양의 정수가 저장된 배열 A가 있다. 삽입 정렬로 배열 A를 오름차순 정렬할 경우 배열 A에 K 번째 저장되는 수를 구해서 우리 서준이를 도와주자.
크기가 N인 배열에 대한 삽입 정렬 의사 코드는 다음과 같다.

insertion_sort(A[1..N]) { # A[1..N]을 오름차순 정렬한다.
    for i <- 2 to N {
        loc = i - 1;
        newItem = A[i];

        # 이 지점에서 A[1..i-1]은 이미 정렬되어 있는 상태
        while (1 <= loc and newItem < A[loc]) {
            A[loc + 1] <- A[loc];
            loc--;
        }
        if (loc + 1 != i) then A[loc + 1] = newItem;
    }
}

입력
첫째 줄에 배열 A의 크기 N(5 ≤ N ≤ 10,000), 저장 횟수 K(1 ≤ K ≤ N2)가 주어진다.
다음 줄에 서로 다른 배열 A의 원소 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)

출력
K 번째 저장 되는 수를 출력한다. 저장 횟수가 K 보다 작으면 -1을 출력한다.

예제 입력 1
5 7
4 5 1 3 2

예제 출력 1
5

4 5 1 3 2 -> 4 5 5 3 2 -> 4 4 5 3 2 -> 1 4 5 3 2 -> 1 4 5 5 2 -> 1 4 4 5 2 -> 1 3 4 5 2 -> 1 3 4 5 5 -> 1 3 4 4 5 -> 1 3 3 4 5 -> 1 2 3 4 5. 총 10회 저장이 발생하고 일곱 번째 저장되는 수는 5이다.

예제 입력 2
5 11
4 5 1 3 2

예제 출력 2
-1
저장 횟수 10이 K 보다 작으므로 -1을 출력한다.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solve();
    }
}

class Solution {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] A = new int[N + 1];  // 1-based indexing
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }

        int count = 0;  // 저장 횟수를 세는 변수
        int answer = -1;  // K번째 저장되는 값

        // 삽입 정렬 구현
        for (int i = 2; i <= N; i++) {
            int loc = i - 1;
            int newItem = A[i];

            // A[1..i-1] 정렬된 상태
            while (1 <= loc && newItem < A[loc]) {
                A[loc + 1] = A[loc];  // 저장 연산
                count++;
                if (count == K) {
                    answer = A[loc];
                }
                loc--;
            }

            if (loc + 1 != i) {
                A[loc + 1] = newItem;  // 저장 연산
                count++;
                if (count == K) {
                    answer = newItem;
                }
            }
        }

        System.out.println(answer);
        sc.close();
    }
}