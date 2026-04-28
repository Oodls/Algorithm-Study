class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int index1 = 0;
        int index2 = 0;
        int maxIndex1 = cards1.length;
        int maxIndex2 = cards2.length;

        for (String word: goal) {
            if (index1 < maxIndex1 && cards1[index1].equals(word)) {
                index1++;
                continue;
            }
            if (index2 < maxIndex2 && cards2[index2].equals(word)) {
                index2++;
                continue;
            }
            return "No";
        }
        return "Yes";
    }
}

