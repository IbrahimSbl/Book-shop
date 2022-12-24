package Customer;

import person.Person;

public class Customer  extends Person{
	private String email;
	private Card card;
	private static int idp=0;
	private int id;
	private String password;
	private ShoppingCart shoppingcart;
	
	public Customer(String name, String phone, String address, int age, String email,String pass) {
		super(name, phone, address, age);
		this.email=email;
		this.id = Integer.parseInt("10"+(++idp));
		this.setPassword(pass);
		shoppingcart = new ShoppingCart();
		// TODO Auto-generated constructor stub
	}
	
	public void SetCard(Card c) {
		this.card=c;
	}
	public Card getCard() {
		return this.card;
	}
	public ShoppingCart getCart() {
		return this.shoppingcart;
	}
	public String toString() {
		return super.toString() + "\nemail : "+ email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
}
