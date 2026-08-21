package practice;

public class num_5 {
    public int maxArea(int[] height) {
        // 双指针从两边开始
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left <= right) {
            int currentArea = (right - left) * Math.min(height[left],height[right]);
            if (height[left] <= height[right] ){
                left++;
            }else {
                right--;
            }
            maxArea = Math.max(currentArea,maxArea);
        }

        return maxArea;
    }
}
