/**

bank: 2진수 배열 
0: empty
1: 보안 장비

2개 보안 장비 사이에 1개의 레이저
- 

결론
- 총 레이저 수
 */
class Solution {
    public int numberOfBeams(String[] bank) {
        int m = bank[0].length();
        int n = bank.length;

        if (n == 1) {
            return 0;
        }

        int answer = 0;
    
        for (int i=0; i< n-1; i++) {
            int nextSecurityDeviceCnt = 0;  

            // 보안장비가 있는 열 찾기
            int pluxRow = 0;
            for (int row=i; row<n-1; row++) {
                nextSecurityDeviceCnt = countSecurityDevices(bank[row+1]);
                if (nextSecurityDeviceCnt != 0) {
                    break;
                }

                pluxRow++;
            }
            
            int now = countSecurityDevices(bank[i]);  
            // 레이저 수 더하기
            answer += nextSecurityDeviceCnt * now;

            // 그 다음 보안 장비가 있는 열도 이동시키기
            i += pluxRow;
        }

        return answer;
    }

    private int countSecurityDevices(String row) {
        int result = 0;

        for (int i=0; i<row.length(); i++) {
            if (row.charAt(i) == '1') {
                result += 1;
            }
        }

        return result;
    }
}