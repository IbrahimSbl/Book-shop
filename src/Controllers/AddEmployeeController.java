package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gluonhq.charm.glisten.control.TextField;

import Customer.Customer;
import application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import person.Employee;
import person.Keeper;
import person.Manager;
import person.Person;

public class AddEmployeeController {
	private Person person;
	@FXML
	private Button updateb;
	@FXML
	private FlowPane main;
	@FXML
	private TextField name;
	@FXML
	private TextField age;
	@FXML
	private TextField id;
	@FXML
	private TextField phone;
	@FXML
	private TextField ppht;
	@FXML
	private TextField odt;
	@FXML
	private TextField hpdt;
	@FXML
	private Label pphl;
	@FXML
	private Label odl;
	@FXML
	private Label hpdl;
	@FXML
	private TextField address;
	@FXML
	private TextField Role;
	@FXML
	private Label role;
	@FXML
	private Label error;
	@FXML
	private TextField emailt;
	@FXML
	private Label emaill;
	@FXML
	private TextField passt;
	@FXML
	private Label passl;
	private Model model = new Model();
	public void setScene() {
		
		address.setDisable(false);
		name.setDisable(false);
		phone.setDisable(false);
		Role.setDisable(false);

		emailt.setDisable(false);
		passt.setDisable(false);
		age.setDisable(false);
		ppht.setDisable(false);
		odt.setDisable(false);
		hpdt.setDisable(false);
		error.setText("");
		//change the name of button to add
		updateb.setText("Add Employee");
	}
	public void update(ActionEvent event) {
		String query = "";
		if(address.getText().isEmpty() || age.getText().isEmpty() || ppht.getText().isEmpty() || odt.getText().isEmpty() || hpdt.getText().isEmpty() || name.getText().isEmpty() || phone.getText().isEmpty() || Role.getText().isEmpty() || emailt.getText().isEmpty() || passt.getText().isEmpty()) {
			error.setText("Fill the empty fields");
			return;
		}else if(!Role.getText().equalsIgnoreCase("manager") && !Role.getText().equalsIgnoreCase("keeper") && !Role.getText().equalsIgnoreCase("customerservice")){
			error.setText("Non valid role");
			return;
		}else{
			
			for(int i=0;i<model.ShowEmployee().size();i++) {//check if the email is found before then he is logged in before 
				if(model.ShowEmployee().get(i) instanceof Manager) {
					Manager en = (Manager)model.ShowEmployee().get(i);
					if(en.getEmail().equals(emailt.getText())) {
						error.setText("Email loged in before");
						return;
					}
					
				}else if (model.ShowEmployee().get(i) instanceof Keeper) {
					Keeper en = (Keeper)model.ShowEmployee().get(i);
					if(en.getEmail().equals(emailt.getText())) {
						error.setText("Email loged in before");
						return;
					}
				}
			}
		}
		if(updateb.getText().equals("Add Employee")) {
			
			if(Role.getText().equalsIgnoreCase("manager")) {
				Manager m =new Manager(name.getText(),phone.getText(),address.getText(),Integer.parseInt(age.getText()),Double.parseDouble(ppht.getText()),Integer.parseInt(hpdt.getText()),emailt.getText(),passt.getText());
				m.setOffDays(0);
				model.ShowEmployee().add(m);
				
			}else if(Role.getText().equalsIgnoreCase("Keeper")) {
				Keeper m =new Keeper(name.getText(),phone.getText(),address.getText(),Integer.parseInt(age.getText()),Double.parseDouble(ppht.getText()),Integer.parseInt(hpdt.getText()),emailt.getText(),passt.getText());
				m.setOffDays(0);
				model.ShowEmployee().add(m);
			
			} 
			Stage stage = (Stage)updateb.getScene().getWindow();
			stage.setOnCloseRequest(e->{
				model.setData();
			});
		}
		
	}
	public void setPerson(Person p) {
		person = p;
	}
}
