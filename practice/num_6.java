package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class num_6 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 数组已经有序，最小的数都大于 0 时，后面不可能再凑出 0。
            if (nums[i] > 0) {
                break;
            }
            // 相同的第一个数会得到相同的三元组，直接跳过。
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                long sum = (long) nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 跳过相同的第二、第三个数，防止答案重复。
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        num_6 num6 = new num_6();
        num6.threeSum(new int[]{-1,0,1,2,-1,-4});
    }
}
