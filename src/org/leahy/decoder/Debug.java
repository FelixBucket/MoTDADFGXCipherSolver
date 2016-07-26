package org.leahy.decoder;

public class Debug {
	public static void printArray(String[][] s) {
		for(int y = 0; y < s.length; y++) {
			for(int x = 0; x < s[0].length; x++) {
				//if(s[y][x].compareTo("j") != 0) {
					if(x == s[0].length - 1) {
						if(s[y][x].compareTo("j") != 0) {
							System.out.println(s[y][x]);
						} else {
							System.out.println("");
						}
					} else {
						if(s[y][x].compareTo("j") != 0) {
							System.out.print(s[y][x]);
						} else {
							System.out.print(s[y][x]);
						}
					}
				//}
			}
		}
	}
	
	public static void printMatrix(String[] s) {
		for(int x = 0; x < s.length; x++) {
			if(x == 4 || x == 9 || x == 14 || x == 19 || x == 24) {
				System.out.println(s[x]);
			}else{
				System.out.print(s[x] + " ");
			}
		}
	}
}
