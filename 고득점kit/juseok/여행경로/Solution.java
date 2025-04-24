import java.util.*;
class Solution {
    boolean visit[];
    String[][] tickets;
    int n;
    String[] answer;
    public String[] solution(String[][] tickets) {
        this.n = tickets.length;

        visit = new boolean[ n ];
        answer = new String[ n + 1 ];
        Arrays.sort( tickets, ( e1, e2 ) -> {
           if( e1[ 0 ].compareTo( e2[ 0 ] ) == 0 ){
               return e1[ 1 ].compareTo( e2[ 1 ] );
           }
            
            return e1[ 0 ].compareTo( e2[ 0 ] );
        });
        this.tickets = tickets;
        List< Integer > lst = new ArrayList<>();
        dfs( 0, lst, "ICN" );
        
        return answer;
    }
    boolean flag = false;
    public void dfs( int depth, List<Integer> lst, String now ){
        if( depth == n ){
            flag = true;
            // System.out.println( lst );
            for( int i = 0; i < n; i ++ ){
                int pos = lst.get( i );
                answer[ i ] = tickets[ pos ][ 0 ];
                answer[ i + 1 ] = tickets[ pos ][ 1 ];
            }
            return;
        }
        
        
        for( int i = 0; i < n && !flag; i ++ ){
            if( !visit[ i ] ){
                if( now.equals( tickets[ i ][ 0 ] ) ){
                    visit[ i ] = true;
                    lst.add( i );
                    dfs( depth + 1, lst, tickets[ i ][ 1 ] );
                    visit[ i ] = false;
                    lst.remove( lst.size() - 1 );
                }
               
            }
        }
    }
}
