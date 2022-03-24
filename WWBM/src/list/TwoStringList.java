package list;

public class TwoStringList {
	private String[][] list;

	public TwoStringList() { // constructor
		list = new String[0][0];
	}

	public void Add(String name, String amount) { // when come new data it creates itself copy.And "new itself = get's
													// new instance"
													// after that gets their initial data back.
		String tempArray[][] = list;
		list = new String[list.length + 1][2];

		for (int i = 0; i < tempArray.length; i++) {
			list[i][0] = tempArray[i][0];
			list[i][1] = tempArray[i][1];
		}
		list[list.length - 1][0] = name;
		list[list.length - 1][1] = amount;

	}

	public void update(String name) { // when string is included in array.Then increment it amount.
		for (int i = 0; i < list.length; i++) {
			if (name.equals(list[i][0])) { // check they are equal in the i. index
				list[i][1] = Integer.toString(Integer.parseInt(list[i][1]) + 1);
			}
		}
	}

	public int[][] insertionSort(String[][] holder) { // used for listing difficulty level , when we read levels from
														// file
														// they are mixed so their queue is not fixed.for fixed it we
														// use insertionSort
		int array[][] = new int[holder.length][2];
		for (int i = 0; i < array.length; i++) { // For transforming String[][] to int[][] used for loop.
			array[i][0] = Integer.parseInt(holder[i][0]);
			array[i][1] = Integer.parseInt(holder[i][1]);
		}
		int n = array.length; // insertion algorithm start here
		for (int j = 1; j < n; j++) {
			int key = array[j][0];
			int i = j - 1;
			while ((i > -1) && (array[i][0] > key)) {
				array[i + 1][0] = array[i][0];
				i--;
			}
			array[i + 1][0] = key;
		}
		return array;
	}

	public String[][] getList() { // return array
		return list;
	}

	public int length() {
		return list.length;
	}

}