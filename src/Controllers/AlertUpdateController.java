package Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import person.Employee;
import person.Keeper;
import person.Manager;
import person.UpdateSalaryVisitor;
import javafx.event.ActionEvent;

public class AlertUpdateController {
	@FXML
	private TextField percentage;
	@FXML
	private TextField payment;
	@FXML
	private Button calculate;
	@FXML
	private Button done;
	@FXML
	private Label error;
	Employee e ;
	public void setScene(){
		percentage.setText("0");
		if(e  instanceof Manager) {
			Manager ef = (Manager)e;
			
				payment.setText(String.valueOf(ef.getPayPerHour()));
			
			
		}else if (e  instanceof Keeper) {
			Keeper ef = (Keeper)e;
				payment.setText(String.valueOf(ef.getPayPerHour()));
			
		}
	}
	// Event Listener on Button[#calculate].onAction
	@FXML
	public void calculateSalary(ActionEvent event) {
		// TODO Autogenerated
		UpdateSalaryVisitor v = null;
		if(Integer.parseInt(percentage.getText()) >= -100) {//the payment wont be < 0
			if(e instanceof Manager) {
				Manager ef = (Manager) e;
				v = new UpdateSalaryVisitor(0,Integer.parseInt(percentage.getText()));
				
				ef.accept(v);
				payment.setText(String.valueOf(ef.getPayPerHour()));
			}else if(e instanceof Keeper) {
				Keeper ef = (Keeper) e;
				v = new UpdateSalaryVisitor(Integer.parseInt(percentage.getText()),0);
				ef.accept(v);
				payment.setText(String.valueOf(ef.getPayPerHour()));
			}
		}
	}
	// Event Listener on Button[#done].onAction
	@FXML
	public void saveData(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage)done.getScene().getWindow();
		stage.setUserData(e);
		error.setText("Value changed exit the dialog box to proceed in your work");
		error.setTextFill(Color.GREEN);
	}
	public void setEmployee(Employee em) {
		this.e=em;
	}
}