function solution(n, bans) {
    bans.sort((s1, s2) => {
        if (s1.length === s2.length) {
            return s1.localeCompare(s2);
        }
        return s1.length - s2.length;
    });
    
    for (const ban of bans) {
        const pos = getPos(ban);
        if (pos <= n) {
            n++;
        }
    }
    
    return generate(n);
}

function getPos(str) {
    let result = 0;
    
    for (let i = 0; i < str.length; i++) {
        const c = str.charAt(i);
        result *= 26;
        result += c.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
    }
    
    return result;
}

function generate(n) {
    let result = "";
    
    while (n > 0) {
        n--;
        result = String.fromCharCode('a'.charCodeAt(0) + (n % 26)) + result;
        n = Math.floor(n / 26);
    }
    
    return result;
}
