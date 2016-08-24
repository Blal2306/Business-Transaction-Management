//NAME : ZAFAR, BLAL
//ID # 108462601
//HOME WORK # 4

import java.io.Serializable;
public class CompanyNode implements Serializable {

	Company data;
	CompanyNode left;
	CompanyNode right;
	
	public CompanyNode(Company d)
	{
		data = d;
	}
	public Company getData()
	{
		return data;
	}
	public void setData(Company d)
	{
		data = d;
	}
	public CompanyNode getLeft()
	{
		return left;
	}
	public void setLeft(CompanyNode l)
	{
		left = l;
	}
	public CompanyNode getRight()
	{
		return right;
	}
	public void setRight(CompanyNode r)
	{
		right = r;
	}
	public Company getRightmostData()
	{
		if(right == null)
			return data;
		else
			return right.getRightmostData();
	}
	public CompanyNode removeRightmost()
	{
		if(right == null)
			return left;
		else
		{
			right = right.removeRightmost();
			return this;
		}
	}
	
	//PRINT THE TREE IN ORDER
	public void inOrderPrint()
	{
		
		if(left != null)
			left.inOrderPrint();
		data.print();
		if(right != null)
			right.inOrderPrint();
			
	}
}
