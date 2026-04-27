class Solution {
    public static String[][] wallpaperMatrix;
    public static int xLength;
    public static int yLength;
    public static int xMax = 0;
    public static int xMin = 50; // 제한사항
    
    public static int yMax = 0; 
    public static int yMin = 50; // 제한사항

    // max는 드래그 시 +1 해야함

    public int[] solution(String[] wallpaper) {
        int[] answer = new  int[4];
        yLength = wallpaper.length;
        xLength = wallpaper[0].length();
        wallpaperMatrix = new String[yLength+1][xLength+1]; // 격자버전 크기 재구성

        for (int i=0; i<yLength; i++){
            for (int j=0; j<xLength; j++){
                if (wallpaper[i].charAt(j) == '#') {
                    xMax = Math.max(j, xMax);
                    xMin = Math.min(j, xMin);
                    yMax = Math.max(i, yMax);
                    yMin = Math.min(i, yMin);
                }
            }
        }

        return new int[]{yMin, xMin, yMax+1, xMax+1};
    }
}