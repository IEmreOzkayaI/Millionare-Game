package concrete;

import list.TwoStringList;

public class Category {

	private int id; // rightnes amount BUT not using for now .
	private String name;

	public Category() {

	}

	public Category(int id, String name) {

		this.id = id;
		this.name = name;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void getCategoryAmount(Question[] questions) {
		TwoStringList categoryHolder = new TwoStringList(); // it creates dynamic array which included with special
															// algorithm
		for (int i = 0; i < questions.length; i++) {
			String categoryName = questions[i].getCategory().getName(); // it gets related category's name
			int amount = 1;
			if (!isContain(categoryName, categoryHolder.getList())) { // it checks this array is contain or not.
				categoryHolder.Add(categoryName, Integer.toString(amount++)); // if it's not contain then it adds
																				// present category name with amount(1)
			} else {
				categoryHolder.update(categoryName);// if it's contained , then increment amount
			}
		}

		printer(categoryHolder.getList()); // send array to the printer for writing
	}

	private static void printer(String[][] dm) { // this printer is created for only 3 different categories because of
													// screen design.
													// But when we get other categories we will update.

		System.out.println("\n Category\tThe number of questions");
		System.out.println(" " + "----------\t-----------------------");
		for (int i = 0; i < dm.length; i++) {

			if (dm[i][0].length() == 5) {
				System.out.println(" " + dm[i][0] + "\t\t\t\t  " + dm[i][1]);

			} else if (dm[i][0].length() == 7) {
				System.out.println(" " + dm[i][0] + "\t\t\t\t" + dm[i][1]);

			}

			else if (dm[i][0].length() == 8) {
				System.out.println(" " + dm[i][0] + "\t\t\t   " + dm[i][1]);

			} else if (dm[i][0].length() == 9) {
				System.out.println(" " + dm[i][0] + "\t\t\t  " + dm[i][1]);

			} else if (dm[i][0].length() == 11) {
				System.out.println(" " + dm[i][0] + "\t\t\t" + dm[i][1]);

			}

			else {
				System.out.println(" " + dm[i][0] + "\t\t\t   " + dm[i][1]);

			}

		}

	}

	private static boolean isContain(String categoryName, String[][] categoryHolder) { // it check contained or not
		boolean flag = false;
		for (int i = 0; i < categoryHolder.length; i++) {
			if (categoryName.equals(categoryHolder[i][0])) { // first column represent category name , second column :
																// amount
				flag = true;
				break;
			}
		}
		return flag;
	}
}
