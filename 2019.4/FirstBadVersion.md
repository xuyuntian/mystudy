# First Bad Version
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

**Example:**
```
Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 
```

# 思路
1. 进行二分查找，查找第一个满足条件的元素。

```
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        //二分查找查找第一个。
        int obj = -1;
        int left = 1,right = n,mid;
        do{
            mid = left+(right-left)/2;
            if(isBadVersion(mid)){
                obj = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
            
        }while(left<=right);
        return obj;
    }
    
}
```