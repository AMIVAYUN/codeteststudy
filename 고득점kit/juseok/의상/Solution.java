import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,Integer> map = new HashMap<>();
        for( String[] cloth: clothes ){
            String type = cloth[ 1 ];
            map.putIfAbsent( type, 0 );
            map.put( type, map.get( type ) + 1 );
        }
        
        for( String key: map.keySet() ){
            answer *= ( map.get( key ) + 1 );    
        }
        
        return --answer;
    }
    
    
}
