package participant;

public class PhoneNumber {
	private String countryCode;
	private String phoneNumber;
	
	public PhoneNumber(String phoneNum) {
		String[] holder = phoneNum.split(" ");
		for (int i = 0; i < holder.length; i++) {
			if(i==0)
				this.setCountryCode(holder[i]);
			else
				this.phoneNumber += holder[i]+" ";
		}
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
