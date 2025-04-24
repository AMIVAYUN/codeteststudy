import java.util.*;
class Solution {
    /**
    
    
    
    */
    List< Integer > lst = new ArrayList<>();
    public int[] solution(String[] operations) {
        int[] answer = new int[ 2 ];
        
        for( String oper : operations ){
            String[] ops = oper.split(" ");
            Integer num = Integer.parseInt( ops[ 1 ] );
            if( ops[ 0 ].equals( "I" ) ){
                insort( num );
            }else{
                if( num == -1l ){
                    if( lst.size() != 0 ){
                        lst.remove( 0 );
                    }
                }else{
                    if( lst.size() != 0 ){
                        lst.remove( lst.size() - 1 );
                    }
                }
            }
        }
        if( lst.size() != 0 ){
            answer[ 1 ] = lst.get( 0 );
            answer[ 0 ] = lst.get( lst.size() - 1 );
        }
        return answer;
    }
    
    public int find( Integer number ){
        if( lst.size() == 0 || !lst.contains( number )){
            return -1;
        }
        int lt = 0, rt = lst.size() - 1;
        int ans = 0;
        while( lt <= rt ){
            int mid = ( lt + rt ) / 2;
            
            if( lst.get( mid ) > number ){
                rt = mid - 1;
            }else{
                lt = mid + 1;
                ans = mid;
            }
        }
        
        return ans;
    }
    
    public void insort( Integer number ){
        if( lst.size() == 0 ){
            lst.add( number );
            return;
        }
        if( lst.get( 0 ) > number ){
            lst.add( 0, number );
            return;
        }else if( lst.get( lst.size() - 1 ) < number ){
            lst.add( number );
            return;
        }
        
        int lt = 0, rt = lst.size() - 1;
        int idx = 0;
        while( lt <= rt ){
            int mid = ( lt + rt ) / 2;
            
            if( lst.get( mid ) >= number ){
                rt = mid - 1;
            }else{
                lt = mid + 1;
            }
        }
        lst.add( lt, number );
        return;
    }
}
