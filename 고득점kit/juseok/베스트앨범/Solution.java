import java.util.*;
class Solution {
    /**
    
    속한 노래가 많이 재생된 장르를 먼저 수록
    장르 내에서 많이 재생된 노래를 먼저 수록
    장르 내에서 재생 횟수가 같으면 고유번호가 낮은 노래를 먼저 수록
    
    */
    HashMap<String, Integer> map = new HashMap<>();
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List< Music > lst = new ArrayList<>();
        for( int i = 0; i < plays.length; i ++ ){
            String genre = genres[ i ];
            int play = plays[ i ];
            map.put( genre, map.getOrDefault( genre, 0 ) + play );
            Music music = new Music( i, play, genre );
            lst.add( music );
        }
        
        PriorityQueue< Music > pq = new PriorityQueue<>( (p1, p2) -> {
            if( map.get( p1.genre ) == map.get( p2.genre ) ){
                if( p1.play == p2.play ){
                    return Integer.compare( p1.idx, p2.idx ); 
                }
                return Integer.compare( p2.play, p1.play );
            } 
            return Integer.compare( map.get( p2.genre ), map.get( p1.genre ) );
        });
        
        for( Music music : lst ){
            pq.add( music );
        }
        
        int i = 0;
        HashMap<String, Integer> counter = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        while( !pq.isEmpty() ){
            Music music = pq.poll();
            
            if( counter.getOrDefault( music.genre, 0 ) < 2 ){
                counter.put( music.genre, counter.getOrDefault( music.genre, 0 ) + 1 );
                result.add( music.idx );
            }
        }
        answer = new int[ result.size() ];
        for( int num : result ){
            answer[ i ++ ] = num;
        }
        return answer;
    }
    
    class Music{
        int idx;
        int play;
        String genre;
        
        public Music( int idx, int play, String genre ){
            this.idx = idx;
            this.play = play;
            this.genre = genre;
        }
    }
}
