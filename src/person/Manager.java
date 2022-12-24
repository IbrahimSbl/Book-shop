package person;
import java.util.ArrayList;

public class Manager extends Employee{
	private double PayPerHour;
	private int offDays;
	private static int idp=0;
	private int id;
	private int HoursPerDay;
	private String password;
	private String email;
	private ArrayList<Employee> employees ;
	public Manager(String name,String phone,String address,int age,double PayPerHour,int HoursPerDay,String email,String password) {
		super(name,phone,address,age);
		this.PayPerHour=PayPerHour;
		this.id = Integer.parseInt("10"+(++idp));
		this.HoursPerDay=HoursPerDay;
		this.setPassword(password);
		this.email = email;
		employees = new ArrayList<Employee>();
	}
	public boolean addEmployee(Employee employee) {
		if(employees.contains(employee))return false;
		else employees.add(employee);
		return true;
		
	}
	public boolean removeEmployee(Employee employee) {
		if(!employees.contains(employee))return false;
		employees.remove(employee);
		return true;
	}
	public void showEmployees() {
		for(int i =0;i<employees.size();i++)
			employees.get(i).toString();
	}
	public String toString() {
		return super.toString() + "\nid : "+ id + "\nPayPerHour : "+PayPerHour+"\n";
	}
	@Override
	public void accept(EmployeeVisitor ev) {
		// TODO Auto-generated method stub
		ev.visit(this);
	}
	public void setPayPerHour(double PayPerHour) {
		this.PayPerHour = PayPerHour;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPayPerHour() {
		return this.PayPerHour;
	}
	public int getId() {
		return this.id;
	}
	public void setOffDays(int offDays) {
		this.offDays = offDays;
	}
	public int getOffDays() {
		return this.offDays;
	}
	public int getHoursPerDay() {
		return HoursPerDay;
	}
	public void setHoursPerDay(int hoursPerDay) {
		HoursPerDay = hoursPerDay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
