import java.util.*;
class Solution {
    /**
        수 연산자 반복
        완탐 불가
        201 이면 답도 없다.
        
        옛날 팰린드롬 문제 처럼 가보자
        
        +
        MAX_DP[ I ][ J ] = MAX_DP[ I ][ K ] + MAX_DP[ K ][ J ];
        MIN_DP[ I ][ J ] = MIN_DP[ I ][ K ] + MIN_DP[ K ][ J ];
        -
        MAX_DP[ I ][ J ] = MAX_DP[ I ][ K ] - MIN_DP[ K ][ J ];
        MIN_DP[ I ][ J ] = MIN_DP[ I ][ K ] - MAX_DP[ K ][ J ];
        
    */
    public int solution(String arr[]) {
        int answer = -1;
        String[] operator = new String[ arr.length / 2 ];
        int n = arr.length / 2 + 1;
        int[] nums = new int[ n ];
        int ni = 0, oi = 0;
        for( int i = 0; i < arr.length; i ++ ){
            
            if( i % 2 == 0 ){
                nums[ ni ++ ] = Integer.parseInt( arr[ i ] );
            }else{
                operator[ oi ++ ] = arr[ i ];
            }
        }
        
        // System.out.println( Arrays.toString( nums ) );
        // System.out.println( Arrays.toString( operator ) );
        
        int[][] min_dp = new int[ n ][ n ];
        int[][] max_dp = new int[ n ][ n ];
        

        
        for( int i = 0; i < n; i ++ ){
            Arrays.fill( min_dp[ i ], Integer.MAX_VALUE );
            Arrays.fill( max_dp[ i ], Integer.MIN_VALUE );
            min_dp[ i ][ i ] = nums[ i ];
            max_dp[ i ][ i ] = nums[ i ];
        }
        for( int size = 2; size <= n; size ++ ){
            // System.out.println( size );
            for( int start = 0; start <= n - size; start ++ ){
                int end = start + size - 1;
                // System.out.println( "start == " + start + " " + " end == " + end );
                for( int k = start; k < end; k ++ ){

                    if( operator[ k ].equals( "+" ) ){
                        max_dp[ start ][ end ] = Math.max( max_dp[ start ][ end ], max_dp[ start ][ k ] + max_dp[ k + 1 ][ end ] );
                        min_dp[ start ][ end ] = Math.min( min_dp[ start ][ end ], min_dp[ start ][ k ] + min_dp[ k + 1 ][ end ] );
                    }else{
                        max_dp[ start ][ end ] = Math.max( max_dp[ start ][ end ], max_dp[ start ][ k ] - min_dp[ k + 1 ][ end ] );
                        min_dp[ start ][ end ] = Math.min( min_dp[ start ][ end ], min_dp[ start ][ k ] - max_dp[ k + 1 ][ end ] );
                    }
                }
                
                // System.out.println( "max == " + max_dp[ start ][ end ] + "min == " + min_dp[ start ][ end ] );
            }
        }
        // for( int i = 0; i < n; i ++ ){
        //     System.out.println( Arrays.toString( max_dp[ i ] ) );
        // }
        
        return max_dp[ 0 ][ n - 1 ];
    }
}
