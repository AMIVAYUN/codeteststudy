// 무식한 문제
function solution(n, q, ans) {
    let count = 0;
    const visited = new Array(n + 1).fill(false);
    
    function backtrack(start, depth) {
        if(depth === 5){
            for (let i = 0; i < q.length; i++) {
                let match = 0;
                for (const num of q[i]) {
                    if (visited[num]) {
                        match++;
                    }
                }
                if (match !== ans[i]) {
                    return;
                }
            }
            count++;
            return;
        }
        
        for (let i = start; i <= n; i++) {
            visited[i] = true;
            backtrack(i + 1, depth + 1);
            visited[i] = false;
        }
    }
    
    backtrack(1, 0);
    return count;
}
