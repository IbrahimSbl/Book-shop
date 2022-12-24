package Controllers;

import java.sql.Connection;

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

public class UpdateController {
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
	private TextField emailt;
	@FXML
	private Label emaill;
	@FXML
	private TextField passt;
	@FXML
	private Label passl;
	@FXML
	private TextField Role;
	@FXML
	private Label role;
	
	private Model model = new Model();
	public void setScene() {
		if(person instanceof Manager) {
			Manager m = (Manager) person;
			
			address.setText(m.getAddress());
			id.setText(String.valueOf(m.getId()));
			name.setText(m.getName());
			phone.setText(m.getPhone());
			age.setText(String.valueOf(m.getAge()));
			Role.setText("manager");
			
			ppht.setText(String.valueOf(m.getPayPerHour()));
			odt.setText(String.valueOf(m.getOffDays()));
			hpdt.setText(String.valueOf(m.getHoursPerDay()));
			
			emailt.setText(m.getEmail());
			passt.setText(m.getPassword());
		}else if(person instanceof Keeper) {
			Keeper m = (Keeper) person;
			
			address.setText(m.getAddress());
			id.setText(String.valueOf(m.getId()));
			name.setText(m.getName());
			phone.setText(m.getPhone());
			age.setText(String.valueOf(m.getAge()));
			Role.setText("keeper");
			
			
			ppht.setText(String.valueOf(m.getPayPerHour()));
			odt.setText(String.valueOf(m.getOffDays()));
			hpdt.setText(String.valueOf(m.getHoursPerDay()));

			
			emailt.setText(m.getEmail());
			passt.setText(m.getPassword());
		}

	}
	public void update(ActionEvent event) {
		if(updateb.getText().equals("Update Profile")) {
			updateb.setText("Done");
			if(person instanceof Manager) {
				
				address.setDisable(false);
				name.setDisable(false);
				phone.setDisable(false);
				Role.setDisable(false);

				age.setDisable(false);
				ppht.setDisable(false);
				odt.setDisable(false);
				hpdt.setDisable(false);
				
				emailt.setDisable(false);
				passt.setDisable(false);
				
			}else if(person instanceof Keeper) {

				address.setDisable(false);
				name.setDisable(false);
				phone.setDisable(false);
				age.setDisable(false);
				Role.setDisable(false);
				ppht.setDisable(false);
				odt.setDisable(false);
				hpdt.setDisable(false);

				emailt.setDisable(false);
				passt.setDisable(false);
			}
		}else if(updateb.getText().equals("Done")) {
			updateb.setText("Update Profile");//remove from model
			for(Employee t : model.ShowEmployee()) {
				if(person instanceof Manager && t instanceof Manager) {
					Manager m = (Manager) t;
					Manager p = (Manager) person;
					if(m.getEmail().equals(p.getEmail())) {
						model.RemoveEmployee(m);
						break;
					}
				}else if(person instanceof Keeper && t instanceof Keeper) {
					Keeper m = (Keeper) t;
					Keeper p = (Keeper) person;
					if(m.getEmail().equals(p.getEmail())) {
						model.RemoveEmployee(m);
						break;
					}
				}
				
			}
			
			if(person instanceof Manager) {
				
				address.setDisable(true);
				name.setDisable(true);
				phone.setDisable(true);
				age.setDisable(true);
				Role.setDisable(true);
				
				ppht.setDisable(true);
				odt.setDisable(true);
				hpdt.setDisable(true);
				emailt.setDisable(true);
				passt.setDisable(true);
				Manager m =new Manager(name.getText(),phone.getText(),address.getText(),Integer.parseInt(age.getText()),Double.parseDouble(ppht.getText()),Integer.parseInt(hpdt.getText()),emailt.getText(),passt.getText());
				m.setOffDays(Integer.parseInt(odt.getText()));
				//remove employee from model then add the new one into it 
				model.ShowEmployee().add(m);
			}else if(person instanceof Keeper) {

				address.setDisable(true);
				name.setDisable(true);
				phone.setDisable(true);
				age.setDisable(true);
				Role.setDisable(true);
				ppht.setDisable(true);
				odt.setDisable(true);
				hpdt.setDisable(true);
				emailt.setDisable(true);
				passt.setDisable(true);
				Keeper m =new Keeper(name.getText(),phone.getText(),address.getText(),Integer.parseInt(age.getText()),Double.parseDouble(ppht.getText()),Integer.parseInt(hpdt.getText()),emailt.getText(),passt.getText());
				m.setOffDays(Integer.parseInt(odt.getText()));
				//remove employee from model then add the new one into it 
				model.ShowEmployee().add(m);
			}
			//set data into database then close the stage
			model.setData();
			Stage stage = (Stage)updateb.getScene().getWindow();
			stage.close();
		}
		
	}
	public void setPerson(Person p) {
		person = p;
	}
}
