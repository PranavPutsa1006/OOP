
interface MyInterface
{
	public void method1();
	public void method2();
}
class demo implements MyInterface
{
	public void method1()
	{
		System.out.println("Method 1 implemented");
	}
	public void method2()
	{
		System.out.println("Method 2 implemented");
	}
	public static void main(String[] args) 
	{
		MyInterface obj=new demo();
		obj.method1();
		obj.method2();
	}

}
