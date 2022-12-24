package Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import person.Keeper;
import person.Manager;
import javafx.scene.image.ImageView;

import java.sql.Connection;

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

public class ProductPageController {
	private ProductFactory pf;
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
	
	Model model = new Model();
	public void setScene() {
		Product p = (Product)pf.getProduct();
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
		
		id.setText(String.valueOf(pf.getId()));
		name.setText(pf.getName());
		
		author.setText(pf.getAuthor());
		publisher.setText(pf.getPublisher());
		edition.setText(pf.getEdition());
		copytype.setText(pf.getCopyType());
		cover.setText(pf.getCover());
		stock.setText(String.valueOf(pf.getStock()));
		price.setText(String.valueOf(pf.getPrice()));
		preview.setText(p.getpreview());
		
		if(p instanceof Fiction) {
			Fiction f = (Fiction)p;
			charac.setStyle("visibility:visible;");
			characl.setStyle("visibility:visible;");
			placel.setStyle("visibility:visible;");
			place.setStyle("visibility:visible;");
			time.setStyle("visibility:visible;");
			timel.setStyle("visibility:visible;");
			problem.setStyle("visibility:visible;");
			probleml.setStyle("visibility:visible;");
			
			
			charac.setText(f.ShowCharacters());
			place.setText(f.getPlace());
			time.setText(f.getTime());
			problem.setText(f.getProblem());
			
		}else if(p instanceof History) {
			History h = (History)p;
			period.setStyle("visibility:visible;");
			periodl.setStyle("visibility:visible;");
			period.setText(h.gettime());
			
		}else if(p instanceof Politics) {
			Politics h = (Politics)p;
			topic.setStyle("visibility:visible;");
			topicl.setStyle("visibility:visible;");
			topic.setText(h.ShowTopics());
			
		}else if(p instanceof Scientific) {
			Scientific h = (Scientific)p;
			topic.setStyle("visibility:visible;");
			topicl.setStyle("visibility:visible;");
			topic.setText(h.ShowTopics());
			
		}else if(p instanceof Technology) {
			Technology h = (Technology)p;
			topic.setStyle("visibility:visible;");
			topicl.setStyle("visibility:visible;");
			topic.setText(h.ShowTopics());
			
		}
	}
	// Event Listener on Button[#edit].onAction
	@FXML
	public void update(ActionEvent event) {
		// TODO Autogenerated
		Product p = pf.getProduct();
		if(edit.getText().equals("Edit")) {
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
			if(p instanceof Fiction) {
				charac.setDisable(false);
				place.setDisable(false);
				time.setDisable(false);
				problem.setDisable(false);

			}else if(p instanceof History) {
				period.setDisable(false);
				
			}else if(p instanceof Politics) {
				
				topic.setDisable(false);
				
			}else if(p instanceof Scientific) {
				topic.setDisable(false);
				
			}else if(p instanceof Technology) {
				topic.setDisable(false);
				
			}
		
		}else if(edit.getText().equals("Done")) {
			if( name.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()
					 || edition.getText().isEmpty() || copytype.getText().isEmpty() || cover.getText().isEmpty() || stock.getText().isEmpty()
					 || price.getText().isEmpty() || preview.getText().isEmpty()) {
				error.setText("Fill all fields");
				return;
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
			if(pf instanceof Book) {
				pfactory = new Book(Double.valueOf(price.getText()),Integer.parseInt(stock.getText()),name.getText(),author.getText(),publisher.getText(),edition.getText(),copytype.getText(),cover.getText());
			}else if(pf instanceof Magazine) {
				pfactory = new Magazine(Double.valueOf(price.getText()),Integer.parseInt(stock.getText()),name.getText(),author.getText(),publisher.getText(),edition.getText(),copytype.getText(),cover.getText());
			}else if(pf instanceof Journal) {
				pfactory = new Journal(Double.valueOf(price.getText()),Integer.parseInt(stock.getText()),name.getText(),author.getText(),publisher.getText(),edition.getText(),copytype.getText(),cover.getText());
			}
			if(pf.getProduct() instanceof Fiction) {
				if(charac.getText().isEmpty() || place.getText().isEmpty() || time.getText().isEmpty() || problem.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				charac.setDisable(true);
				place.setDisable(true);
				time.setDisable(true);
				problem.setDisable(true);
				
				Fiction pn = new Fiction(preview.getText(),place.getText(),time.getText(),problem.getText());
				pn.addCharacter(charac.getText());
				pfactory.setProduct(pn);
			}else if(pf.getProduct() instanceof History) {
				if(period.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				period.setDisable(true);
				pfactory.setProduct(new History(preview.getText(),period.getText()));
				
			}else if(pf.getProduct() instanceof Politics) {
				if(topic.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				topic.setDisable(true);
				Politics pn = new Politics(preview.getText());
				pn.addTopic(topic.getText());
				pfactory.setProduct(pn);
				
			}else if(pf.getProduct() instanceof Scientific) {
				if(topic.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				topic.setDisable(true);
				Scientific pn = new Scientific(preview.getText());
				pn.addTopic(topic.getText());
				pfactory.setProduct(pn);
			}else if(pf.getProduct() instanceof Technology) {
				if(topic.getText().isEmpty()) {
					error.setText("Fill all fields");
					return;
				}
				topic.setDisable(true);
				Technology pn = new Technology(preview.getText());
				pn.addTopic(topic.getText());
				pfactory.setProduct(pn);
			}
			edit.setText("Edit");
			//model is different than that in the keeper 
			//then the pf that we have is not the same in the model here
			//so we need to search to delete it 
			for(ProductFactory t : model.getInventory().getStock()) {
				if(t.getName().equals(pf.getName())) {
					model.getInventory().RemoveProduct(t);
					break;
				}
			}
			model.getInventory().AddProduct(pfactory);
			
			Stage stage = (Stage)edit.getScene().getWindow();
			model.setData();
		}
		
	}
	
	public void setFactory(ProductFactory pf) {
		this.pf = pf;
	}
}