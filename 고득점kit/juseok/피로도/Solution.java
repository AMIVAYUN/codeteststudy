import java.util.*;
class Solution {
    int k, dungeons[][], seq[], n;
    boolean[] visit;
    int answer = 0;
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        this.n = dungeons.length;
        this.seq = new int [ n ];
        visit = new boolean[ n ];
        /**
            
            최소 필요 피로도 -> 최소한 필요한 피로도 
            
            던전 갯수 8개
        */
        
        getPermutation( 0 );
        
        return answer;
    }
    
    public void getPermutation( int depth ){
        if( depth == n ){
            calculate();
            return;
        }
        
        for( int i = 0; i < n; i++ ){
            if( !visit[ i ] ){
                visit[ i ] = true;
                seq[ depth ] = i;
                getPermutation( depth + 1 );
                visit[ i ] = false;
            }
        }
    }
    
    public void calculate(){
        int p = this.k;
        int count = 0;
        for( int i : seq ){
            if( dungeons[ i ][ 0 ] > p ) break;
            p -= dungeons[ i ][ 1 ];
            count ++;
        }
        
        answer = Math.max( answer, count );
        
    }
}
