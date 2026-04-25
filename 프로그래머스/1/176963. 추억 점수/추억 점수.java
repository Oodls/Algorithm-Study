import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
    
        int popular = name.length;
        int length = photo.length;
        
        int[] result = new int[length];
        Map<String, Integer> yearningMap = new HashMap<String, Integer>();
        
        // 추억점수 mapping
        for (int i=0; i<popular; i++) {
            yearningMap.put(name[i], yearning[i]);
        }
        
        // 추억점수 계산
        for (int i=0; i<length; i++) {
            int sum = 0;
            for (int j=0; j<photo[i].length; j++) {
                sum += yearningMap.getOrDefault(photo[i][j], 0);
            }
            result[i] = sum;
        }
        
        return result;    
        
    }
}