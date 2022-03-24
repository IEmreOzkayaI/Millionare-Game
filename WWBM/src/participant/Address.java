package participant;

public class Address {
	private String street;
	private String no;
	private String district;//ilçe
	private String province;//il
	private String country;
	
	public Address(String address) {
		String[] holder = address.split(";");
		this.setStreet(holder[0]);
		this.setNo(holder[1]);
		this.setDistrict(holder[2]);
		this.setProvince(holder[3]);
		this.setCountry(holder[4]);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
