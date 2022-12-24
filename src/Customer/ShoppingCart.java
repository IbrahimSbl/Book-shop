package Customer;

import java.util.ArrayList;

import Product.ProductFactory;

public class ShoppingCart {
	private Payment payment;
	private ArrayList <ProductFactory> Cart = new ArrayList <ProductFactory>();
	
	public void SetPayment(Payment p) {
		this.payment=p;
	}
	
	public void ShowCart() {
		//upon working on the view these info need to be expressed on the screen
		for(ProductFactory p:Cart) {
			System.out.println(p.getId());
			System.out.println(p.getName());
			System.out.println(p.getPrice());
			System.out.println("-------------------------------------------------------------");
		}
		System.out.println("The Total is: " + CalculateTotal());
	}
	public ArrayList <ProductFactory> getCart() {
		return Cart;
	}
	public void AddItem(ProductFactory i) {
		if(Cart.add(i)) {
			System.out.println("Item successfully added to your ShoppingCart");
		}else
			System.out.println("Item can not be added to your ShoppingCart at this moment, try again later");
	}
	
	public void RemoveItem(ProductFactory i) {
		if(Cart.remove(i)) {
			System.out.println("Item removed from ShoppingCart Successfully");
		}else 
			System.out.println("Item not fount in your ShoppingCart");
	}
	
	public double CalculateTotal() {
		double total = 0;
		for(ProductFactory p:Cart) {
			total = total + p.getPrice();
		}
		return total;
	}
	
	public String Pay() {
		double amount = CalculateTotal();
		return payment.Pay(amount);
	}
}
