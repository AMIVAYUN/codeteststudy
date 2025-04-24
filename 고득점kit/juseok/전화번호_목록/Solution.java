import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Node root = new Node();
        
        for( String address: phone_book ){
            root.put( address );
        }
        
        for( String address: phone_book ){
            
            if( root.isSub( address ) ){
                continue;
            }else{
                answer = false; break;
            }
        }
        
        return answer;
    }
    
    
    class Node{
        boolean isEnd;
        HashMap<Character, Node > nodes;
        
        public Node(){
            this.nodes = new HashMap<>();
            isEnd = false;
        }
        
        public void put( String str ){
            Node pos = this;
            for( int i = 0; i < str.length(); i ++ ){
                pos.nodes.putIfAbsent( str.charAt( i ), new Node() );
                pos = pos.nodes.get( str.charAt( i ) );
                if( i == str.length() - 1 ){
                    pos.isEnd = true;
                }
            }
        }
        
        public boolean isSub( String str ){
            Node pos = this;
            for( int i = 0; i < str.length(); i ++ ){
                pos = pos.nodes.get( str.charAt( i ) );
            }
            
            return pos.nodes.isEmpty();
        }
    }
}
