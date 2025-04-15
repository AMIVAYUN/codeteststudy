import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int n = triangle.length;
        
        int[][] dp = new int[501][501];
        dp[ 1 ][ 1 ] = triangle[ 0 ][ 0 ];
            
        for( int i = 2; i <= n; i ++ ){
            for( int j = 1; j <= i; j ++ ){
                dp[ i ][ j ] = Math.max( dp[ i ][ j ], triangle[ i - 1 ][ j - 1 ] + dp[ i - 1 ][ j ] );
                dp[ i ][ j ] = Math.max( dp[ i ][ j ], triangle[ i - 1 ][ j - 1 ] + dp[ i - 1 ][ j - 1 ] );
            }
        }
        
        
        for( int i = 1; i <= n; i ++ ){
            answer = Math.max( dp[ n ][ i ], answer );
        }
        
        return answer;
        
        
    }
}
