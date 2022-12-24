package person;
public abstract class Employee extends Person implements EmployeeVisitable {

	
	public Employee(String name,String phone,String address,int age) {
		super(name,phone,address,age);

	}
	public abstract void accept(EmployeeVisitor ev);
}
