import java.util.*;
class Solution {
    /*
        cpp java python
        backend frontend
        junior senior
        chicken pizza

        3 2 2 2 => 24
        + 위치 찾기 n개
        쿼리의 갯수? 최대 10만

        10만번 돌면서 위치를 찾아서 그안에서 선형으로 찾는다? ->
        위험할 듯


    */
    public HashMap< String, ArrayList<Integer> > map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[ query.length ];

        for( String i : info ){
            String[] a = i.split(" ");
            subset( 0, a, new StringBuilder() );
        }


        int idx = 0;
        for( String q : query ){
            String[] a = q.split(" and ");
            StringBuilder sb = new StringBuilder();
            for( int i = 0; i < a.length - 1; i ++ ){
                sb.append( a[ i ] );
            }
            String[] re = a[ a.length - 1 ].split( " " );
            sb.append( re[ 0 ] );
            int num = Integer.parseInt( re[ 1 ] );
            ArrayList<Integer> lst = map.getOrDefault( sb.toString(), new ArrayList() );
            int ans = binarySearch( lst, num );
            if( ans == -1 ){
                answer[ idx ++ ] = 0;
            }else{
                answer[ idx ++ ] = lst.size() - ans;
            }

        }



        return answer;
    }

    public Integer binarySearch( ArrayList< Integer > lst, Integer number ){
        // System.out.println( lst.toString() + " , num = " + number );
        if( lst.size() == 0 ) return -1;
        if( lst.get( lst.size() - 1 ) < number ) return lst.size();
        if( lst.get( 0 ) >= number ) return 0;

        int lt = 0; int rt = lst.size() - 1;
        int ans = lst.size() - 1;
        while( lt <= rt ){
            int mid = ( lt + rt ) / 2;

            if( lst.get( mid ) >= number ){
                rt = mid - 1;
                ans = mid;
            }else{
                lt = mid + 1;
            }

        }


        return ans;
    }


    public void subset( int depth, String[] info, StringBuilder sb ){

        if( info.length == depth + 1 ){
            Integer num = Integer.parseInt( info[ depth ] );
            ArrayList< Integer > lst = map.getOrDefault( sb.toString(), new ArrayList<>() );
            int idx = binarySearch( lst, num );
            if( idx == -1 || idx == 0 ){
                lst.add( 0, num );
            }else{
                lst.add( idx, num );
            }
            map.put( sb.toString(), lst );
            return;
        }

        int originLen = sb.length();
        sb.append( info[ depth ] );
        subset( depth + 1, info, sb );
        sb.setLength( originLen );
        sb.append( "-" );
        subset( depth + 1, info, sb );
    }
}