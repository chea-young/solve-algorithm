import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Solve_선인장숨기기 {

	public static void main(String[] args) throws IOException {
		Solution468379 solution = new Solution468379();
		// // 테스트 케이스 1
		// int[] answer1 = solution.solution(4, 5, 2, 2, new int[][]{{0, 0}, {3, 1}, {1, 3}, {2, 4}, {1, 1}, {2, 2}, {2, 3}, {0, 4}});
		// System.out.println("Test 1 - Expected: [2, 2], Result: [" + answer1[0] + ", " + answer1[1] + "]");
		//
		// // 테스트 케이스 2
		// int[] answer2 = solution.solution(3, 3, 1, 1, new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}});
		// System.out.println("Test 2 - Expected: [1, 1], Result: [" + answer2[0] + ", " + answer2[1] + "]");
		//
		// // 테스트 케이스 3
		// int[] answer3 = solution.solution(4, 6, 3, 4, new int[][]{{1, 2}});
		// System.out.println("Test 3 - Expected: [0, 0], Result: [" + answer3[0] + ", " + answer3[1] + "]");
		//
		// // 테스트 케이스 4
		// int[] answer4 = solution.solution(4, 6, 1, 2, new int[][]{{0, 1}, {0, 3}, {0, 5}, {1, 1}, {1, 3}, {1, 5}, {2, 1}, {2, 3}, {2, 5}, {3, 1}, {3, 3}, {3, 5}});
		// System.out.println("Test 4 - Expected: [3, 4], Result: [" + answer4[0] + ", " + answer4[1] + "]");
		//
		// // 테스트 케이스 5
		// int[] answer5 = solution.solution(2, 2, 2, 2, new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 0}});
		// System.out.println("Test 5 - Expected: [0, 0], Result: [" + answer5[0] + ", " + answer5[1] + "]");

		// 테스트 케이스 6
		int[] answer6 = solution.solution(4, 4, 3, 1, new int[][]{{2, 0}, {1, 3}, {3, 2}, {0, 1}});
		System.out.println("Test 6 - Expected: [0, 2], Result: [" + answer6[0] + ", " + answer6[1] + "]");
	}

	/**
	 * m,n 격자
	 * 선인장 크기: h,w (회전 불가)
	 * 비구름: 정해진 순서대로 비를 뿌림
	 * - 처음으로 선인장 구역에 떨어진 경우 청므으로 맞는 순간 기록 -> 가능한 늦게 맞기
	 *
	 * drops : 빗방울이 떠어지는 순서
	 *
	 * 1. 선인장이 비를 맞지 않도록
	 * 2. 선인장이 비를 맞는 경우, 가능한 늦게 맞도록 (여러 개 중에는 위쪽, 왼쪽)
	 */
	static class Solution468379 {
		private static int WIDTH = 0;
		private static int HEIGHT = 0;

		public int[] solution(int m, int n, int h, int w, int[][] drops) {
			int[] answer = {0, 0};
			WIDTH = w;
			HEIGHT = h;

			int[][] grid = new int[m][n]; // 0: 비구름 없음, 0보다 큰 수: 비구름이 떨어지는 순서, -1 : 조회
			// 비 맞는 위치 기록
			for (int i = 0; i < drops.length; i++) {
				grid[drops[i][0]][drops[i][1]] = i + 1;
			}

			// 위치 찾기
			Integer dropOrder = getDrops(grid, 0,0);;
			Queue<int[]> queue = new LinkedList<>();
			queue.add(new int[]{0, 0});
			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				int x = current[0];
				int y = current[1];

				// 비가 떨어지는 가장 늦은 케이스 확인
				int nowDrop = getDrops(grid, x, y);
				if (nowDrop != dropOrder) {
					if (nowDrop ==0) {
						return new int[]{x, y};
					} else {
						if (dropOrder < nowDrop) {
							dropOrder = nowDrop;
							answer = new int[]{x, y};
						}
					}
				}

				// 다음 위치 탐색 (오른쪽, 아래쪽)
				if (y + WIDTH < n) {
					queue.add(new int[]{x, y + 1});
				} else if (x + HEIGHT < m) {
					queue.add(new int[]{x + 1, 0});
				}
			}

			return answer;
		}

		// x, y 위치에 선인장을 놓았을 때, 비를 맞는 순서 반환
		// 선인장을 놓은 장소에서 가장 먼저 빗방울이 떨어진 순서를 기록, 없으면 0 반환
		private static Integer getDrops(int[][] grid, int x, int y) {
			Integer result = 0;

			for (int i = x; i < x + HEIGHT; i++) {
				for (int j= y; j < y+ WIDTH; j++) {
					int num = grid[i][j];
					if (num > 0 && (result == 0 || num < result)) {
						result = num;
					}
				}
			}

			return result;
		}
	}
}



