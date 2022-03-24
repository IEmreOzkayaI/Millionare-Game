package concrete;

import list.TwoStringList;

public class Level {

	private int id;
	private String levelName;

	public Level() {
		
	}
	public Level(int id, String levelName) {
		this.id = id;
		this.levelName = levelName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public void getLevelAmount(Question[] questions) {
		TwoStringList levelHolder = new TwoStringList(); // it creates dynamic array which included with special algorithm 
		for (int i = 0; i < questions.length; i++) {
			String levelName = questions[i].getLevel(); // it gets related level's name
			int amount = 1;
			if (!isContain(levelName, levelHolder.getList())) { // it checks this array is contain or not.
				levelHolder.Add(levelName, Integer.toString(amount++));// if it's not contain then it adds present level name with amount(1)
			} else {
				levelHolder.update(levelName);// if it's contained , then increment amount
			}
		}
		;
		printer(levelHolder.insertionSort(levelHolder.getList()));// send array to the printer for writing
																 //But there is special insertion sort algorithm for listing easy to hard level.
																//We explain it in TwoStringList.
	}

	private static void printer(int[][] dm) {// this printer is write data's


		System.out.println("\n Difficulty Level\tThe number of questions");
		System.out.println(" ----------------\t-----------------------");
		for (int i = 0; i < dm.length; i++) {
			System.out.println(" "+"\t\t" + dm[i][0] + "\t\t\t\t\t" + dm[i][1]);
		}
		System.out.println();

	}

	private static boolean isContain(String levelName, String[][] levelHolder) {// it check contained or not
		boolean flag = false;
		for (int i = 0; i < levelHolder.length; i++) {
			if (levelName.equals(levelHolder[i][0])) { // first column represent category name , second column : amount
				flag = true;
				break;
			}
		}
		return flag;
	}

}
