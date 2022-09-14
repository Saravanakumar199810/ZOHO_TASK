package ArrayPrograms1;

public class Relationship1 {

	public static void main(String[] args) {
		String[][] arr = {{"luke","shaw"},{"wayne","rooney"},{"rooney","ronaldo"},{"shaw","rooney"}};
		int count=0, max=0;
		String grandfather="",father="";
		for(int i=0;i<arr.length;i++) {
			father=arr[i][0];
			count=0;
			for(int j=0;j<arr.length;j++) {
				if(arr[j][1].equals(father)) {
					count++;
					if(max<count) {
						max=count;
						grandfather=arr[i][1];
					}
				}
			}
		}
		System.out.println(grandfather+" has "+max+" Grand Childrens");
	}

}
