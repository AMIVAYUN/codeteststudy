// 풀이 잘봤읍니다..
// 그거 보고나서 품
function solution(n, tops) {
    let dp = new Array((n * 2) + 2).fill(0);
    dp[0] = 1;
    dp[1] = 1;
    
    for (let i = 2; i <= n * 2 + 1; i++) {
        if ((i % 2 === 0) && (tops[i / 2 - 1] === 1)) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 10007;
        } else {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }
    }
    
    return dp[n * 2 + 1];
}
