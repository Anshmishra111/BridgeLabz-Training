package Leetcode;

import java.util.Arrays;
import java.util.Scanner;

public class TwoSum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[] nums = new int[size];
		for(int i = 0; i < size; i++) {
			nums[i] = sc.nextInt();
		}
		int target = sc.nextInt();
		System.out.println(Arrays.toString(twoSum(nums, target)));
		sc.close();
	}

	public static int[] twoSum(int[] nums, int target) {
		int n=nums.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
	}
}
