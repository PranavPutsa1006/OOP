
abstract class base
{
	abstract void fun();
}
class derived extends base
{

	void fun() 
	{
		System.out.println("derived fun() called");
	}
	
}
public class main_impl 
{
	public static void main(String[] args) 
	{
		base b=new derived();
		b.fun();
	}

}
