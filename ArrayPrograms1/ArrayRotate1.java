package ArrayPrograms1;
import java.util.Arrays;

public class ArrayRotate1 {

	public static int[] leftRotate(int[] arr,int n, int d) {
		int temp;
		for(int i=0;i<d;i++) {
			temp=arr[0];	
			for(int j=0;j<n-1;j++) {
				arr[j]=arr[j+1];
			}
			arr[n-1]=temp;
		}
		return arr;
	}
	
	public static int[] rightRotate(int[] arr,int n, int d) {
		int temp;
		for(int i=0;i<d;i++) {
			temp=arr[n-1];	
			for(int j=n-1;j>0;j--) {
				arr[j]=arr[j-1];
			}
			arr[0]=temp;
		}
		return arr;
	}
	
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		int n = arr.length;
		System.out.println("left Rotate : "+Arrays.toString(leftRotate(arr,n,2)));
		System.out.println("right Rotate : "+Arrays.toString(rightRotate(arr,n,3)));
	}

}
