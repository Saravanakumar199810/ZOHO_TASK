package ArrayPrograms1;

import java.util.Arrays;

public class ArrayMedian {
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double val=0;
        int mid,size=nums1.length;
        int[] nums3=Arrays.copyOf(nums1, nums1.length+nums2.length);
        for(int i=0;i<nums2.length;i++) {
        	nums3[size]=nums2[i];
        	size++;
        }
        Arrays.sort(nums3);
        if(nums3.length%2==0) {
        	mid=nums3.length/2;
        	double ans=((nums3[mid-1])+(nums3[mid]))/2.0;
        	return ans;
        }
        else {
        	val=Math.ceil(nums3.length/2);
        	mid=(int)val;
        	return nums3[mid];
        }
    }

	public static void main(String[] args) {
		int[] num1= {1,4};
		int[] num2= {13,8};
		ArrayMedian an = new ArrayMedian();
		System.out.println(an.findMedianSortedArrays(num1, num2));
	}

}
