package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Customer.Customer;
import application.Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController implements Initializable{
	@FXML
	private Label successMessage;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField confirmPass;
	@FXML
	private Label passError;
	@FXML
	private TextField fnTextField;
	@FXML
	private TextField lnTextField;
	@FXML
	private TextField emailTextField;
	private Model model = new Model();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}
	
	public void registerButtonOnAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		
		
		if(fnTextField.getText().isEmpty() || lnTextField.getText().isEmpty() ) {
			passError.setText("Please enter your full name");
			successMessage.setText("");
            return;
        }else if(emailTextField.getText().isEmpty()) {
        	passError.setText("Please enter your email address");
			successMessage.setText("");
            return;
        }else if(!emailTextField.getText().isEmpty()){//if there is an entered email
        											  //we will check if it is in the database in the database
        	
        	ArrayList<Customer> c = model.getCustomers();
        	for(int i=0;i<c.size();i++) {
        		if(c.get(i).getEmail().equals(emailTextField.getText())) {
        			passError.setText("This email address is registered before!!");
	        		return;
        		}
        	}
        	if(pass.getText().isEmpty()) {
                
            	passError.setText("Please enter a password");
    			successMessage.setText("");
                return;
            }else if(pass.getText().equals(confirmPass.getText())) {
    			registerUser();
    			passError.setText("");
    			successMessage.setText("User has been registered successfuly");
    		}
    		else {
    			passError.setText("Password does not match");
    			successMessage.setText("");
    		}
    		
        } 
	}
	
	public void registerUser() throws SQLException, ClassNotFoundException, IOException  {
		
		String first = fnTextField.getText();
		String last = lnTextField.getText();
		String email = emailTextField.getText();
		String password = pass.getText();

		Customer newRegistered = new Customer(first + " "+last,/*phone number but now */null,/*address but now */null, /*age but now */0,email,password);
		
		model.getCustomers().add(newRegistered);
		//redirects the customer to the customer page
		passError.setText("");
		//direct the customer to the customer page and set its view and object 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/CustomerPage.fxml"));
		Parent root = loader.load();
		//close this window
		Stage stage = new Stage();
		model.setData();
		stage.close();
		//get the instance of the CustomerPageController to set the person at Customer controller 
		//and then set the scene to fill the data inside it
		CustomerPageController cpc = (CustomerPageController) loader.getController();
		//cpc.setModel(model);
		cpc.setPerson(newRegistered);
		cpc.setScene();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Welcome "+newRegistered.getName());
		stage.show();
		
	}
	
}

