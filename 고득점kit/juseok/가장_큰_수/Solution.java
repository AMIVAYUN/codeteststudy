import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        List< String > nums = new ArrayList<>();
        
        for( int number: numbers ){
            nums.add( number + "" );
        }
        
        Collections.sort( nums, ( s1, s2 ) -> {
            return (s2 + s1).compareTo(s1 + s2);
        });
        if( nums.get( 0 ).equals("0") ){
            return "0";
        }
        for( String s : nums ){
            sb.append( s );
        }      
        
        return sb.toString();
    }
}
