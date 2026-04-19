class Solution {
    public int solution(int[][] signals) {
        int maxSearchTime = 1;
        int n = signals.length;
        for (int[] s : signals) {
            int cycle = s[0] + s[1] + s[2];
            maxSearchTime = getLCM(maxSearchTime, cycle);
        }
        int g = signals[0][0];
        int y = signals[0][1];
        int r = signals[0][2];
        int proc = g;
        while (proc < maxSearchTime) {
            for (int i=0; i<y; i++) {
                proc += 1;
                if(isAnswerExist(signals, proc, n)) {
                    return proc;
                }
            }
            proc += g+r;
        }
        return -1;
    }
    
    // 특정 시점에 모든 신호등이 y인지 확인
    private boolean isAnswerExist(int[][] signals, int proc, int n) {
        for (int i=1; i<n; i++) {
            int sigG = signals[i][0];
            int sigY = signals[i][1];
            int sigR = signals[i][2];
            int cycle = sigG + sigY + sigR;
            
            int remain = (proc-1)%cycle;
            
            // 10에 신호등이 y인지
            if (remain < sigG || remain >= sigG + sigY ) {
                return false;
            }
        }
        return true;
    }
    
    private int getGCD(int a, int b) {
        while(b>0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    
    private int getLCM(int a, int b) {
        if(a == 0 || b == 0) return 0;
        return (a*b) / getGCD(a,b);
    }
}

/*
초 노 파
2 5
5 7
n개의 신호주기를 담은 2차원 배열 signals

2<signals.length<n<=5

모든 신호등이 노란새기 되는 빠른시각을 리턴
존재하지 않으면 -1

합이 20까지 구하기


*/