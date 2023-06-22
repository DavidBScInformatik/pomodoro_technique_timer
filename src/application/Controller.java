package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

	@FXML
	private Spinner<Integer> focusSpinner;
	@FXML
	private Spinner<Integer> shortBreakSpinner;
	@FXML
	private Spinner<Integer> longBreakSpinner;
	@FXML
	private Spinner<Integer> intervalSpinner;
	@FXML
	private HBox controlLayout;
	@FXML
	private Button focusButton;
	@FXML
	private Label processLabel = new Label("FOCUS!");
	@FXML
	private Label minutesLabel;
	@FXML
	private Label textLabel;

	private Spinner<Integer>[] spinnerArr = new Spinner[4];
	private Label[] labelArr = new Label[3];

	@FXML
	void focus(ActionEvent event) throws InterruptedException {
		this.minutesLabel.textProperty().unbind();
		this.creatArrays();
		this.startTask();
		
		this.controlLayout.getChildren().remove(focusButton);
		this.controlLayout.getChildren().add(processLabel);
		this.processLabel.setTextFill(Color.RED);
		this.processLabel.setStyle("-fx-font: 40 arial;");
	}

	private void creatArrays() {
		this.spinnerArr[0] = this.focusSpinner;
		this.spinnerArr[1] = this.shortBreakSpinner;
		this.spinnerArr[2] = this.longBreakSpinner;
		this.spinnerArr[3] = this.intervalSpinner;

		this.labelArr[0] = this.minutesLabel;
		this.labelArr[1] = this.textLabel;
		this.labelArr[2] = this.processLabel;
	}

	private void startTask() {
		Task thread = new Task(this.spinnerArr, this.labelArr);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		minutesLabel.textProperty().bind(Bindings.convert(focusSpinner.valueProperty()));
	}
}
