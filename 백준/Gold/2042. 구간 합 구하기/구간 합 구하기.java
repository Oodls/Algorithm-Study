import java.io.*;
import java.util.*;

public class Main {
    static long[] indexTree;
    static long startIndex;
    static long k = 1;


    static long prefixSum(long from, long to){ // from 번쨰 자리부터 to자리 까지 구간합
        long s = startIndex + from - 1;
        long e = startIndex + to - 1;
        ArrayList<Long> sumList = new ArrayList<>();   
        long result = 0;
        
        while (s <= e){
            if (s % 2 == 1){
                sumList.add(indexTree[(int)s]);
            }
            if (e % 2 == 0) {
                sumList.add(indexTree[(int)e]);
            }

            s = (s+1) / 2;
            e = (e-1) / 2;
        }
        for (int i=0; i<sumList.size(); i++){
            result += sumList.get(i);
        }
        return result;
    }

    static void update(long from, long to){ // from번째 수를 to로 변경
        long index = startIndex + from - 1;
        long gap = indexTree[(int)index] - to;

        while (index >= 1){
            indexTree[(int)index] -= gap; 
            index /= 2;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long l = Long.parseLong(st.nextToken());
            
        startIndex = 2;
        while (startIndex < n){ // k값 구하기
            startIndex *= 2;
            k += 1;
        }

        indexTree = new long[(int)startIndex * 2]; // 세그먼트 트리의 크기는 2^(k+1)
        
        for (int i=0; i<n; i++){ // 리프노드에 값 넣기
            long num = Long.parseLong(br.readLine());
            indexTree[(int)startIndex+i] = num;
        }

        for (int i=(int)startIndex-1; i>=1; i--){
            indexTree[i] = indexTree[2 * i] + indexTree[2 * i + 1];
        }

        for (int i=0; i<m+l; i++){ // 질의 혹은 업데이트
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st1.nextToken());
            long from = Long.parseLong(st1.nextToken());
            long to = Long.parseLong(st1.nextToken());

            if (type == 1){ // 업데이트
                update(from, to);
            } 
            else if (type == 2) { // 구간합
                bw.write(String.valueOf(prefixSum(from,to)));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
/*
어떤 N개의 수가 주어져 있는데, 그 수의 변경이 빈번히 일어나고, 중간에 부분의 합을 계속 구해야 한다.
-> 세그먼트 트리로 해결

첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), l(1 ≤ l ≤ 10,000) 가 주어진다.
M은 수의 변경이 일어나는 횟수
l는 구간 합을 구하는 횟수

둘째 줄 부터 N+1번째 줄까지 N개의 수가 주어짐 -> (트리 배열의 크기는 2^k >= N이 되는 최소 l의 2^(k+1))

N+2번쨰 줄 부터 N+M+l+1 번쨰 줄 까지 세개의 정수 a,b,c가 주어지는데, 
a가 1인 경우 b번째 수를 c로 변경, 
a가 2인 경우에는 b번째 수 부터 c번쨰 수 까지의 합을 구한다.


필요한 것 :
indexTree
startIndex
k
*/