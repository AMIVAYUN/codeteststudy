import java.util.*;
class Solution {
    
    int parent[];
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        this.parent = new int[ n ];
        for( int i = 0; i < n; i ++ ){
            parent[ i ] = i;
        }
        Arrays.sort( costs, ( e1, e2 ) -> {
           return Integer.compare( e1[ 2 ], e2[ 2 ] ); 
        });
        
        for( int i = 0; i < costs.length; i ++ ){
            int[] cost = costs[ i ];
            int x = cost[ 0 ]; int y = cost[ 1 ]; int c = cost[ 2 ];
            int px = find( x );
            int py = find( y );
            
            if( px != py ){
                union( x, y );
                answer += c;
            }
        }
        
        return answer;
    }
    
    public int find( int k ){
        if( parent[ k ] != k ){
            parent[ k ] = find( parent[ k ] );
        }
        return parent[ k ];
    }
    
    public void union( int x, int y ){
        int px = find( x );
        int py = find( y );
        
        if( px > py ){
            parent[ px ] = py;
        }else{
            parent[ py ] = px;
        }
        return;
    }
}
