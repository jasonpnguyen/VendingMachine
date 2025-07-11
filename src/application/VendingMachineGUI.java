/*
Name: Jason Nguyen
Date: 11/28/22
Description: Creates a GUI for our iVend Vending Machine.
Sources Cited: Class slides
 */
package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VendingMachineGUI extends Application {
	@Override
	
	public void start(Stage primaryStage) {
		
		//This declares the vending machine.
		VendingMachine iVend = new VendingMachine(2,1,5);
		
		//These create the buttons for the user to select a candy.
		Button snickersButton = new Button("Snickers");
		Button twixButton = new Button("Twix");
		Button reesesButton = new Button("Reeses");
		
		//These create the transactional buttons, purchase, insertCents, returnCents,
		//and the getProfits button. 
		Button purchase = new Button("Purchase");
		Button insertCents = new Button("Insert cents");
		Button returnCents = new Button ("Return cents");
		Button getProfits = new Button("Get profits");
		
		//This creates a label that outputs texts that are helpful to the customer
		//it will output errors and prices and a thank you message. The textfield 
		//is for the customer to input their change amount.
		Label topLabel = new Label();
		TextField textField1 = new TextField();

		//This label will return amount of cents returned and also how much profit 
		//was made.
		Label bottomLabel = new Label("");
		
		//This is my GridPane where I set the buttons of the candies,
		//And where I also set my transactional buttons.
		//Additionally it includes the labels and textfields I made.
		GridPane pane = new GridPane();
		pane.add(textField1, 0, 0);
		pane.add(insertCents, 1, 0);
		pane.add(topLabel, 0, 1);
		pane.add(snickersButton, 0, 2);
		pane.add(twixButton, 0, 3);
		pane.add(reesesButton, 0, 4);
		pane.add(purchase, 1, 2);
		pane.add(returnCents, 1, 3);
		pane.add(getProfits, 1, 4);
		pane.add(bottomLabel, 0, 5);
		
		//These just makes it look a little nicer and less clumped.
		pane.setHgap(105);
		pane.setVgap(10);	
		pane.setPadding(new Insets(10));
		
		//VBox to hold the GridPane.
		VBox vbox = new VBox(pane);
		
		//This sets the stage.
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Vending Machine");
		primaryStage.show();
		
		//This happens when the Snickers button is pressed.
		//It will either print out the price and select the candy or tell the customer
		//that Snickers are out of stock.
		snickersButton.setOnAction(event ->{
			try {
				iVend.makeSelection(0);
				topLabel.setText("Snickers are 100 cents.");
			}
			catch(Exception ImproperSelectionException) {
				topLabel.setText("Sorry, Snickers are out of stock.");
			}
		});
		
		//This happens when the Twix button is pressed.
		//It will either print out the price and select the candy or tell the customer
		//that Twix are out of stock.
		twixButton.setOnAction(event ->{
			try {
				iVend.makeSelection(1);
				topLabel.setText("Twix are 115 cents.");
			}
			catch(Exception ImproperSelectionException) {
				topLabel.setText("Sorry, Twix are out of stock.");
			}
		});
		
		//This happens when the Reeses button is pressed.
		//It will either print out the price and select the candy or tell the customer
		//that Reeses are out of stock.
		reesesButton.setOnAction(event ->{
			try {
				iVend.makeSelection(2);
				topLabel.setText("Reeses are 130 cents.");
			}
			catch(Exception ImproperSelectionException) {
				topLabel.setText("Sorry, Reeses are out of stock.");
			}
		});
		//When the customer presses the purchase button.
		//It will run the method and then return the remaining amount of change
		//the customer has.
		purchase.setOnAction(event ->{
			try {
				bottomLabel.setText("Here is your change: " + iVend.purchaseSelection());
				topLabel.setText("Thank you! Retrieve item below!");
				}
			catch(Exception ImproperPurchaseException) {
				topLabel.setText("Please select an item or insert more cents.");
				}
		});
		
		//When the incertCents button is pressed it will take the inputed text
		//and use that as the currency inside the vending machine. If the customer
		//does not input a number that is a multiple of 5, then an error will occur
		//and it will force the user to type a number that is a multiple of 5.
		insertCents.setOnAction(event ->{
			try {
				iVend.insertCents(Integer.parseInt(textField1.getText()));
				topLabel.setText("You have inserted " + textField1.getText() + " cents.");
				textField1.setText("");
			}
			catch(Exception ImproperCoinsException) {
				topLabel.setText("Please insert an amount that is a multiple of 5.");
				textField1.setText("");
			}
		});
		
		//Returns the unspent/remaining cents.
		returnCents.setOnAction(event ->{
			bottomLabel.setText("Here is your cents: " + iVend.returnUnspentCents());
		});
		//Returns profit from the transactions.
		getProfits.setOnAction(event ->{
			bottomLabel.setText("iVend profit: " + iVend.getProfits());
		});	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
