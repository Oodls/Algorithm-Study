import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        int testCase = Integer.parseInt(br.readLine());
        
        for (int i=0; i< testCase; i++){
            int n = Integer.parseInt(br.readLine());
            HashMap<Long, Integer> delMap = new HashMap<>();
            PriorityQueue<Long> maxQ = new PriorityQueue<>((a,b) -> {
                return Long.compare(b,a);
            });
            PriorityQueue<Long> minQ = new PriorityQueue<>((a,b) -> {
                return Long.compare(a,b);
            });
            int count = 0;
            
            for (int j=0; j<n; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                long num = Long.parseLong(st.nextToken());

                if (type.equals("I")){ // 삽입
                    maxQ.add(num);
                    minQ.add(num);
                    delMap.put(num, delMap.getOrDefault(num,0) + 1);
                    count ++;
                }
                else if(num == 1 && count > 0){ // 삭제
                    while(delMap.get(maxQ.peek()) <= 0) {
                        maxQ.poll();
                    }
                    long delNum = maxQ.peek();
                    if (delMap.get(delNum) >= 1){
                        delMap.put(maxQ.poll(), delMap.get(delNum) - 1);
                        count --;
                    }
                    
                } 
                else if (num == -1 && count > 0){
                    while(delMap.get(minQ.peek()) <= 0) {
                        minQ.poll();
                    }
                    long delNum = minQ.peek();
                    if (delMap.get(delNum) >= 1){
                        delMap.put(minQ.poll(), delMap.get(delNum) - 1);
                        count --;
                    }
                }
            }
            if (count > 0){
                while(delMap.get(maxQ.peek()) <= 0){ // map기준 삭제된 경우에만 반복
                    maxQ.poll();
                }
                bw.write(String.valueOf(maxQ.peek())+" ");
                while(delMap.get(minQ.peek()) <= 0){ // map기준 삭제된 경우에만 반복
                    minQ.poll();
                }
                bw.write(String.valueOf(minQ.peek()));
                bw.newLine();
            } else {
                bw.write("EMPTY");
                bw.newLine();
            }
        }
        
        bw.flush();
    }
}
