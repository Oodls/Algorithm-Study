class Solution {
    static String[][] parkMatrix;
    static int xLength;
    static int yLength;
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        xLength = park[0].length();
        yLength = park.length;
        parkMatrix = new String[yLength][xLength];

        Dot start = new Dot(0,0);

        for (int i=0; i<yLength; i++) {
            String[] row = park[i].split("");
            for (int j=0; j<xLength; j++) {
                if (row[j].equals("S")){
                    start = new Dot(i, j);
                    parkMatrix[i][j] = row[j];
                } else {
                    parkMatrix[i][j] = row[j];
                }
            }
        }
        
        
        Dot proc = start.copy();
        for (String command: routes) {
            String[] commands = command.split(" ");
            proc = move(commands[0], Integer.parseInt(commands[1]), proc);
        }
        answer[0] = proc.getY();
        answer[1] = proc.getX();

        return answer;
    }

    public Dot move(String direction, int count, Dot location) {
        switch (direction) {
            case "E":
                // 공원 밖 판정
                if (location.getX()+count > xLength - 1) {
                    return location;
                }
                for (int i=1; i<=count; i++) {
                    //장애물 판정
                    if (parkMatrix[location.getY()][location.getX()+i].equals("X")){
                        return location;
                    }
                }
                return new Dot(location.getY(), location.getX()+count);
            case "W":
                // 공원 밖 판정
                if (location.getX()-count < 0) {
                    return location;
                }
                for (int i=1; i<=count; i++) {
                    //장애물 판정
                    if (parkMatrix[location.getY()][location.getX()-i].equals("X")){
                        return location;
                    }
                }
                return new Dot(location.getY(), location.getX()-count);
            case "S":
                // 공원 밖 판정
                if (location.getY()+count > yLength - 1) {
                    return location;
                }
                for (int i=1; i<=count; i++) {
                    //장애물 판정
                    if (parkMatrix[location.getY()+i][location.getX()].equals("X")){
                        return location;
                    }
                }
                return new Dot(location.getY()+count, location.getX());
            case "N":
                // 공원 밖 판정
                if (location.getY()-count < 0) {
                    return location;
                }
                for (int i=1; i<=count; i++) {
                    //장애물 판정
                    if (parkMatrix[location.getY()-i][location.getX()].equals("X")){
                        return location;
                    }
                }
                return new Dot(location.getY()-count, location.getX());
            default:
                return location;
        }
    }

    class Dot {
        int x;
        int y;

        public Dot(int y, int x) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Dot copy() {
            return new Dot(y, x);
        }
    }
}