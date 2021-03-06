package Assignment3;

public class Electronics extends Item 
{

	protected boolean fragile;
	protected String state;
	// Variables, constructors etc. here.
	
	public Electronics (String itemName, double itemPrice, int numItems, int itemWeight, boolean isItFragile, String location)
	{
		super(itemName, itemPrice, numItems, itemWeight);
		fragile = isItFragile;
		state = location.toUpperCase();
	}
	
	//overload calculatePrice() from item class, since:
	//  - Fragile electronics require premium shipping
	//  - Sales tax based on shipping destination (state)
	public double calculatePrice()
	{
		double final_price = price;
		
		if ((state.equals("TX")) || (state.equals("NM")) || (state.equals("VA")) || (state.equals("AZ")) || (state.equals("AK")))
			final_price = price; // NO sales tax if sold to designated states
		else
			final_price *= 1.1; // %10 sales tax otherwise
		
		double shippingCost = 20 * weight * quantity;
		if (fragile)
			shippingCost *= 1.2; // fragile requires premium shipping
		
		final_price += shippingCost;
		final_price = Math.round(final_price * 100) / 100.00; // round to the nearest penny
		return final_price;
	}
	
	//Implement print methods as necessary
	
	public boolean isFragile ()
	{
		return fragile;
	}
	
	public void setFragile (boolean isItFragile)
	{
		fragile = isItFragile;
	}
	
	void printItemAttributes () 
	{
		double finalCost = calculatePrice();
		System.out.printf("Name: %s Price: $%.2f Quantity: %d Weight: %d Fragile: %b State: %s ITEM TOTAL: $%.2f\n", name, price, quantity, weight, fragile, state, finalCost);
	}

}
