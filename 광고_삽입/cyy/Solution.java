import java.util.*;

public class Solution {
    public static String solution(String playTime, String advTime, String[] logs) {
        int playTimeSec = toSec(playTime);
        int advTimeSec = toSec(advTime);
        // 해쉬맵으로도 해봤는데 범위가 작다보니 오히려 배열이 더빠름
        long[] views = new long[playTimeSec + 1];
        
        processLogs(logs, views, playTimeSec);
        
        for (int i = 1; i <= playTimeSec; i++) {
            views[i] += views[i - 1];
        }
        
        return findOptimalAdTime(views, playTimeSec, advTimeSec);
    }
    
    private static void processLogs(String[] logs, long[] views, int playTimeSec) {
        for (String log : logs) {
            String[] times = log.split("-");
            int start = toSec(times[0]);
            int end = toSec(times[1]);
            views[start]++;
            if (end <= playTimeSec) {
                views[end]--;
            }
        }
    }
    
    private static String findOptimalAdTime(long[] views, int playTimeSec, int advTimeSec) {
        long maxViewTime = 0;
        long curViewTime = 0;
        int bestStart = 0;
        
        for (int i = 0; i < advTimeSec; i++) {
            curViewTime += views[i];
        }
        maxViewTime = curViewTime;
        
        for (int start = 1; start <= playTimeSec - advTimeSec; start++) {
            curViewTime = curViewTime - views[start - 1] + views[start + advTimeSec - 1];
            if (curViewTime > maxViewTime) {
                maxViewTime = curViewTime;
                bestStart = start;
            }
        }
        
        return toTime(bestStart);
    }
    
    private static int toSec(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return h * 3600 + m * 60 + s;
    }
    
    private static String toTime(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
