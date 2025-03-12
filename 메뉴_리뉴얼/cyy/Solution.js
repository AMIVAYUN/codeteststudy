function solution(orders, course) {
    const answer = [];
    
    const sortedOrders = orders.map(order => 
        order.split('').sort().join('')
    );
    
    course.forEach(len => {
        const map = new Map();
        
        // 각 주문에 대해 가능한 조합을 생성
        sortedOrders.forEach(order => {
            comb(order, len).forEach(combo => {
                map.set(combo, (map.get(combo) || 0) + 1);
            });
        });
                
        let maxCount = 2; // 최소 2번 이상 주문
        map.forEach((count) => {
            maxCount = Math.max(maxCount, count);
        });
        
        if (maxCount < 2) return;
        
        map.forEach((count, combo) => {
            if (count === maxCount) {
                answer.push(combo);
            }
        });
    });
    
    return answer.sort();
}

function comb(str, size) {
    const results = [];
    
    function backtrack(start, cur) {
        if (cur.length === size) {
            results.push(cur);
            return;
        }
        
        for (let i = start; i < str.length; i++) {
            backtrack(i + 1, cur + str[i]);
        }
    }
    
    backtrack(0, '');
    return results;
}
