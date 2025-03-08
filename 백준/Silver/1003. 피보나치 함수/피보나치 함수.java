import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static ArrayList<Fibo> fiboList;

    static class Fibo {
        int zero;
        int one;

        public Fibo(int z, int o) {
            this.zero = z;
            this.one = o;
        }
    }
    
    public static void main(String[] args) throws IOException{
        int numCase = Integer.parseInt(br.readLine());
        fiboList = new ArrayList<>();
        fiboList.add(new Fibo(1,0));
        fiboList.add(new Fibo(0,1));
        for (int i=2; i<=40; i++){
            Fibo fiboA = fiboList.get(i-2);
            Fibo fiboB = fiboList.get(i-1);
            int z = fiboA.zero + fiboB.zero;
            int o = fiboA.one + fiboB.one;
            fiboList.add(new Fibo(z, o));
        }
        
        for (int i=0; i<numCase; i++){
            int n = Integer.parseInt(br.readLine());
            Fibo fibo = fiboList.get(n);
            bw.write(String.valueOf(fibo.zero)+" "+String.valueOf(fibo.one));
            bw.newLine();
        }
        bw.flush();
    }
}
/*
fibo(0) = 1 0
fibo(1) = 0 1
fibo(2) = 1 1
fibo(3) = 1 2
fibo(4) = 2 3




*/