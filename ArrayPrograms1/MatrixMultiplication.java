package ArrayPrograms1;

public class MatrixMultiplication {

	public static void main(String[] args) {
		int[][] mat1 = {{1,2},{3,4}};
		int[][] mat2 = {{5,6,7},{8,9,10}};
		int[][] multiply = new int[mat1.length][mat2[0].length];
		
		for(int i=0;i<mat1.length;i++) {
			for(int j=0;j<mat2[0].length;j++) {
				for(int k=0;k<mat2.length;k++) {
					multiply[i][j]+=mat1[i][k]*mat2[k][j];
				}
			}
		}
		
		for(int i=0;i<multiply.length;i++) {
			for(int j=0;j<multiply[0].length;j++) {
				System.out.print(multiply[i][j]+" ");
			}
			System.out.println();
		}
	}

}
