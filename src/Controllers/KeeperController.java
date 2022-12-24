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
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import Customer.Customer;
import Product.Book;
import Product.Fiction;
import Product.History;
import Product.Journal;
import Product.Magazine;
import Product.Politics;
import Product.ProductFactory;
import Product.Scientific;
import Product.Technology;
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
import person.Employee;
import person.Keeper;
import person.Manager;
import person.Person;

public class KeeperController implements Initializable{
		private Person person ;
		@FXML
		private FlowPane view;
		@FXML
		private Button logout;
		@FXML
		private Button books;
		@FXML
		private Button journals;
		@FXML
		private Button magazines;
		boolean changed = false;
		Model model = new Model();
		@FXML
		public void showBook(ActionEvent event) throws FileNotFoundException{
			//to clear the products then replace the new one upon them
			if(changed == true) {
				model.loadData();
				changed = false;
			}
			view.getChildren().clear();
			for(int i=0;i<model.getInventory().getStock().size();i++) {
				if(model.getInventory().getStock().get(i) instanceof Book) {
					Book b= (Book)model.getInventory().getStock().get(i); 
					FlowPane main = new FlowPane(Orientation.HORIZONTAL);
					main.setVgap(1.5);
					main.setPrefWidth(261);;
					Image image = new Image(new FileInputStream("images/"+b.getName().trim()+".png"));
					ImageView img = new ImageView();
					img.setFitWidth(260);
					img.setFitHeight(163.0);
					img.setImage(image);
					
					FlowPane inner = new FlowPane(Orientation.HORIZONTAL);
					inner.setPrefHeight(129);
					inner.setPrefWidth(261);
					inner.setVgap(1.5);
					
					Label namel = new Label("Name:");
					namel.textFillProperty().set(Color.BLUE);
					TextField namet = new TextField();
					namet.setText(b.getName());
					namet.setPrefWidth(190);
					namel.setPrefWidth(64);
					namet.setDisable(true);
					namet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label pricel = new Label("Price:");
					pricel.textFillProperty().set(Color.BLUE);
					TextField pricet = new TextField();
					pricet.setText(String.valueOf(b.getPrice()));
					pricet.setPrefWidth(190);
					pricel.setPrefWidth(64);
					pricet.setDisable(true);
					pricet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label publisherl = new Label("Publisher:");
					publisherl.textFillProperty().set(Color.BLUE);
					TextField publishert = new TextField();
					publishert.setText(b.getPublisher());
					publishert.setPrefWidth(190);
					publisherl.setPrefWidth(64);
					publishert.setDisable(true);
					publishert.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label descriptionl = new Label("Description:");
					descriptionl.textFillProperty().set(Color.BLUE);
					TextField descriptiont = new TextField();
					descriptiont.setText(b.getProduct().getpreview());
					descriptiont.setPrefWidth(190);
					descriptiont.setPrefHeight(58);
					descriptionl.setPrefWidth(64);
					descriptiont.setDisable(true);
					descriptiont.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label quantityl = new Label("Quantity:");
					quantityl.textFillProperty().set(Color.BLUE);
					TextField quantityt = new TextField();
					quantityt.setText(String.valueOf(b.getStock()));
					quantityt.setPrefWidth(190);
					quantityl.setPrefWidth(64);
					quantityt.setDisable(true);
					quantityt.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");

					Button btnedt = new Button("Edit");
					btnedt.setPrefHeight(25);
					btnedt.setPrefWidth(254);
					btnedt.setId(String.valueOf(b.getId()));
					btnedt.setOnAction(e->{
						editProduct(b);
					});
					
					Button btndlt = new Button("Delete");
					btndlt.setPrefHeight(25);
					btndlt.setPrefWidth(254);
					btndlt.setId(String.valueOf(b.getId()));
					btndlt.setOnAction(e->{
						deleteProduct(b);
					});
					
					inner.getChildren().addAll(namel,namet,pricel,pricet,publisherl,publishert,descriptionl,descriptiont,quantityl,quantityt,btnedt,btndlt);

					main.getChildren().addAll(img,inner);
					
					view.getChildren().add(main);
				}
				}
				
			
		}
		@FXML
		public void showMagazine(ActionEvent event) throws FileNotFoundException {
			//to clear the products then replace the new one upon them
			if(changed == true) {
				model.loadData();
				changed = false;
			}
			view.getChildren().clear();
			for(int i=0;i<model.getInventory().getStock().size();i++) {
				if(model.getInventory().getStock().get(i) instanceof Magazine) {
					Magazine b= (Magazine)model.getInventory().getStock().get(i); 
					FlowPane main = new FlowPane(Orientation.HORIZONTAL);
					main.setVgap(1.5);
					main.setPrefWidth(261);;
					Image image = new Image(new FileInputStream("images/"+b.getName().trim()+".png"));
					ImageView img = new ImageView();
					img.setFitWidth(260);
					img.setFitHeight(163.0);
					img.setImage(image);
					
					FlowPane inner = new FlowPane(Orientation.HORIZONTAL);
					inner.setPrefHeight(129);
					inner.setPrefWidth(261);
					inner.setVgap(1.5);
					
					Label namel = new Label("Name:");
					namel.textFillProperty().set(Color.BLUE);
					TextField namet = new TextField();
					namet.setText(b.getName());
					namet.setPrefWidth(190);
					namel.setPrefWidth(64);
					namet.setDisable(true);
					namet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label pricel = new Label("Price:");
					pricel.textFillProperty().set(Color.BLUE);
					TextField pricet = new TextField();
					pricet.setText(String.valueOf(b.getPrice()));
					pricet.setPrefWidth(190);
					pricel.setPrefWidth(64);
					pricet.setDisable(true);
					pricet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label publisherl = new Label("Publisher:");
					publisherl.textFillProperty().set(Color.BLUE);
					TextField publishert = new TextField();
					publishert.setText(b.getPublisher());
					publishert.setPrefWidth(190);
					publisherl.setPrefWidth(64);
					publishert.setDisable(true);
					publishert.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label descriptionl = new Label("Description:");
					descriptionl.textFillProperty().set(Color.BLUE);
					TextField descriptiont = new TextField();
					descriptiont.setText(b.getProduct().getpreview());
					descriptiont.setPrefWidth(190);
					descriptiont.setPrefHeight(58);
					descriptionl.setPrefWidth(64);
					descriptiont.setDisable(true);
					descriptiont.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label quantityl = new Label("Quantity:");
					quantityl.textFillProperty().set(Color.BLUE);
					TextField quantityt = new TextField();
					quantityt.setText(String.valueOf(b.getStock()));
					quantityt.setPrefWidth(190);
					quantityl.setPrefWidth(64);
					quantityt.setDisable(true);
					quantityt.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");

					Button btnedt = new Button("Edit");
					btnedt.setPrefHeight(25);
					btnedt.setPrefWidth(254);
					btnedt.setId(String.valueOf(b.getId()));
					btnedt.setOnAction(e->{
						editProduct(b);
					});
					
					Button btndlt = new Button("Delete");
					btndlt.setPrefHeight(25);
					btndlt.setPrefWidth(254);
					btndlt.setId(String.valueOf(b.getId()));
					btndlt.setOnAction(e->{
						deleteProduct(b);
					});
					
					inner.getChildren().addAll(namel,namet,pricel,pricet,publisherl,publishert,descriptionl,descriptiont,quantityl,quantityt,btnedt,btndlt);

					main.getChildren().addAll(img,inner);
					
					view.getChildren().add(main);
				}		
			}
		}
		@FXML
		public void showJournal(ActionEvent event) throws FileNotFoundException{
			//to clear the products then replace the new one upon them
			if(changed == true) {
				model.loadData();
				changed = false;
			}
			view.getChildren().clear();
			for(int i=0;i<model.getInventory().getStock().size();i++) {
				if(model.getInventory().getStock().get(i) instanceof Magazine) {
					Magazine b= (Magazine)model.getInventory().getStock().get(i); 
					FlowPane main = new FlowPane(Orientation.HORIZONTAL);
					main.setVgap(1.5);
					main.setPrefWidth(261);;
					Image image = new Image(new FileInputStream("images/"+b.getName().trim()+".png"));
					ImageView img = new ImageView();
					img.setFitWidth(260);
					img.setFitHeight(163.0);
					img.setImage(image);
					
					FlowPane inner = new FlowPane(Orientation.HORIZONTAL);
					inner.setPrefHeight(129);
					inner.setPrefWidth(261);
					inner.setVgap(1.5);
					
					Label namel = new Label("Name:");
					namel.textFillProperty().set(Color.BLUE);
					TextField namet = new TextField();
					namet.setText(b.getName());
					namet.setPrefWidth(190);
					namel.setPrefWidth(64);
					namet.setDisable(true);
					namet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label pricel = new Label("Price:");
					pricel.textFillProperty().set(Color.BLUE);
					TextField pricet = new TextField();
					pricet.setText(String.valueOf(b.getPrice()));
					pricet.setPrefWidth(190);
					pricel.setPrefWidth(64);
					pricet.setDisable(true);
					pricet.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label publisherl = new Label("Publisher:");
					publisherl.textFillProperty().set(Color.BLUE);
					TextField publishert = new TextField();
					publishert.setText(b.getPublisher());
					publishert.setPrefWidth(190);
					publisherl.setPrefWidth(64);
					publishert.setDisable(true);
					publishert.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label descriptionl = new Label("Description:");
					descriptionl.textFillProperty().set(Color.BLUE);
					TextField descriptiont = new TextField();
					descriptiont.setText(b.getProduct().getpreview());
					descriptiont.setPrefWidth(190);
					descriptiont.setPrefHeight(58);
					descriptionl.setPrefWidth(64);
					descriptiont.setDisable(true);
					descriptiont.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");
					
					Label quantityl = new Label("Quantity:");
					quantityl.textFillProperty().set(Color.BLUE);
					TextField quantityt = new TextField();
					quantityt.setText(String.valueOf(b.getStock()));
					quantityt.setPrefWidth(190);
					quantityl.setPrefWidth(64);
					quantityt.setDisable(true);
					quantityt.setStyle("-fx-background-radius:10;"+"-fx-border-color:red;"+"-fx-border-radius:10;");

					Button btnedt = new Button("Edit");
					btnedt.setPrefHeight(25);
					btnedt.setPrefWidth(254);
					btnedt.setId(String.valueOf(b.getId()));
					btnedt.setOnAction(e->{
						editProduct(b);
					});
					
					Button btndlt = new Button("Delete");
					btndlt.setPrefHeight(25);
					btndlt.setPrefWidth(254);
					btndlt.setId(String.valueOf(b.getId()));
					btndlt.setOnAction(e->{
						deleteProduct(b);
					});
					
					inner.getChildren().addAll(namel,namet,pricel,pricet,publisherl,publishert,descriptionl,descriptiont,quantityl,quantityt,btnedt,btndlt);

					main.getChildren().addAll(img,inner);
					
					view.getChildren().add(main);
				}						
			}
		}
		@FXML
		public void addBook(ActionEvent event){
			view.getParent().setDisable(true);//disable the view
			//interface to add
			if(changed == true) {
				model.loadData();
			}
			Parent ap = new AnchorPane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ProductPage.fxml"));
			AddProductController pc = new AddProductController();
			//to set controller to the fxml document because I use it to many controllers
			loader.setController(pc);
			
			try {
				ap = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pc.setScene();
			Scene scene  = new Scene(ap);
			Stage stage = new Stage();
			stage.setTitle("Add");
			stage.setScene(scene);
			stage.setOnHiding(ev->{
				view.getParent().setDisable(false);
				model.loadData();
			});
			stage.show();
		}
		public void deleteProduct(ProductFactory p) {
			if(changed == true) {
				model.loadData();
				changed = false;
			}
			model.getInventory().getStock().remove(p);//remove product from stock
			model.setData();
			model.loadData();
			if(p instanceof Book) {
				books.fire();
			}else if(p instanceof Journal) {
				journals.fire();
			}else {
				magazines.fire();
			}
			
		}
		public void editProduct(ProductFactory p){
			view.getParent().setDisable(true);//fordisabling the view when moving to other
			//interface to update
			if(changed == true) {
				model.loadData();
				changed = false;
			}
			Parent ap = new AnchorPane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ProductPage.fxml"));
			ProductPageController pc = new ProductPageController();
			
			pc.setFactory(p);
			loader.setController(pc);
			
			try {
				ap = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pc.setScene();
			Scene scene  = new Scene(ap);
			Stage stage = new Stage();
			stage.setTitle("Edit");
			stage.setScene(scene);
			stage.setOnHiding(e->{
				view.getParent().setDisable(false);
				model.loadData();
				changed = false;
			});
			
			stage.show();
		}
		@FXML
		public void showProfile(ActionEvent event) throws IOException {
			view.getParent().setDisable(true);
			changed = true;
			AnchorPane ap = new AnchorPane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ProfilePage.fxml"));
			ap = loader.load();
			//get the instance of the ProfilePageController to set the person at login controller 
			//and then set the scene to fill the data inside it
			ProfilePageController ppc = (ProfilePageController)loader.getController();
			ppc.setPerson(person);//to set the person attribute at the profile controller
			ppc.setScene();//to load the data when called
			
			Scene scene = new Scene(ap);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setOnHiding(ev->{
				view.getParent().setDisable(false);
			});
			stage.show();
		}
		@FXML
		public void logout(ActionEvent event) throws IOException {
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.setOnHiding(e->{
				model.setData();
			});
			stage.close();
			Parent root ;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/LOGINPage.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		@FXML
		public void showSalary(ActionEvent event) {
			Keeper e = (Keeper)person;
			for(Employee t:model.ShowEmployee()) {//check if employee reference is changed then update it
				if(t instanceof Keeper) {
					Keeper t1 = (Keeper)t;
					if(t1.getEmail().equals(e.getEmail())) {
						this.setPerson(t1);
						e = t1;
					}
				}
			}
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
				if(t.getDate() == 1) {
					
					((Keeper)person).setOffDays(0);
					model.setData();
					model.loadData();
				}
			});
			stage.show();
		}
		@FXML
		public void discount(ActionEvent event) {
			view.getParent().setDisable(true);
			Stage stage = new Stage();
			Parent root = null;
			Scene scene;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/DiscountPage.fxml"));
			try {
				root = loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Discount");
			stage.setOnHiding(ev->{
				model.loadData();
				view.getParent().setDisable(false);
			});
			stage.show();
		}
		
		public void setPerson(Person person) {
			this.person = person;
		}
		public void setScene() {
			books.fire();
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
}