package Customer;

public class Card {
	private String name_on_card;
	private String card_number;
	private String cvv;
	private String date_of_exp;

	public Card(String name, String CNumber, String CVV, String DateofExp) {
		this.setName_on_card(name);
		this.card_number=CNumber;
		this.setCvv(CVV);
		this.setDate_of_exp(DateofExp);
	}
	public String card_Number() {
		return this.card_number;
	}
	public String GetLast4Dig() {
		return this.card_number.substring(12);
	}

	public String getName_on_card() {
		return name_on_card;
	}

	public void setName_on_card(String name_on_card) {
		this.name_on_card = name_on_card;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getDate_of_exp() {
		return date_of_exp;
	}

	public void setDate_of_exp(String date_of_exp) {
		this.date_of_exp = date_of_exp;
	}
}
