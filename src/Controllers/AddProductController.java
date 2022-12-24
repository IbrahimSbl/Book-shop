
package Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import person.Keeper;
import person.Manager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gluonhq.charm.glisten.control.TextField;

import Customer.Customer;
import Product.Book;
import Product.Fiction;
import Product.History;
import Product.Journal;
import Product.Magazine;
import Product.Politics;
import Product.Product;
import Product.ProductFactory;
import Product.Scientific;
import Product.Technology;
import application.Model;

public class AddProductController {
	@FXML
	private ImageView image;
	@FXML
	private FlowPane main;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField author;
	@FXML
	private TextField publisher;
	@FXML
	private TextField edition;
	@FXML
	private TextField copytype;
	@FXML
	private TextField cover;
	@FXML
	private TextField stock;
	@FXML
	private TextField price;
	@FXML
	private TextField preview;
	@FXML
	private FlowPane main1;
	@FXML
	private TextField period;
	@FXML
	private Label periodl;
	@FXML
	private TextField charac;
	@FXML
	private Label characl;
	@FXML
	private Label placel;
	@FXML
	private TextField place;
	@FXML
	private TextField time;
	@FXML
	private Label timel;
	@FXML
	private TextField problem;
	@FXML
	private Label probleml;
	@FXML
	private TextField topic;
	@FXML
	private Label topicl;
	@FXML
	private Label error;
	@FXML
	private Button edit;
	boolean history,politics,fiction,scientific,technology,book,magazine,journal;
	private Model model = new Model();
	private ArrayList<ProductFactory> pf = model.getInventory().getStock();
	
