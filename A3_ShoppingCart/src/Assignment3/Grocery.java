package Assignment3;

public class Grocery extends Item {
	protected boolean perishable; // true if perishable; false if non-perishable
	
	public Grocery (String itemName, double itemPrice, int numItems, int itemWeight, boolean perish)
	{
		super(itemName, itemPrice, numItems, itemWeight);
		perishable = perish;
	}
	
	//override calculatePrice() from item class, since:
	//  - Perishable groceries require premium shipping
	//  - No sales tax is added for groceries
	public double calculatePrice ()
	{
		double final_price = price;
		// no sales tax
		double shippingCost = 20 * weight * quantity;
		if (perishable) // force premium shipping if grocery is perishable
			shippingCost *= 1.2;
		final_price += shippingCost;
		final_price = Math.round(final_price * 100) / 100.00; // round to the nearest penny
		return final_price;
	}
	
	// Implement print methods as necessary	
	
	// the "get" method for boolean perishable
	public boolean isPerishable ()
	{
		return perishable;
	}
	
	public void setPerishable (boolean newPerish)
	{
		perishable = newPerish;
	}
	
	void printItemAttributes () 
	{
		double finalCost = calculatePrice();
		System.out.printf("Name: %s Price: $%.2f Quantity: %d Weight: %d Perishable: %b ITEM TOTAL: $%.2f\n", name, price, quantity, weight, perishable, finalCost);
	}
	
}
