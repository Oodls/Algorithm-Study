class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int length = section.length;
        int stroke = 0;
        for (int i=0; i<length; i++) {
            if (section[i] > stroke) {
                answer += 1;
                stroke = section[i]+m-1;
            }
        }
        return answer;
    }
}