	public void setScene() {
		period.setStyle("visibility:hidden;");
		periodl.setStyle("visibility:hidden;");
		charac.setStyle("visibility:hidden;");
		characl.setStyle("visibility:hidden;");
		placel.setStyle("visibility:hidden;");
		place.setStyle("visibility:hidden;");
		time.setStyle("visibility:hidden;");
		timel.setStyle("visibility:hidden;");
		problem.setStyle("visibility:hidden;");
		probleml.setStyle("visibility:hidden;");
		topic.setStyle("visibility:hidden;");
		topicl.setStyle("visibility:hidden;");
		
		id.setDisable(true);
		name.setDisable(true);
		
		author.setDisable(true);
		publisher.setDisable(true);
		edition.setDisable(true);
		copytype.setDisable(true);
		cover.setDisable(true);
		stock.setDisable(true);
		price.setDisable(true);
		preview.setDisable(true);
		
		Label lb = new Label("Choose to continue:");
		
		ToggleGroup radios = new ToggleGroup();
		RadioButton his = new RadioButton("History");
		RadioButton pol = new RadioButton("Politics");
		RadioButton fic = new RadioButton("Fiction");
		RadioButton sci = new RadioButton("Scientific");
		RadioButton tech = new RadioButton("Technology");
		//set toggle group
		his.setToggleGroup(radios);
		pol.setToggleGroup(radios);
		fic.setToggleGroup(radios);
		sci.setToggleGroup(radios);
		tech.setToggleGroup(radios);
		
		his.setOnAction(e->{
			period.setStyle("visibility:visible;");
			periodl.setStyle("visibility:visible;");
			charac.setStyle("visibility:hidden;");
			characl.setStyle("visibility:hidden;");
			placel.setStyle("visibility:hidden;");
			place.setStyle("visibility:hidden;");
			time.setStyle("visibility:hidden;");
			timel.setStyle("visibility:hidden;");
			problem.setStyle("visibility:hidden;");
			probleml.setStyle("visibility:hidden;");
			topic.setStyle("visibility:hidden;");
			topicl.setStyle("visibility:hidden;");

			period.setDisable(false);
			
			history=true;
			politics=false;
			fiction=false;
			scientific=false;
			technology=false;
		});
		pol.setOnAction(e->{
			period.setStyle("visibility:hidden;");
			periodl.setStyle("visibility:hidden;");
			charac.setStyle("visibility:hidden;");
			characl.setStyle("visibility:hidden;");
			placel.setStyle("visibility:hidden;");
			place.setStyle("visibility:hidden;");
			time.setStyle("visibility:hidden;");
			timel.setStyle("visibility:hidden;");
			problem.setStyle("visibility:hidden;");
			probleml.setStyle("visibility:hidden;");
			topic.setStyle("visibility:visible;");
			topicl.setStyle("visibility:visible;");

			topic.setDisable(false);
			
			history=false;
			politics=true;
			fiction=false;
			scientific=false;
			technology=false;
		});
		fic.setOnAction(e->{
			period.setStyle("visibility:hidden;");
			periodl.setStyle("visibility:hidden;");
			charac.setStyle("visibility:visible;");
			characl.setStyle("visibility:visible;");
			placel.setStyle("visibility:visible;");
			place.setStyle("visibility:visible;");
			time.setStyle("visibility:visible;");
			timel.setStyle("visibility:visible;");
			problem.setStyle("visibility:visible;");
			probleml.setStyle("visibility:visible;");
			topic.setStyle("visibility:hidden;");
			topicl.setStyle("visibility:hidden;");

			
			charac.setDisable(false);
			place.setDisable(false);
			time.setDisable(false);
			problem.setDisable(false);
			
			history=false;
			politics=false;
			fiction=true;
			scientific=false;
			technology=false;
		});
		sci.setOnAction(e->{
			period.setStyle("visibility:hidden;");
			periodl.setStyle("visibility:hidden;");
			charac.setStyle("visibility:hidden;");
			characl.setStyle("visibility:hidden;");
			placel.setStyle("visibility:hidden;");
			place.setStyle("visibility:hidden;");
			time.setStyle("visibility:hidden;");
			timel.setStyle("visibility:hidden;");
			problem.setStyle("visibility:hidden;");
			probleml.setStyle("visibility:hidden;");
			topic.setStyle("visibility:visible;");
			topicl.setStyle("visibility:visible;");

			topic.setDisable(false);
			
			history=false;
			politics=false;
			fiction=false;
			scientific=true;
			technology=false;
		});
		tech.setOnAction(e->{
			period.setStyle("visibility:hidden;");
			periodl.setStyle("visibility:hidden;");
			charac.setStyle("visibility:hidden;");
			characl.setStyle("visibility:hidden;");
			placel.setStyle("visibility:hidden;");
			place.setStyle("visibility:hidden;");
			time.setStyle("visibility:hidden;");
			timel.setStyle("visibility:hidden;");
			problem.setStyle("visibility:hidden;");
			probleml.setStyle("visibility:hidden;");
			topic.setStyle("visibility:visible;");
			topicl.setStyle("visibility:visible;");

			topic.setDisable(false);
			history=false;
			politics=false;
			fiction=false;
			scientific=false;
			technology=true;
		});
		Label lb1 = new Label("Choose to continue:");
		
		ToggleGroup radio = new ToggleGroup();
		RadioButton b = new RadioButton("Book");
		RadioButton m = new RadioButton("Magazine");
		RadioButton j = new RadioButton("Journal");
		b.setToggleGroup(radio);
		m.setToggleGroup(radio);
		j.setToggleGroup(radio);
		
		b.setOnAction(e->{
			lb1.setStyle("visibility:hidden;");
			b.setStyle("visibility:hidden;");
			m.setStyle("visibility:hidden;");
			j.setStyle("visibility:hidden;");
			book = true;
			magazine = false;
			journal = false;
			main1.getChildren().addAll(lb,his,pol,fic,sci,tech);
		});
		m.setOnAction(e->{
			lb1.setStyle("visibility:hidden;");
			b.setStyle("visibility:hidden;");
			m.setStyle("visibility:hidden;");
			j.setStyle("visibility:hidden;");
			
			book = false;
			magazine = true;
			journal = false;
			main1.getChildren().addAll(lb,his,pol,fic,sci,tech);
		});
		j.setOnAction(e->{
			lb1.setStyle("visibility:hidden;");
			b.setStyle("visibility:hidden;");
			m.setStyle("visibility:hidden;");
			j.setStyle("visibility:hidden;");
			
			book = false;
			magazine = false;
			journal = true;
			main1.getChildren().addAll(lb,his,pol,fic,sci,tech);
		});
		main1.getChildren().addAll(lb1,b,m,j);
		
		edit.setText("ADD");
	}
	// Event Listener on Button[#edit].onAction
	@FXML
	public void update(ActionEvent event) {
		// TODO Autogenerated
		String query = "";
		if(edit.getText().equals("ADD")) {
			edit.setText("Done");
			id.setDisable(true);
			name.setDisable(false);
			
			author.setDisable(false);
			publisher.setDisable(false);
			edition.setDisable(false);
			copytype.setDisable(false);
			cover.setDisable(false);
			stock.setDisable(false);
			price.setDisable(false);
			preview.setDisable(false);
			if(fiction) {
				charac.setDisable(false);
				place.setDisable(false);
				time.setDisable(false);
				problem.setDisable(false);

			}else if(history) {
				period.setDisable(false);
				
			}else if(politics) {
				
				topic.setDisable(false);
				
			}else if(scientific) {
				topic.setDisable(false);
				
			}else if(technology) {
				topic.setDisable(false);
				
			}
		
		}else if(edit.getText().equals("Done")) {
			

			if(name.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()
					 || edition.getText().isEmpty() || copytype.getText().isEmpty() || cover.getText().isEmpty() || stock.getText().isEmpty()
					 || price.getText().isEmpty() || preview.getText().isEmpty()) {
				error.setText("Fill all fields");
				return;
			}
			if(!book && !magazine && !journal) {
				error.setText("Choose a type");
				return;
			}
			if(!fiction && !history && !politics && !technology && ! scientific) {
				error.setText("Choose a type");
				return;
			}
			//check if the product is found before
			for(int i = 0;i<pf.size();i++) {
				if(pf.get(i).getName().equals(name.getText())) {
					error.setText("This item is added before");
					return;
				}
			}
			id.setDisable(true);
			name.setDisable(true);
			
			author.setDisable(true);
			publisher.setDisable(true);
			edition.setDisable(true);
			copytype.setDisable(true);
			cover.setDisable(true);
			stock.setDisable(true);
			price.setDisable(true);
			preview.setDisable(true);
			
			ProductFactory pfactory=null;
			if(book) {
				pfactory = new Book(Double.valueOf(price.getText()),Integer.parseInt(stock.getText()),name.getText(),author.getText(),publisher.getText(),edition.getText(),copytype.getText(),cover.getText());
			}else if(magazine) {
				pfactory = new Magazine(Double.valueOf(price.getText()),Integer.parseInt(stock.getText()),name.getText(),author.getText(),publisher.getText(),edition.getText(),copytype.getText(),cover.getText());
			}else if(journal) {
				pfactory = new Journal(Double.valueOf(price.getText()),Integer.parseInt(stock.getText()),name.getText(),author.getText(),publisher.getText(),edition.getText(),copytype.getText(),cover.getText());
			}
			if(fiction) {
				if(charac.getText().isEmpty() || place.getText().isEmpty() || time.getText().isEmpty() || problem.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				charac.setDisable(true);
				place.setDisable(true);
				time.setDisable(true);
				problem.setDisable(true);
				
				Fiction p = new Fiction(preview.getText(),place.getText(),time.getText(),problem.getText());
				p.addCharacter(charac.getText());
				pfactory.setProduct(p);
			}else if(history) {
				if(period.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				period.setDisable(true);
				pfactory.setProduct(new History(preview.getText(),period.getText()));
				
			}else if(politics) {
				if(topic.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				topic.setDisable(true);
				Politics p = new Politics(preview.getText());
				p.addTopic(topic.getText());
				pfactory.setProduct(p);
				
			}else if(scientific) {
				if(topic.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				topic.setDisable(true);
				
				Scientific p = new Scientific(preview.getText());
				p.addTopic(topic.getText());
				pfactory.setProduct(p);
			}else if(technology) {
				if(topic.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				topic.setDisable(true);
				
				Technology p = new Technology(preview.getText());
				p.addTopic(topic.getText());
				pfactory.setProduct(p);
			}
			
			
			edit.setText("ADD");
			
			model.getInventory().getStock().add(pfactory);
			Stage stage = (Stage)edit.getScene().getWindow();
			//because if the keeper want to add many products we wait him to finish the we update the datbase
			stage.setOnCloseRequest(e->{
				//put the new data into database
				model.setData();
			});
			Image img = null;
			try {
				img = new Image(new FileInputStream("images/"+name.getText()+".PNG"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			image.setImage(img);
		}
		
	}
}
