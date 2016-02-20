package Assignment3;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
	// Vincent: we want to ensure item attributes are not modified without permission, thus they are protected.
	//          Use get and set methods to properly modify an item's attributes.
	protected String name;
	protected double price;
	protected int quantity;
	protected int weight;
	
// You will need a constructor (Why?). Create it here.
	public Item (String itemName, double itemPrice, int numItems, int itemWeight)
	{
		name = itemName;
		price = Math.round(itemPrice * 100) / 100; // round to the nearest cent
		if (numItems < 1)
			quantity = 1; // ensure non-negative integer
		else
			quantity = numItems;
		weight = itemWeight;
	}
	
	public double calculatePrice (boolean premium) // This will be overridden by subclasses, as tax and shipping rules change between subitems
	{
		double final_price = 0;
		// Standard rules for price calculation, in order:
		//  - Add sales tax (%10)
		//  - Add standard shipping (20 * weight) * quantity
		//    OR, premium shipping = standard * 1.2
		final_price = price * 1.1; // %10 sales tax
		double shippingCost = 20 * weight * quantity;
		if (premium)
			shippingCost *= 1.2;
		final_price += shippingCost;	
		final_price = Math.round(final_price * 100) / 100; // round to the nearest penny
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
	}
	
	public String getName ()
	{
		return name;
	}
	
	public void setName (String newName)
	{
		name = newName;
	}
	
	public double getPrice ()
	{
		return price;
	}
	
	public void setPrice (double newPrice)
	{
		price = Math.round(newPrice * 100) / 100; // round to nearest penny
	}
	
	public int getQuantity ()
	{
		return quantity;
	}
	
	public void setQuantity (int newQuantity)
	{
		if (newQuantity < 1)
			quantity = 1; // ensure non-negative integer
		else
			quantity = newQuantity;
	}
	public int getWeight()
	{
		return weight;
	}
	
	public void setWeight (int newWeight)
	{
		weight = newWeight;
	}
}
