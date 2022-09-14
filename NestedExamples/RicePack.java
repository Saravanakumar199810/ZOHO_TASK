package NestedExamples;
import java.math.BigInteger;

public class RicePack {
	public static void main(String[] args) {
		BigInteger bigint = BigInteger.valueOf(2);
		for(int i=2;i<=64;i++) {
			bigint= bigint.multiply(bigint.valueOf(2));
		}
		System.out.println(bigint);
	}
}
