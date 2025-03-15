import java.util.*;
class Solution {
    /**

     합승 시
     출발 지점에서 각 노드로 가는 최소 비용.
     그 노드에서 a,b로 가는 비용의 합.

     3~200
     요금 10만
     int로 ㄱ

     */
    int mx = 4000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = mx;
        PriorityQueue< int[] > pq = new PriorityQueue<>( ( e1, e2 ) -> Integer.compare( e1[ 0 ], e2[ 0 ] ) );
        int[][] dist = new int[ n + 1 ][ n + 1 ];
        int[][] graph = new int[ n + 1 ][ n + 1 ];
        for( int i = 0; i < n + 1; i ++ ){
            Arrays.fill( dist[ i ], mx );
            Arrays.fill( graph[ i ], mx );
        }
        for( int[] fare: fares ){
            graph[ fare[ 0 ] ][ fare[ 1 ] ] = fare[ 2 ];
            graph[ fare[ 1 ] ][ fare[ 0 ] ] = fare[ 2 ];
        }

        for( int i = 1; i < n + 1; i ++ ){
            dist[ i ][ i ] = 0;
            pq.add( new int[]{ 0, i, i });
        }


        while( !pq.isEmpty() ){
            int[] pos = pq.poll();
            int cost = pos[ 0 ];
            int startPos = pos[ 1 ];
            int now = pos[ 2 ];

            if( dist[ startPos ][ now ] < cost ) continue;

            for( int i = 1; i < n + 1; i ++ ){
                if( graph[ now ][ i ] != mx ){
                    int next = graph[ now ][ i ] + cost;
                    if( next < dist[ startPos ][ i ] ){
                        dist[ startPos ][ i ] = next;
                        pq.add( new int[]{ next, startPos, i });
                    }
                }
            }
        }

        for( int i = 1; i < n + 1; i ++ ){
            answer = Math.min( answer, dist[ i ][ a ] + dist[ i ][ b ] + dist[ s ][ i ] );
        }

        return answer;
    }
}
