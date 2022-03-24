package concrete;

import java.util.Locale;
import java.util.Random;

import checker.SpellChecker;
import console.ConsolePrinter;
import list.QuestionList;
import list.SingleStringList;
import list.TwoStringList;
import participant.Participant;
import participant.Statistic;

public class Question {

	private int questionId;
	private Category category; // we define it as a class data type because we will use for statistic
	private String text;
	private String[] choices;
	private String answer;
	private String level;
	private static int money = 0;
	private static int degreeOfCompetitionQuestion = 1;

	private QuestionList myList; // dynamic question array
	private SingleStringList readerList; // dynamic array for stopWords
	private static SpellChecker spellChecker; // class for checking spell .

	private int categoryId = 0; // next week we will use for statistics
	private String[] choicesName = { "A", "B", "C", "D" };

	public Question() {

	}

	public Question(int questionId, Category category, String text, String[] choices, String answer, String level) {
		this.questionId = questionId;
		this.category = category;
		this.text = text;
		this.choices = choices;
		this.answer = answer;
		this.level = level;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getChoices() {
		return choices;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void add(String[] lines, String[] dictionary) { // Add questions after lines cleaning from punctuation

		myList = new QuestionList();
		spellChecker = new SpellChecker();
		for (int i = 0; i < lines.length; i++) {

			Question qs = new Question();
			String[] words = lines[i].split("#");
			qs.setQuestionId(i + 1);
			qs.setCategory(new Category(categoryId, words[0]));
			qs.setText(spellChecker.spellCheck(words[1], dictionary));// it check spell
			qs.setChoices(new String[] { words[2], words[3], words[4], words[5] });
			qs.setAnswer(words[6]);
			qs.setLevel(words[7]);

			myList.Add(qs);
			categoryId++;

		}
	}

	public String[][] getWordCloud(String[] stopWords) {
		Question[] q = myList.getList(); // return questions
		TwoStringList list = new TwoStringList(); // return two dimensional array
		for (int i = 0; i < q.length; i++) {
			if (!q[i].getText().equalsIgnoreCase("-")) { // for writing question don'T came again
				String[] holder = deleteStopWords(q[i].getText(), stopWords); // cleans stopwords from question's text
																				// part
				for (int j = 0; j < holder.length; j++) {
					if (!isContain(holder[j], list.getList())) { // after cleaning gets which word is not stop word .
																	// according to their difficulty level.
																	// we storage with level because getting level by
																	// level.
						list.Add(holder[j], q[i].getLevel());
					}
				}

			}
		}

		return list.getList();
	}

	public String[] deleteStopWords(String question, String[] stopWords) { // cleans stop words from text part
		readerList = new SingleStringList();

		question = question.replaceAll("\\p{Punct}", ""); // replace all punctuations with ""
		question = question.replace("  ", " "); // replace all double space to single space
		String[] word = question.split(" "); // split them according to single space to the array
		for (int j = 0; j < word.length; j++) { // it adds to the array if word is not stop_word.
			if (word[j] != "") {
				if (isContain(word[j].toLowerCase(new Locale("en", "US")), stopWords)) {
					word[j] = "";
				} else {

					if (!word[j].equals("___")) {
						readerList.Add(word[j]); // adding part
					}
				}
			}

		}
		return readerList.getList(); // return words which is not stop.
	}

	public String[] printWordCloud(String[][] wordCloud) {

		Random random = new Random();
		SingleStringList wordCloudAccordingToLevel = new SingleStringList();

		for (int i = 0; i < wordCloud.length; i++) { // it creates word cloud according to question level determined by
														// degree of competition.
														// it's increment automaticly user answered questions
														// correctly.When called function.
			if ((Integer.toString(degreeOfCompetitionQuestion).equals(wordCloud[i][1]))) {
				wordCloudAccordingToLevel.Add(wordCloud[i][0]);
			}

		}

		String[] words = wordCloudAccordingToLevel.getList();
		int[] randomHolder = new int[words.length];
		for (int i = 0; i < randomHolder.length; i++) {
			randomHolder[i] = -1;
		}
		// word.
		for (int i = 0; i < words.length; i++) { // it generates randomly digits for writing words randomly
			int rndm = random.nextInt(words.length);
			if (!isContain(rndm, randomHolder)) { // it controls generated random number duplicated or not.
				randomHolder[i] = rndm;
			} else {
				--i;
			}

		}

		int size = randomHolder.length;
		if (randomHolder.length > 10)
			size = 10;
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				System.out.print("\t");

			}
			System.out.print(" " + words[randomHolder[i]].toLowerCase(new Locale("en", "US")) + " "); // it prints words
																										// according to
																										// random
			// index.
			if (i == 5) {
				System.out.println();
				System.out.print("\t\t ");
			}

		}
		return words;

	}

	public Question[] getQuestions() { // return questions.

		return myList.getList();

	}

