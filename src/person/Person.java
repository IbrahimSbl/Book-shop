package person;

public class Person {
	private String name;
	private int age;
	private String phone,address;
	public Person(String name,String phone,String address,int age) {
		this.address = address;
		this.age = age;
		this.phone = phone;
		this.name = name;
	}
	public String toString() {
		return "name : "+name+"\nage : "+age+"\nphone : "+phone+"\naddress : "+address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getAddress() {
		return this.address;
	}
	
}
