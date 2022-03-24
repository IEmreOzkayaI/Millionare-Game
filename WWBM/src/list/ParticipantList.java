package list;

import participant.Participant;

public class ParticipantList {
	
	private Participant list[];

	public ParticipantList() {
		list = new Participant[0];
	}

	public void Add(Participant item) {// when come new data it creates itself copy.And "new itself = get's new instance" 
		  							  //after that gets their initial data back.
		Participant tempArray[] = list;
		list = new Participant[list.length + 1];

		for (int i = 0; i < tempArray.length; i++) {
			list[i] = tempArray[i];
		}
		list[list.length - 1] = item;

	}

	public Participant[] getList() { // return array
		return list;
	}
	
	public int length() { // return length of array
		return list.length;
	}

}
