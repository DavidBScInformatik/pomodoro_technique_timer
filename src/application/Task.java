package application;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;

public class Task extends Thread {

	private Spinner<Integer>[] spinnerArr;
	private Label[] labelArr;

	Task(Spinner<Integer>[] spinnerArr, Label[] labelArr) {
		this.spinnerArr = spinnerArr;
		this.labelArr = labelArr;
	}

	@Override
	public void run() {
		int interval = (int) this.spinnerArr[3].getValue();
		int focusMinutesLeft = (int) this.spinnerArr[0].getValue();
		int breakMinutesLeft = (int) this.spinnerArr[1].getValue();
		int longBreakMinutesLeft = (int) this.spinnerArr[2].getValue();
		int secondsLeft = 5;
		boolean getABreak = false;
		boolean getALongBreak = false;
		
		// as long as still have interval and had no long break yet it will run
		while (interval != 0 || !getALongBreak) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// first off check if there still minutes to focus and still have focus interval
			if (focusMinutesLeft >= 0 && getABreak == false && interval > 0) {
				//after that check if there are still secons left
				if (secondsLeft >= 0) {
					this.labelArr[0].setTextFill(Color.RED);
					// update the view 
					Updater update = new Updater(this.labelArr, "FOCUS!", Color.RED, focusMinutesLeft, secondsLeft);
					Platform.runLater(update);
					secondsLeft--;
				} else {
					// if seconds become 0 substract a minute
					focusMinutesLeft--;
					secondsLeft = 5;
					if (focusMinutesLeft == 0) {
						getABreak = true;
						interval--;
					}
				}
				// same as in focus is in break phase
			} else if (breakMinutesLeft >= 0 && getABreak == true && interval > 0) {
				if (secondsLeft >= 0) {
					this.labelArr[0].setTextFill(Color.GREEN);
					Updater update = new Updater(this.labelArr, "BREAK CHILL!", Color.GREEN, breakMinutesLeft, secondsLeft);
					Platform.runLater(update);
					secondsLeft--;
				} else {
					breakMinutesLeft--;
					secondsLeft = 5;
					if (breakMinutesLeft <= 0 && interval != 0) {
						focusMinutesLeft = (int) this.spinnerArr[0].getValue();
						getABreak = false;
					}
				}
			} else {
				// ... and in long break phase
				if (secondsLeft >= 0) {
					this.labelArr[0].setTextFill(Color.GREEN);
					Updater update = new Updater(this.labelArr, "BREAK CHILL!", Color.GREEN, longBreakMinutesLeft, secondsLeft);
					Platform.runLater(update);
					secondsLeft--;
				} else {
					longBreakMinutesLeft--;
					secondsLeft = 5;
					if(longBreakMinutesLeft <= 0) {
						getALongBreak = true;
					}
				}
				
			}
		}
	}
}
