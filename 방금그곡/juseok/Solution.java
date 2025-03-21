import java.util.*;
class Solution {

    /**
     n -> 100
     m -> 1439

     완탐인가?
     */

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int len = 0;

        StringBuilder melody = new StringBuilder();
        int i = 0;
        while( i < m.length() ){
            if( i + 1 < m.length() && m.charAt( i + 1 ) == '#' ){
                String p = m.charAt( i ) + "";
                melody.append( p.toLowerCase() );
                i+=2;
            }else{
                String p = m.charAt( i ) + "";
                melody.append( p );
                i++;
            }
        }
        for( String info: musicinfos ){
            String[] infos = info.split( "," );
            int time = getDiff( infos[ 0 ].split( ":" ), infos[ 1 ].split(":") );
            StringBuilder target = new StringBuilder();
            i = 0;
            String title = infos[ 2 ];
            String ml = infos[ 3 ];
            while( i < ml.length() ){
                if( i + 1 < ml.length() && ml.charAt( i + 1 ) == '#' ){
                    String p = ml.charAt( i ) + "";
                    target.append( p.toLowerCase() );
                    i+=2;
                }else{
                    String p = ml.charAt( i ) + "";
                    target.append( p );
                    i++;
                }
            }
            ml = target.toString();
            if( ml.length() <= time ){
                int idx = 0;
                while( ml.length() < time ){
                    ml += ml.charAt( idx++ );
                }
            }else{
                ml = ml.substring( 0, time );
            }
            if( ml.contains( melody.toString() ) ){
                if( len < ml.length() ){
                    len = ml.length();
                    answer = title;
                }

            }
        }

        return answer;

    }

    public int getDiff( String[] t1, String[] t2 ){
        // System.out.println( Arrays.toString( t1 ) );
        // return 1;
        return Integer.parseInt( t2[ 1 ] ) + ( Integer.parseInt( t2[ 0 ] ) * 60 ) - ( Integer.parseInt( t1[ 1 ] ) + ( Integer.parseInt( t1[ 0 ] ) * 60 ) );
    }
}