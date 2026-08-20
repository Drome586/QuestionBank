package practice;

import java.util.Arrays;
import java.util.HashSet;

public class num_3 {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLen = 1;
        for (int num : numSet) {
            // 只有是起始点的时候才会进入到循环里面查找
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int tempLen = 1;
                while (numSet.contains(currentNum + 1)) {
                    tempLen += 1;
                    currentNum += 1;
                }
                maxLen = Math.max(maxLen, tempLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        num_3 num3 = new num_3();
        num3.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
    }
}
