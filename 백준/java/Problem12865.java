import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem12865 {
	static int[][] dp;

	static int[] W; // weight
	static int[] V; // value

	/**
	 * N 물건
	 * 무게 W
	 * 가치 V
	 *
	 * K 만큼의 무게만 가능
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		W = new int[N+1];
		V = new int[N+1];

		for (int i = 1; i <= N; i++) {  // 1부터 시작
			st = new StringTokenizer(br.readLine(), " ");
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		dp = new int[N+1][K + 1];
		int result = solve(N, K);
		System.out.println(result);
	}

	static int solve(int n, int k) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if(j < W[i]){ // 현재 물건의 무게가 j(현재 담을 수 있는 무게)보다 크면
					dp[i][j] = dp[i-1][j];
					continue;
				}

				// 이전까지의 값 | 현재 물건의 가치 + 이전 물건들 중 j - W[i] 무게에 담을 수 있는 최대 가치
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
			}
		}
		return dp[n][k];
	}
}
