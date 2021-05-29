package letmecalculate;

public class calculator
{
	public int add(int a,int b)
	{
		return a+b;
	}
	public static void main(String[] args)
	{
		calculator obj=new calculator();
		System.out.println(obj.add(10, 20));
	}
}
