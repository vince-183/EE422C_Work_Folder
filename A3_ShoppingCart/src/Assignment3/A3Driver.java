package Assignment3;

import java.util.*;
import java.io.*;

public class A3Driver {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
	
		String filename = args[0];
		
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
		
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String[] fileArgs = s.split(" +");
				
				String operation;
				String category;
				String name;
				double price;
				int quantity ;
				int weight;
				String optField1, optField2;
				
				boolean condition;
				
				Grocery newGrocery;
				Electronics newElectronic;
				Clothing newClothing;
				
				Iterator<Item> i = shoppingCart.iterator();
				//boolean validInput = checker.validate(fileArgs);
				if(((fileArgs.length >= 6)&&(fileArgs.length <= 8))||((fileArgs.length >= 1)&&(fileArgs.length <= 3))){
					
					//Check if each operation matches up with # of arguments (if-then block)
					
					operation = fileArgs[0];
					
					name = "";
					category = "";
					name = "";
					price = 0;
					quantity = 0;
					weight = 0;
					optField1 = "";
					optField2 = "";
					
					if(fileArgs.length >= 2)
						name = fileArgs[1];
					
					if(operation.equals("update"))
						quantity = Integer.parseInt(fileArgs[2]);
					
					if(fileArgs.length >= 4){
						category = fileArgs[1];
						name = fileArgs[2];
						price = Double.parseDouble(fileArgs[3]);
						quantity = Integer.parseInt(fileArgs[4]);
						weight = Integer.parseInt(fileArgs[5]);
					}
					
					//Error checking on valid input arguments for all arguments (e.g. what if weight = 5.2.3?)
					
					if(fileArgs.length >= 7)
						optField1 = fileArgs[6];
					
					condition = false;
					
					if(category.equals("electronics")){
						if(optField1.equals("F"))
							condition = true;
						else if(optField1.equals("NF"))
							condition = false;
					}else if(category.equals("groceries")){
						if(optField1.equals("P"))
							condition = true;
						else if(optField1.equals("NP"))
							condition = false;
					}
					
					
					if(fileArgs.length == 8)
						optField2 = fileArgs[7];
					
					switch(operation){
						case "insert":
							switch(category){
								case "clothing":
									newClothing = new Clothing(name, price, quantity, weight);
									shoppingCart.add(newClothing);
									break;
								case "electronics":
									newElectronic = new Electronics(name, price, quantity, weight, condition, optField2);
									shoppingCart.add(newElectronic);
									break;
								case "groceries":
									newGrocery = new Grocery(name, price, quantity, weight, condition);
									shoppingCart.add(newGrocery);
									break;
								default:
							}
							break;
						case "search":
							int counter = 0;
							while (i.hasNext()) 
							{
								Item temp = i.next();
								if(temp.getName().equals(name))
									counter++;
							}
							System.out.printf("Number of %s objects found: %d\n\n", name, counter);
							break;
						case "delete":
							int itrIndex = 0;
							int noDeleted = 0;
							while (i.hasNext()) 
							{
								Item temp = i.next();
								if(temp.getName().equals(name)){
									shoppingCart.remove(itrIndex);
									noDeleted++;
									i = shoppingCart.iterator();
									itrIndex = -1;
								}
								itrIndex++;	
							}
							System.out.printf("Number of %s objects deleted: %d\n\n\n", name, noDeleted);
							break;
						case "update":
							boolean qChanged = false;
							while (i.hasNext() && (!qChanged)) 
							{
								Item temp = i.next();
								if(temp.getName().equals(name)){
									temp.setQuantity(quantity);
									qChanged = true;
									System.out.printf("Name: %s New_Quantity: %d\n\n\n", name, quantity);
								}	
							}
							break;
						case "print":
							double totalPrice = 0.00;
							while (i.hasNext()) 
							{
								Item temp = i.next();
								totalPrice += temp.calculatePrice();
								temp.printItemAttributes();
							}
							System.out.printf("\nGrand Total after Sales Tax and Shipping: $%.2f\n\n\n", totalPrice);
							break;
						default:
					}
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
