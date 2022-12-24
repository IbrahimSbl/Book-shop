package Product;

public abstract class ProductFactory implements ProductVisitable{
	private Product product;
	private static int pid=0;
	private int id;
	private String name;
	private String author;
	private String publisher;
	private String edition;
	private String copytype;
	private String cover;
	private int stock;
	private double price;
	
	public abstract void buildProduct(Product p);
	public abstract void accept(ProductVisitor pv);
	
	public ProductFactory(double price,int stock, String name, String author, String publisher, String edition, String copytype, String cover) {
		this.id = Integer.parseInt("40"+(++pid));
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.copytype = copytype;
		this.cover = cover;
		this.price = price;
		this.stock=stock;
	}
	
	public int getId() {
		return this.id;
	}
	public int getStock() {
		return this.stock;
	}
	public double getPrice() {
		return this.price;
	}
	public String getName() {
		return this.name;
	}
	public String getAuthor() {
		return this.author;
	}
	public String getPublisher() {
		return this.publisher;
	}
	public String getEdition() {
		return this.edition;
	}
	public String getCopyType() {
		return this.copytype;
	}
	public String getCover() {
		return this.cover;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public void setCopyType(String copytype) {
		this.copytype = copytype;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	

}