	public Question print(String input) { // return questions.BUT not used for now.
		
		Question[] q = myList.getList();
		Question forDetection = new Question();

		for (Question question : q) {
			String quest = question.getText().toLowerCase().replaceAll("\\p{Punct}", ""); // replace all punctuations
																							// with ""
			quest = quest.replace("  ", " "); // replace all double space to single space
			if (isContain(input.toLowerCase(new Locale("en", "US")), quest.split(" "))
					&& (Integer.parseInt(question.getLevel()) == degreeOfCompetitionQuestion)) {
				System.out.println();
				forDetection = question;

				System.out.println(" " + degreeOfCompetitionQuestion + ")" + question.getText()); // text part
				String[] choices = question.getChoices(); // choices part
				int a = 0;
				for (String choice : choices) {

					System.out.println("   " + choicesName[a] + ")" + choice);
					a++;
				}
				question.setText("-");
				break;
			}
		}
		return forDetection;
	}

	public boolean controlAnswer(String answer, Question inComingQuestion,Participant participant ,Box box, ConsolePrinter cp,Statistic statisticWriter) {
		boolean flag = false;
		
		if (answer.equalsIgnoreCase("E")) {
			System.out.println("   Thank You For Attendance \t");
			System.out.println("   You won $" + money);
			System.out.println();
			money = 0;
			degreeOfCompetitionQuestion = 1;
			flag = false;
		}
		if (answer.equalsIgnoreCase("J")) {
			cp.reset2();
			box.setPercentage("-");
			answer = box.percentageUsing(inComingQuestion, cp);
			if(!answer.equalsIgnoreCase("n"))
				System.out.println(answer);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (answer.equalsIgnoreCase("K")) {
			cp.reset2();
			box.setDoubleDip("-");
			answer = box.doubleDipUsing(inComingQuestion, cp);
			if(!answer.equalsIgnoreCase("n"))
				System.out.println(answer);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (inComingQuestion.getAnswer().equalsIgnoreCase(answer)) {
			
			updateMoney(degreeOfCompetitionQuestion);
			box.setMoney(money);
			System.out.println("   Correct Answer!");
			degreeOfCompetitionQuestion++;

			flag = true;
			statisticWriter.saveStatistic(inComingQuestion, participant, flag);
		}

		else {
			if (!answer.equalsIgnoreCase("E") && !answer.equalsIgnoreCase("N")) {
				System.out.println("   Wrong answer, GAME OVER!");
				getMoney();
				money = 0;
				degreeOfCompetitionQuestion = 1;
				flag = false;
				statisticWriter.saveStatistic(inComingQuestion, participant, flag);

			}

		}
		if (answer.equalsIgnoreCase("N")) {
			System.out.println("\n   GAME OVER!");
			getMoney();
			money = 0;
			degreeOfCompetitionQuestion = 1;
			flag = false;
		}

		if (degreeOfCompetitionQuestion == 6) {
			flag = false;
			getMoney();
			degreeOfCompetitionQuestion = 1;

		}
		return flag;
	}

	private void updateMoney(int degreeOfCompetitionQuestion2) {
		int[] price = { 20000, 80000, 150000, 250000, 500000 };
		money += price[degreeOfCompetitionQuestion2 - 1];
	}

	private void getMoney() {

		if (money < 100000) {
			System.out.println("   You won $0");
		}
		
		if (money >= 100000 && money < 500000) {
			System.out.println("   You won $100.000");
		}

		if (money >= 500000 && money < 1000000) {
			System.out.println("   You won $500.000");
		}
		if (money == 1000000) {
			System.out.println("   You won $1.000.000");
		}

		money = 0;

	}

	private static boolean isContain(String string, String[] dictionary) {// THESE ARE OVERLOADING FOR CHECKING IS
																			// INCLUDED IN ARRAY OR NOT
		boolean flag = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (string.equals(dictionary[i])) {
				flag = true;
			}
		}
		return flag;
	}

	private static boolean isContain(String string, String[][] dictionary) {// THESE ARE OVERLOADING FOR CHECKING IS
																			// INCLUDED IN ARRAY OR NOT
		boolean flag = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (string.equals(dictionary[i][0])) {
				flag = true;
			}
		}
		return flag;
	}

	private static boolean isContain(int randomNumber, int[] randomHolder) {// THESE ARE OVERLOADING FOR CHECKING IS
																			// INCLUDED IN ARRAY OR NOT
		boolean flag = false;
		for (int i = 0; i < randomHolder.length; i++) {
			if (randomNumber == (randomHolder[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean isValid(String[] getCloudWithLevel, String selection) {
		boolean flag = false;
		for (int i = 0; i < getCloudWithLevel.length; i++) {
			if (selection.equalsIgnoreCase(getCloudWithLevel[i])) {
				flag = true;
			}
		}
		return flag;
	}

}
