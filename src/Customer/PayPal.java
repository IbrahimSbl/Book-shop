package Customer;

public class PayPal implements Payment{
	private String user_name;
	private String Password;
	
	public PayPal(String UName, String Pass) {
		this.user_name = UName;
		this.setPassword(Pass);
	}
	
	@Override
	public String Pay(double ammount) {
		//upon working on the view this need to be expressed on the screen
		return ("An amount of '" + ammount +"' has been to charged to PayPal account :" + this.user_name);
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
