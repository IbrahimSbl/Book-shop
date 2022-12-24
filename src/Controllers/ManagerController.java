package Controllers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import Product.ProductFactory;
import application.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import person.Keeper;
import person.Manager;
import person.Person;
import person.UpdateSalaryVisitor;
import person.Employee;

public class ManagerController implements Initializable{
		private Person person ;
		@FXML
		private FlowPane view;
		@FXML
		private Button logout;
		@FXML
		private Button employees;
		//for update salary interface
		@FXML 
		private TextField percentage;
		@FXML 
		private TextField payment;
		@FXML
		private Button calculate;
		@FXML
		private Button done;
		
		private Model model = new Model();
		boolean changed = false;
	
		@FXML
		public void showEmployees(ActionEvent event) throws FileNotFoundException {
			//to clear the products then replace the new one upon them
			if(changed == true) {
				model.loadData();//we are in the view so if the model is changed then just load the data
				changed = false;
			}
			view.getChildren().clear();
			for(int i=0;i<model.ShowEmployee().size();i++) {
				Employee e = model.ShowEmployee().get(i);
				if(e instanceof Manager) {
					if(((Manager)e).getEmail().equals(((Manager)person).getEmail())) {
						setPerson((Person)e);
					}
				}
				
				FlowPane main = new FlowPane(Orientation.HORIZONTAL);
				main.setVgap(1.5);
				main.setPrefWidth(261);;
				Image image = new Image(new FileInputStream("images/contact.png"));
				ImageView img = new ImageView();
				img.setImage(image);
				img.setFitWidth(260.0);
				img.setFitHeight(163.0);
				
				FlowPane inner = new FlowPane(Orientation.HORIZONTAL);
				inner.setPrefHeight(129);
				inner.setPrefWidth(261);
				inner.setVgap(1.5);
				main.getChildren().addAll(img,inner);

				TextField idt,namet,phonet,addresst,aget,jobt;
				int Id = 0;
				Label idl = new Label("ID:");
				idl.textFillProperty().set(Color.BLUE);
				idt = new TextField();
				if(model.ShowEmployee().get(i) instanceof Manager) {
					Manager en = (Manager)e;
					idt.setText(String.valueOf(Id=en.getId()));
				}else if (model.ShowEmployee().get(i) instanceof Keeper) {
					Keeper en = (Keeper) e;
					idt.setText(String.valueOf(Id=en.getId()));
				}
				
				idt.setPrefWidth(190);
				idl.setPrefWidth(64);
				idt.setDisable(true);
				idt.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
				
				Label namel = new Label("Name:");
				namel.textFillProperty().set(Color.BLUE);
				namet = new TextField();
				namet.setText(e.getName().trim());
				namet.setPrefWidth(190);
				namel.setPrefWidth(64);
				namet.setDisable(true);
				namet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
				
				Label addressl = new Label("Address:");
				addressl.textFillProperty().set(Color.BLUE);
				 addresst = new TextField();
				addresst.setText(e.getAddress().trim());
				addresst.setPrefWidth(190);
				addressl.setPrefWidth(64);
				addresst.setDisable(true);
				addresst.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
				
				Label phonel = new Label("Phone:");
				phonel.textFillProperty().set(Color.BLUE);
				 phonet = new TextField();
				phonet.setText(e.getPhone().trim());
				phonet.setPrefWidth(190);
				phonel.setPrefWidth(64);
				phonet.setDisable(true);
				phonet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
				
				Label agel = new Label("Age:");
				agel.textFillProperty().set(Color.BLUE);
				 aget = new TextField();
				aget.setText(String.valueOf(e.getAge()) );
				aget.setPrefWidth(190);
				agel.setPrefWidth(64);
				aget.setDisable(true);
				aget.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
				
				Label jobl = new Label("Role:");
				jobl.textFillProperty().set(Color.BLUE);
				jobt = new TextField();
				if(model.ShowEmployee().get(i) instanceof Manager) {

					jobt.setText("Manager");
				}else if (model.ShowEmployee().get(i) instanceof Keeper) {
					jobt.setText("Keeper");
					
				}
				jobt.setPrefWidth(190);
				jobl.setPrefWidth(64);
				jobt.setDisable(true);
				jobt.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
				
				Button btn = new Button("Update");
				btn.setId(String.valueOf(Id));
				btn.setOnAction(ev->{
					updateEmployee(e);
				});
				btn.setPrefHeight(27);
				btn.setPrefWidth(254);
				Button btn1 = new Button("Remove");
				btn1.setId(String.valueOf(Id));
				btn1.setOnAction(ev->{
					deleteEmployee(Integer.parseInt(btn1.getId()));
				});
				btn1.setPrefHeight(27);
				btn1.setPrefWidth(254);
				
				Button btn2 = new Button("Update Salary");
				btn2.setId(String.valueOf(Id));
				btn2.setOnAction(ev->{
					UpdateSalary(e);
				});
				btn2.setPrefHeight(27);
				btn2.setPrefWidth(254);
				
				Button btn3 = new Button("Show Salary");
				btn3.setId(String.valueOf(Id));
				btn3.setOnAction(ev->{
					getSalary(e);
				});
				btn3.setPrefHeight(27);
				btn3.setPrefWidth(254);
				
				inner.getChildren().addAll(idl,idt,namel,namet,addressl,addresst,phonel,phonet,agel,aget,jobl,jobt,btn,btn1,btn2,btn3);
				view.getChildren().add(main);
			}
		}

