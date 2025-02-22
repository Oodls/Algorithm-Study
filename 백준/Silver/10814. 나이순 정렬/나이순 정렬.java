import java.io.*;
import java.util.*;

public class Main {

    static class Member {
        int age;
        String name;
        int count;

        public Member(int age, String name, int count){
            this.age = age;
            this.name = name;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Member> list = new ArrayList<>();
        int count = 1;

        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken(), count));
            count ++;
        }

        Collections.sort(list, (a,b) -> {
            if (a.age != b.age){
                return a.age - b.age;
            } else {
                return a.count - b.count;
            }
        });

        for (int i=0; i<n; i++){
            Member member = list.get(i);
            int age = member.age;
            String name = member.name;
            bw.write(String.valueOf(age)+" "+name);
            bw.newLine();
        }
        bw.flush();
    }
}
