import java.util.*;
class Solution {
    /**

     * logs 30만?
     99시간 59분 59초 -> 356400 + 3540 + 59 -> 359999 흠

     40만 * 30만 천억 죽어도 안된다.


     시작 시간 기준 앞뒤로 가보자.
     // //T.O
     // for( int i = 0; i < n; i ++ ){
     //     int st = ilogs[ i ][ 0 ];
     //     int et = st + at > mx ? mx : st + at;
     //     int cnt = 0;
     //     for( int j = 0; j < n; j ++ ){
     //         if( ilogs[ j ][ 0 ] > et ){
     //             break;
     //         }
     //         if( ilogs[ j ][ 1 ] >= st ){
     //             cnt++;
     //         }
     //     }
     //     if( count < cnt ){
     //         answer = getTime( ilogs[ i ][ 0 ] );
     //         count = cnt;
     //     }
     // }


     */
    int mx = 359999; // 99:59:59
    int[][] ilogs;
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int idx = 0;
        int n = logs.length;
        this.mx = Math.max( mx, getSecond( play_time ) );
        int at = getSecond( adv_time );
        int counts[] = new int[ mx + 1 ];
        this.ilogs = new int[ n ][ 2 ];
        if( at >= getSecond( play_time ) ){
            return "00:00:00";
        }
        for( String log : logs ){
            String[] times = log.split("-");
            int st = getSecond( times[ 0 ] );
            int et = getSecond( times[ 1 ] );
            for( int i = st; i < et; i ++ ){
                counts[ i ] ++;
            }
        }
        long count = 0;
        for( int i = 0; i < at; i ++ ){
            count += counts[ i ];
        }

        long[] window = new long[]{ count, 0 };

        for( int i = at; i < mx + 1; i ++ ){
            count = count - counts[ i - at ] + counts[ i ];
            if( count > window[ 0 ] ){

                window[ 0 ] = count;
                window[ 1 ] = i - at + 1;
            }

        }



        return getTime( (int)window[ 1 ] );
    }

    public int getSecond( String time ){
        String[] ts = time.split(":");
        return Integer.parseInt( ts[ 0 ] ) * 3600 + Integer.parseInt( ts[ 1 ] ) * 60 + Integer.parseInt( ts[ 2 ] );
    }
    public String getTime( int second ){
        int h = second / 3600;
        second = second % 3600;
        int m = second / 60;
        int s = second % 60;

        String result = "";
        result += h < 10 ? "0" + h + ":" : h + ":";
        result += m < 10 ? "0" + m + ":" : m + ":";
        result += s < 10 ? "0" + s : s;
        return result;
    }



}