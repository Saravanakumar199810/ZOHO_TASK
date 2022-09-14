package ArrayPrograms1;

public class StringPattern1 {

	public static void main(String[] args) {
		String str = "PROGRAM";
		if (str.length() % 2 == 0) {
			System.out.println(0);
		} else {
			char[] ch = str.toCharArray();
			for (int i = 0; i < str.length(); i++) {
				for (int j = 0; j < str.length(); j++) {
					if (i == j || j == str.length() - i - 1) {
						System.out.print(ch[j] + " ");
					} else {
						System.out.print("  ");
					}
				}
				System.out.println();
			}
		}
	}

}
