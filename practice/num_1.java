package practice;

import java.util.HashMap;
import java.util.Map;

public class num_1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndexMap.containsKey(target - nums[i])) {
                return new int[]{numToIndexMap.get(nums[i]), i};
            } else {
                numToIndexMap.put(nums[i], i);
            }
        }
        return new int[]{};
    }
}
