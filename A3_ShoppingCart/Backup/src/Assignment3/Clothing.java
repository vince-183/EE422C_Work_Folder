package Assignment3;

public class Clothing extends Item 
{
	// NO extra attributes apart from item class.
	// Just have no premium shipping option available.
	
	public Clothing (String itemName, double itemPrice, int numItems, int itemWeight)
	{
		super(itemName, itemPrice, numItems, itemWeight);
	}
	
	//override calculatePrice() from item class, since:
	//  - There is no premium shipping option.
	public double calculatePrice (boolean premium) // ("premium" parameter is ignored)
	{
		double final_price = 0;
		final_price = price * 1.1; // %10 sales tax
		double shippingCost = 20 * weight * quantity;
		final_price += shippingCost; // standard shipping only
		final_price = Math.round(final_price * 100) / 100; // round to the nearest penny
		return final_price;
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
	}
	

}
