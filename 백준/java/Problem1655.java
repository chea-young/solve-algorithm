import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1655 {
	private static Queue<Integer> leftNums = new PriorityQueue<>((o1,o2) -> o2-o1); // 최대힙 (큰수가 index 0)
	private static Queue<Integer> rightNums = new PriorityQueue<>((o1,o2) -> o1-o2); // 최소힙 (작은 수가 index 0)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			Integer num = Integer.parseInt(st.nextToken());

			sb.append(findMiddle(num)+"\n");
		}

		System.out.println(sb.toString());
	}

	private static int findMiddle(Integer num) {
		if(leftNums.size() == rightNums.size()) {
			leftNums.add(num);
		} else {
			rightNums.add(num);
		}

		// maxHeap이 더 클 경우 원소 switch
		if(!leftNums.isEmpty() && !rightNums.isEmpty()) {
			if(leftNums.peek() > rightNums.peek()) {
				int tmp = leftNums.poll();
				leftNums.offer(rightNums.poll());
				rightNums.offer(tmp);
			}
		}

		return leftNums.peek();
	}
}
