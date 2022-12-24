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
import java.util.ResourceBundle;

import Customer.Customer;
import Product.Book;
import Product.Inventory;
import Product.Journal;
import Product.Magazine;
import Product.ProductFactory;
import application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import person.Keeper;
import person.Manager;
import person.Person;

public class CustomerPageController implements Initializable{
		private Customer person ;
		
		@FXML
		private FlowPane view;
		@FXML
		private Button logout;
		@FXML
		private Button books;
		Model model = new Model();
		@FXML
		public void showBook(ActionEvent event) throws FileNotFoundException{
			model = new Model();
			//to clear the products then replace the new one upon them
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
					
					Button btn = new Button("Add To Cart");
					btn.setOnAction(e->{
						addToCart(b);
					});
					btn.setPrefHeight(50);
					btn.setPrefWidth(254);
					
					inner.getChildren().addAll(namel,namet,pricel,pricet,publisherl,publishert,descriptionl,descriptiont,quantityl,quantityt,btn);

					main.getChildren().addAll(img,inner);
					
					view.getChildren().add(main);
				}
				}
				
			
		}
		@FXML
		public void showMagazine(ActionEvent event) throws FileNotFoundException{
			model.loadData();
			//to clear the products then replace the new one upon them
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
					
					Button btn = new Button("Add To Cart");
					btn.setOnAction(e->{
						addToCart(b);
					});
					btn.setPrefHeight(50);
					btn.setPrefWidth(254);
					
					
					inner.getChildren().addAll(namel,namet,pricel,pricet,publisherl,publishert,descriptionl,descriptiont,quantityl,quantityt,btn);

					main.getChildren().addAll(img,inner);
					
					view.getChildren().add(main);
				}
				}
				
		}		
		@FXML
		public void showJournal(ActionEvent event) throws FileNotFoundException{
			model.loadData();
			//to clear the products then replace the new one upon them
			view.getChildren().clear();

			for(int i=0;i<model.getInventory().getStock().size();i++) {
				if(model.getInventory().getStock().get(i) instanceof Journal) {
					Journal b= (Journal)model.getInventory().getStock().get(i); 
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
					
					Button btn = new Button("Add To Cart");
					btn.setOnAction(e->{
						addToCart(b);
					});
					btn.setPrefHeight(50);
					btn.setPrefWidth(254);
					
					
					inner.getChildren().addAll(namel,namet,pricel,pricet,publisherl,publishert,descriptionl,descriptiont,quantityl,quantityt,btn);

					main.getChildren().addAll(img,inner);
					
					view.getChildren().add(main);
				}
				}
				
		}
		public void addToCart(ProductFactory p) {
			boolean book=false,magazine=false,journal=false;
			if(p instanceof Book) {
				book = true;
			}else if(p instanceof Magazine) {
				magazine = true;
			}else {
				journal = true;
			}
			if(person.getCart().getCart().contains(p))return;
			person.getCart().AddItem(p);
			for(ProductFactory pf : model.getInventory().getStock()) {//decrease the quantity by 1
				if(pf.getName().equals(p.getName())) {
					if(pf.getStock() == 1) {
						model.RemoveProduct(pf);
					}else {
						pf.setStock(pf.getStock()-1);
					}
					break;
				}
			}
			System.out.println("set data");
			model.setData();
			if(book) {
				books.fire();//then add references to each button and fire it
			}
		}
		@FXML
		public void showCart(ActionEvent event) {
			Parent root = null;
			Stage stage = new Stage();
			Scene scene ;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/Cart.fxml"));
			//Disable the view till coming back from cart
			view.getParent().setDisable(true);
			CartController c = new CartController();
			loader.setController(c);
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			c.setCustomer(person);
			c.setScene();
			
			scene = new Scene(root);
			stage.setTitle("Shopping Cart");
			stage.setScene(scene);
			stage.setOnHiding(e->{
				if(stage.getUserData() instanceof String) {
					Payment(person);
				}else {
					//enable the view
					view.getParent().setDisable(false);
					Customer cs = (Customer) stage.getUserData();//the item is removed
					if(cs !=null) {
						person = cs;
						model.loadData();
					}
				}
			});
			stage.show();
		}
		public void Payment(Customer customer) {
			//the view still disabled
			Parent root = null;
			Stage stage = new Stage();
			Scene scene ;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/PaymentPage.fxml"));
			PaymentPageController payment = new PaymentPageController();
			loader.setController(payment);
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			payment.setCustomer(person);
			
			scene = new Scene(root);
			stage.setTitle("Payment");
			stage.setScene(scene);
			stage.setOnHiding(e->{
				//enable the view
				view.getParent().setDisable(false);
				Customer cs = (Customer) stage.getUserData();
				if(cs !=null) {
					person = cs;
					model.loadData();
				}
				
			});
			stage.show();
		}
		
		
		@FXML
		public void showProfile(ActionEvent event) throws IOException {
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
			stage.show();
		}
		@FXML
		public void logout(ActionEvent event) throws IOException {
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.close();
			Parent root ;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/LOGINPage.fxml"));
			
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setOnHiding(ev->{
				model.setData();
			});
			stage.show();
			
		}
		//to set the scene initially on book by clicking the book button
		public void setScene() {//this function is called from the login page
			books.fire();//when closed save data to database
			
		}
		public void setPerson(Person person) {
			this.person = (Customer) person;
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
		
		
		
}