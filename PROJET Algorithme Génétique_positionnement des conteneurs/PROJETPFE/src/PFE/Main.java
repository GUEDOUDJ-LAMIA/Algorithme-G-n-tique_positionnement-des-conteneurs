package PFE;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
	public static Stage window=null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			window = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/interface1/pagedac.fxml"));
			Scene scene = new Scene(root);
            window.initStyle(StageStyle.UTILITY); 
            window.setTitle(" Application	");
			window.setScene(scene);
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}}



	
		


