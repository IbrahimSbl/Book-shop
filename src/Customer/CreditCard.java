package Customer;

public class CreditCard implements Payment {
	private Card card;
	
	public CreditCard(Card c) {
		this.card=c;
	}
	
	@Override
	public String Pay(double ammount) {
		//upon working on the view this need to be expressed on the screen
		return ("An amount of '" + ammount +"' has been to credited to card /n --xxxx xxxx xxxx " + this.card.GetLast4Dig());
	}
	
}
