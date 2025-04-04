import java.util.*;
class Solution {
    /*

    오는 간선이 없다 -> 정점
    
    all true인건 많겠지만 all true가 여러개인 것은 하나이고, 그래프는 최소 2개 이므로 나가는 간선 2개 오는 간선 0이 바로 시작 정점
    
    
    
    */
    List< List < Integer > > go = new ArrayList<>();
    int[] back;
    int[] answer;
    int 도넛 = 1;
    int 막대 = 2;
    int 팔자 = 3;
    int mx, meetcount = 0;
    public int[] solution(int[][] edges) {
        answer = new int[ 4 ];
        
        mx = 0;
        for( int[] edge : edges ){
            int x = edge[ 0 ];
            int y = edge[ 1 ];
            
            mx = Math.max( x, mx );
            mx = Math.max( y, mx );
        }
        
        mx++;
        back = new int[ mx ];
        
        for( int i = 0; i < mx; i ++ ){
            go.add( new ArrayList<>() );
            
        }
        
        for( int[] edge: edges ){
            int x = edge[ 0 ];
            int y = edge[ 1 ];
            go.get( x ).add( y );
            back[ y ]++;
        }
        
        int start = 0;
        List< Integer > starts = new ArrayList<>();
        for( int i = 1; i < mx; i++ ){
            if( go.get( i ).size() > 1 && back[ i ] == 0 ){
                start = i;
            }
        }
        
        
        answer[ 0 ] = start;
        
        find( start );
        
        
        return answer;
    }
    
    public void find( int start ){
        for( int arrive : go.get( start ) ){     
            
            
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            boolean[] check = new boolean[ mx ];
            check[ arrive ] = true;
            int node = 1;
            int vertex = go.get( arrive ).size();
            dq.add( arrive );
            while( !dq.isEmpty() ){
                int pos = dq.poll();

                

                for( int next : go.get( pos ) ){
                    if( !check[ next ] ){
                        check[ next ] = true;
                        dq.add( next );
                        node++;
                        vertex += go.get( next ).size();
                    } 
                }
                
            }

            if( vertex - node == -1 ){
                // System.out.println( "arrive = "+ arrive + "막대" );
                // System.out.println( vertex +" n:" + node );
                answer[ 막대 ]++;
            }else if( vertex - node == 1 ){
                // System.out.println( "arrive = "+ arrive + "팔자");
                // System.out.println( vertex +" n:" + node );
                answer[ 팔자 ]++;
            }else{
                // System.out.println( "arrive = "+ arrive + "도넛");
                // System.out.println( vertex +" n:" + node );
                answer[ 도넛 ]++; 
            }

        }
    }
    
//     public void dfs( int start, int[] check ){

//         for( int next: go.get( start ) ){
//             if( visit[ start ][ next ] == 0 ){
//                 visit[ start ][ next ]++;
//                 if( check[ next ] > 0 ){
//                     meetcount++;
//                 }else{
//                     check[ next ] = 1;
//                 }
//                 dfs( next, check );
                
//             }
//         }
//     }
}
