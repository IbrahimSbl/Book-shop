package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import person.Employee;
import person.Keeper;
import person.Manager;
import person.MonthSalaryVisitor;
import person.Person;

public class SalaryViewController{
	@FXML
	private Label text;
	@FXML
	private Button done;
	Employee employee ;
	public void setScene() {
		double salary;
		MonthSalaryVisitor ms = new MonthSalaryVisitor();
		
		if(employee instanceof Manager) {
			Manager m = (Manager)employee;
			m.accept(ms);
		}else if(employee instanceof Keeper) {
			Keeper m = (Keeper)employee;
			m.accept(ms);
		}
		salary = ms.getSalary();
		text.setText(employee.getName()+"'s salary till now is "+salary+" $");
	}
	// Event Listener on Button[#done].onAction
	@FXML
	public void back(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage)done.getScene().getWindow();
		stage.close();
	}
	public void setEmployee(Employee p) {
		employee = p;
	}
	
}
