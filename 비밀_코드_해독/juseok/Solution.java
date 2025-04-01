class Solution {
    int n, m, len, q[][], ans[], answer;
    boolean[] visit;
    public int solution(int n, int[][] q, int[] ans) {
        this.answer = 0;
        this.n = n;
        this.q = q;
        this.m = ans.length;
        this.len = q[ 0 ].length;
        this.ans = ans;
        this.visit = new boolean[ n + 1 ];

        getCombination( 0, 0 );
        return answer;
    }
    
    public void getResult(){

        boolean result = true;
        for( int i = 0; i < m; i ++ ){
            int[] now = q[ i ];
            int cnt = 0;
            for( int j = 0; j < len; j ++ ){
                if( visit[ now[ j ] ] ) cnt++;
            }
            if( cnt != ans[ i ] ){
                result = false;
                break;
            }
        }
        if( result ) answer++;
    }
    
    public void getCombination( int depth, int start ){
        if( depth == len ){
            getResult();
            return;
        }
        
        for( int i = start + 1; i <= n; i ++ ){
            if( !visit[ i ] ){
                visit[ i ] = true;
                getCombination( depth + 1, i );
                visit[ i ] = false;
            }
        }
    }
}
