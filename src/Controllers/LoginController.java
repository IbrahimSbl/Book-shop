package Controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;

import Customer.Customer;
import application.Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import person.Employee;
import person.Keeper;
import person.Manager;
import javafx.stage.*;

public class LoginController implements Initializable {
	@FXML
	private Label errorMessage; 
	@FXML
	private Button loginButton;
	@FXML
	private TextField emailTextField;
	@FXML
	private PasswordField pass;

	private Model model=new Model();
	public void loginOnAction(ActionEvent event){
		if((emailTextField.getText().isEmpty() == false) && (pass.getText().isEmpty() == false)) {
			validLogin();
            
        }
		else {
			errorMessage.setText("Invalid Login. Fill the empty fields.");
		}
	}

	public void validLogin(){
		
		FXMLLoader loader;
		Parent root = null;
		Stage stage = new Stage();
		Scene scene = null;
		String emailaddress = emailTextField.getText();
		String password = String.valueOf(pass.getText());
		
		ArrayList<Employee> e = model.ShowEmployee();
		for(int i=0;i<e.size();i++) {
			Employee employee = e.get(i);
			if(employee instanceof Manager) {
				Manager m = (Manager)employee;
				if(m.getEmail().equals(emailaddress.trim()) && m.getPassword().trim().equals(password.trim())) {
					errorMessage.setText("");
						//go to manager page and set its person 
						loader = new FXMLLoader(getClass().getResource("/Resources/ManagerPage.fxml"));
						try {
							root = loader.load();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//close this window
						stage = (Stage) loginButton.getScene().getWindow();
						stage.close();
						stage = new Stage();
						ManagerController mc = (ManagerController) loader.getController();
						mc.setPerson(m);
						mc.setScene();
						scene = new Scene(root); 
						stage.setScene(scene);
						stage.setTitle("Welcome "+m.getName().trim());
						stage.show();
					}
				
			}else if (employee instanceof Keeper) {
				Keeper m = (Keeper) employee;
				if(m.getEmail().equals(emailaddress.trim()) && m.getPassword().trim().equals(password.trim())) {
					errorMessage.setText("");
					//close this window
					stage = (Stage) loginButton.getScene().getWindow();
					stage.close();
					stage = new Stage();
						//go to keeper page and set its person 
						loader = new FXMLLoader(getClass().getResource("/Resources/KeeperPage.fxml"));
						try {
							root = loader.load();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						KeeperController mc = (KeeperController) loader.getController();
						mc.setPerson(m);
						mc.setScene();
						scene = new Scene(root); 
						stage.setScene(scene);
						stage.setTitle("Welcome "+m.getName().trim());
						stage.show();
					}
					
				
				
			}
		}
		
		ArrayList<Customer> c = model.getCustomers();
		for(int i=0;i<c.size();i++) {
				if(c.get(i).getEmail().equals(emailaddress.trim()) && c.get(i).getPassword().trim().equals(password.trim())) {
					errorMessage.setText("");
					//go to manager page and set its person 
					loader = new FXMLLoader(getClass().getResource("/Resources/CustomerPage.fxml"));
					try {
						root = loader.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//close this window
					stage = (Stage) pass.getScene().getWindow();
					stage.close();
					stage = new Stage();
					CustomerPageController mc = (CustomerPageController) loader.getController();
					mc.setPerson(c.get(i));
					mc.setScene();
					scene = new Scene(root); 
					stage.setScene(scene);
					stage.setTitle("Welcome "+c.get(i).getName().trim());
					stage.show();
					
				}
				
				
			}
		errorMessage.setText("Non valid login");
	}
	
	public void createAccount(ActionEvent event) {
		try {
			AnchorPane root;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/RegisterPage.fxml"));
			Stage stage = (Stage)errorMessage.getScene().getWindow();
			stage.close();//close current window
			root = loader.load();
			Stage registerStage = new Stage();
			Scene scene = new Scene(root,520,434);
			registerStage.setTitle("Registration");
			registerStage.setScene(scene);
			registerStage.show();
			
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
		


}

