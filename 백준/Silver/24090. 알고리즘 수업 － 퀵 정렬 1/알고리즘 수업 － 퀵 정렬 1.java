
/*
오늘도 서준이는 퀵 정렬 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
N개의 서로 다른 양의 정수가 저장된 배열 A가 있다. 퀵 정렬로 배열 A를 오름차순 정렬할 경우 배열 A에 K 번째 교환되는 수를 구해서 우리 서준이를 도와주자.
크기가 N인 배열에 대한 퀵 정렬 의사 코드는 다음과 같다.

quick_sort(A[p..r]) { # A[p..r]을 오름차순 정렬한다.
    if (p < r) then {
        q <- partition(A, p, r);  # 분할
        quick_sort(A, p, q - 1);  # 왼쪽 부분 배열 정렬
        quick_sort(A, q + 1, r);  # 오른쪽 부분 배열 정렬
    }
}

partition(A[], p, r) {
    x <- A[r];    # 기준원소
    i <- p - 1;   # i는 x보다 작거나 작은 원소들의 끝지점
    for j <- p to r - 1  # j는 아직 정해지지 않은 원소들의 시작 지점
        if (A[j] ≤ x) then A[++i] <-> A[j]; # i값 증가 후 A[i] <-> A[j] 교환
    if (i + 1 != r) then A[i + 1] <-> A[r]; # i + 1과 r이 서로 다르면 A[i + 1]과 A[r]을 교환
    return i + 1;
}


입력
첫째 줄에 배열 A의 크기 N(5 ≤ N ≤ 10,000), 교환 횟수 K(1 ≤ K ≤ 108)가 주어진다.
다음 줄에 서로 다른 배열 A의 원소 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)

출력
K 번째 교환되는 두 개의 수를 작은 수부터 한 줄에 출력한다. 교환 횟수가 K 보다 작으면 -1을 출력한다.
예제 입력
5 1
2 5 1 4 3

3

예제 출력
2 2
2 5 1 4 3
(i=0, j=1, A[1]과 A[1]이 교환됨)
-> 2 5 1 4 3(i=1, j=2)
-> 2 5 1 4 3(i=1, j=3, A[2]와 A[3]이 교환됨)
-> 2 1 5 4 3(i=2, j=4)
-> 2 1 5 4 3(i=2, j=5, A[3]과 A[5]가 교환됨)
-> 2 1 3 4 5(i=0, j=1)
-> 2 1 3 4 5(i=0, j=2, A[1]과 A[2]가 교환됨)
-> 1 2 3 4 5(i=3, j=4, A[4]와 A[4]가 교환됨)
-> 1 2 3 4 5(i=4, j=5) -> 1 2 3 4 5(최종 상태). 총 5회 교환이 발생하고 첫 번째 교환은 2와 2이다.


예제 입력
5 2
2 5 1 4 3

예제 출력
1 5
총 5회 교환이 발생하고 두 번째 교환은 1과 5이다.

예제 입력 3
5 10
2 5 1 4 3
예제 출력 3
-1
교환 횟수 5가 K 보다 작으므로 -1을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());

        int size = Integer.parseInt(st1.nextToken());
        int target = Integer.parseInt(st1.nextToken());

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        Solution sol = new Solution();
        sol.solve(arr, target, size);

    }
}

class Solution {
    int count =0;
    int[] arr;
    public void solve(int[] a, int target, int size) {
        arr = a.clone();
        quickSort( 0, size - 1, target);
        if (count < target) {
            System.out.println(-1);
        }
    }

    public void quickSort(int start ,int end, int target) {
        if (start < end){
            int boundary = partition(start, end, target);
            quickSort(start, boundary - 1, target);
            quickSort(boundary + 1, end, target);
        }
    }

    public int partition(int start, int end, int target) {
        int pivot = arr[end];
        int i = start -1;
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                swap(++i,j, target);
            }
        }
        if (i+1 != end) {
            swap(i+1, end, target);
        }
        return i+1;
    }

    public void swap(int i, int j, int target) {
        ++count;
        if (count == target){
            int chk = arr[i] - arr[j];
            if (chk < 0){
                System.out.println(arr[i]+" "+arr[j]);
            } else {
                System.out.println(arr[j]+" "+arr[i]);
            }
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
