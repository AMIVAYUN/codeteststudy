import java.util.*;
class Solution {
    int mod = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[ n + 1 ][ m + 1 ];
        for( int[] pud : puddles ){
            int x = pud[ 1 ];
            int y = pud[ 0 ];
            dp[ x ][ y ] = -1;
        }

        dp[ 1 ][ 1 ] = 1;
        for( int x = 1; x <= n; x ++ ){
            for( int y = 1; y <= m; y ++ ){
                if( dp[ x ][ y ] == -1 ) continue;
                int prevX = dp[ x - 1 ][ y ] != -1 ? dp[ x - 1 ][ y ] : 0;
                int prevY = dp[ x ][ y - 1 ] != -1 ? dp[ x ][ y - 1 ] : 0;
                // System.out.println( x + " " + y + " " +  prevX + " " + prevY );
                dp [ x ][ y ] += ( prevX + prevY );
                dp [ x ][ y ] %= mod;

            }
        }

        return dp[ n ][ m ];
    }
}