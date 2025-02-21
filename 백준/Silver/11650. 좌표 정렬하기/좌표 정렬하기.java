import java.io.*;
import java.util.*;

public class Main {

    static class Xy{
        int x;
        int y;

        public Xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Xy> xyList = new ArrayList<>(n);

        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xyList.add(new Xy(x,y));
        }

        Collections.sort(xyList, (a,b) -> {
            if (a.x != b.x) {
                return a.x-b.x;
            } else {
                return a.y-b.y;
            }
        });

        for (int i=0; i<n; i++){
            bw.write(String.valueOf(xyList.get(i).x)+" "+String.valueOf(xyList.get(i).y));
            bw.newLine();
        }
        bw.flush();
    }
}
