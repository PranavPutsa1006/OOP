interface inf1
{
	public void method1();
}
interface inf2 extends inf1
{
	public void method2();
}
class test implements inf2 
{
	public void method1()
	{
		System.out.println("method 1");
	}
	public void method2()
	{
		System.out.println("method 2");
	}
	public static void main(String[] args) 
	{
		inf2 obj=new test();
		obj.method1();
		obj.method2();
	}
}
