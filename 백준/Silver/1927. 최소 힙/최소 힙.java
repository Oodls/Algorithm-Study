import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            if (x > 0) {
                minHeap.add(x); // 최소 힙에 추가
            } else {
                // 0이 들어오면 최소값 출력 후 제거
                output.append(minHeap.isEmpty() ? "0" : minHeap.poll()).append("\n");
            }
        }
        scanner.close();
        System.out.print(output);
    }
}
