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
		public double calculatePrice () // ("premium" parameter is ignored)
		{
			double final_price = price;
			final_price *= 1.1; // %10 sales tax
			double shippingCost = 20 * weight * quantity;
			final_price += shippingCost; // standard shipping only
			final_price = Math.round(final_price * 100) / 100.00; // round to the nearest penny
			return final_price;
			
			//Warning for non-premium?
		}
		
		void printItemAttributes () 
		{
			double finalCost = calculatePrice();
			System.out.printf("Name: %s Price: $%.2f Quantity: %d Weight: %d ITEM TOTAL: $%.2f\n", name, price, quantity, weight, finalCost);
		}
	

}
