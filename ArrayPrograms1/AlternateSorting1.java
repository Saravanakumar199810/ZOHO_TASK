package ArrayPrograms1;
import java.util.Arrays;

public class AlternateSorting1 {

	public static void main(String[] args) {
		int[] nums = {1,2,1,4,5,6,8,8};
		int[] altersort = new int[nums.length];
		Arrays.sort(nums);
		int min=(nums.length%2==0)?(nums.length/2)-1:nums.length/2;
		int max=min+1;
		for(int i=0;i<nums.length;i++) {
			altersort[i]=nums[min];
			if(min>0)min--;
			i++;
			if(max<nums.length)
			altersort[i]=nums[max];
			max++;
		}
		System.out.println(Arrays.toString(altersort));
	}

}
