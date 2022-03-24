package participant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import checker.FileReader;
import concrete.Question;
import list.SingleStringList;
import list.StatisticList;
import list.TwoStringList;

public class Statistic {

	private int questionId;
	private int participantId;
	private boolean result;

	StatisticList list;

	File file;

	FileReader reader;
	final String FILENAME = "result.txt";

	public Statistic() {
		file = new File(FILENAME);
		reader = new FileReader();
		list = new StatisticList();

	}

	public Statistic(int questionId, int participantId, boolean result) {
		super();
		this.questionId = questionId;
		this.participantId = participantId;
		this.result = result;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public void saveStatistic(Question question, Participant participant, boolean result) {
		String str = question.getQuestionId() + "#" + participant.getParticipantId() + "#" + result;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.append(str);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			e.getMessage();

		}

	}

	public void readTxt() {
		String[] lines = reader.readFile(FILENAME);
		for (int i = 0; i < lines.length; i++) {
			String[] words = lines[i].split("#");
			Statistic statistic = new Statistic();
			statistic.setQuestionId(Integer.parseInt(words[0]));
			statistic.setParticipantId(Integer.parseInt(words[1]));
			statistic.setResult(Boolean.parseBoolean(words[2]));
			list.Add(statistic);
		}
	}

	// getting for all time best participant
	public String getMostSuccesfull(Participant[] participants) {
		TwoStringList stringList = new TwoStringList();

		TwoStringList results = new TwoStringList();

		int id = 0;
		int trueAmount = 0;
		String name = "";
		Statistic[] statistic = list.getList();

		for (int i = 0; i < list.length(); i++) {
			stringList.Add(Integer.toString(statistic[i].getParticipantId()),
					Boolean.toString(statistic[i].getResult()));

		}

		// getting twodimensional class to local variable
		String[][] getParticipantsQuestions = stringList.getList();

		// compare txt with participant class
		for (int i = 0; i < stringList.length(); i++) {
			id = Integer.parseInt(getParticipantsQuestions[i][0]);

			for (int j = 0; j < getParticipantsQuestions.length; j++) {

				if (Integer.parseInt(getParticipantsQuestions[j][0]) == id) {
					if (Boolean.parseBoolean(getParticipantsQuestions[j][1]) == true)
						trueAmount++;
				}

			}
			if (!isContain(Integer.toString(id), results.getList()) && trueAmount != 0) {
				results.Add(Integer.toString(id), Integer.toString(trueAmount));
			}
			trueAmount = 0;

		}

		// getting most given true answer
		String idOfMostUser = getMost(results.getList());

		for (int i = 0; i < participants.length; i++) {
			if (participants[i].getParticipantId() == Integer.parseInt(idOfMostUser))
				name = participants[i].getName();

		}

		return name;
	}

	public String getCategoryMost(Question[] questions, boolean result) {
		TwoStringList stringList = new TwoStringList();
		TwoStringList results = new TwoStringList();

		String categoryName = "";
		int trueAmount = 0;
		String name = "";
		Statistic[] statistic = list.getList();

		for (int i = 0; i < list.length(); i++) {
			for (int j = 0; j < questions.length; j++) {
				if (statistic[i].getQuestionId() == questions[j].getQuestionId())
					stringList.Add(questions[j].getCategory().getName(), Boolean.toString(statistic[i].getResult()));

			}

		}

		// getting twodimensional class to local variable
		String[][] getQuestionResult = stringList.getList();

		// compare txt with participant class amount repeating or not
		for (int i = 0; i < getQuestionResult.length; i++) {
			categoryName = getQuestionResult[i][0];

			for (int j = 0; j < getQuestionResult.length; j++) {

				if (getQuestionResult[j][0].equalsIgnoreCase(categoryName)) {
					if (Boolean.parseBoolean(getQuestionResult[j][1]) == result)
						trueAmount++;
				}

			}
			if (!isContain(categoryName, results.getList()) && trueAmount != 0) {
				results.Add(categoryName, Integer.toString(trueAmount));
			}
			trueAmount = 0;

		}

		// getting most given true answer
		String nameOfMost = getMost(results.getList());

		for (int i = 0; i < questions.length; i++) {
			if (questions[i].getCategory().getName() == nameOfMost)
				name = nameOfMost;

		}

		return name;
	}

	public float getLess(Participant[] participants, int age1, int age2) {
		TwoStringList stringList = new TwoStringList();

		int trueAmount = 0;
		int participantAmount = 0;
		float avg = 0f;
		Statistic[] statistic = list.getList();

		for (int i = 0; i < list.length(); i++) {
			stringList.Add(Integer.toString(statistic[i].getParticipantId()),
					Boolean.toString(statistic[i].getResult()));
		}

		String[][] ageResult = stringList.getList();

		for (int i = 0; i < participants.length; i++) {
			for (int j = 0; j < ageResult.length; j++) {
				if (ageResult[j][0].equalsIgnoreCase(Integer.toString(participants[i].getParticipantId()))
						&& (participants[i].getBirthDate().getAge() <= age2)
						&& (participants[i].getBirthDate().getAge() > age1)) {
					participantAmount++;
					if (Boolean.parseBoolean(ageResult[j][1]) == true) {
						trueAmount++;

					}
				}

			}
		}

		avg = (float) ((float) trueAmount / (float) participantAmount);
		return avg;
	}

	public String getHigherCity(Participant[] participants) {
		SingleStringList stringList = new SingleStringList();
		TwoStringList results = new TwoStringList();

		String categoryName = "";
		int trueAmount = 0;
		String name = "";
		Statistic[] statistic = list.getList();

		for (int i = 0; i < list.length(); i++) {
			for (int j = 0; j < participants.length; j++) {
				if (statistic[i].getParticipantId() == participants[j].getParticipantId())
					stringList.Add(participants[j].getAddress().getProvince());

			}

		}

		// getting twodimensional class to local variable
		String[] getQuestionResult = stringList.getList();

		// compare txt with participant class amount repeating or not
		for (int i = 0; i < getQuestionResult.length; i++) {
			categoryName = getQuestionResult[i];

			for (int j = 0; j < getQuestionResult.length; j++) {

				if (getQuestionResult[j].equalsIgnoreCase(categoryName)) {

					trueAmount++;

				}
				if (!isContain(categoryName, results.getList()) && trueAmount != 0) {
					results.Add(categoryName, Integer.toString(trueAmount));
				}
				trueAmount = 0;

			}
		}
		// getting most given true answer
		String nameOfMost = getMost(results.getList());

		for (int i = 0; i < participants.length; i++) {
			if (participants[i].getAddress().getProvince() == nameOfMost)
				name = nameOfMost;

		}

		return name;

	}

	public String getMost(String[][] users) {
		String name = "";
		int max = 0;
		for (int i = 0; i < users.length; i++) {
			if (Integer.parseInt(users[i][1]) > max) {
				max = Integer.parseInt(users[i][1]);
				name = users[i][0];
			}
		}
		return name;
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
}