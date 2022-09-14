package ArrayPrograms1;
import java.util.Arrays;

public class NumberWeightage {

	public static void main(String[] args) {
		int[] nums = {49,36,8,10,12};
		int[][] weights = new int[nums.length][2];
		int sum,temp;
		double val=0;
		for(int i=0;i<nums.length;i++) {
			weights[i][0]=nums[i];
			val=Math.sqrt(nums[i]);
			sum=0;
			if(nums[i]==val*val)
				sum+=5;
			if(nums[i]%4==0 && nums[i]%6==0)
				sum+=4;
			if(nums[i]%2==0)
				sum+=3;
			weights[i][1]=sum;
		}
		for(int i=0;i<nums.length-1;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(weights[i][1]<weights[j][1]) {
					temp=weights[i][1];
					weights[i][1]=weights[j][1];
					weights[j][1]=temp;
					temp=weights[i][0];
					weights[i][0]=weights[j][0];
					weights[j][0]=temp;
				}
			}
		}
		System.out.println(Arrays.deepToString(weights));

	}

}
