package participant;

import java.time.LocalDate;
import java.util.Date;

public class Birthdate {
	private int day;
	private int month;
	private int year;
	LocalDate currentDate = LocalDate.now(); 
	public Birthdate(String birthDate) {
		String[] info =  birthDate.split("\\."); // gets date directly string and after that split according to "."
		
		this.day = Integer.parseInt(info[0]);
		this.month = Integer.parseInt(info[1]);
		this.year = Integer.parseInt(info[2]);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getAge() { // calculate age from current year .
		int age = currentDate.getYear()-this.year;
		return age;
	}
	
}
