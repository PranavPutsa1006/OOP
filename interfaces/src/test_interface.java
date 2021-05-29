//Interface declaration:user 1
interface drawable
{
	void draw();
}
//Implementation:user 2
class rectangle implements drawable
{
	public void draw() 
	{
		System.out.println("Draw rectangle");
	}
}
class circle implements drawable
{
	public void draw()
	{
		System.out.println("Draw circle");
	}
}
//Using interface:user 3
class test_interface
{
	public static void main(String args[])
	{
		drawable obj1=new rectangle();
		obj1.draw();
		drawable obj2=new circle();
		obj2.draw();
	}
}