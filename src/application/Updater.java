package application;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Updater implements Runnable {

	private Label[] label;
	private int minutesLeft;
	private int secondsLeft;
	private String text;
	private Color color;

	public Updater(Label[] label, String text, Color color, int minutesLeft, int secondsLeft) {
		this.label = label;
		this.minutesLeft = minutesLeft;
		this.secondsLeft = secondsLeft;
		this.text = text;
		this.color = color;
	}

	@Override
	public void run() {
		this.label[2].setText(text);
		this.label[2].setTextFill(color);
		
		if (this.minutesLeft == 0) {
			System.out.println("Debug...");
		} else {
			if(this.secondsLeft >= 10) {
				this.label[0].setText(Integer.toString(this.minutesLeft-1) + ":" + this.secondsLeft);
			}else {
				this.label[0].setText(Integer.toString(this.minutesLeft-1) + ":0" + this.secondsLeft);
			}
		}
	}
}