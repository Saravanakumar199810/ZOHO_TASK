package BitwiseExamples;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class BitwiseProblems1 {
	
	public static void swap() {
		int a=10,b=5;
		System.out.println("Befor Swap : a = "+a+" : b = "+b);
		a^=b;
		b^=a;
		a^=b;
		System.out.println("After Swap : a = "+a+" : b = "+b);
	}
	
	public static void duplicate() {
		int[] arr = {1,2,3,2,5,6,7,5};
		ArrayList<Integer> al = new ArrayList<Integer>();
		Arrays.sort(arr);
		for(int i=0;i<arr.length;i++) {
			if(i!=arr.length-1 && (arr[i]^arr[i+1])!=0) {
				al.add(arr[i]);
			}
			else if(i==arr.length-1 && (arr[i]^arr[i-1])!=0) {
				al.add(arr[i]);
			}
		}
		System.out.println(al);
	}
	
	public static void oddEven() {
		int n=6;
		if((n & 1)!=1) 
			System.out.println("Even number");
		else
			System.out.println("Odd number");
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1=swap\n2=duplicate\n3=Oddeven :");
		int num=sc.nextInt();
		
		switch (num){
			case 1:
				swap();
				break;
			case 2:
				duplicate();
				break;
			case 3:
				oddEven();
				break;
			default:
				System.out.println("Wrong value");
		}
	}

}
