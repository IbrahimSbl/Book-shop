package application;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controllers.CustomerPageController;
import Customer.Customer;
import Product.Book;
import Product.Fiction;
import Product.History;
import Product.Inventory;
import Product.Journal;
import Product.Magazine;
import Product.Politics;
import Product.Product;
import Product.ProductFactory;
import Product.Scientific;
import Product.Technology;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import person.Employee;
import person.Keeper;
import person.Manager;

public class Model{ 
	private ArrayList<Customer> customers=new ArrayList<Customer>();
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private Inventory  inventory = new Inventory(); 
	public Model(){
		this.loadData();
	}
	public void setInventory(Inventory inv) {
		this.inventory = inv;
	}
	public Inventory getInventory() {
		return this.inventory;
	}
	public boolean AddEmployee(Employee employee){
		employees.add(employee);
		return true;
	}
	public boolean RemoveEmployee(Employee employee){
		if(!employees.contains(employee))return false;
		employees.remove(employee);
		return true;
	}
	public ArrayList<Employee>  ShowEmployee(){
		return employees;
	}
	public void AddProduct(ProductFactory productFactory){
		inventory.AddProduct(productFactory);
	}
	public Boolean  RemoveProduct(ProductFactory product){
		if(!inventory.getStock().contains(product)) return false;
		inventory.RemoveProduct(product);
		return true;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public boolean AddCustomer(Customer customer) {
		if(!this.customers.contains(customer)) {
			this.customers.add(customer);
			return true;
		}
		return false;
	}
	public void ShowProducts() {
		inventory.ShowProducts();
	}
	public Connection connect(String dbURL,String user,String passw)  {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connect = null;
		try {
			connect= DriverManager.getConnection(dbURL,user, passw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	public void truncate(Connection myConn) {
		Statement statement = null;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "TRUNCATE TABLE BOOKS";
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "TRUNCATE TABLE JOURNAL";
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "TRUNCATE TABLE MAGAZINE";
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "TRUNCATE TABLE CUSTOMER";
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "TRUNCATE TABLE EMPLOYEE";
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void loadData() {
		//to remove if found the data from the lists before filling it from database
		this.inventory.getStock().clear();
		this.customers.clear();
		this.employees.clear();
		Connection myConn = this.connect("jdbc:sqlserver://DE\\SQLEXPRESS;databaseName=BookShop", "sa", "bob1234");
		Statement statement;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet q;
		q= this.getData(myConn, "*","CUSTOMER","");
		try {
			while(q.next()) {
				this.customers.add(new Customer(q.getString("NAME").trim(),q.getString("PHONE"),q.getString("ADRESS"),q.getInt("AGE"),q.getString("EMAIL").trim(),q.getString("PASSWORD").trim()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q= this.getData(myConn, "*","EMPLOYEE","");
		try {
			while(q.next()) {
				if(q.getString("ROLE").trim().equalsIgnoreCase("manager")) {
					Manager m =new Manager(q.getString("NAME").trim(),q.getString("PHONE").trim(),q.getString("ADRESS").trim(),q.getInt("AGE"),q.getInt("PAYPERHOUR"),q.getInt("HOURPERDAY"),q.getString("EMAIL").trim(),q.getString("PASSWORD").trim());
					m.setOffDays(q.getInt("OFFDAYS"));
					this.employees.add(m);
						
				}else if(q.getString("ROLE").trim().equalsIgnoreCase("keeper")) {
					Keeper m =new Keeper(q.getString("NAME").trim(),q.getString("PHONE").trim(),q.getString("ADRESS").trim(),q.getInt("AGE"),q.getInt("PAYPERHOUR"),q.getInt("HOURPERDAY"),q.getString("EMAIL").trim(),q.getString("PASSWORD").trim());
					m.setOffDays(q.getInt("OFFDAYS"));
					this.employees.add(m);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		q= this.getData(myConn, "*","BOOKS","");
		try {
			while(q.next()) {
				Book pf = new Book(q.getDouble("PRICE"),q.getInt("STOCK"),q.getString("NAME").trim(),q.getString("AUTHOR").trim(),q.getString("PUBLISHER").trim(), q.getString("EDITION").trim(), q.getString("COPYTYPE").trim(), q.getString("COVER").trim());
				
				if(q.getString("PERIOD")!=null) {
					History p = new History(q.getString("PREVIEW"),q.getString("PERIOD"));
					pf.setProduct(p);
				}else if (q.getString("PLACE")!=null) {
					Fiction p = new Fiction(q.getString("PREVIEW").trim(),q.getString("PLACE").trim(),q.getString("TIME").trim(),q.getString("PROBLEM").trim());
					p.addCharacter(q.getString("CHARACTERS").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Scientific p = new Scientific(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Politics p = new Politics(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Technology p = new Technology(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}
				this.inventory.AddProduct(pf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q= this.getData(myConn, "*","MAGAZINE","");
		try {
			while(q.next()) {
				ProductFactory pf = new Magazine(q.getDouble("PRICE"),q.getInt("STOCK"),q.getString("NAME").trim(),q.getString("AUTHOR").trim(),q.getString("PUBLISHER").trim(), q.getString("EDITION").trim(), q.getString("COPYTYPE").trim(), q.getString("COVER").trim());
				
				if(q.getString("PERIOD")!=null) {
					History p = new History(q.getString("PREVIEW"),q.getString("PERIOD"));
					pf.setProduct(p);
				}else if (q.getString("PLACE")!=null) {
					Fiction p = new Fiction(q.getString("PREVIEW").trim(),q.getString("PLACE").trim(),q.getString("TIME").trim(),q.getString("PROBLEM").trim());
					p.addCharacter(q.getString("CHARACTERS").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Scientific p = new Scientific(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Politics p = new Politics(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Technology p = new Technology(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}

				this.inventory.AddProduct(pf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q= this.getData(myConn, "*","JOURNAL","");
		try {
			while(q.next()) {
				ProductFactory pf = new Journal(q.getDouble("PRICE"),q.getInt("STOCK"),q.getString("NAME").trim(),q.getString("AUTHOR").trim(),q.getString("PUBLISHER").trim(), q.getString("EDITION").trim(), q.getString("COPYTYPE").trim(), q.getString("COVER").trim());
				
				if(q.getString("PERIOD")!=null) {
					History p = new History(q.getString("PREVIEW"),q.getString("PERIOD"));
					pf.setProduct(p);
				}else if (q.getString("PLACE")!=null) {
					Fiction p = new Fiction(q.getString("PREVIEW").trim(),q.getString("PLACE").trim(),q.getString("TIME").trim(),q.getString("PROBLEM").trim());
					p.addCharacter(q.getString("CHARACTERS").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Scientific p = new Scientific(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Politics p = new Politics(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}else if(q.getString("TOPIC")!=null) {
					Technology p = new Technology(q.getString("PREVIEW").trim());
					p.addTopic(q.getString("TOPIC").trim());
					pf.setProduct(p);
				}

				this.inventory.AddProduct(pf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setData() {
		Connection myConn = this.connect("jdbc:sqlserver://DE\\SQLEXPRESS;databaseName=BookShop", "sa", "bob1234");
		this.truncate(myConn);
		Statement statement;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query ="";
		for(int i=0;i<employees.size();i++) {
			Employee e = employees.get(i);
			if(e instanceof Manager) {
				Manager m = (Manager) e;
				query = m.getId()+",'"+m.getName()+"','"+m.getAddress()+"','"+m.getPhone()+"',"+m.getAge()+",'manager',"+m.getPayPerHour()+","+m.getOffDays()+","+m.getHoursPerDay()+",'"+m.getEmail()+"','"+m.getPassword()+"'";
				this.Insert(myConn, "EMPLOYEE", query);
			}else if (e instanceof Keeper) {
				Keeper m = (Keeper) e;
				query = m.getId()+",'"+m.getName()+"','"+m.getAddress()+"','"+m.getPhone()+"',"+m.getAge()+",'keeper',"+m.getPayPerHour()+","+m.getOffDays()+","+m.getHoursPerDay()+",'"+m.getEmail()+"','"+m.getPassword()+"'";
				this.Insert(myConn, "EMPLOYEE", query);
				
			}
		}
		query ="";
		for(int i=0;i<customers.size();i++) {
			Customer m = customers.get(i);
			String phone ;
			if(m.getPhone()==null)phone = null; 
			else phone = "'"+m.getPhone()+"'";
			String address ;
			if(m.getPhone()==null)address = null; 
			else address = "'"+m.getPhone()+"'";
			query = m.getId()+",'"+m.getName()+"',"+address+","+phone+","+m.getAge()+",'"+m.getEmail()+"','"+m.getPassword()+"'";
			this.Insert(myConn, "CUSTOMER", query);
				
			
		}
		query ="";
		ArrayList<ProductFactory> products = this.inventory.getStock();
		for(int i=0;i<products.size();i++) {
			ProductFactory pf = products.get(i);
			Product q = pf.getProduct();
			query = pf.getId()+",'"+pf.getName()+"','"+pf.getAuthor()+"','"+pf.getPublisher()+"','"+pf.getEdition()+"','"+pf.getCopyType()+"','"+pf.getCover()+"',"+pf.getStock()+","+pf.getPrice()+",'"+q.getpreview()+"'";
			
			if(q instanceof History) {
				History p = (History)q;
				query += ",'History','"+p.gettime()+"',null,null,null,null,null";

			}else if (q instanceof Fiction) {
				Fiction p = (Fiction)q;

				query += ",'fiction',null,'"+p.ShowCharacters()+"','"+p.getPlace()+"','"+p.getTime()+"','"+p.getProblem()+"',null";
			}else if(q instanceof Scientific) {
				Scientific p = (Scientific)q;
				query += ",'Scientific',null,null,null,null,null,'"+p.ShowTopics()+"'";

			}else if(q instanceof Politics) {
				Politics p = (Politics)q;
				query += ",'Politics',null,null,null,null,null,'"+p.ShowTopics()+"'";

			}else if(q instanceof Technology) {
				Technology p = (Technology)q;
				query += ",'Technology',null,null,null,null,null,'"+p.ShowTopics()+"'";
				
			}
			if (pf instanceof Book) {
				Book b = (Book) pf;
				this.Insert(myConn,"BOOKS", query);
			}else if (pf instanceof Magazine) {
				Book b = (Book) pf;
				this.Insert(myConn,"MAGAZINE", query);
			}else if (pf instanceof Journal) {
				Book b = (Book) pf;
				this.Insert(myConn,"JOURNAL", query);
			}
		}
	}
	
	public ResultSet getData(Connection myConn,String select,String from,String where) {
		String query="";
		if(where.equals("")) {
			query = "SELECT "+select+" FROM "+from;
		}else {
			query = "SELECT "+select+" FROM "+from+" WHERE "+where;
		}
		Statement statement = null;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet q = null;
		try {
			q = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}
	public boolean Insert(Connection myConn,String table,String values) {
		String insertFields = "INSERT INTO "+table+" VALUES ("+values+")";
		Statement statement = null;
		try {
			statement= myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.execute(insertFields);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}