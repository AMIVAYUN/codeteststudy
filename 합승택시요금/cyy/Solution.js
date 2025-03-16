function solution(n, s, a, b, fares) {
    const graph = Array.from({ length: n + 1 }, () => 
        Array.from({ length: n + 1 }, () => Infinity)
    );
    
    for (let i = 1; i <= n; i++) {
        graph[i][i] = 0;
    }
    
    for (const [c, d, f] of fares) {
        graph[c][d] = f;
        graph[d][c] = f; 
    }
    
    // 플로이드-워셜
    for (let k = 1; k <= n; k++) {
        for (let i = 1; i <= n; i++) {
            for (let j = 1; j <= n; j++) {
                if (graph[i][k] + graph[k][j] < graph[i][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }
    }
    
    let minFare = Infinity;
    
    for (let i = 1; i <= n; i++) {
        const fare = graph[s][i] + graph[i][a] + graph[i][b];
        minFare = Math.min(minFare, fare);
    }
    
    return minFare;
}
