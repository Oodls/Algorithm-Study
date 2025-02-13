import java.io.*;
import java.util.*;

public class Main {
    static int[] setArr;
    static int found;

    static int find(int n){ // Union 시, 집합의 대표 노드들을 업데이트
        if (setArr[n] == n){
            found = n;
        } else {
            find(setArr[setArr[n]]);
            setArr[n] = found;
        }
        return found;
    }

    static void union(int a, int b){
        int superA = find(a); // a가 속한 집합의 대표 노드
        int superB = find(b); // b가 속한 집합의 대표 노드
        int flag = superA - superB;
        if (flag < 0){ // b를 a에 편입
            setArr[superB] = superA;
        } else { //a를 b에 편입
            setArr[superA] = superB;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int node = Integer.parseInt(st.nextToken());
        int calc = Integer.parseInt(st.nextToken());
        setArr = new int[node + 1];
        for (int i=1; i<=node; i++){ // 인덱스를 대표 노드로 초기화
            setArr[i] = i;
        }
        for (int i=0; i<calc; i++){ // 주어진 연산 수만큼 반복
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st2.nextToken()); // 연산 타입
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            
            if (type == 0){ // union
                union(a,b);
            } 
            else if(type == 1){ //find
                int superA = find(a);
                int superB = find(b);

                if (superA == superB){
                    bw.write("YES");
                    bw.newLine();
                } else {
                    bw.write("NO");
                    bw.newLine();
                }
            }
        }
        bw.flush();
    }
}
