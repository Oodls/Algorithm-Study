
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/*
문제
어떤 단어를 뒤에서부터 읽어도 똑같다면 그 단어를 팰린드롬이라고 한다. 'radar', 'sees'는 팰린드롬이다.
수도 팰린드롬으로 취급할 수 있다. 수의 숫자들을 뒤에서부터 읽어도 같다면 그 수는 팰린드롬수다.
121, 12421 등은 팰린드롬수다. 123, 1231은 뒤에서부터 읽으면 다르므로 팰린드롬수가 아니다.
또한 10도 팰린드롬수가 아닌데, 앞에 무의미한 0이 올 수 있다면 010이 되어 팰린드롬수로 취급할 수도 있지만,
특별히 이번 문제에서는 무의미한 0이 앞에 올 수 없다고 하자.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있으며, 각 줄마다 1 이상 99999 이하의 정수가 주어진다. 입력의 마지막 줄에는 0이 주어지며, 이 줄은 문제에 포함되지 않는다.

출력
각 줄마다 주어진 수가 팰린드롬수면 'yes', 아니면 'no'를 출력한다.
 */

/*
121
1231
12421
0

yes
no
yes
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> strArr = new ArrayList<>();
        while (true) {
            String str = br.readLine();
            if (Objects.equals(str, "0")) {
                break;
            }
            strArr.add(str);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (String str : strArr) {
            if (str.length()%2 == 0) {
                int rear = str.length() - 1;
                for (int i = 0; i < str.length()/2; i++) {
                    if (str.charAt(i) == str.charAt(rear)) {
                        if (rear - i == 1) {
                            bw.write("yes");
                            bw.newLine();
                            break;
                        }
                        rear--;
                    } else if (str.charAt(i) != str.charAt(rear)) {
                        bw.write("no");
                        bw.newLine();
                        break;
                    }

                }
            } else {
                int rear = str.length() - 1;
                for (int i = 0; i < str.length()/2 + 1; i++) {
                    if (str.charAt(i) == str.charAt(rear)) {
                        if (rear == i) {
                            bw.write("yes");
                            bw.newLine();
                            break;
                        }
                        rear--;
                    } else if (str.charAt(i) != str.charAt(rear)) {
                        bw.write("no");
                        bw.newLine();
                        break;
                    }
                }
            }
        }
        bw.flush();
    }


}
