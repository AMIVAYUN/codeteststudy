import java.util.*;
class Solution {
    boolean[] done;
    int[][] graph;
    public int solution(int n, int[][] results) {
        int answer = 0;
        graph = new int[ n ][ n ];
        
        for( int[] result : results ){
            int a = result[ 0 ] - 1;
            int b = result[ 1 ] - 1;
            graph[ a ][ b ]++;
            graph[ b ][ a ]--;
        }
        
        for( int k = 0; k < n; k ++ ){
            for( int i = 0; i < n; i ++ ){
                for( int j = 0; j < n; j ++ ){
                    if( j == i ) continue;
                    
                    if( graph[ i ][ k ] == graph[ k ][ j ] && graph[ i ][ k ] == 1 ){
                        graph [ i ][ j ] = 1;
                        graph[ j ][ i ] = -1; graph[ j ][ k ] = -1; graph[ k ][ i ] = -1;

                    }
                }
            }
        }
        // for( int i = 0; i < n; i ++ ){
        //     System.out.println( Arrays.toString( graph[ i ] ) );
        // }
        outer: for( int i = 0; i < n; i ++ ){
            int cnt = 0;
            for( int j = 0; j < n; j ++ ){
                if( graph[ i ][ j ] == 0 ) cnt++;
                if( cnt > 1 ) continue outer;
            
            }
            answer++;
        }
        
        
        return answer;
    }
}
