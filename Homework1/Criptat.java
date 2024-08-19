import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class Criptat {
	private static final int maxLength = 10005;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("criptat.in"));
		// Number of words
		int n = scanner.nextInt();
		scanner.nextLine();
		// Store the unique characters in the list of words
		HashSet<Character> uniqueChars = new HashSet<>();
		// Store the frequency of each character in the list of words
		ArrayList<HashMap<Character, Integer>> charFreq = new ArrayList<>();
		// Store the words
		String[] words = new String[n];

		// Read the words and store the characters in the list
		for (int i = 0; i < n ; i++) {
			words[i] = scanner.next();
			HashMap<Character, Integer> freq = new HashMap<>();
			for (Character c : words[i].toCharArray()) {
				uniqueChars.add(c);
				freq.put(c, freq.getOrDefault(c, 0) + 1);
			}
			charFreq.add(freq);
			scanner.nextLine();
		}

		int result = passwordLength(uniqueChars, words, charFreq);

		PrintWriter pw = new PrintWriter("criptat.out");
		pw.printf("%d\n", result);
		pw.close();
		scanner.close();
	}

	private static int passwordLength(HashSet<Character> uniqueChars, String[] words,
										ArrayList<HashMap<Character, Integer>> charFreq) {
		int result = 0;

		// Iterate through the unique characters
		while (!uniqueChars.isEmpty()) {
			Character c = uniqueChars.iterator().next();
			uniqueChars.remove(c);
			result = recursive(words, result, charFreq, c);
		}
		return result;
	}

	private static int recursive(String[] words, int result, ArrayList<HashMap<Character,
														Integer>> charFreq, Character c) {
		int[] dp = new int[maxLength];
		for (int j = 0; j < words.length ; j++) {
			// Calculate the length of the word
			int len = words[j].length();
			// If the character is in the word
			if (charFreq.get(j).containsKey(c)) {
				int curr_len = maxLength - 1;
				// Calculate the frequency of the character for every length of the password
				while (curr_len >= 0) {
					//
					if (dp[curr_len] != 0 || curr_len == 0) {
						if (dp[curr_len] + charFreq.get(j).get(c) > dp[curr_len + len]) {
							// Update the frequency of the character
							dp[curr_len + len] = dp[curr_len] + charFreq.get(j).get(c);
						}
					}
					curr_len--;
				}
			} else {
				// If the character is not in the word
				// We can add the word to the password to increase the length
				int curr_len = maxLength - 1;
				while (curr_len >= 0) {
					if (dp[curr_len] != 0 || curr_len == 0) {
						if (dp[curr_len] > dp[curr_len + len]) {
							dp[curr_len + len] = dp[curr_len];
						}
					}
					curr_len--;
				}
			}
		}
		// Calculate the maximum length of the password
		for (int l = 0; l < maxLength; l++) {
			// If the character appears more than half of the times in the password
			if (2 * dp[l] > l) {
				result = Math.max(result, l);
			}
		}
		return result;
	}
}
