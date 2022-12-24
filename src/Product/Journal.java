package Product;

public class Journal extends ProductFactory{
	public Journal(double price,int stock,String n,String a,String p,String e,String cp, String co){
		super(price,stock, n, a, p, e, cp, co);
	}
	public void accept(ProductVisitor pv) {
		pv.visit(this);
	}
	@Override
	public void buildProduct(Product p) {
		// TODO Auto-generated method stub
		this.setProduct(p);
	}
	
	
	
}