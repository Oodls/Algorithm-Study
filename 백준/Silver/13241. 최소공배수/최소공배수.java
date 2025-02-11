import java.io.*;
import java.util.*;
public class Main {
    static long mod(long a, long b){
        return a%b;    
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long bigger;
        long smaller;
        long temp;
        if (a>b){
            bigger = a;
            smaller = b;
        } else {
            bigger = b;
            smaller = a;
        }

        long maxDivisor;
        
        while(true){
            temp = smaller;
            smaller = mod(bigger, smaller);
            bigger = temp;
            

            if (smaller == 0){
                maxDivisor = bigger;
                break;
            }
        }

        long minMultiple = a * b / maxDivisor;
        
        bw.write(String.valueOf(minMultiple));
        bw.newLine();
        bw.flush();
    }
}