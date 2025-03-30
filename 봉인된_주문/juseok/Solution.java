import java.util.*;
class Solution {
    /**
        소문자 11글자 이하로 구성됨.
        
            1. 글자 수가 적은 주문부터 먼저 기록
            2. 글자 수가 같다면 사전 순서대로 기록
            
        필요한 것
            1. n번째 단어 생성기
            2. ban이 n 범위에 포함되는지 체크
            3. 만약 n 값에 해당하는게 ban이면 ( + 1 )
            
            abcd
            1 -> e
            
        한 글자 26가지
        
        두 글자 26 * 26 즉 26의 제곱 수 만큼 연산 필
        
        
            
    */
    char[] unit = new char[26];
    Map<Character, Integer> map = new HashMap<>();
    public String solution(long n, String[] bans) {
        String answer = "";
        

        Arrays.sort( bans, ( s1, s2 ) -> {
            if( s1.length() == s2.length() ){
                return s1.compareTo( s2 ); 
            }
           return Integer.compare( s1.length(), s2.length() );
        });
        
        
        for( String ban : bans ){
            long pos = getPos( ban );
            if( pos <= n ){
                n++;
            }
        }
        

        
        
        return generate( n );
    }
    
    public String generate( long n ){
        StringBuilder sb = new StringBuilder();
        while( n-- > 0 ){
            sb.append( (char)( 'a' + n % 26 ) );
            n /= 26;
        }
        return sb.reverse().toString();
        
    }
    
    public long getPos( String str ){
        long result = 0;
        
        for( int i = 0; i < str.length(); i++ ){
            char c = str.charAt( i );
            result *= 26;
            result += c - 'a' + 1;
        }
        return result;
    }
}
