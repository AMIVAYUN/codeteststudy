class Solution {
    int n, m;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        this.n = n;
        this.m = m;
        int dist = getDist( x, y, r, c );
        if( k % 2 == 0 ){
            if( dist % 2 != 0 ) return "impossible";
        }else{
            if( dist % 2 == 0 ) return "impossible";
        }

        if( dist > k ){
            return "impossible";
        }

        //l r u d -> d l r u
        while( k > 0 ){
            if( isRight( x + 1, y ) ){
                if( getDist( x + 1, y, r, c ) <= k ){
                    x++;
                    k--;
                    answer +="d";
                    continue;
                }
            }
            if( isRight( x, y - 1 ) ){
                if( getDist( x, y - 1, r, c ) <= k ){
                    y--;
                    k--;
                    answer +="l";
                    continue;
                }
            }

            if( isRight( x, y + 1 ) ){
                if( getDist( x, y + 1, r, c ) <= k ){
                    y++;
                    k--;
                    answer +="r";
                    continue;
                }
            }

            if( isRight( x - 1, y ) ){
                if( getDist( x - 1, y, r, c ) <= k ){
                    x--;
                    k--;
                    answer +="u";
                    continue;
                }
            }


        }

        return answer;
    }

    boolean isRight( int x, int y ){
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }

    int getDist( int x1, int y1, int x2, int y2 ){
        return Math.abs( x1 - x2 ) + Math.abs( y1 - y2 );
    }
}