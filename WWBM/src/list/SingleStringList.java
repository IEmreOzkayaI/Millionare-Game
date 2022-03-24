package list;

public class SingleStringList {// their working style similar with ParticipantList.Only difference is it storage string
	
	private String list[];

	public SingleStringList() {
		list = new String[0];
	}

	public void Add(String item) {
		String tempArray[] = list;
		list = new String[list.length + 1];

		for (int i = 0; i < tempArray.length; i++) {
			list[i] = tempArray[i];
		}
		list[list.length - 1] = item;

	}

	public String[] getList() {
		return list;
	}

	public void reset() {
		
		
	}

}