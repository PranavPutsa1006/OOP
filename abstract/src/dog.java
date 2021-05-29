abstract class animal
{
	public abstract void sound();
}
public class dog extends animal
{
	public void sound()
	{
		System.out.println("Woof");
	}
	public static void main(String[] args) 
	{
		animal obj=new dog();
		obj.sound();
	}

}
