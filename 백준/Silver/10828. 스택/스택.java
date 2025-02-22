import java.io.*;
import java.util.*;

public class Main {
    static class Stack{
        private int[] stack = new int[10001];
        private int count = 0;

        public Stack(){
            
        }
        
        public void push(int n){
            stack[count] = n;
            count ++;
        }

        public int pop(){
            if (count > 0) {
                count--;
                int n = stack[count];
                stack[count] = 0;
                return n;    
            } 
            else {
                return -1;
            }
            
        }

        public int size(){
            return count;
        }

        public int empty(){
            if (count == 0){
                return 1;
            } else {
                return 0;
            }
        }

        public int top(){
            if (count > 0){
                return stack[count-1];    
            }
            else {
                return -1;
            }
            
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        int n = Integer.parseInt(br.readLine());
        
        Stack stack = new Stack();
        for (int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String func = st.nextToken();
            int num = 0;
            if (func.equals("push")){
                num = Integer.parseInt(st.nextToken());
            }

            switch(func){
                    case "push" :
                        stack.push(num);
                        break;

                    case "pop" :
                        bw.write(String.valueOf(stack.pop()));
                        bw.newLine();
                        break;

                    case "size" :
                        bw.write(String.valueOf(stack.size()));
                        bw.newLine();
                        break;

                    case "empty" :
                        bw.write(String.valueOf(stack.empty()));
                        bw.newLine();
                        break;

                    case "top" :
                        bw.write(String.valueOf(stack.top()));
                        bw.newLine();
                        break;
            }
        }
       
        bw.flush();
    }
}
