package console;

import java.util.Scanner;

import checker.FileReader;
import concrete.Box;
import concrete.Category;
import concrete.Level;
import concrete.Question;
import participant.Participant;
import participant.Statistic;

public class Main {

	public static void main(String[] args) throws Exception {

		ConsolePrinter cp = new ConsolePrinter(); // It prints enigma console.

		Scanner scan = new Scanner(System.in); // Reads data
		int choice; // user selection for switch case
		String fileName = ""; // getting file name with scanner from user
		boolean flag = true; // for stop the while loop which include switch case
		 int menuManager = 0;
		Question question = new Question(); // it creates a instance of Questions class = Object , for storage related
											// // data
		Level level = new Level(); // it creates a instance of level class = Object , for storage related data
		Category category = new Category(); // it creates a instance of category class = Object , for storage related //
											// data
		Participant participant = new Participant(); // it creates a instance of participant class = Object , for //
														// storage related data
		FileReader reader = new FileReader(); // It's class which include special functions for reading files.
		Statistic statisticWriter = new Statistic();
		/* Menu Part */
		System.out.println("\n\n ***** Menu *****");
		System.out.println(" 1.Load questions");
		System.out.println(" 2.Load participants");
		System.out.println(" 3.Start competition");
		System.out.println(" 4.Show statistics");
		System.out.print(" 5.Exit\n\n");

		while (flag) { // it return until user exit from game.
			System.out.print(" > Enter you choice : ");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				menuManager++;
				while (!fileName.equals("questions.txt")) { // detect file name is wanted or not
					System.out.print(" > Enter file name to load : ");
					fileName = scan.next();
				}
				String[] lines = reader.readFile(fileName); // reads file (this function is included in
															// FileReader
				// class)
				String[] dictionary = reader.readFile("dictionary.txt");// reads file (this function is included in
																		// FileReader class)

				question.add(lines, dictionary); // sends lines of related questions and dictionary file's elements.
				category.getCategoryAmount(question.getQuestions());// it get category amount with special function
																	// which included in category
				level.getLevelAmount(question.getQuestions());// it get level amount with special function which
																// included in level

				break;
			case 2:
				menuManager++;
				while (!fileName.equals("participants.txt")) {
					System.out.print(" > Enter file name to load : ");
					fileName = scan.next();
				}
				String[] participants = reader.readFile(fileName);// reads file (this function is included in
																	// FileReader
				// class)
				participant.add(participants);// sends and adds lines of related participants
				break;
			case 3:
				String ans = "y";
				while (ans.toLowerCase().equals("y")) {
					Box box = new Box();
					boolean isCorrect = true;
					String[] stopWords = reader.readFile("stop_words.txt");// reads file (this function is included in
																			// FileReader class)
					String[][] wordCloud = question.getWordCloud(stopWords);// it gets word cloud according to question
																			// level randomly.their
					// content change according to level difficulty
					// sends stopWords for cleaning them from question.
					Participant cominParticipant = participant.getRandomParticipant();
					System.out.println("\n" + " Contestant : " + cominParticipant.getName());

					while (isCorrect) {
						System.out.println(
								"\n -------------------------------------------------------------------------------");
						System.out.println(" Word Cloud;");
						System.out.println();
						String[] getCloudWithLevel = question.printWordCloud(wordCloud);

						System.out.println("\n");
						System.out.print(" > Enter your selection: ");
						String selection = scan.next();

						while (!question.isValid(getCloudWithLevel, selection)) {
							System.out.println(" !! Please enter right word !!\n");
							System.out.print(" > Enter your selection: ");
							selection = scan.next();

						}
						Question inComingQuestion = question.print(selection); // It's not ready for now.

						System.out.println();
						System.out.print(" > Enter your choice (E:Exit): ");

						cp.getCoordinatPoint();
						cp.template();
						cp.printBox(box);
						cp.time();

						String answer = Character.toString(cp.keyList());
						if (!answer.equalsIgnoreCase("n"))
							System.out.println(answer);
						Thread.sleep(1000);

						isCorrect = question.controlAnswer(answer, inComingQuestion, cominParticipant, box, cp,
								statisticWriter);
						cp.reset();
						if (!isCorrect) {

							System.out.println("\n > Next contestant? (Y/N)");
							System.out.print(" > ");
							ans = scan.next();
						}

					}
				}
				break;
			case 4:
				if (menuManager != 2) {
					System.out.println("\n   Please Enter Firstly Step 1 and Step 2 \n");
				} else {
					statisticWriter.readTxt();
					System.out.println("\n  Statistics");
					System.out.println("  •	  The most successful contestant : "
							+ statisticWriter.getMostSuccesfull(participant.getParticipants()));
					System.out.println("  •	  The category with the most correctly answered : "
							+ statisticWriter.getCategoryMost(question.getQuestions(), true));
					System.out.println("  •	  The category with the most badly answered : "
							+ statisticWriter.getCategoryMost(question.getQuestions(), false));
					System.out.printf(
							"  •	  Age<=30    %.2f" + "   " + "  30<Age<= 50    %.2f" + "   " + "  Age>50    %.2f",

							statisticWriter.getLess(participant.getParticipants(), 0, 30),
							statisticWriter.getLess(participant.getParticipants(), 30, 50),
							statisticWriter.getLess(participant.getParticipants(), 50, 150)

					);
					System.out.println("\n  •	  The city with the highest number of participants : "
							+ statisticWriter.getHigherCity(participant.getParticipants()));
					System.out.println();
				}

				break;
			case 5:
				System.out.println("\nBYE\n");
				flag = false;
				break;
			default:
				break;
			}
		}

		scan.close();
	}

}