		public void getSalary(Employee e) {
			Stage stage = new Stage();
			Parent root = null;
			Scene scene;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/SalaryView.fxml"));
			SalaryViewController s= new SalaryViewController();
			loader.setController(s);
			try {
				root = loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			s.setEmployee(e);
			s.setScene();
			
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Salary");
			stage.setOnHiding(ev->{
				Calendar cal = Calendar.getInstance();
				Date t = cal.getTime();
				if(t.getDate() == 1) {//if the current day is the first in the month when the employee choose to see his salary
									  //then we set the off days to 0
					if(e instanceof Manager) {
						Manager m = (Manager)e;
						m.setOffDays(0);
					}else if(e instanceof Keeper) {
						Keeper m = (Keeper)e;
						m.setOffDays(0);
					}
				}
				model.setData();
				model.loadData();
			});
			stage.show();
		}
		public void UpdateSalary(Employee e) {
			if(changed == true) {
				model.loadData();
				
			}
			changed = true;
			view.getParent().setDisable(true);
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/AlertUpdate.fxml"));
			AlertUpdateController alert = new AlertUpdateController();
			loader.setController(alert);
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			alert.setEmployee(e);
			alert.setScene();
			Scene scene = new Scene(root);
			stage.setTitle("Update Salary");
			stage.setScene(scene);
			stage.setOnHiding(event->{
				Employee employee = (Employee)stage.getUserData();
				if(employee instanceof Manager) {
					Manager m = (Manager)employee;
					model.RemoveEmployee(e);//I put it here since if we exit the update salary scene without updating any thing the getUserData returns null  
					model.AddEmployee(m);
				}else if (employee instanceof Keeper) {
					Keeper m = (Keeper) employee;
					model.RemoveEmployee(e);//I put it here since if we exit the update salary scene without updating any thing the getUserData returns null  
					model.AddEmployee(m);
					person =m;
				}
				model.setData();
				view.getParent().setDisable(false);
				employees.fire();
			});
			stage.show();
			
		}
		@FXML
		public void showProfile(ActionEvent event) throws IOException {
			changed = true;
			AnchorPane ap = new AnchorPane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ProfilePage.fxml"));
			ap = loader.load();
			
			//get the instance of the ProfilePageController to set the person at login controller 
			//and then set the scene to fill the data inside it
			ProfilePageController ppc = (ProfilePageController)loader.getController();
			ppc.setPerson(person);//to set the person attribute at the customer controller
			ppc.setScene();//to load the data when called
			
			Scene scene = new Scene(ap);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}
		@FXML
		public void logout(ActionEvent event) throws IOException {
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setOnHiding(e->{
				model.setData();
			});
			stage.close();
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/LOGINPage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			model.setData();
		}
		public void setPerson(Person person) {
			this.person = person;
		}
		public void setScene() {
			employees.fire();
		}
		@FXML
		public void addEmployee(ActionEvent event) {
			if(changed == true) {
				model.loadData();//we are in the view so if the model is changed then just load the data
			}
			changed = true;
			//we need a view to take the the inputs enter them to database then fire the show employee
			FXMLLoader loader;
			Parent root = null;
			Stage stage = new Stage();
			Scene scene;
			loader = new FXMLLoader(getClass().getResource("/Resources/UpdatePage.fxml"));
			AddEmployeeController p = new AddEmployeeController();
			
			loader.setController(p);
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.setScene();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Add Employee");
			
			stage.show();
			
		}
		public void deleteEmployee(int id){
			for(int i=0;i<model.ShowEmployee().size();i++) {
				if(model.ShowEmployee().get(i) instanceof Manager) {
					Manager en = (Manager)model.ShowEmployee().get(i);
					if(en.getId() == id) {
						//delete employee from model
						model.ShowEmployee().remove(en);
						break;
					}
					
				}else if (model.ShowEmployee().get(i) instanceof Keeper) {
					Keeper en = (Keeper)model.ShowEmployee().get(i);
					if(en.getId() == id) {
						//delete employee from model
						model.ShowEmployee().remove(en);
						break;
					}
				}
			}
			//save changes into database
			model.setData();
			changed = true;
			//then update the scene
			employees.fire();
			
		}
		public void updateEmployee(Employee e){
			//interface to update
			FXMLLoader loader;
			Parent root = null;
			Stage stage = new Stage();
			view.getParent().setDisable(true);
			Scene scene;
			loader = new FXMLLoader(getClass().getResource("/Resources/UpdatePage.fxml"));
			
			UpdateController p = new UpdateController();
			loader.setController(p);
			try {
				root = loader.load();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
			p.setPerson(e);
			p.setScene();
			changed = true;
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Profile");
			stage.setOnHiding(ev->{
				model.loadData();
				view.getParent().setDisable(false);
			});
			
			stage.show();
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
		
		
		
}