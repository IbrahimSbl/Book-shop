package Product;

public class DiscountVisitor extends ProductVisitor {
	private double percentageBook;
	private double percentageMagazine;
	private double percentageJournal;
	private double newprice;

	public DiscountVisitor(int pBook, int pMagazine, int pJournal){
		percentageBook = pBook;
		percentageMagazine = pMagazine;
		percentageJournal = pJournal;
	}

	public void visit(Book b){
		newprice = b.getPrice();
		newprice *= 1-(percentageBook/100.0);
		b.setPrice(newprice);
	}
	public void visit(Magazine m){
		newprice = m.getPrice();
		newprice *=1- (percentageMagazine/100.0);
		m.setPrice(newprice);
	}
	public void visit(Journal j){
		newprice = j.getPrice();
		newprice *= 1 - (percentageJournal/100.0);
		j.setPrice(newprice);
	}

}
