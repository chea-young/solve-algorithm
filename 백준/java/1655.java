
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 동생이 말해야 하는 수: 지금까지 말한 수의 중간 값
 * 중간값
 * - 짝수: 두 수 중 작은 수
 * <p>
 * 출력
 * - 동생이 말해야 하는 수 구하는 프로그램 (maxHeap 의 top이 중간 값을 가지게 됨)
 *
 * [값 넣기]
 * 두 개의 PriorityQueue의 크기가 같은 경우 maxHeap에 입력된 값을 추가한다.
 * - 입력한 값이 minHeap의 최솟값보다 크다면 둘을 swap 한다.
 * 두 개의 크기가 다른 경우 minHeap에 입력된 값을 추가한다.
 * - 입력한 값이 maxHeap의 최댓값보다 작다면 둘을 swap 한다.
 *
 * [swap 하기]
 * minHeap
 */

public class Solve1655 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());


        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);  // 작은 값이 우선순위
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 큰 값이 우선순위
        for (int i = 0; i < N; i++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            // 중간 값 찾기
            if(minHeap.size() == maxHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            if(!minHeap.isEmpty() && !maxHeap.isEmpty())
                if(minHeap.peek() < maxHeap.peek()){ // swap
                    int tmp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(tmp);
                }

            System.out.println(maxHeap.peek());
        }

    }
}
