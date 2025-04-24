import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        int[] answer = {};
        
        int i = 0;
        
        while( i < progresses.length ){
            int count = 0;
            int progress = progresses[ i ];
            int speed = speeds[ i ];
            int remainDay = getRemainDay( progress, speed );
            while( i < progresses.length && remainDay >= getRemainDay( progresses[ i ], speeds[ i ] ) ){
                count++;
                i++;
            }
            result.add( count );
            
        }
        answer = new int[ result.size() ];
        int idx = 0;
        for( int re : result ) answer[ idx ++ ] = re;
        return answer;
    }
    
    public int getRemainDay( int progress, int speed ){
        return ( 100 - progress ) / speed + ( ( 100 - progress ) % speed == 0 ? 0 : 1 );
    }
}
