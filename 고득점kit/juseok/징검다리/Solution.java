import java.util.*;
class Solution {
    /**
        
        관점을 바꿔서 이분탐색으로 거리가 maxDist일 때 이걸 바위를 지워서 구현 가능한가?로 바라보면 편하다.
    
    */
    int rocks[], n, k;
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        this.n = n;
        this.k = rocks.length + 2;
        this.rocks = new int[ k ];
      
        
        
        int lt = 0; int rt = distance;
        Arrays.sort( rocks );
         for( int i = 0; i < rocks.length; i ++ ){
            this.rocks[ i + 1 ] = rocks[ i ];
        }
        this.rocks[ k - 1 ] = distance;

        // System.out.println( Arrays.toString( this.rocks ) );
        while( lt <= rt ){
            int mid = ( lt + rt ) / 2;
            if( available( mid ) ){
                lt = mid + 1;
                answer = mid;
            }else{
                rt = mid - 1;
            }
        }
        
        return answer;
    }
    
    public boolean available( int dist ){
        int cnt = 0;
        int pos = 0;
        
        while( pos < k ){
            int next = pos + 1;
            while( next < k && rocks[ next ] - rocks[ pos ] < dist ){
                // System.out.println( rocks[ next ] + " " + rocks[ pos ] );
                cnt++;
                next++;
            }
            pos = next;
        }
        // System.out.println("dist " + dist + ", result = " + cnt );
        return cnt <= n;
    }
}
