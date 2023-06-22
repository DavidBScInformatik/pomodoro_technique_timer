/*
 * Pomodoro Technique Timer
 * 
 * In preparation for my application process, I developed small projects.
 * If you have any questions about the code, you can email me at any time. 
 * You can find my email address in my CV.
 * 
 * This project hase been poblished on https://github.com/DavidBScInformatik
 * 
 * Designed and developed with ♥ by David Jahn - Copyright 2023
 */

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FirstFXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
