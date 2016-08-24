//NAME : ZAFAR, BLAL
//ID # 108462601
//HOME WORK # 4


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;


public class CompanyTracker implements Serializable {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Scanner x = new Scanner(System.in);
		
		//MAIN DATA
		CompanyTree data;
		
		//Search for the file
		java.io.File file = new java.io.File("tree.obj");
		if(file.exists()) //if the file exist read the tree from it else create a new one
		{
			FileInputStream file2 = new FileInputStream("tree.obj");
			ObjectInputStream inStream = new ObjectInputStream(file2);
			data = (CompanyTree) inStream.readObject();
		}
		else
		{
			System.out.print("Please enter the first company's name : ");
			String temp = x.nextLine();
			data = new CompanyTree(temp);
		}
			
		
		
		
		printMenu();
		char input = getInput();
		String tempInput;
		
		while(true)
		{
			switch(Character.toLowerCase(input))
			{
				case 'r' : 
					System.out.print("Enter the new Company name : ");
					tempInput = x.nextLine();
					data.insert(tempInput);
					if(data.exists(tempInput)) //confirm that company has been added
						System.out.println("The new Company has been added");
					
					else
						System.out.println("That company name is already in use ....");
					break;
					
				case 'p' :
					System.out.print("Enter the Company name : ");
					tempInput = x.nextLine();
					System.out.println("Comp\tPurc\tRef\tSales\tCmsn\tTotal");
					System.out.println("----\t----\t---\t-----\t----\t-----");
					data.getInformation(tempInput);
					break;
				case 'd' :
					System.out.println("Comp\tPurc\tRef\tSales\tCmsn\tTotal");
					System.out.println("----\t----\t---\t-----\t----\t-----");
					data.root.inOrderPrint();
					break;
				case 'q' :
					System.out.println("Ending .....");
					FileOutputStream file3 = new FileOutputStream("tree.obj");
					ObjectOutputStream outStream = new ObjectOutputStream(file3);
					outStream.writeObject(data);
					outStream.close();
					System.exit(0);
					break;
			}
			printMenu();
			input = getInput();
		}
		
	}
	
	public static void printMenu()
	{
		System.out.println("-------------------------------------------------");
		System.out.println("R\t(Report a Purchase)");
		System.out.println("P\t(Print information)");
		System.out.println("D\t(Display information about all companies)");
		System.out.println("Q\t(Quit the program)");
		System.out.println("-------------------------------------------------");
		
	}
	
	public static char getInput()
	{
		Scanner x = new Scanner(System.in);
		String out;
		System.out.print("Select a menu option : ");
		out = x.nextLine();
		
		return out.charAt(0);
	}

}
