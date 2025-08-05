/**

fruits: 과일의 양 리스트
- 과일 종류의 수보다 크거나 같은 용량을 가진 가장 왼쪽 바구니에 담아야함.

baskets: 수용량 리스트
- 하나의 종류의 과일만 들 수 있음.

--> 배치가 되지 않은 과일 유형의 수 반환

 */
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        boolean[] checkedBaskets = new boolean [baskets.length];

        int result = 0;
        for (int fruit : fruits) {
            boolean isExistBasket = false;
            for (int j=0; j<baskets.length; j++) {
                if (checkedBaskets[j]) { // 이미 과일이 들어간 경우
                    continue;
                }

                if (fruit <= baskets[j]) {
                    checkedBaskets[j] = true;
                    isExistBasket = true;
                    break;
                }
            }
            
            if (!isExistBasket) {
                result += 1;
            }
        }

        return result;
    }
}