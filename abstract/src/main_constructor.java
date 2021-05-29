
abstract class base1
{
	base1()
	{
		System.out.println("Base constructor called");
	}
	abstract void fun();
}
class derived1 extends base1
{
	derived1()
	{
		System.out.println("Derived constructor called");
	}
	void fun()
	{
		System.out.println("Derived fun() called");
	}
}
public class main_constructor 
{
	public static void main(String[] args) 
	{
		derived1 d=new derived1();
		d.fun();
	}
}
