import java.io.*;
import java.util.*;

public class Main {

    static Map<String, Node> nodeMap = new HashMap<>();
    static int v;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node {
        String left;
        String right;

        public Node(String l, String r){
            left = l;
            right = r;
        }
    }

    static void preorderTraversal(String nodeName) throws IOException{
        if (!nodeName.equals(".")){
            Node node = nodeMap.get(nodeName);
            bw.write(nodeName); // parent
            preorderTraversal(node.left); // left
            preorderTraversal(node.right); // right
        }
    }

    static void inorderTraversal(String nodeName) throws IOException{
        if (!nodeName.equals(".")){
            Node node = nodeMap.get(nodeName);
            inorderTraversal(node.left); // left
            bw.write(nodeName); // parent
            inorderTraversal(node.right); // right
        }
    }


    static void postorderTraversal(String nodeName) throws IOException{
        if (!nodeName.equals(".")){
            Node node = nodeMap.get(nodeName);
            postorderTraversal(node.left); // left
            postorderTraversal(node.right); // right
            bw.write(nodeName); // parent
            
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        v = Integer.parseInt(br.readLine());
        
        for (int i=1; i<=v; i++){ // 이진트리에 저장
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();
            
            nodeMap.put(a,new Node(b,c)); // Map에 a(parent)를 키로 b와 c를 left, right로 저장한 Node 객체를 value로 저장
        }

        preorderTraversal("A"); // A는 항상 root이므로 A로 시작
        bw.newLine();
        inorderTraversal("A");
        bw.newLine();
        postorderTraversal("A");
        bw.flush();
    }
}

/* 트리 순회 - 백준 1991 (실버 1)
이진 트리를 입력받아 전위 순회, 중위 순회, 후위 순회한 결과를 출력하는 프로그램

123456789
abcd ef g


대충 코드 끄적여 보기
전위 순회
static void preorderTraversal(int node){
        if (tree[node] != null && node < V){
            arrList.add(tree[node]); // 부모
            preorderTraversal(tree[node * 2]) // left
            preorderTraversal(tree[node * 2 + 1]) // right
        } 
}

중위 순회
static void inorderTraversal(int node){
        if (tree[node] != null && node < V){
            inorderTraversal(tree[node * 2]) // left
            arrList.add(tree[node]); // 부모
            inorderTraversal(tree[node * 2 + 1]) // right
        } 
}

후위 순회
static void postorderTraversal(int node){
        if (tree[node] != null && node < V){
            postorderTraversal(tree[node * 2]) // left
            postorderTraversal(tree[node * 2 + 1]) // right
            arrList.add(tree[node]); // 부모
        } 
}
완전이진트리만 주어진다고 생각하고 처음에 인덱스 트리 배열로 해결하려고 했다.
일반 이진트리, 편향 이진 트리일때의 배열 크기가 커지는 문젤르 생각 안했음.
특히 편향 이진트리는 2^V(2의 노드 개수 만큼 제곱) 의 크기를 배열이 가지게 되므로 문제
따라서 이 문제는 지금 코드처럼 객체에 right, left값을 저장해 두고 map으로 조회하면서 했었어야 함
*/

