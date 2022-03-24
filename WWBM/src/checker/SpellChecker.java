package checker;

public class SpellChecker {

	public String spellCheck(String question, String[] dictionary) {
		String[] words; // store question part after split from " " s
		String sentence = ""; // return after checking final sentence
		String temp = ""; // using for checking operations.Hold right words.
		char[] punctionHolder = question.toCharArray(); // for checking one by one we convert char array.
		char[] punctionHolder2; // it holds

		question = question.replaceAll("(?![_-])\\p{Punct}", " ");

		question = question.replaceAll("[0-9]", " ");
		
		words = question.split(" ");

		for (int j = 0; j < words.length; j++) {
			int index = 0;
			if (!words[j].equals("")) {
				index = words[j].charAt(0);
			}

			if (index >= 65 && 90 >= index) {
				temp += words[j] + " ";

			}

			else if (isContain(words[j], dictionary)) { // this word is contained on the dictionary or not

				temp += words[j] + " "; // add

			} else {

				temp += controlTrue(words[j], dictionary) + " "; // control it according to one digit problem or two
																	// digit problem

			}

		}
		punctionHolder2 = temp.toCharArray();
		for (int i = 0; i < punctionHolder.length; i++) { // compare last sentence with first type of sentece for
															// putting their pucntuations on the right area.
			if (punctionHolder2[i] == ' ' && !(punctionHolder[i] == ' ')) {
				char ch = punctionHolder[i];
				punctionHolder2[i] = ch;
			}

			sentence += punctionHolder2[i];

		}

		return sentence; // return right sentence with their all words and punct.

	}

	private static boolean isContain(String string, String[] dictionary) { // controls it is included or not.
		boolean flag = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (string.equals(dictionary[i])) {
				flag = true;
			}
		}
		return flag;
	}

	private static String controlTrue(String word, String[] dictionary) {
		// 2x3 yapýsýnýda çeviriyor
		// google ve microsoft eklendi
		if (!word.contains("_")) {

			char[] arr = word.toCharArray();
			int length = word.length();
			String result = "";
			int flag = 0;

			char[] holder = new char[5];

			for (int i = 0; i < dictionary.length; i++) {

				String reference = dictionary[i];
				int length2 = reference.length();
				char[] controlOneByOne = reference.toCharArray();

				if (length == length2) {
					flag = 0;
					int a = 0;
					for (int j = 0; j < reference.length(); j++) {

						if (arr[j] == controlOneByOne[j]) {
							flag++;

						} else {
							if(a==4) {
								continue;
							}else {
								
							holder[++a] = arr[j];
							holder[++a] = controlOneByOne[j];
							}
						
						}
					}
					if (flag == length - 1) {
						result = reference;
						break;
					}
					if (flag == length - 2 && holder[1] == holder[4] && holder[2] == holder[3]) {
						result = reference;
						break;
					}
				}

			}

			return result;
		} else {
			return word;
		}

	}

}
