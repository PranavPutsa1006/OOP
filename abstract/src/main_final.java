
abstract class Super
{
	final void fun()
	{
		System.out.println("Super fun() called");
	}
}
class sub extends Super
{
	
}
public class main_final 
{
	public static void main(String[] args) 
	{
		Super s=new sub();
		s.fun();
	}

}
