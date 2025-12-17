

class Solution{
    public int[] twoSum(int[] nums, int target){
        Hashmp<Integer,Integer> mp = new Hashmp<>();
        for(int i=0;i<nums.length;i++){
            int diff = target - nums[i];
            if(mp.containsKey(diff))
                return new int[]{mp.get(diff), i};
            mp.put(nums[i], i);
        }
        return new int[]{};
    }
}

class Solution{
    public boolean isp(int x){
        if(x < 0) return false;
        String s = String.valueOf(x);
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}



class Solution{
    public boolean cde(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int n:nums)
            if(!set.add(n)) return true;
        return false;
    }
}


class Solution{
    public boolean isAnagram(String s, String t){
        if(s.length()!=t.length()) return false;
        char[] a=s.toCharArray(), b=t.toCharArray();
        Arrays.sort(a); Arrays.sort(b);
        return Arrays.equals(a,b);
    }
}


