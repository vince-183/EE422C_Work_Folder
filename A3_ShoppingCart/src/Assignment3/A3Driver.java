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
			
			int fileLine = 0;
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				fileLine++;
				String[] fileArgs = s.split(" +");
				
				String operation;
				String category;
				String name;
				double price;
				int quantity ;
				int weight;
				String optField1, optField2;
				
				boolean condition;
				boolean validInput = true;
				
				Grocery newGrocery;
				Electronics newElectronic;
				Clothing newClothing;
				
				Iterator<Item> i = shoppingCart.iterator();
				
				//boolean validInput = checker.validate(fileArgs);
				switch(fileArgs[0]){
					case "insert":
						try{
							operation = fileArgs[0];
							category = fileArgs[1];
							name = fileArgs[2];
							price = Double.parseDouble(fileArgs[3]);
							quantity = Integer.parseInt(fileArgs[4]);
							weight = Integer.parseInt(fileArgs[5]);
							switch(fileArgs[1]){
								case "clothing":
									if(fileArgs.length != 6){
										System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
									}else{
										newClothing = new Clothing(name, price, quantity, weight);
										shoppingCart.add(newClothing);
									}
									break;
								case "electronics":
									if(fileArgs.length != 8){
										System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
									}else{
										try{
											optField1 = fileArgs[6];
											optField2 = fileArgs[7];
											condition = false;
											
											if(optField1.equals("F"))
												condition = true;
											else if(optField1.equals("NF"))
												condition = false;
											else{
												validInput = false;
												System.err.printf("ERROR: Invalid input in argument <fragile> in line %d\n\n", fileLine);
											}
											
											newElectronic = new Electronics(name, price, quantity, weight, condition, optField2);
											shoppingCart.add(newElectronic);
											
										}catch(IndexOutOfBoundsException e){
											System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
										}
									}
									break;
								case "groceries":
									if(fileArgs.length != 7){
										System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
									}else{
										try{
											optField1 = fileArgs[6];
											condition = false;
											
											if(optField1.equals("P"))
												condition = true;
											else if(optField1.equals("NP"))
												condition = false;
											else
												validInput = false;
											
											newGrocery = new Grocery(name, price, quantity, weight, condition);
											shoppingCart.add(newGrocery);
											
										}catch(IndexOutOfBoundsException e){
											System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
										}
									}
									break;
								default:
									System.err.printf("ERROR: Invalid category, must be 'electronics', 'groceries', or 'clothing'\n\n", fileLine);
							}
						}catch(NumberFormatException ignore){
							System.err.printf("ERROR: Invalid numeric data type, must be in form <String> <String> <String> <double> <int> <int> <String> <String> in line %d\n\n", fileLine);
						}catch(IndexOutOfBoundsException e){
							System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
						}
						break;
					case "delete":
						if(fileArgs.length != 2){
							System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
						}else{
							try{
								name = fileArgs[1];
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
							}catch(IndexOutOfBoundsException e){
								System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
							}
						}
						break;
					case "search":
						if(fileArgs.length != 2){
							System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
						}else{
							try{
								name = fileArgs[1];
								int counter = 0;
								while (i.hasNext()) 
								{
									Item temp = i.next();
									if(temp.getName().equals(name))
										counter++;
								}
								System.out.printf("Number of %s objects found: %d\n\n", name, counter);
							}catch(IndexOutOfBoundsException e){
								System.err.printf("ERROR: Invalid number of arguments in line %d\n", fileLine);
							}
						}
						break;
					case "update":
						if(fileArgs.length != 3){
							System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
						}else{
							try{
								name = fileArgs[1];
								quantity = Integer.parseInt(fileArgs[2]);
								
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
							}catch(NumberFormatException ignore){
								System.err.printf("ERROR: Invalid numeric data type, must be in form <String> <String> <int> in line %d\n\n", fileLine);
							}catch(IndexOutOfBoundsException e){
								System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
							}
						}
						break;
					case "print":
						if(fileArgs.length != 1){
							System.err.printf("ERROR: Invalid number of arguments in line %d\n\n", fileLine);
						}else{
							double totalPrice = 0.00;
							while (i.hasNext()) 
							{
								Item temp = i.next();
								totalPrice += temp.calculatePrice();
								temp.printItemAttributes();
							}
							System.out.printf("\nGrand Total after Sales Tax and Shipping: $%.2f\n\n\n", totalPrice);
						}
						break;
					default:
						if(!s.trim().isEmpty())
							System.err.printf("ERROR: Invalid command in line %d\n\n", fileLine);
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
