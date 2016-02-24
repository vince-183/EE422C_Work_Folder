package Assignment3;

public class Electronics extends Item 
{
	protected boolean fragile;
	// Variables, constructors etc. here.
	
	public Electronics (String itemName, double itemPrice, int numItems, int itemWeight, boolean isItFragile)
	{
		super(itemName, itemPrice, numItems, itemWeight);
		fragile = isItFragile;
	}
	
	//overload calculatePrice() from item class, since:
	//  - Fragile electronics require premium shipping
	//  - Sales tax based on shipping destination (state)
	public double calculatePrice (boolean premium, String state)
	{
		double final_price = 0;
		
		if ((state == "TX") || (state == "NM") || (state == "VA") || (state == "AZ") || (state == "AK"))
			final_price = price; // NO sales tax if sold to designated states
		else
			final_price = price * 1.1; // %10 sales tax otherwise
		
		double shippingCost = 20 * weight * quantity;
		if (premium || fragile)
			shippingCost *= 1.2; // fragile requires premium shipping
		
		final_price += shippingCost;
		final_price = Math.round(final_price * 100) / 100; // round to the nearest penny
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
}
