package Product;

public class History extends Product {
	String TimePeriod;

	public History(String preview,String timeperiod){
		super(preview);
		TimePeriod=timeperiod;
	}
	public void setTime(String pre){
		this.preview=pre;
	}
	public String gettime(){
		return this.TimePeriod;
	}

}
