//NAME : ZAFAR, BLAL
//ID # 108462601
//HOME WORK # 4

import java.io.Serializable;
import java.util.Scanner;


public class CompanyTree implements Serializable {

	CompanyNode root;
	
	//CONSTRUCTOR
	public CompanyTree(String firstCompanyName)
	{
		insert(firstCompanyName);
	}
	public void insert(String newComp)
	{
		boolean done = false;
		CompanyNode newNode;
		CompanyNode cursor;
		
		if(root == null) //THE TREE IS EMPTY
		{
			newNode = new CompanyNode(generateFirstNode(newComp));
			root = newNode;
		}
		else //THE TREET IS NOT EMPTY
		{
			cursor = root;
			while(!done) //find a place to insert
			{
				//THE ELEMENT IS LESS THAN THE CURSOR
				if(newComp.compareTo(cursor.getData().getName()) < 0) 
				{
					if(cursor.getLeft() == null)
					{
						//create the new node
						Company input = createCompany(newComp);
						if(isValidCompany(input))
						{
							newNode = new CompanyNode(input);
							cursor.setLeft(newNode);
							done = true;
						}
						else
						{
							System.out.println("Invalid values entered, company not created ...");
							done = true;
						}
						
					}
					else
						cursor.getLeft();
				}
				
				//THE ELEMENT IS GREATER THAN THE CURSOR
				else if(newComp.compareTo(cursor.getData().getName()) > 0)
				{
					if(cursor.getRight() == null)
					{
						Company input = createCompany(newComp);
						if(isValidCompany(input))
						{
							newNode = new CompanyNode(input);
							cursor.setRight(newNode);
							done = true;
						}
						else
						{
							System.out.println("Invalid values entered, company not created ...");
							done = true;
						}
						
						
					}
					else
						cursor = cursor.getRight();
				}
				else
					done = true; //to break the while loop
				
			}
		}
	}
	//REMOVE A NODE FROM THE TREE
	public boolean remove(String item)
	{
		CompanyNode cursor = root;
		CompanyNode parentOfCursor = null;
		
		//move left or right to find the desired node
		while(cursor != null && !cursor.getData().getName().equals(item))
		{
			parentOfCursor = cursor;
			if(item.compareTo(cursor.getData().getName()) < 0)
				cursor = cursor.getLeft();
			else
				cursor = cursor.getRight();
		}
		
		if(cursor == null)
			return false;
		
		else
		{
			//removing a root with no left children
			if(cursor == root && cursor.getLeft() == null)
				root = root.getRight();
			
			//Not a root and doesn't have left children
			else if(cursor != root && cursor.getLeft() == null)
			{
				if(cursor == parentOfCursor.getLeft())
					parentOfCursor.setLeft(cursor.getRight());
				else
					parentOfCursor.setRight(cursor.getRight());
			}
			
			//For all remaining cases
			else
			{
				cursor.setData(cursor.getLeft().getRightmostData());
				cursor.setLeft(cursor.getLeft().removeRightmost());
			}
			
			return true;
			
		}
	}
	//PRINT THE INFORMATION ABOUT A COMPANY
	public void getInformation(String item)
	{
		CompanyNode cursor = root;
		CompanyNode parentOfCursor = null;
		
		//Move left or right to find the item
		while(cursor != null && !cursor.getData().getName().equals(item))
		{
			parentOfCursor = cursor;
			if(item.compareTo(cursor.getData().getName()) < 0)
				cursor = cursor.getLeft();
			else
				cursor = cursor.getRight();
		}
		
		if(cursor == null)
			System.out.println("There is no company that has the given name ...");
		else
			cursor.getData().print();
	}
	//POST CONDITION : check if a company exists in the tree 
	public boolean exists(String item)
	{
		CompanyNode cursor = root;
		CompanyNode parentOfCursor = null;
		
		while((cursor != null) && cursor.getData().getName().compareTo(item) != 0)
		{
			parentOfCursor = cursor;
			if(item.compareTo(cursor.getData().getName()) < 0)
				cursor = cursor.getLeft();
			else
				cursor = cursor.getRight();
		}
		
		if(cursor == null)
			return false;
		else
			return true;
	}
	//POSTCONDITION : returns the the company object with the given name
	public Company getCompany(String item)
	{
		CompanyNode cursor = root;
		CompanyNode parentOfCursor = null;
		
		while(cursor != null && cursor.getData().getName().compareTo(item) != 0)
		{
			parentOfCursor = cursor;
			if(item.compareTo(cursor.getData().getName()) < 0)
				cursor = cursor.getLeft();
			else
				cursor = cursor.getRight();
		}
		
		if(cursor  == null)
			return null;
		else
			return cursor.getData();
		
	}
	
	//Creates the first initial node
	public Company generateFirstNode(String name) 
	{
		return new Company(name, 0, null);
	}
	//Create a company based on the data
	public Company createCompany(String name)
	{
		double trans;
		String referrer;
		Company referrerObj;
		
		
		Scanner x = new Scanner(System.in);
		
		System.out.print("Referring Company : ");
		referrer = x.nextLine();
		referrerObj = getCompany(referrer); //return the referring company object
		System.out.print("Enter the price of the transaction : ");
		trans = x.nextDouble();
		
		
		return new Company(name, trans, referrerObj);
		
		
	}
	
	//check if the values entered are valid
	public boolean isValidCompany(Company a)
	{
		if((a.purchase <= 0) | (a.getReferrer() == null))
			return false;
		else 
			return true;
	}
	
	
}
