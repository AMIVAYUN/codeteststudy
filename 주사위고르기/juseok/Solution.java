import java.util.*;
class Solution {
    /**

     이 문제의 요점 중 하나는 대칭성을 이룬다는 것.

     12 -> 34의 반대 결과이다.


     */
    int n;
    int totCnt = 0;
    ArrayList<int[]> comb = new ArrayList<>();

    int[] nums, dice[];
    int[] answer;
    String ans= "";
    int mx;
    public int[] solution(int[][] dice) {

        n = dice.length;
        this.dice = dice;
        nums = new int[ n / 2 ];
        answer= new int[ n / 2 ];
        mx = 0;
        getPerm( 0, new int[ n / 2 ] );

        getComb( 0, 0 );


        return answer;
    }

    public void getComb( int depth, int pos ){
        if( depth == n / 2 ){
            getResult();
            return;
        }
        for( int i = pos; i < n; i ++ ){
            nums[ depth ] = i;
            getComb( depth + 1, i + 1 );
        }
    }

    public void getResult(){
        int k = n / 2;

        boolean[] v = new boolean[ n ];
        for( int i : nums ){
            v[ i ] = true;
        }

        int[] vnums = new int[ k ];
        int idx = 0;
        for( int i = 0; i < n; i ++ ){
            if( !v[ i ] ){
                vnums[ idx++ ] = i;
            }
        }
        ArrayList<Integer> lst = new ArrayList<>(), vlst = new ArrayList<>();

        for( int[] origin: comb ){
            int otot = 0;
            for (int i = 0; i < k; i ++ ){
                int diceIdx = nums[ i ];
                int pos = origin[ i ];
                otot += dice[ diceIdx ][ pos ];

            }
            InSort( lst, otot );
        }
        for( int[] versus : comb ){
            int vtot = 0;
            for( int i = 0; i < k; i++ ){
                int diceIdx = vnums[ i ];
                int pos = versus[ i ];

                vtot += dice[ diceIdx ][ pos ];
            }
            InSort( vlst, vtot );
        }
        int win = 0, lose = 0;

        for( int i : lst ){

            if( vlst.isEmpty() || vlst.get( 0 ) > i ){
                continue;
            }else{
                int lt = 0, rt = vlst.size() - 1;
                int pos = -1;

                while( lt <= rt ){
                    int mid = ( lt + rt ) / 2;

                    if( vlst.get( mid ) >= i ){
                        rt = mid - 1;
                    }else{
                        pos = mid;
                        lt = mid + 1;
                    }
                }

                win += pos + 1;

            }





        }

        if( win > mx ){
            mx = win;
            for( int i = 0; i < k; i ++ ){
                answer[ i ] = nums[ i ] + 1;
            }
            Arrays.sort( answer );
        }

        return;
    }

    public void InSort( ArrayList< Integer > lst, int target ){
        if( lst.size() == 0 ){
            lst.add( target );
            return;
        }else if( lst.get( 0 ) > target ){
            lst.add( 0, target );
            return;
        }else if( lst.get( lst.size() - 1 ) < target ){
            lst.add( target );
            return;
        }


        int lt = 0; int rt = lst.size() - 1;
        int pos = -1;
        while( lt <= rt ){
            int mid = ( lt + rt ) / 2;

            if( lst.get( mid ) >= target ){
                pos = mid;
                rt = mid - 1;
            }else{
                lt = mid + 1;

            }
        }

        // System.out.println( lst );
        // System.out.println( "target = " + target + " , pos == " + pos );
        lst.add( pos, target );
        return;

    }


    public void getPerm( int depth, int[] arr ){
        if( depth == n / 2 ){
            comb.add( deepcopy( arr ) );
            return;
        }

        for( int i = 0; i < 6; i ++ ){
            arr[ depth ] = i;
            getPerm( depth + 1, arr );
            arr[ depth ] = 0;
        }
    }

    public int[] deepcopy( int[] arr ){
        int[] newArr = new int[ n / 2 ];
        for( int i = 0; i < n / 2; i ++ ){
            newArr[ i ] = arr[ i ];
        }
        return newArr;
    }
}