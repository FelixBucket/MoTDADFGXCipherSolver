/**
 * Created by Justin Leahy
 * Created on Monday, July 18, 2016
 * Last Edited on Saturday, July 23, 2016
 */
package org.leahy.decoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Brain {
	
	private static String[][] original = new String[][]{
		{"F", "F", "G", "X", "G", "D"},
		{"G", "F", "F", "A", "G", "F"},
		{"G", "G", "D", "D", "G", "F"},
		{"F", "F", "X", "X", "F", "F"},
		{"F", "D", "G", "F", "F", "G"},
		{"F", "D", "G", "F", "F", "G"},
		{"F", "D", "G", "G", "F", "G"},
		{"F", "G", "F", "G", "A", "A"},
		{"F", "X", "F", "X", "D", "X"},
		{"X", "F", "X", "D", "G", "F"},
		{"F", "A", "G", "G", "F", "F"},
		{"J", "J", "J", "J", "A", "F"},
	};
	
	//The values that are static and never change
	private static String[] originalValues = new String[]{
			"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "K",
			"L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z"
	};
	
	//The values that each character will correspond to when generating random values
	private static String[] characterValues = new String[]{
			"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "K",
			"L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z"
	};
	
	private static String[] encryptedValues = new String[] {
		"AA", "AD", "AF", "AG", "AX",
		"DA", "DD", "DF", "DG", "DX",
		"FA", "FD", "FF", "FG", "FX",
		"GA", "GD", "GF", "GG", "GX",
		"XA", "XD", "XF", "XG", "XX"
	};
	
	
	//All the keywords that we think are possible (Can be changed later if needed)
	private static String[] keywords = new String[]{
			"fedcba", "fedbca", "fedbac", "fedabc", "fedacb", "fedcab", "fecdba", "fecdab", "fecbda",
			"fecbad", "fecadb", "fecabd", "febdca", "febdac", "febcda", "febcad", "febadc", "febacd",
			"feadcb", "feadbc", "feabdc", "feabcd", "feacbd", "feacdb", "efdcba", "efdbca", "efdbac",
			"efdabc", "efdacb", "efdcab", "efcdba", "efcdab", "efcbda", "efcbad", "efcadb", "efcabd",
			"efbdca", "efbdac", "efbcda", "efbcad", "efbadc", "efbacd", "efadcb", "efadbc", "efabdc",
			"efabcd", "efacbd", "efacdb"
	};
	
	private static String message = "";

	private static String stringCharacterValues = "";
	
	private static Random random = new Random();
	
	private static List<String> keywordsGenerated = new ArrayList<String>();
	private static List<String> characterValuesGenerated = new ArrayList<String>();
	private static List<String> messagesGenerated = new ArrayList<String>();
	
	public Brain() {
		
	}
	
	static void decrypt() {
		for(int x = 0; x != Main.number; x++) {
			int r1 = random.nextInt(keywords.length);
			String keyword = keywords[r1]; //Will work on this once the decryption is finished
			generateRandomSquare();
			for(int z = 0; z < characterValues.length; z++) {
				stringCharacterValues += characterValues[z];
			}
			String[][] sortedArray = phaseOne(keyword);
			String[] encryptChars = phaseTwo(sortedArray);
			String[] decryptChars = phaseThree(encryptChars);
			for(int y = 0; y < decryptChars.length; y++) {
				message += decryptChars[y];
			}
			
			keywordsGenerated.add(keyword);
			characterValuesGenerated.add(stringCharacterValues);
			messagesGenerated.add(message);
			message = "";
			stringCharacterValues = "";
		}
	}
	
	private static String[][] phaseOne(String s) {
		String sortedS = sortKeyword(s);
		int[] order = sortedOrder(s, sortedS);
		String[][] sortedArray = new String[original.length][original[1].length];
		
		for(int x = 0; x < order.length; x++) {
			for(int y = 0; y < order.length; y++) {
				if(x == order[y]) {
					for(int z = 0; z < sortedArray.length; z++) {
						sortedArray[z][x] = original[z][y];
					}
				}
			}
		}
		
		return sortedArray;
	}
	
	private static String sortKeyword(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
	}
	
	private static int[] sortedOrder(String key, String sorted) {
		int[] order = new int[key.length()];
		
		for(int x = 0; x < key.length(); x++) {
			for(int y = 0; y < key.length(); y++) {
				if(sorted.charAt(x) == key.charAt(y)) {
					order[x] = y;
				}
 			}
		}
		
		return order;
	}
	
	private static String[] phaseTwo(String[][] s) {
		String[] chars = new String[countChars(s)];
		int num = 0;
		int count = 0;
		
		for(int x = 0; x < countChars(s); x++) {
			chars[x] = "";
		}
		
		for(int y = 0; y < s.length; y++) {
			for(int x = 0; x < s[0].length; x++) {
				if(s[y][x].compareTo("j") != 0) {
				chars[num] += s[y][x].toUpperCase();
				count++;
				if(count == 2) {
					num++;
					count = 0;
					}
				}
			}
		}
		
		return chars;
	}
	
	private static int countChars(String[][] s) {
		int chars = 0;
		
		for(int y = 0; y < s.length; y++) {
			for(int x = 0; x < s[0].length; x++) {
				if(s[y][x].compareTo("j") != 0) {
					chars++;
				}
			}
		}
		
		return chars/2;
	}
	
	private static String[] phaseThree(String[] s) {
		String[] chars = new String[s.length];
		
		for(int x = 0; x < chars.length; x++) {
			chars[x] = "";
		}
		
		for(int x = 0; x < s.length; x++) {
			for(int y = 0; y < encryptedValues.length; y++) {
				if(s[x].compareTo(encryptedValues[y]) == 0) {
					chars[x] = characterValues[y];
				}
			}
		}
		return chars;
	}
	
	private static void generateRandomSquare() {
		List<String> list = new ArrayList<String>();
		
		for(int x = 0; x < originalValues.length; x++) {
			list.add(originalValues[x]);
		}
		
		for(int x = 0; x < originalValues.length; x++) {
			int randInt = random.nextInt(list.size());
			characterValues[x] = list.get(randInt);
			list.remove(randInt);
		}
	}
	
	public static String getKeyword(int index) {
		return keywordsGenerated.get(index);
	}
	
	public static String getLetterSquare(int index) {
		System.out.println(index);
		System.out.println(characterValuesGenerated.get(index));
		return characterValuesGenerated.get(index);
	}
	
	public static String getMessage(int index) {
		return messagesGenerated.get(index);
	}
}
