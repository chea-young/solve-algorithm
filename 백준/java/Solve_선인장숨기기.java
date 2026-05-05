import java.util.*;

public class Solve_선인장숨기기 {

	public static void main(String[] args) throws Exception {
		Solution solution = new Solution();
		int[] a1 = solution.solution(4, 5, 2, 2, new int[][]{{0,0},{3,1},{1,3},{2,4},{1,1},{2,2},{2,3},{0,4}});
		System.out.println("Test 1 - Expected: [2, 2], Result: [" + a1[0] + ", " + a1[1] + "]");
		int[] a2 = solution.solution(3, 3, 1, 1, new int[][]{{0,0},{0,1},{0,2},{1,0}});
		System.out.println("Test 2 - Expected: [1, 1], Result: [" + a2[0] + ", " + a2[1] + "]");
		int[] a3 = solution.solution(4, 6, 3, 4, new int[][]{{1,2}});
		System.out.println("Test 3 - Expected: [0, 0], Result: [" + a3[0] + ", " + a3[1] + "]");
		int[] a4 = solution.solution(4, 6, 1, 2, new int[][]{{0,1},{0,3},{0,5},{1,1},{1,3},{1,5},{2,1},{2,3},{2,5},{3,1},{3,3},{3,5}});
		System.out.println("Test 4 - Expected: [3, 4], Result: [" + a4[0] + ", " + a4[1] + "]");
		int[] a5 = solution.solution(2, 2, 2, 2, new int[][]{{0,0},{0,1},{1,1},{1,0}});
		System.out.println("Test 5 - Expected: [0, 0], Result: [" + a5[0] + ", " + a5[1] + "]");
		int[] a6 = solution.solution(4, 4, 3, 1, new int[][]{{2,0},{1,3},{3,2},{0,1}});
		System.out.println("Test 6 - Expected: [0, 2], Result: [" + a6[0] + ", " + a6[1] + "]");
	}

	static class Solution {

		public int[] solution(int m, int n, int h, int w, int[][] drops) {
			// grid[i][j] = 해당 칸에 떨어지는 빗방울 순서 (없으면 0)
			int[][] grid = new int[m][n];
			for (int i = 0; i < drops.length; i++) {
				grid[drops[i][0]][drops[i][1]] = i + 1;
			}

			// 1단계: 각 행마다 너비 w 슬라이딩 윈도우 최솟값
			// rowMin[i][j] = grid[i][j..j+w-1] 구간의 비0 최솟값
			int[][] rowMin = new int[m][n];
			for (int i = 0; i < m; i++) {
				rowMin[i] = slidingWindowMin(grid[i], n, w);
			}

			// 2단계: 각 열마다 높이 h 슬라이딩 윈도우 최솟값
			// prefixMin[i][j] = rowMin[i..i+h-1][j] 구간의 비0 최솟값
			// = (i,j)에 선인장을 놓았을 때 가장 먼저 맞는 빗방울 순서
			int[][] prefixMin = new int[m][n];
			for (int j = 0; j < n; j++) {
				int[] col = new int[m];
				for (int i = 0; i < m; i++)  { // slidingWindowMin 함수가 행 단위로 작동하기 때문에 열 단위로 데이터를 재구성
					col[i] = rowMin[i][j];
				}
				int[] colMin = slidingWindowMin(col, m, h);
				for (int i = 0; i < m; i++) {
					prefixMin[i][j] = colMin[i];
				}
			}

			// 3단계: 결과 탐색 O(m*n)
			int[] answer = {0, 0};
			int maxDrop = 0;

			for (int x = 0; x <= m - h; x++) {
				for (int y = 0; y <= n - w; y++) {
					int nowDrop = prefixMin[x][y];
					if (nowDrop == 0) {
						return new int[]{x, y};  // 비를 안 맞는 위치 즉시 반환
					}
					if (nowDrop > maxDrop) {
						maxDrop = nowDrop;
						answer = new int[]{x, y};
					}
				}
			}

			return answer;
		}

		/**
		 * 1D 슬라이딩 윈도우 최솟값 (0은 "없음"으로 취급)
		 * result[j] = arr[j..j+k-1] 구간에서 0이 아닌 값들의 최솟값 (모두 0이면 0)
		 */
		private int[] slidingWindowMin(int[] arr, int n, int k) {
			int[] result = new int[n];
			// Deque에는 인덱스를 저장. 현재 위치로 부터 슬라이딩 윈도 크기 내의 index 0이 항상 슬라이딩 윈도우의 최소값이 되도록 관리하는 저장소
			Deque<Integer> deque = new ArrayDeque<>();

			for (int i = 0; i < n; i++) {
				// 윈도우를 벗어난 인덱스 제거
				while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
					deque.pollFirst();
				}

				// 0이 아닌 값에 대해서만 Deque 유지
				// 현재 값이 Deque 뒤쪽 값보다 작거나 같으면(단, 0 제외) 뒤쪽 제거
				if (arr[i] != 0) {
					while (!deque.isEmpty()) {
						int backIdx = deque.peekLast();
						// 뒤쪽이 0이거나, 현재 값이 더 작으면 제거
						if (arr[backIdx] == 0 || arr[i] <= arr[backIdx]) {
							deque.pollLast();
						} else {
							break;
						}
					}
					deque.addLast(i); // deque가 비어있다는 의미: 이전의 값이 모두 0이었다는 의미이기 때문에
				}

				// 윈도우가 완성된 이후부터 결과 기록
				if (i >= k - 1) {
					result[i - k + 1] = deque.isEmpty() ? 0 : arr[deque.peekFirst()];
				}
			}

			return result;
		}
	}
}