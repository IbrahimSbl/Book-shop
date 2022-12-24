package Controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class myDataType{
	private SimpleStringProperty name;
	private SimpleDoubleProperty price;
	public myDataType(String name,double p){
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(p);
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public double getPrice() {
		return price.get();
	}
	public void setPrice(double price) {
		this.price = new SimpleDoubleProperty(price);
	}
}