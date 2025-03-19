import java.util.*;
class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        /**
         top 이 없으면 그냥 f(n) = f( n - 1 ) + f( n - 2 );
         top 이 있다면? f(n) = f( n - 1 ) * 2 + f( n - 2 );
         @implNote
         top이 없다는 가정하에
         이유( f( n ) 위치에서 내가 가질 수 있는 경우의 수 )
         -f( n - 2 )에서 사다리꼴을 놓는 것
         -f( n - 1 )에서 삼각형을 놓는 것

         그럼 top이 생겨버린다면?
         - n % 2 지점에서 top이 있다면 f( n - 1 )에서 삼각형만 놓던게 사다리꼴로 위로 채울 수 있다. -> *2
         - f( n - 2 )는 여전히 사다리꼴만 놓을 수 있다.
         */
        int[] dp = new int[ ( n * 2 ) + 2 ];
        dp[ 0 ] = 1;
        dp[ 1 ] = 1;
        for( int i = 2; i <= n * 2 + 1; i ++ ){
            if( ( i % 2 == 0 ) && ( tops[ i / 2 - 1 ] == 1 ) ){
                dp[ i ] =  ( dp[ i - 1 ] * 2 + dp[ i - 2 ] ) % 10007;
            }else{
                dp[ i ] = ( dp[ i - 1 ] + dp[ i - 2 ] ) % 10007;
            }
        }

        return dp[ n * 2 + 1 ];
    }
}