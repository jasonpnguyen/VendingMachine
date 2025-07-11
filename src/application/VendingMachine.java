/*Name: Jason Nguyen
 * Date: 11/11/22
 * Description: This code creates a vending machine and 
 * implements the basic mechanics of a vending machine.
 * Sources Cited: 
 */


package application;

public class VendingMachine implements VendingMachineInterface {
	//These keep track of how much of each candy is in stock.
	private int snickersStock;
	private int twixStock;
	private int reesesStock;
	//These keep track of the cost of the candy.
	private int snickersCost;
	private int twixCost;
	private int reesesCost;
	//This is how much money the customer has inserted into the vending machine.
	private int change;
	//This is just a string to be able to print out the name of the candy later on.
	private String selectionName;
	//This keeps track of how much the user is charged.
	private int chargeAmt;
	//This keeps track of the number of the selected candy to use later on when they click the candy's "button".
	private int selectionNum;
	//This keeps track of the profit amount for the getProfit method.
	private int profit;
	
	//This is the constructor method.
	public VendingMachine(int s, int t, int r) {
		//These set the stock of each candy when the Vendor later on chooses to add more stock or when stock is purchased.
		snickersStock = s;
		twixStock = t;
		reesesStock = r;
		//Sets the prices of the candies.
		snickersCost = 100;
		twixCost = 115;
		reesesCost = 130;
		//Change is initialized at 0 because no coins/change has been inserted into the vending machine.
		change = 0;
		//Sets the string of the candy to an empty string. Then will be use when a transaction is made.
		selectionName = "";
		//These are set to 0 since no transactions have been made.
		chargeAmt = 0;
		profit = 0;
		
	}
	//If the customer inserts coins that are not divisible 5 a exception is thrown.
	//So only coins that are divisible and greater than 0 can be inserted into the vending machine.
	public void insertCents(int c) {
		if (c % 5!= 0 || c<= 0) {
			throw new ImproperCoinsException();
		}
		else {
			//The amount the customer inserts is added into the coin amount in the vending machine.
			change += c;
		}
	}
	//This takes the chosen candy of the customer and charges the price and checks 
	//whether their is enough stock to be sold.
	public void makeSelection(int s) {
		//s can either be: 0,1,2 so it will choose the candy based on the input.
		selectionNum = s;
		
		//The if/else statements below will throw exceptions if there are none of the chosen candy in stock
		//it also will then print out the name of the candy to the user and define the chargeAmt
		//to the price of the chosen candy.
		if (s == 0) {
			if (snickersStock <= 0) {
				throw new ImproperSelectionException("Snickers");
			}
			else {
				selectionName = "Snickers";
				System.out.println("Snickers are $" + snickersCost + ".");
				chargeAmt = snickersCost;
			}
		}
		else if (s == 1) {
			if (twixStock <= 0) {
				throw new ImproperSelectionException("Twix");
			}
			else {
				selectionName = "Twix";
				System.out.println("twix are $" + twixCost + ".");
				chargeAmt = twixCost;
			}
		}
		else if(s == 2) {
			if (reesesStock <= 0) {
				throw new ImproperSelectionException("Reeses");
			}
			else {
				selectionName = "Reeses";
				System.out.println("Reeses are $" + reesesCost + ".");
				chargeAmt = reesesCost;
			}
		}
		//If the customer types a selection number that is not one of the three(0,1,or 2)
		//then the ImproperSelectionException() error will be thrown.
		else {
			throw new ImproperSelectionException();
		}
	}
	//This purchases the candy that the customer has selected.
	//It also makes sure the customer makes a selection before purchasing the candy.
	//If the customer does not, then it will run a ImproperPurchaseException().
	//Additionally if the user does not have enough funds it will run the second 
	//ImproperPurchaseException() and print out how much extra change/funds is needed to purchase the candy.
	public int purchaseSelection() {
		//This is just one way to check if the user has selected a candy to be purchased.
		//And if they haven't then it throws the exception.
		if (selectionName == "") {
			throw new ImproperPurchaseException();
		}
		else {
			//If the amount of change in the Vending Machine is less than the amount to
			//be charged then the user will be thrown an exception and be requested to 
			//insert more funds into the machine to purchase the candy.
			if (change < chargeAmt) {
				throw new ImproperPurchaseException(chargeAmt-change);
			}
			//The rest of the code simulates the candy being purchased.
			else {
				//This will get the remainder of the money to be returned back to the customer.
				change -= chargeAmt;
				//the charged amount will be added to the profit accumulator.
				profit += chargeAmt;
				//This just prints out whatever candy was purchased and tell the user where to retrieve the candy.
				System.out.println(selectionName + " has been purchased. Retrieve item below.");
				//Whichever candy the user had selected will have 1 stock removed.
				if (selectionNum == 0) {
					snickersStock--;		
				}
				else if(selectionNum == 1) {
					twixStock--;
				}
				else if (selectionNum == 2){
					reesesStock--;
				}
				//This returns the remaining change amount that will be returned to the customer.
				return returnUnspentCents();
			}
		}
	}
	//This simulates the button to return the change to the customer 
	//when they change their mind and don't want to purchase anything.
	//Additionally it will reset the amount of change in the Vending Machine to 0.
	public int returnUnspentCents() {
		int leftoverChange = change;
		change = 0;
		return leftoverChange;
	}
	//This just returns the amount of profit that the Vending Machine has made.
	public int getProfits() {
		return profit;
	}
	
}
