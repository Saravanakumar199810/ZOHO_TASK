package NestedExamples;
import java.util.Arrays;

public class Nestedfor {

	public static void main(String[] args) {

		// sum of array values is equals to target.
		// It print the index of two values.

		int[] nums = { 2, 7, 11, 15 };
		int target = 9;
		int[] ans = new int[2];
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				if (i != j) {
					if (nums[i] + nums[j] == target) {
						ans[0] = i;
						ans[1] = j;
					}
				}
			}
		}
		System.out.println(Arrays.toString(ans));
	}

}
