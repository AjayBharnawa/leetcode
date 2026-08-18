import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int windows = n - k + 1;

        Map<Integer, List<Integer>> map = new HashMap<>();

        // Store all positions of each number
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        int ans = -1;

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int value = entry.getKey();
            List<Integer> indices = entry.getValue();

            int covered = 0;
            int left = -1;
            int right = -1;

            for (int index : indices) {
                // Range of window starting positions
                // where nums[index] is included
                int L = Math.max(0, index - k + 1);
                int R = Math.min(index, windows - 1);

                if (left == -1) {
                    left = L;
                    right = R;
                } 
                else if (L > right + 1) {
                    covered += right - left + 1;
                    left = L;
                    right = R;
                } 
                else {
                    right = Math.max(right, R);
                }
            }

            if (left != -1) {
                covered += right - left + 1;
            }

            // Appears in exactly one subarray of size k
            if (covered == 1) {
                ans = Math.max(ans, value);
            }
        }

        return ans;
    }
}