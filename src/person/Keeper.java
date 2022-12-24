package person;

import Product.Book;
import Product.Inventory;
import Product.Product;
import Product.ProductFactory;

public class Keeper extends Employee{
	private int offDays;
	private static int idp=0;
	private int id;
	private int HoursPerDay;
	private double PayPerHour;
	private String password;
	private String email;
	private Inventory inventory= new Inventory();
	public Keeper(String name, String phone, String address, int age,double PayPerHour, int HoursPerDay,String email,String password) {
		super(name, phone, address, age);
		this.PayPerHour=PayPerHour;
		this.HoursPerDay=HoursPerDay;
		this.id = Integer.parseInt("20"+(++idp));
		this.setPassword(password);
		this.email = email;
		// TODO Auto-generated constructor stub
	}
	public void setInventory(Inventory inv) {
		this.inventory = inv;
	}
	public Inventory getInventory() {
		return this.inventory;
	}
	public void newBook(ProductFactory pb,Product product) {
		pb.buildProduct(product);
		inventory.AddProduct(pb);
	}
	@Override
	public void accept(EmployeeVisitor ev) {
		ev.visit(this);
	}
	public String toString() {
		return super.toString() + "\nid : "+ id + "\nPayPerHour : "+PayPerHour+"\n";
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPayPerHour(double PayPerHour) {
		this.PayPerHour = PayPerHour;
	}
	public int getId() {
		return this.id;
	}
	public double getPayPerHour() {
		return this.PayPerHour;
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
