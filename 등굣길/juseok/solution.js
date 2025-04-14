function solution(m, n, puddles) {
    let answer = 0;
    let dp = new Array( n + 1 );

    for( let i = 0; i < n + 1; i ++ ){
        dp[ i ] =  new Array( m + 1 ).fill( 0 );

    }


    dp[ 1 ][ 1 ] = 1;
    const mod = 1_000_000_007;
    puddles.forEach( i => dp[ i[ 1 ] ][ i[ 0 ] ] = -1 );

    for( let x = 1; x < n + 1; x++ ){
        for( let y = 1; y < m + 1; y ++ ){
            if( dp[ x ][ y ] == -1 ) continue;
            const prevX = dp[ x - 1 ][ y ] == -1 ? 0 : dp[ x - 1 ][ y ];
            const prevY = dp[ x ][ y - 1 ] == -1 ? 0 : dp[ x ][ y - 1 ];
            dp[ x ][ y ] += prevX + prevY;
            dp[ x ][ y ] %= mod;

        }
    }
    return dp[ n ][ m ];
}