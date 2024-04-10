package midterm;

import javax.xml.crypto.Data;

public class Student {
	private String id;
	private String name;
	private String address;
	private Data dateOfBirth;
	public Student(String id, String name, String address, Data dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Data getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Data dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
