package Product;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<ProductFactory> stock = new ArrayList<ProductFactory>();
	public boolean AddProduct(ProductFactory pb) {
		return stock.add(pb);
	}
	public boolean RemoveProduct(ProductFactory pb) {
		return stock.remove(pb);
	}
	public ArrayList<ProductFactory> getStock() {
		return stock;
	}

	public void setStock(ArrayList<ProductFactory> stock) {
		this.stock = stock;
	}
	public ArrayList<ProductFactory> ShowProducts() {
		return stock;
	}
}
