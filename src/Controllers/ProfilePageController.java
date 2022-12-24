package Controllers;


import com.gluonhq.charm.glisten.control.TextField;

import Customer.Customer;
import Product.ProductFactory;
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

public class ProfilePageController {
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
	private Model model = new Model();
	public void setScene() {
		if(person instanceof Customer) {
			Customer c = (Customer)person;
			address.setText(c.getAddress());
			id.setText(String.valueOf(c.getId()));
			name.setText(c.getName());
			age.setText(String.valueOf(c.getAge()));
			phone.setText(c.getPhone());
			pphl.setStyle("visibility:hidden;");
			ppht.setStyle("visibility:hidden;");
			odl.setStyle("visibility:hidden;");
			odt.setStyle("visibility:hidden;");
			hpdl.setStyle("visibility:hidden;");
			hpdt.setStyle("visibility:hidden;");
		}else if(person instanceof Manager) {
			Manager m = (Manager) person;
			
			address.setText(m.getAddress());
			id.setText(String.valueOf(m.getId()));
			name.setText(m.getName());
			phone.setText(m.getPhone());
			age.setText(String.valueOf(m.getAge()));
			
			
			ppht.setText(String.valueOf(m.getPayPerHour()));
			odt.setText(String.valueOf(m.getOffDays()));
			hpdt.setText(String.valueOf(m.getHoursPerDay()));
		}else if(person instanceof Keeper) {
			Keeper m = (Keeper) person;
			
			address.setText(m.getAddress());
			id.setText(String.valueOf(m.getId()));
			name.setText(m.getName());
			phone.setText(m.getPhone());
			age.setText(String.valueOf(m.getAge()));
			
			
			ppht.setText(String.valueOf(m.getPayPerHour()));
			odt.setText(String.valueOf(m.getOffDays()));
			hpdt.setText(String.valueOf(m.getHoursPerDay()));

		}
		
	}
	@FXML
	public void update(ActionEvent event) {
		String query = "";
		if(updateb.getText().equals("Update Profile")) {
			updateb.setText("Done");
			if(person instanceof Customer) {
				address.setDisable(false);
				name.setDisable(false);
				phone.setDisable(false);
				age.setDisable(false);
			}else if(person instanceof Manager) {
				
				address.setDisable(false);
				name.setDisable(false);
				phone.setDisable(false);

				age.setDisable(false);
				ppht.setDisable(false);
				odt.setDisable(false);
				hpdt.setDisable(false);

				
			}else if(person instanceof Keeper) {

				address.setDisable(false);
				name.setDisable(false);
				phone.setDisable(false);
				age.setDisable(false);

			}
		}else if(updateb.getText().equals("Done")) {
			updateb.setText("Update Profile");
			//remove from model
			if(person instanceof Employee) {
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
			}
			if(person instanceof Customer) {
				for(Customer t: model.getCustomers()) {
					Customer c = (Customer) person;
					if(t.getEmail().equals(c.getEmail())) {
						model.getCustomers().remove(t);
					}
				}
			}
			
			if(person instanceof Customer) {
				address.setDisable(true);
				name.setDisable(true);
				phone.setDisable(true);
				age.setDisable(true);

				
				model.getCustomers().remove(person);
				person.setAddress(address.getText());
				person.setName(name.getText());
				person.setAge(Integer.parseInt(age.getText()));
				person.setPhone(phone.getText());

				model.getCustomers().add((Customer) person);
			}else if(person instanceof Manager) {
				
				address.setDisable(true);
				name.setDisable(true);
				phone.setDisable(true);
				age.setDisable(true);
				
				ppht.setDisable(true);
				odt.setDisable(true);
				hpdt.setDisable(true);
				Manager m = (Manager)person;

				m.setAddress(address.getText());
				m.setName(name.getText());
				m.setAge(Integer.parseInt(age.getText()));
				m.setPhone(phone.getText());
				m.setHoursPerDay(Integer.parseInt(hpdt.getText()));
				m.setOffDays(Integer.parseInt(odt.getText()));
				m.setPayPerHour(Double.parseDouble(ppht.getText()));
				
				model.ShowEmployee().add(m);
				
			}else if(person instanceof Keeper) {

				address.setDisable(true);
				name.setDisable(true);
				phone.setDisable(true);
				age.setDisable(true);
				Keeper k = (Keeper)person;

				k.setAddress(address.getText());
				k.setName(name.getText());
				k.setAge(Integer.parseInt(age.getText()));
				k.setPhone(phone.getText());
				
				model.ShowEmployee().add(k);
			} 
			//update the database then close this window
			model.setData();
			Stage stage1 = (Stage) name.getScene().getWindow();
			stage1.close();
		}
		
	}
	public void setPerson(Person p) {
		person = p;
	}
}
