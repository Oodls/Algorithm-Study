import java.util.Scanner;

public class Main {
    // 소수인지 판별하는 메서드
    public static boolean isPrime(int num) {
        // 1은 소수가 아님
        if (num == 1) {
            return false;
        }
        
        // 2부터 num의 제곱근까지 확인
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // N 입력 받기
        int N = sc.nextInt();
        
        // 소수 개수를 저장할 변수
        int count = 0;
        
        // N개의 수를 입력받아 소수인지 확인
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if (isPrime(num)) {
                count++;
            }
        }
        
        // 결과 출력
        System.out.println(count);
        
        sc.close();
    }
}