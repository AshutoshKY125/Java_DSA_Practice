import java.util.*;

class Solution {

    public static List<Integer> replaceNonCoprimes(int[] nums) {

        // Final optimized solution using a stack-like approach

        List<Integer> l = new ArrayList<>();

        // Edge case: if there's only one number, return it directly
        if (nums.length == 1) {
            l.add(nums[0]);
            return l;
        }

        // Traverse through each number in the array
        for (int x : nums) {

            // While stack (list) has elements, check with the last one
            while (l.size() > 0) {
                int last = l.get(l.size() - 1);

                // Calculate GCD of current number and last element in the list
                int gcdval = gcd(last, x);

                // If they are coprime (GCD == 1), stop merging
                if (gcdval == 1) {
                    break;
                }

                // If not coprime:
                // Remove the last element and merge both into LCM
                l.remove(l.size() - 1);
                x = (last / gcdval) * x;  // LCM formula adjustment
            }

            // Push the current number (either as-is or merged value) into the list
            l.add(x);
        }

        // Return the final list after all possible merges
        return l;
    }

    // Utility function to calculate GCD (Euclidean algorithm)
    public static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
