/**
dimensions: 2차원 정수 배열
- dimensions[i][0]: 길이
- dimensions[i][1]: 직사각형의 너비 i

return: 가장 긴 대각선
 */
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int result = 0;
        double maxDiagonal = 0;

        for (int[] dimension : dimensions) {
            double diagonal = Math.sqrt(dimension[0]*dimension[0] + dimension[1]*dimension[1]);

            if (maxDiagonal < diagonal) {
                maxDiagonal = diagonal;

                result = dimension[0]*dimension[1];
            }
            else if (maxDiagonal == diagonal) {
               int newValue = dimension[0]*dimension[1];
                if (newValue > result) {
                    result = newValue;
                }
            }
        }

        return result;
    }
}