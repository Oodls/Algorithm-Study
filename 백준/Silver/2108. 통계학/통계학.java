
/*
수를 처리하는 것은 통계학에서 상당히 중요한 일이다. 통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다. 단, N은 홀수라고 가정하자.

산술평균 : N개의 수들의 합을 N으로 나눈 값
중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
최빈값 : N개의 수들 중 가장 많이 나타나는 값
범위 : N개의 수들 중 최댓값과 최솟값의 차이
N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 단, N은 홀수이다. 그 다음 N개의 줄에는 정수들이 주어진다. 입력되는 정수의 절댓값은 4,000을 넘지 않는다.

출력
첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다.

둘째 줄에는 중앙값을 출력한다.

셋째 줄에는 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.

넷째 줄에는 범위를 출력한다.
 */

/*
예제 입력
5
1
3
8
-2
2

예제 출력
2
2
1
10
 */

/*
최대 입력값 500000 개 이니까
O(n^2)까지 가능할듯

중앙값, 범위 = 배열에 입력값 받고 정렬 후 뽑아내기 O(nlogn)
산술평균 = 입력값 받으면서 계산하기 O(n)
최빈값 = Hashmap에 입력값 받으면서 그 수에 해당하는 위치 가산, 마지막에 정렬 후 뽑아내기 O(nlogn)
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long sum = 0;
        ArrayList<Integer> numList = new ArrayList<>();
        HashMap<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            numList.add(num);
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }

        Collections.sort(numList);




        int mean = (int) Math.round((double) sum / n);
        int median = numList.get(n/2);
        int range = numList.get(numList.size() - 1) - numList.get(0);
        List <Integer> list = new ArrayList<>();

        // 최빈값 처리 수정
        List<Map.Entry<Integer,Integer>> mapList = new ArrayList<>(numMap.entrySet());
        mapList.sort((a, b) -> {
            int compare = b.getValue().compareTo(a.getValue());  // 빈도수 내림차순
            if (compare == 0) {
                return a.getKey().compareTo(b.getKey());  // 같은 빈도수면 값 오름차순
            }
            return compare;
        });

        int maxFreq = mapList.get(0).getValue();
        int moderate;
        if (mapList.size() == 1 || mapList.get(1).getValue() != maxFreq) {
            moderate = mapList.get(0).getKey();
        } else {
            moderate = mapList.get(1).getKey();
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf((int)mean)+"\n");
        bw.write(String.valueOf(median)+"\n");
        bw.write(String.valueOf(moderate)+"\n");
        bw.write(String.valueOf(range)+"\n");
        bw.flush();
    }
}

