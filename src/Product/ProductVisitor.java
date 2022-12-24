package Product;

public abstract class ProductVisitor {
	public abstract void visit(Book b);
	public abstract void visit(Magazine m);
	public abstract void visit(Journal j);
}
