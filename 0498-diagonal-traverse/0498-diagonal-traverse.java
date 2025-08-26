/**
    mat = m * n 매트리스 
    
 */
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat[0].length;
        int n = mat.length;

        int[] result = new int[m*n];
        int index = 0;
        int i = 0;
        int j = 0;       
        while(index < m*n) {

            // ↗
            while(validateLocation(m, n, i, j)) {
                result[index] = mat[i][j];
                index += 1;

                i -= 1;
                j += 1;
            }

            // revert
            i += 1;
            j -= 1;

            // move
            j +=1; 
            if (!validateLocation(m, n, i, j)) {
                j -=1;
                i +=1;
            }

            // ↙ 
            while(validateLocation(m, n, i, j)) {
                result[index] = mat[i][j];
                index += 1;

                i += 1;
                j -= 1;
            }

            // revert
            i -= 1;
            j += 1;

            // move
            i += 1;
            if (!validateLocation(m, n, i, j)) {
                i -= 1;
                j += 1;
            }
        }

        return result;
    }

    private boolean validateLocation(int m, int n, int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }
}