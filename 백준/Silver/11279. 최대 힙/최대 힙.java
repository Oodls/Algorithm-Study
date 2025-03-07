import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // 최대 힙 (내림차순 정렬)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            if (x > 0) {
                maxHeap.add(x); // 최대 힙에 추가
            } else {
                output.append(maxHeap.isEmpty() ? "0" : maxHeap.poll()).append("\n");
            }
        }
        scanner.close();
        System.out.print(output);
    }
}
