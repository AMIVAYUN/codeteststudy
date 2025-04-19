function solution(arr) {
    var answer = -1;
    const n = parseInt( arr.length / 2 + 1 );
    
    const nums = Array( n ).fill( 0 );
    const opers = Array( n - 1 ).fill( '' );
    
    let oi = 0, ni = 0;
    for( let i = 0; i < arr.length; i ++ ){
        if( i % 2 == 0 ){
            nums[ ni ++ ] = parseInt( arr[ i ] );
        }else{
            opers[ oi ++ ] = arr[ i ];
        }
    }
    // console.log( nums, opers );
    
    const maxDp = Array.from( Array( n ), () => Array( n ).fill( -Infinity ) );
    const minDp = Array.from( Array( n ), () => Array( n ).fill( Infinity ) );
    
    for( let i = 0; i < n; i ++ ){
        maxDp[ i ][ i ] = nums[ i ];
        minDp[ i ][ i ] = nums[ i ];
    }
    
    for( let size = 2; size <= n; size ++ ){
        for( let start = 0; start <= n - size; start ++ ){
            let end = start + size - 1;
            // console.log( start, end );
            for( let k = start; k < end; k ++ ){
                if( opers[ k ] == "+" ){
                        maxDp[ start ][ end ] = Math.max( maxDp[ start ][ end ], maxDp[ start ][ k ] + maxDp[ k + 1 ][ end ] );
                        minDp[ start ][ end ] = Math.min( minDp[ start ][ end ], minDp[ start ][ k ] + minDp[ k + 1 ][ end ] );
                    }else{
                        maxDp[ start ][ end ] = Math.max( maxDp[ start ][ end ], maxDp[ start ][ k ] - minDp[ k + 1 ][ end ] );
                        minDp[ start ][ end ] = Math.min( minDp[ start ][ end ], minDp[ start ][ k ] - maxDp[ k + 1 ][ end ] );
                    }
            }
        }
    }

    // console.log( maxDp );
    return maxDp[ 0 ][ n - 1 ];
}
