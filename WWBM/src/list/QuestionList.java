package list;

import concrete.Question;

public class QuestionList { // their working style similar with ParticipantList.Only difference is it storage questions
	
	private Question list[];

	public QuestionList() {
		list = new Question[0];
	}

	public void Add(Question item) {
		Question tempArray[] = list;
		list = new Question[list.length + 1];

		for (int i = 0; i < tempArray.length; i++) {
			list[i] = tempArray[i];
		}
		list[list.length - 1] = item;

	}

	public Question[] getList() {
		return list;
	}
	
	public int length() {
		return list.length;
	}

}