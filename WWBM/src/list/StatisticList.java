package list;

import participant.Statistic;

public class StatisticList {

	private Statistic list[];

	public StatisticList() {
		list = new Statistic[0];
	}

	public void Add(Statistic item) {// when come new data it creates itself copy.And "new itself = get's new
										// instance"
										// after that gets their initial data back.
		Statistic tempArray[] = list;
		list = new Statistic[list.length + 1];

		for (int i = 0; i < tempArray.length; i++) {
			list[i] = tempArray[i];
		}
		list[list.length - 1] = item;

	}

	public Statistic[] getList() { // return array
		return list;
	}

	public int length() { // return length of array
		return list.length;
	}

}
