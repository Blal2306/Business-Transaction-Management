//NAME : ZAFAR, BLAL
//ID # 108462601
//HOME WORK # 4

import java.io.Serializable;
public class Company implements Serializable {

	String name;
	double purchase, commission, salesAmount;
	Company reference;
	
	//Constructor
	public Company(String name, double purchase, Company reference)
	{
		this.name = name;
		if(purchase == 0) //THE CASE FOR THE FIRST NODE
			this.purchase = 0;
		else if(purchase > 0 && reference != null)
			makePurchase(purchase, reference);
		this.reference = reference;
	}
	//Accessory and mutator methods
	public Company getReferrer()
	{
		return reference;
	}
	public void addCommission(double amount)
	{
		commission+=amount;
	}
	public void madeSales(double amount)
	{
		salesAmount+=amount;
	}
	//Buy goods from a company
	public void makePurchase(double amount, Company a)
	{
		if(amount <= 1)
			throw new IllegalArgumentException("Value out of bound ...");
		double temp = amount;
		Company raw = a; // reference to the original company
		int chainLength = 1; //length can't be more than 4 
		double percent = 5; //percent commission will increase with each increment of the referrer
		
		purchase+=amount; //increment the purchase
		
		//pay commissions
		while((a.getReferrer() != null) && chainLength != 4)
		{
			a.getReferrer().addCommission((double) percent/100 * amount);
			chainLength++;
			temp-=(double) percent/100 * amount; //subtract commission from the sales
			a = a.getReferrer(); //move a forward
			percent += 5;
		}
		
		//All commission have been paid, add the remaining amount to the original companies sales
		raw.madeSales(temp);
		
		
	}
	public String getName()
	{
		return name;
	}
	//print information about a particular company
	public void print()
	{
		System.out.print(name+"\t");
		if(purchase!=0)
		{
			System.out.print("$"+purchase+"\t");
		}
		else
			System.out.print(" \t");
		if(getReferrer() != null)
			System.out.print(getReferrer().getName()+"\t");
		else
			System.out.print(" \t");
		if(salesAmount!=0)
			System.out.print("$"+salesAmount+"\t");
		else
			System.out.print(" \t");
		if(commission != 0)
			System.out.print("$"+commission+"\t");
		else
			System.out.print(" \t");
		System.out.println("$"+(salesAmount+commission));
	}
	
}
