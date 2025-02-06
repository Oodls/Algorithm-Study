
/*
문제
세준이는 양수와 +, -, 그리고 괄호를 가지고 식을 만들었다. 그리고 나서 세준이는 괄호를 모두 지웠다.
그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.
괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.

입력
첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다.
그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다. 수는 0으로 시작할 수 있다.
입력으로 주어지는 식의 길이는 50보다 작거나 같다.

출력
첫째 줄에 정답을 출력한다.
 */

/*
예제 입력
55-50+40

예제 출력
-35

예제 입력
10+20+30+40

예제 출력
100

예제 입력
00009-00009

예제 출력
0
 */

// 48 ~ 57 까지가 아라비아 숫자 아스키 코드

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        //System.out.println(Integer.parseInt("00009"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int[] tokens = new int[50];
        int index = 0;
        String token = "";
        boolean isPlus = true;
        for (int i = 0; i < line.length(); i++) {
            if ((int)(line.charAt(i)) >= 48 && (int)(line.charAt(i)) <= 57) {
                token = token + line.charAt(i);
                if (i == line.length() - 1) {
                    int num = isPlus ? 1 : -1;
                    tokens[index] = (Integer.parseInt(token))*num;
                    break;
                }
                if (line.charAt(i+1) == '+') {
                    int num = isPlus ? 1 : -1;
                    isPlus = true;
                    tokens[index] = (Integer.parseInt(token))*num;
                    token = "";
                    index++;
                } else if (line.charAt(i+1) == '-') {
                    int num = isPlus ? 1 : -1;
                    isPlus = false;
                    tokens[index] = (Integer.parseInt(token))*num;
                    token = "";
                    index++;
                }
            }
        }

        int sum = 0;
        boolean check = false;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] < 0) {
                check = true;
                sum += tokens[i];
                continue;
            }
            int num = check ? -1 : 1;
            sum += tokens[i] * num;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();
    }
}
