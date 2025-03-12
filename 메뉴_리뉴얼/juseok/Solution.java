import java.util.*;

/**
 * 메뉴 조합을 찾는 솔루션 클래스
 * 최소 두명의 손님이 주문한 조합을 찾습니다.
 */
class Solution {
    // 각 길이별 메뉴 조합의 출현 횟수를 저장
    private Map<Integer, Map<String, Integer>> combinationCounts = new HashMap<>();
    // 각 길이별 가장 많이 주문된 메뉴 조합 목록
    private Map<Integer, List<String>> mostOrderedCombinations = new HashMap<>();
    // 각 길이별 최대 주문 횟수
    private int[] maxOrderCounts;

    public String[] solution(String[] orders, int[] course) {
        // 초기화
        int maxCourseLength = course[course.length - 1];
        maxOrderCounts = new int[maxCourseLength + 1];

        // 각 주문에 대해 처리
        for (String order : orders) {
            // 알파벳 순으로 정렬
            char[] charArray = order.toCharArray();
            Arrays.sort(charArray);
            String sortedOrder = new String(charArray);

            // 각 코스 길이별로 조합 생성
            for (int courseLength : course) {
                findCombinations(new StringBuilder(), courseLength, 0, sortedOrder);
            }
        }

        // 결과 수집
        List<String> result = new ArrayList<>();
        for (int courseLength : course) {
            // 최소 2명 이상의 손님이 주문한 조합만 포함
            if (maxOrderCounts[courseLength] <= 1) continue;

            // 해당 길이의 가장 많이 주문된 조합들 추가
            List<String> combinations = mostOrderedCombinations.get(courseLength);
            if (combinations != null) {
                result.addAll(combinations);
            }
        }

        // 알파벳 순으로 정렬
        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    /**
     * DFS를 이용해 가능한 조합을 생성하고 카운트하는 메서드
     */
    private void findCombinations(StringBuilder current, int targetLength, int startIndex, String order) {
        // 목표 길이에 도달했을 때
        if (current.length() == targetLength) {
            String combination = current.toString();

            // 해당 길이의 조합 맵 가져오기
            Map<String, Integer> lengthCombinations = combinationCounts.computeIfAbsent(
                    targetLength, k -> new HashMap<>());

            // 조합 카운트 증가
            int count = lengthCombinations.getOrDefault(combination, 0) + 1;
            lengthCombinations.put(combination, count);

            // 최대 주문 횟수 갱신 및 목록 업데이트
            if (count > maxOrderCounts[targetLength]) {
                // 새로운 최대값 설정
                maxOrderCounts[targetLength] = count;

                // 새 목록 생성
                List<String> newList = new ArrayList<>();
                newList.add(combination);
                mostOrderedCombinations.put(targetLength, newList);
            } else if (count == maxOrderCounts[targetLength]) {
                // 기존 최대값과 같으면 목록에 추가
                mostOrderedCombinations.computeIfAbsent(
                        targetLength, k -> new ArrayList<>()).add(combination);
            }
            return;
        }

        // 모든 가능한 조합 탐색
        for (int i = startIndex; i < order.length(); i++) {
            current.append(order.charAt(i));
            findCombinations(current, targetLength, i + 1, order);
            current.deleteCharAt(current.length() - 1);
        }
    }
}