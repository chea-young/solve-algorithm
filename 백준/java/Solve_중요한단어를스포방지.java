import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solve_중요한단어를스포방지 {

	/**
	 * 왼쪽 -> 오른쪽 하나씩 클릭하여 중요한 단어가 몇개 인지
	 * 단어는 공백 , 알파벳 + 숫자
	 **/
	class Solution {
		public int solution(String message, int[][] spoiler_ranges) {
			char[] hideMessageArray = message.toCharArray();

			// hide string
			for (int [] range : spoiler_ranges) {
				for (int i = range[0]; i <= range[1]; i++) {
					if (hideMessageArray[i] == ' ') {
						continue;
					}

					hideMessageArray[i] = '*';
				}
			}

			Set<String> uniqueStrings = getUniqueStrings(hideMessageArray);

			int answer = 0;
			char[] messageArray = message.toCharArray();
			for (int [] range : spoiler_ranges) {
				for (int i = range[0]; i <= range[1]; i++) {
					hideMessageArray[i] = messageArray[i];
				}

				Set<String> newUniqueStrings = getUniqueStrings(hideMessageArray);
				if (uniqueStrings.size() < newUniqueStrings.size()) {
					answer += newUniqueStrings.size() - uniqueStrings.size();
					uniqueStrings = newUniqueStrings;
				}
			}

			return answer;
		}

		Set<String> getUniqueStrings(char[] hideString) {
			String strings = new String(hideString);
			List<String> uniqueStrings = new ArrayList<>();

			for (String str : strings.split(" ")) {
				if (str.contains("*")) {
					continue;
				}
				uniqueStrings.add(str);
			}

			return new HashSet<>(uniqueStrings);
		}
	}

	public static void main(String[] args) throws IOException {
		// String message = "here is muzi here is a secret message";
		// int[][] spoiler_ranges = {{0, 3}, {23,28}};

		String message = "my phone number is 01012345678 and may i have your phone number";
		int[][] spoiler_ranges = {{5,5}, {25,28}, {34, 40}, {53, 59}};

		Solution solution = new Solve_중요한단어를스포방지().new Solution();
		System.out.println(solution.solution(message, spoiler_ranges));
	}
}
