abstract class parent
{
	void fun()
	{
		System.out.println("base fun() called");
	}
}
class child extends parent
{
	
}
public class non_abstract 
{
	public static void main(String[] args) 
	{
		child c=new child();
		c.fun();
	}

}
