package concrete;

import java.util.Scanner;

import console.ConsolePrinter;


public class Box {
	private int money = 0;
	private int time = 0;
	private String percentage = "%50(J)";
	private String doubleDip = "Double Dip(K)";
	private String[] choicesName = { "A", "B", "C", "D" };
	Scanner scan = new Scanner(System.in);

	public Box() {

	}

	public Box(int money) {
		this.money = money;
	}

	public Box(int money, int time, String percentage, String doubleDip) {
		this.money = money;
		this.time = time;
		this.percentage = percentage;
		this.doubleDip = doubleDip;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getDoubleDip() {
		return doubleDip;
	}

	public void setDoubleDip(String doubleDip) {
		this.doubleDip = doubleDip;
	}

	public String percentageUsing(Question inComingQuestion,ConsolePrinter cp) {
		int indexOfanswer = 0;
		String[] choices = inComingQuestion.getChoices();
		for (int i = 0; i < choices.length; i++) {

			if (choicesName[i].equalsIgnoreCase(inComingQuestion.getAnswer())) {
				indexOfanswer = i;
			}
		}

		for (int i = 0; i < choices.length; i++) {
			if (indexOfanswer == i) {
				continue;
			}
			if (indexOfanswer < 2) {
				if (!(i == (indexOfanswer + 2))) {
					choices[i] = "-";
					inComingQuestion.setChoices(choices);
				}
			}
			if (indexOfanswer >= 2) {
				if (!(i == (indexOfanswer - 2))) {
					choices[i] = "-";
					inComingQuestion.setChoices(choices);
				}
			}
		}
		printLastTypeOfChoices(inComingQuestion);
		cp.template2();
		cp.printBox(this);
		System.out.print(" > Enter your choice (E:Exit): ");
		String choice =  Character.toString(cp.keyList());
		return choice;
	}

	public String doubleDipUsing(Question inComingQuestion,ConsolePrinter cp) {
		printLastTypeOfChoices(inComingQuestion);
		cp.template2();
		cp.printBox(this);
		System.out.print(" > Enter your choice (E:Exit): ");
		String answer =  Character.toString(cp.keyList());

		
		if (inComingQuestion.getAnswer().equalsIgnoreCase(answer)) {
			return answer;
		} else if(answer.equalsIgnoreCase("N")) {
			return answer;
		}
		else {
			System.out.println(answer);
			cp.reset2();
			int indexOfWrongAnswer = 0;
			System.out.println("   Wrong Answer, Please Make Your Second Choice!\n");
			String[] choices = inComingQuestion.getChoices();
			for (int i = 0; i < choices.length; i++) {

				if (choicesName[i].equalsIgnoreCase(answer)) {
					indexOfWrongAnswer = i;
				}
			}
			for (int i = 0; i < choices.length; i++) {
				if (indexOfWrongAnswer == i) {
					System.out.println("   " + choicesName[i] + ")"+ "-");
				} else {
					System.out.println("   " + choicesName[i] + ")" + choices[i]);
				}
			}
			cp.template2();
			cp.printBox(this);
			System.out.print(" > Enter your choice (E:Exit): ");
			answer =  Character.toString(cp.keyList());
		}

		return answer;
	}

	public void printLastTypeOfChoices(Question inComingQuestion) {
		String[] choices = inComingQuestion.getChoices();
		for (int i = 0; i < choicesName.length; i++) {
			System.out.println("   " + choicesName[i] + ")" + choices[i]);
		}
		System.out.println();

	}

}
