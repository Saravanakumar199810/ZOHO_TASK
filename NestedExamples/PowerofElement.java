package NestedExamples;
import java.util.Scanner;

public class PowerofElement {
	public static void main(String[] args) {
		int val,pow;
		Scanner sc = new Scanner(System.in);
		val=sc.nextInt();
		pow=sc.nextInt();
		System.out.println("The value is : "+(val<<pow-1));
	}
}
