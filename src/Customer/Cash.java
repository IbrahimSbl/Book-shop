package Customer;

public class Cash implements Payment{
	private String reciever_name;
	
	@Override
	public String Pay(double ammount) {
		//upon working on the view this need to be expressed on the screen
		return ("An amount of '" + ammount +"' will be collected upon delivery form " + this.reciever_name);
	}

}
