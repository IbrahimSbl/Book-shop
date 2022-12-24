package application;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import com.gluonhq.charm.glisten.control.*;

import Controllers.LoginController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.image.*;

public class Main extends Application{    
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/LOGINPage.fxml"));
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}//jdbc:sqlserver://hostname\\instance:port;databaseName=dbName

/*
 In SQL Server Configuration Manager, in the console pane, expand SQL Server Network Configuration, 
 select Protocols for <instance name>, and then in the right pane double-click TCP/IP.
 Then restart the sqlExpress server from services
 */
	/*
	 Then go to services and start the sql server browser if it is stoped
	 */
