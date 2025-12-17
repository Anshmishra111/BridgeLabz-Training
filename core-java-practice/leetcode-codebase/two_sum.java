
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











