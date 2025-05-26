class Solution {
    /**
    
    월 화 수 목 금 토 일
    
    */
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int n = schedules.length;
        
        for( int i = 0; i < n; i ++ ){
            int cut = schedules[ i ];
            
            int week = startday;
            boolean flag = true;
            for( int j = 0; j < 7; j ++ ){
                int today = startday + j;
                if( today > 7 ) today %= 7;
                
                if( today == 6 || today == 7 ) continue;
                if( getSchedule( cut ) < timelogs[ i ][ j ] ){
                    flag = false;
                    break;
                }
            }
            
            
            if( flag ) answer++;
            
        }
        
        return answer;
    }
    private int getSchedule(int schedule) {
        schedule += 10;

        if (schedule % 100 >= 60) {
            int h = (schedule / 100) + 1;
            int m = (schedule % 100) - 60;
            schedule = (h * 100) + m;
        }
        return schedule;
    }
}
