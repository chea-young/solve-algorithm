class Solution {
    public int triangularSum(int[] nums) {
        while (nums.length > 1) {
            int[] newNums = new int[nums.length-1];
            for (int i=0; i<nums.length-1; i++) {
                int sum = nums[i] + nums[i+1];
                if (sum >= 10) { // 한 자리 수로 만들기
                    sum -= 10;
                }

                if (sum != 0) {
                    newNums[i] = sum;
                }
            }

            nums = newNums;
        }

        return nums[0];
    }
}