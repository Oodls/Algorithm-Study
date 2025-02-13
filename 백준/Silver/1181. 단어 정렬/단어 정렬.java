import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        
        Set<String> set = new HashSet<String>(n);
        for (int i=0; i<n; i++){
            String str = br.readLine();
            set.add(str);
        }
        ArrayList<String> dic = new ArrayList<String>(set);

        Collections.sort(dic, (a,b) -> {
            int aLength = a.length();
            int bLength = b.length();
            if (aLength == bLength){
                for (int i=0; i<aLength; i++){
                    if (a.charAt(i) != b.charAt(i)){
                        return (int)(a.charAt(i)) - (int)(b.charAt(i));
                    } 
                } return 0;
            } else {
                return aLength - bLength;
            }
        });
        for (int i=0; i<set.size(); i++){
            bw.write(dic.get(i));
            bw.newLine();
        }
        bw.flush();
    }
}
