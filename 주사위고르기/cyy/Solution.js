// 모든 주사위 조합
function getComb(arr, size) {
  const result = [];

  function combine(start, cur) {
    if (cur.length === size) {
      result.push([...cur]);
      return;
    }

    for (let i = start; i < arr.length; i++) {
      cur.push(arr[i]);
      combine(i + 1, cur);
      cur.pop();
    }
  }

  combine(0, []);
  return result;
}

// 모든 합 + 횟수 Map으로 구해서 배열로 반환
function calculateAllSums(dice, index) {
  const sumCounts = new Map();

  function dfs(idx, currentSum) {
    if (idx === index.length) {
      sumCounts.set(currentSum, (sumCounts.get(currentSum) || 0) + 1);
      return;
    }

    const diceIdx = index[idx];
    for (const face of dice[diceIdx]) {
      dfs(idx + 1, currentSum + face);
    }
  }

  dfs(0, 0);
  return Array.from(sumCounts.entries());
}

function solution(dice) {
  const n = dice.length;
  const half = n / 2;

  const combs = getComb(
    [...Array(n).keys()].map((i) => i + 1),
    half
  );

  let maxWinRate = -1;
  let bestComb = [];

  for (const combA of combs) {
    const combB = [...Array(n).keys()]
      .map((i) => i + 1)
      .filter((num) => !combA.includes(num));

    const aIdx = combA.map((i) => i - 1);
    const bIdx = combB.map((i) => i - 1);

    const aSums = calculateAllSums(dice, aIdx);
    const bSums = calculateAllSums(dice, bIdx);

    let wins = 0;
    let total = 0;

    for (const [sumA, countA] of aSums) {
      for (const [sumB, countB] of bSums) {
        const cases = countA * countB;
        if (sumA > sumB) wins += cases;
        total += cases;
      }
    }

    const winRate = wins / total;

    if (winRate > maxWinRate) {
      maxWinRate = winRate;
      bestComb = [...combA];
    }
  }

  return bestComb;
}
