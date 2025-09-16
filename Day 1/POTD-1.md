Day 1 – Replace Non-Coprimes in Array (LeetCode POTD)
=====================================================

### Problem Link

LeetCode – [Replace Non-Coprimes in Array](https://leetcode.com/problems/replace-non-coprime-numbers-in-array/submissions/1773143252/?envType=daily-question&envId=2025-09-16)

### My LeetCode Attempt
![My Attempt](POTD-1.png)

🧠 Iteration 1 – First Intuition
--------------------------------

**Intuition**My initial thought was simple:

*   Traverse through the list.
    
*   If two adjacent numbers are not coprime (i.e., GCD > 1), replace them with their LCM.
    
*   Keep moving forward.
    

I felt this greedy approach should work because at each step I’m merging pairs into one number.

**Code (1st attempt):**
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public static List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> l = new ArrayList<>();
        
        if (nums.length == 1) {
            l.add(nums[0]);
            return l;
        }

        for (int x : nums) {
            l.add(x);
        }

        int p = 0; 
        int q = 1; 

        while (q < l.size()) {
            int gcdval = gcd(l.get(p), l.get(q));
            
            if (gcdval > 1) {
                int lcm = (l.get(p) / gcdval) * l.get(q);

                l.remove(p);
                l.remove(p);
                l.add(p, lcm);

                if (p > 0) {
                    p--; 
                    q--; 
                }
            } else {
                p++;
                q++;
            }
        }      
        return l;
    }
    public static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
```

 **Mistakes / Learnings**

*   The logic broke down when merging created new non-coprimes earlier in the list.
    
*   I only checked the next pair once, instead of re-checking from the start after each merge.
    
*   This caused cases where the final answer was wrong because missed merges remained in the list.
    

🧠 Iteration 2 – Fix Attempt (Re-checking from Start)
-----------------------------------------------------

**Intuition**To fix the above mistake, I thought: _“What if after every merge, I reset and start checking from the beginning again?”_This way, I’d never miss any possible non-coprime pairs.

**Code (2nd attempt):**

```java
class Solution {
    public static List<Integer> replaceNonCoprimes(int[] nums) {

        List<Integer> l = new ArrayList<>();
        if( nums.length == 1){
            l.add(nums[0]);
            return l;
        }

        for( int x : nums){
            l.add(x);
        }

        int q = 1;
        int p = 0;

        while( q < l.size()){
            int gcdval = gcd(l.get(p), l.get(q));
            if( gcdval > 1){
                int lcm = (l.get(p) / gcdval)*l.get(q);
                l.remove(p);
                l.remove(p);
                l.add(p, lcm);
                q = 1;
                p = 0;
            }
            else{
                p++;
                q++;
            }
        }      
        return l;
    }

    public static int gcd(int a , int b){
        return (b == 0)? a : gcd(b, a%b);
    }
}
```

**Mistakes / Learnings**

*   This fixed the correctness issue, but now performance tanked.
    
*   On large inputs, I got **Time Limit Exceeded (TLE)** because I kept resetting to the start every time a merge happened.
    
*   Basically, too many repeated checks → inefficient.
    

🧠 Iteration 3 – Optimized Approach (Final Solution)
----------------------------------------------------

**Intuition**I realized the problem was with how I was re-checking.Instead of restarting the whole loop each time, I thought:

*   Use the list like a **stack**.
    
*   For each number:
    
    *   Compare it with the last element in the list.
        
    *   If GCD > 1, merge them into an LCM and continue checking backward.
        
    *   Otherwise, just push the number.
        

This way, merges propagate backwards automatically without resetting the entire list.

**Code (Final attempt):**

```java
class Solution {
    public static List<Integer> replaceNonCoprimes(int[] nums) {

        List<Integer> l = new ArrayList<>();
        if( nums.length == 1){
            l.add(nums[0]);
            return l;
        }

        for( int x : nums){
            while( l.size() > 0){
                int last = l.get(l.size()-1);
                int gcdval = gcd(last, x);

                if( gcdval == 1){
                    break;
                }
                l.remove(l.size()-1);
                x = (last/gcdval)*x;
            }
            l.add(x);
        }       
        return l;
    }

    public static int gcd(int a , int b){
        return (b == 0)? a : gcd(b, a%b);
    }
}
```

**Mistakes Fixed**

*   No missed merges (stack ensures backward propagation).
    
*   No TLE (no unnecessary restarts).
    
*   Correct and efficient solution. ✅
    

✨ Key Takeaways
---------------

*   Brute force logic sometimes works for small cases but fails in edge cases.
    
*   Fixing correctness can lead to performance issues → efficiency matters.
    
*   Using a **stack-based approach** gave me the clean and optimized solution.
    
*   Lesson: _Always balance between correctness and performance when iterating on solutions._
