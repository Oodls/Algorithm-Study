
/*

입력
첫 줄에는 문자열의 길이 L이 들어온다. 둘째 줄에는 영문 소문자로만 이루어진 문자열이 들어온다.

입력으로 주어지는 문자열은 모두 알파벳 소문자로만 구성되어 있다.

출력
문제에서 주어진 해시함수와 입력으로 주어진 문자열을 사용해 계산한 해시 값을 정수로 출력한다.

Small (50점)
1 ≤ L ≤ 5
Large (50점)
1 ≤ L ≤ 50

예제 입력
5
abcde

예제 출력
4739715


 */


import java.io.*;

public class Main {
    private static final int r = 31;
    private static final int M = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int[] sequence = new int[n];

        for (int i = 0; i < s.length(); i++) {
            sequence[i] = getNum(s.charAt(i));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(hashing(sequence) + "\n");
        bw.flush();
        bw.close();
    }

    static int getNum(char c) {
        return (int) c % 96;
    }

    static long hashing(int[] sequence) {
        long currentR = 1;
        long result = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (i > 0) {
                currentR =(currentR * r) % M;
            }
            result = (result + (sequence[i] * currentR) %M) %M;
        }

        return result;
    }


}
