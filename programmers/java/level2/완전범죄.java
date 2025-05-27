/**
 * A도둑 B도둑
 * -> 흔적을 최소화
 *
 * A도둑
 * - info[i][0] 흔적
 * - 경찰: 흔적이 n 이상
 * B도둑
 * - info[i][1] 흔적
 * - 경찰: 흔적이 m 이상
 *
 * 흔적의 수는 1 이상 3이하
 * return A 도둑이 남긴 누적 개수 최소값, 불가능한 경우 -1
 */
class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        Trace trace = new Trace(info, n, m);
        
        trace.stealThings(0, 0, 0);
        
        return trace.minATrace == null ? -1 : trace.minATrace;
    }
    
    class Trace {
        private int[][] info;
        private int n;
        private int m;
        public Integer minATrace;
        private Integer[][] memo; // 메모이제이션 배열
        
        public Trace(int[][] info, int n, int m) {
            this.info = info;
            this.n = n;
            this.m = m;
            this.memo = new Integer[info.length + 1][n + 1]; // A 도둑의 흔적 수에 대한 메모이제이션
        }
        
        public void stealThings(int index, int aTrace, int bTrace) {
            if (index == info.length) {
                if (minATrace == null) {
                    minATrace = aTrace;
                } else {
                    minATrace = Math.min(minATrace, aTrace);
                }
                
                return;
            }
            
            // 메모이제이션 체크
            if (memo[index][aTrace] != null && memo[index][aTrace] == bTrace) { // (2)
                return; // 이미 계산된 상태
            }
            memo[index][aTrace] = bTrace; // 현재 상태 저장
            
            // A
            int changedA = aTrace + info[index][0];
            if (changedA < n) {
                if (minATrace == null || minATrace > changedA) { // (1)
                    stealThings(index+1, changedA, bTrace);
                }
            }
            
            // B
            int changedB = bTrace + info[index][1];
            if (changedB < m) {
                stealThings(index+1, aTrace, changedB);
            }
        }
    }
}
